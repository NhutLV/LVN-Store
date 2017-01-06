package training.fpt.nhutlv.lvnstore.model.service;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import training.fpt.nhutlv.lvnstore.model.response.APIMovies;

/**
 * Created by NhutDu on 20/12/2016.
 */

public interface MoviesService {

    @GET("movie/popular")
    Call<APIMovies> getPopularMovies(@Query("api_key") String api_key, @Query("page") int page);

}
