package training.fpt.nhutlv.lvnstore.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import io.realm.Realm;
import io.realm.RealmResults;
import training.fpt.nhutlv.lvnstore.R;
import training.fpt.nhutlv.lvnstore.entities.AppInfo;
import training.fpt.nhutlv.lvnstore.event.NumberFavourite;
import training.fpt.nhutlv.lvnstore.event.RemovePositionEvent;

/**
 * Created by HCD-Fresher039 on 12/27/2016.
 */

public class ListAppAdapter extends RecyclerView.Adapter<ListAppAdapter.ListAppViewHolder>{

    private Context mContext;
    private ArrayList<AppInfo> mApps;
    Realm realm = Realm.getDefaultInstance();
    AppInfo app;
    int position;

    public ListAppAdapter(Context mContext, ArrayList<AppInfo> mApps) {
        this.mContext = mContext;
        this.mApps = mApps;
    }

    @Override
    public ListAppViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_app,parent,false);
        return new ListAppViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ListAppViewHolder holder, final int position) {

        app = mApps.get(position);
        this.position = position;
        holder.title.setText(app.getTitle());
        holder.category.setText(app.getCategory());
        holder.shortDesc.setText(app.getShort_desc());
        holder.numberRating.setText("("+String.valueOf(app.getNumber_rating())+")");
        holder.rating.setRating(app.getRating());
        Picasso.with(mContext).load(app.getIcon()).placeholder(R.drawable.image).into(holder.icon);
        LayerDrawable stars = (LayerDrawable) holder.rating.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);

        holder.rating.setIsIndicator(false);

        holder.favourite.setChecked(app.isFavourite());

    }

    @Override
    public int getItemCount() {
        return mApps.size();
    }

    class ListAppViewHolder extends RecyclerView.ViewHolder{

        ImageView icon;
        TextView title;
        TextView category;
        TextView shortDesc;
        TextView numberRating;
        RatingBar rating;
        CheckBox favourite;
        LinearLayout layoutDetail;

        public ListAppViewHolder(View itemView) {
            super(itemView);
            icon = (ImageView) itemView.findViewById(R.id.icon);
            title = (TextView) itemView.findViewById(R.id.title);
            category = (TextView) itemView.findViewById(R.id.category);
            shortDesc = (TextView) itemView.findViewById(R.id.short_desc);
            numberRating = (TextView) itemView.findViewById(R.id.number_rating);
            rating = (RatingBar) itemView.findViewById(R.id.rating);
            favourite = (CheckBox) itemView.findViewById(R.id.favorite);
            layoutDetail = (LinearLayout) itemView.findViewById(R.id.layout_detail);

            layoutDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mMyClickDetailLister.onCLickItem(getAdapterPosition());
                }
            });

            favourite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(favourite.isChecked()){
                        app.setFavourite(true);
                        app.setSize(new Random().nextInt(1000));
                        realm.beginTransaction();
                        realm.copyToRealm(app);
                        realm.commitTransaction();
                        RealmResults<AppInfo> results = realm.where(AppInfo.class).findAll();
                        EventBus.getDefault().postSticky(new RemovePositionEvent(getAdapterPosition(),true,app));
                        EventBus.getDefault().postSticky(new NumberFavourite(results.size()));


                    }else{
                        realm.beginTransaction();
                        app.setFavourite(false);
                        RealmResults<AppInfo> results = realm.where(AppInfo.class).equalTo("size",app.getSize()).findAll();
                        results.deleteAllFromRealm();
                        realm.commitTransaction();
                        RealmResults<AppInfo> results1 = realm.where(AppInfo.class).findAll();
                        EventBus.getDefault().postSticky(new NumberFavourite(results1.size()));
                        EventBus.getDefault().postSticky(new RemovePositionEvent(getAdapterPosition(),false,app));
                    }
                }
            });
        }
    }

    public void setFilter(List<AppInfo> countryModels) {
        mApps = new ArrayList<>();
        mApps.addAll(countryModels);
        notifyDataSetChanged();
    }


    MyClickDetailLister mMyClickDetailLister;

    public void setMyClickDetailLister(MyClickDetailLister myClickDetailLister) {
        mMyClickDetailLister = myClickDetailLister;
    }

    public interface MyClickDetailLister{

        void onCLickItem(int position);
    }
}

