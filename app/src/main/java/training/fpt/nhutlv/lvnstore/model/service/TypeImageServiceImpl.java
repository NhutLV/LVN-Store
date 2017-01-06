package training.fpt.nhutlv.lvnstore.model.service;

import android.content.Context;
import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import training.fpt.nhutlv.lvnstore.entities.TypeImage;
import training.fpt.nhutlv.lvnstore.model.Configuration;
import training.fpt.nhutlv.lvnstore.model.response.APITypeImage;
import training.fpt.nhutlv.lvnstore.utils.Constants;


/**
 * Created by NhutDu on 19/12/2016.
 */

public class TypeImageServiceImpl {
    private static final String TAG = TypeImageServiceImpl.class.getSimpleName();

    //region Properties

    private Context mContext;

    //endregion

    //region Constructor

    public TypeImageServiceImpl(Context mContext) {
        this.mContext = mContext;
    }

    public TypeImageServiceImpl() {
    }

    //endregion

    public void getImageURL(final training.fpt.nhutlv.lvnstore.utils.Callback<TypeImage> callback){

        TypeImageService service = Configuration.getClient().create(TypeImageService.class);

        Call<APITypeImage> call = service.getImageURL(Constants.API_KEY);

        call.enqueue(new Callback<APITypeImage>() {
            @Override
            public void onResponse(Call<APITypeImage> call, Response<APITypeImage> response) {
               if(response.isSuccessful() && response.body().getTypeImage()!=null){
//                   Toast.makeText(mContext,"URL base "+response.body().getTypeImage().getBaseURL(),Toast.LENGTH_LONG).show();
                   Log.d(TAG, "TypeImage: "+response.body().getTypeImage().getBaseURL());
                   Log.d(TAG, "TypeImage: "+response.body().getTypeImage().getProfileSizes()[0]);
                   callback.onResult(response.body().getTypeImage());
               }else{
                   Log.d(TAG, "TypeImage: "+" Get Failed Type Image");
               }
            }

            @Override
            public void onFailure(Call<APITypeImage> call, Throwable t) {
                Log.d(TAG, "TypeImage: ",t);
            }
        });
    }
}
