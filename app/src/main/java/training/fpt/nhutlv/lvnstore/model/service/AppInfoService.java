package training.fpt.nhutlv.lvnstore.model.service;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import training.fpt.nhutlv.lvnstore.entities.AppInfo;
import training.fpt.nhutlv.lvnstore.model.response.APIAppInfo;

/**
 * Created by NhutDu on 05/01/2017.
 */

public interface AppInfoService {

    @GET("top_google_charts.json?limit=20")
    Call<APIAppInfo> getListByName(@Query("list_name") String categoryName,@Query("access_token") String accessToken,@Query("page") int page);


    @GET("lookup.json?")
    Call<AppInfo> getAppInfoByPackageName(@Query("p") String packageName, @Query("access_token") String accessToken);
}
