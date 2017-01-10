package training.fpt.nhutlv.lvnstore.model.service;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import training.fpt.nhutlv.lvnstore.entities.AppInfo;
import training.fpt.nhutlv.lvnstore.model.Configuration;
import training.fpt.nhutlv.lvnstore.model.response.APIAppInfo;
import training.fpt.nhutlv.lvnstore.utils.Constant;


/**
 * Created by NhutDu on 19/12/2016.
 */

public class AppInfoServiceImpl {
    private static final String TAG = AppInfoServiceImpl.class.getSimpleName();

    //region Properties

    private Context mContext;

    //endregion

    //region Constructor

    public AppInfoServiceImpl(Context mContext) {
        this.mContext = mContext;
    }

    public AppInfoServiceImpl() {
    }

    //endregion

    public void getListByCategoryName(String categoryName,final int page, final training.fpt.nhutlv.lvnstore.utils.Callback<ArrayList<AppInfo>> callback){
        AppInfoService service = Configuration.getClient().create(AppInfoService.class);
        Call<APIAppInfo> call = service.getListByName(categoryName,Constant.ACCESS_TOKEN,page);
        call.enqueue(new Callback<APIAppInfo>() {
            @Override
            public void onResponse(Call<APIAppInfo> call, Response<APIAppInfo> response) {
                ArrayList<AppInfo> apps = new ArrayList<AppInfo>();
                APIAppInfo apiMovies = response.body();
                if(response.isSuccessful() && apiMovies.getListApp()!=null){
                    Log.d("TAG AppInfo","OK ");
                    if(page==1){
                        apps = response.body().getListApp();
                        callback.onResult(apps);
                    }else if (page > 1){
                        apps.addAll(response.body().getListApp());
                        callback.onResult(apps);
                    }
                }else{
                    Log.d("TAG AppInfo ","Failed");
                }
            }

            @Override
            public void onFailure(Call<APIAppInfo> call, Throwable t) {
                Log.d("TAG AppInfo ","Failed",t);
            }
        });
    }

    public void getAppInfoByPackageName(String packageName, final training.fpt.nhutlv.lvnstore.utils.Callback<AppInfo> callback){

        AppInfoService service= Configuration.getClient().create(AppInfoService.class);
        Call<AppInfo> call = service.getAppInfoByPackageName(packageName, Constant.ACCESS_TOKEN);
        call.enqueue(new Callback<AppInfo>() {
            @Override
            public void onResponse(Call<AppInfo> call, Response<AppInfo> response) {
                if(response.isSuccessful()){
                    Log.d(TAG, "onResponse: OK");
                    callback.onResult(response.body());
                }else{
                    Log.d(TAG, "onResponse: Failed");
                }
            }

            @Override
            public void onFailure(Call<AppInfo> call, Throwable t) {
                Log.d(TAG, "onFailure: ",t);
            }
        });

    }

}
