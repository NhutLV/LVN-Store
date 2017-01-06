package training.fpt.nhutlv.lvnstore.model.service;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import training.fpt.nhutlv.lvnstore.entities.Movies;
import training.fpt.nhutlv.lvnstore.model.Configuration;
import training.fpt.nhutlv.lvnstore.model.response.APIMovies;
import training.fpt.nhutlv.lvnstore.utils.Constants;


/**
 * Created by NhutDu on 19/12/2016.
 */

public class MoviesServiceImpl {
    private static final String TAG = MoviesServiceImpl.class.getSimpleName();

    //region Properties

    private Context mContext;

    //endregion

    //region Constructor

    public MoviesServiceImpl(Context mContext) {
        this.mContext = mContext;
    }

    public MoviesServiceImpl() {
    }

    //endregion

    public void getPopularMovies(final int page, final training.fpt.nhutlv.lvnstore.utils.Callback<ArrayList<Movies>> callback){
        MoviesService service = Configuration.getClient().create(MoviesService.class);
        Call<APIMovies> call = service.getPopularMovies(Constants.API_KEY,page);
        call.enqueue(new Callback<APIMovies>() {
            @Override
            public void onResponse(Call<APIMovies> call, Response<APIMovies> response) {
                ArrayList<Movies> movies = new ArrayList<Movies>();
                APIMovies apiMovies = response.body();
                if(response.isSuccessful() && apiMovies.getListMovies()!=null){
                    if(page==1){
                        movies = response.body().getListMovies();
                        callback.onResult(movies);
                    }else if (page > 1){
                        movies.addAll(response.body().getListMovies());
                        callback.onResult(movies);
                    }
                }else{
                    Log.d(TAG, "onResponse: Failed load popular movies");
                }
            }

            @Override
            public void onFailure(Call<APIMovies> call, Throwable t) {
                Log.d(TAG, "onFailure: ",t);
            }
        });
    }

}
