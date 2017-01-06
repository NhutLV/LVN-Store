package training.fpt.nhutlv.lvnstore.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import training.fpt.nhutlv.lvnstore.R;
import training.fpt.nhutlv.lvnstore.entities.Movies;
import training.fpt.nhutlv.lvnstore.entities.TypeImage;
import training.fpt.nhutlv.lvnstore.model.service.TypeImageServiceImpl;


/**
 * Created by NhutDu on 21/12/2016.
 */

public class RecyclerViewMoviesBoxAdapter extends RecyclerView.Adapter<RecyclerViewMoviesBoxAdapter.MoviesViewHolder> {

    //region Properties

    Context mContext;
    ArrayList<Movies> mListMovies;
    TypeImage mTypeImage;

    //endregion

    //region Constructors

    public RecyclerViewMoviesBoxAdapter(Context mContext, ArrayList<Movies> mListMovies) {
        this.mContext = mContext;
        this.mListMovies = mListMovies;
    }

    //endregion


    @Override
    public MoviesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fragment_movies_box, parent, false);
        return new MoviesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MoviesViewHolder holder, int position) {
        final Movies movies = mListMovies.get(position);
        TypeImageServiceImpl service = new TypeImageServiceImpl();

        Picasso.with(mContext).load("http://image.tmdb.org/t/p/" + "w185" + movies.getPosterPath()).placeholder(R.drawable.no_image).into(holder.imgPoster);
        holder.txtTitle.setText(movies.getTitle());

    }

    @Override
    public int getItemCount() {
        return mListMovies.size();
    }

    class MoviesViewHolder extends RecyclerView.ViewHolder {

        ImageView imgPoster;
        TextView txtTitle;

        public MoviesViewHolder(View itemView) {
            super(itemView);
            imgPoster = (ImageView) itemView.findViewById(R.id.image_poster_movies_box);
            txtTitle = (TextView) itemView.findViewById(R.id.title_movies_box);
        }
    }
}
