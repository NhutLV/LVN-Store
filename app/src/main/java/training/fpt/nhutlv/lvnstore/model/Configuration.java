package training.fpt.nhutlv.lvnstore.model;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.io.InputStreamReader;

import io.realm.RealmList;
import io.realm.RealmObject;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import training.fpt.nhutlv.lvnstore.entities.RealmString;

/**
 * Created by NhutDu on 18/12/2016.
 */

public class Configuration {


    private static Retrofit retrofit = null;

    public static final String BASE_URL = "https://data.42matters.com/api/v2.0/android/apps/";
    public static final String TOP_FREE = "topselling_free";
    public static final String TOP_PAID = "topselling_paid";
    public static final String MOVERS_SHAKER = "movers_shakers";
    public static final String TOP_GROSSING = "topgrossing";


    public static Retrofit getClient() {

        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }


//    private static Gson createGson() {
//        return new GsonBuilder().setLenient()
//                .setDateFormat("yyyy-MM-dd")
//                .create();
//    }

    static Gson gson = new GsonBuilder()

            .setExclusionStrategies(new ExclusionStrategy() {

                @Override

                public boolean shouldSkipField(FieldAttributes f) {

                    return f.getDeclaringClass().equals(RealmObject.class);

                }

                @Override

                public boolean shouldSkipClass(Class<?> clazz) {

                    return false;

                }

            })

            .registerTypeAdapter(new TypeToken<RealmList<RealmString>>() {}.getType(), new TypeAdapter<RealmList<RealmString>>() {

                @Override

                public void write(JsonWriter out, RealmList<RealmString> value) throws IOException {

                }

                @Override

                public RealmList<RealmString> read(JsonReader in) throws IOException {

                    RealmList<RealmString> list = new RealmList<RealmString>();

                    in.beginArray();

                    while (in.hasNext()) {

                        list.add(new RealmString(in.nextString()));

                    }

                    in.endArray();

                    return list;

                }

            })

            .setLenient()
            .setDateFormat("yyyy-MM-dd")
            .create();

}