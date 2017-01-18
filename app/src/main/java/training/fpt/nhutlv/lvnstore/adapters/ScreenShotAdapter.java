package training.fpt.nhutlv.lvnstore.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import io.realm.RealmList;
import training.fpt.nhutlv.lvnstore.R;
import training.fpt.nhutlv.lvnstore.entities.RealmArrayByte;
import training.fpt.nhutlv.lvnstore.entities.RealmString;

/**
 * Created by NhutDu on 29/12/2016.
 */

public class ScreenShotAdapter extends RecyclerView.Adapter<ScreenShotAdapter.ScreenShotViewHolder>{

    Context mContext;
    RealmList<RealmString> images;

    public ScreenShotAdapter(Context mContext,RealmList<RealmString> images) {
        this.mContext = mContext;
        this.images = images;
    }

    @Override
    public ScreenShotViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_screenshot,parent,false);
        return new ScreenShotViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ScreenShotViewHolder holder, int position) {

        Picasso.with(mContext).load(images.get(position).getValue()).placeholder(R.mipmap.ic_holder).into(holder.imageView);

//        Bitmap bmp = BitmapFactory.decodeByteArray(images.get(position).getByteImage(), 0, images.get(position).getByteImage().length);
//        holder.imageView.setImageBitmap(Bitmap.createScaledBitmap(bmp, 100,
//                100, false));
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    class ScreenShotViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;

        public ScreenShotViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.image_screenshot);
        }
    }
}
