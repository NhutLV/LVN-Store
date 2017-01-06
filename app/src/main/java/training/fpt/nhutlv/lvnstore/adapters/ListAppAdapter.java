package training.fpt.nhutlv.lvnstore.adapters;

import android.content.Context;
import android.content.Intent;
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

import java.util.ArrayList;

import training.fpt.nhutlv.lvnstore.DetailAppActivity;
import training.fpt.nhutlv.lvnstore.R;
import training.fpt.nhutlv.lvnstore.entities.AppInfo;

/**
 * Created by HCD-Fresher039 on 12/27/2016.
 */

public class ListAppAdapter extends RecyclerView.Adapter<ListAppAdapter.ListAppViewHolder>{

    private Context mContext;
    private ArrayList<AppInfo> mApps;

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
    public void onBindViewHolder(ListAppViewHolder holder, int position) {

        AppInfo app = mApps.get(position);
        holder.title.setText(app.getTitle());
        holder.category.setText(app.getCategory());
        holder.shortDesc.setText(app.getShort_desc());
        holder.numberRating.setText("("+String.valueOf(app.getNumber_rating())+")");
        holder.rating.setRating(app.getRating());

        LayerDrawable stars = (LayerDrawable) holder.rating.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);

        holder.layoutDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                mContext.startActivity(new Intent(mContext,DetailAppActivity.class));
            }
        });

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
        }
    }
}
