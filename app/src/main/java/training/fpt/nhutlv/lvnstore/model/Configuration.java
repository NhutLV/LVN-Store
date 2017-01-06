package training.fpt.nhutlv.lvnstore.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by NhutDu on 18/12/2016.
 */

public class Configuration {


    private static Retrofit retrofit = null;

    private static final String URL_BASE ="https://api.themoviedb.org/3/";
    private static final String URL_MOVIE_POPULAR ="https://api.themoviedb.org/3/movie/";
    private static final String URL_MOVIE_TOP_RATED ="https://api.themoviedb.org/3/movie/";
    private static final String URL_MOVIE_UPCOMING ="https://api.themoviedb.org/3/movie/popular?api_key=6cb1ab70342ce4b11dc26b59fdf7e442&language=en";
    private static final String URL_MOVIE_NOW_PLAYING ="https://api.themoviedb.org/3/movie/popular?api_key=6cb1ab70342ce4b11dc26b59fdf7e442&language=en";


    private static final String URL_TV_POPULAR ="https://api.themoviedb.org/3/tv/popular?api_key=6cb1ab70342ce4b11dc26b59fdf7e442&language=en";
    private static final String URL_TV_TOP_RATED ="https://api.themoviedb.org/3/tv/popular?api_key=6cb1ab70342ce4b11dc26b59fdf7e442&language=en";
    private static final String URL_TV_UPCOMING ="https://api.themoviedb.org/3/tv/popular?api_key=6cb1ab70342ce4b11dc26b59fdf7e442&language=en";
    private static final String URL_TV_NOW_PLAYING ="https://api.themoviedb.org/3/tv/popular?api_key=6cb1ab70342ce4b11dc26b59fdf7e442&language=en";


    public static Retrofit getClient() {

        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(URL_BASE)
                    .addConverterFactory(GsonConverterFactory.create(createGson()))
                    .build();
        }
        return retrofit;
    }


    private static Gson createGson() {
        return new GsonBuilder().setLenient()
                .setDateFormat("yyyy-MM-dd")
                .create();
    }

}
