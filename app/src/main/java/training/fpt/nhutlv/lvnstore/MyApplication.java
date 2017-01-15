package training.fpt.nhutlv.lvnstore;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import training.fpt.nhutlv.lvnstore.utils.NetworkChangeReceiver;

/**
 * Created by NhutDu on 31/12/2016.
 */

public class MyApplication extends Application {

    private static MyApplication mInstance;

    public static synchronized MyApplication getInstance() {
        return mInstance;
    }

    public void setConnectivityListener(NetworkChangeReceiver.ConnectivityReceiverListener listener) {
        NetworkChangeReceiver.connectivityReceiverListener = listener;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name(Realm.DEFAULT_REALM_NAME)
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);

        mInstance = this;
    }
}
