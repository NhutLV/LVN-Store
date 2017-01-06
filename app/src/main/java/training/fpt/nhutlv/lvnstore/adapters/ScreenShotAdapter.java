package training.fpt.nhutlv.lvnstore.adapters;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import training.fpt.nhutlv.lvnstore.R;

/**
 * Created by NhutDu on 29/12/2016.
 */

public class ScreenShotAdapter extends RecyclerView.Adapter<ScreenShotAdapter.ScreenShotViewHolder>{

    Context mContext;
    int [] images;

    public ScreenShotAdapter(Context mContext, int [] images) {
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
        holder.imageView.setImageDrawable(mContext.getResources().getDrawable(images[position]));
    }

    @Override
    public int getItemCount() {
        return images.length;
    }

    class ScreenShotViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;

        public ScreenShotViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.image_screenshot);
        }
    }
}
