package training.fpt.nhutlv.lvnstore.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

import training.fpt.nhutlv.lvnstore.R;
import training.fpt.nhutlv.lvnstore.entities.AppInfo;

/**
 * Created by HCD-Fresher039 on 12/27/2016.
 */

public class GridAppAdapter extends RecyclerView.Adapter<GridAppAdapter.ListAppViewHolder>{

    private Context mContext;
    private ArrayList<AppInfo> mApps;

    public GridAppAdapter(Context mContext, ArrayList<AppInfo> mApps) {
        this.mContext = mContext;
        this.mApps = mApps;
    }

    @Override
    public ListAppViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid_app,parent,false);
        return new ListAppViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListAppViewHolder holder, int position) {

        AppInfo app = mApps.get(position);
        holder.title.setText(app.getTitle());

    }

    @Override
    public int getItemCount() {
        return mApps.size();
    }

    class ListAppViewHolder extends RecyclerView.ViewHolder{

        ImageView icon;
        TextView title;

        public ListAppViewHolder(View itemView) {
            super(itemView);
            icon = (ImageView) itemView.findViewById(R.id.grid_image);
            title = (TextView) itemView.findViewById(R.id.grid_title);
        }
    }
}
