package training.fpt.nhutlv.lvnstore.model.storage;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import training.fpt.nhutlv.lvnstore.entities.AppInfo;
import training.fpt.nhutlv.lvnstore.utils.Callback;

/**
 * Created by NhutDu on 31/12/2016.
 */

public class AppInfoController {

    static Realm realm;

    public AppInfoController() {
        realm = Realm.getDefaultInstance();
    }

    public static Realm newInstance(){
        realm = Realm.getDefaultInstance();
        return realm;
    }

    public static void addAppInfo(AppInfo appInfo){
        realm.beginTransaction();
        realm.copyFromRealm(appInfo);
        realm.commitTransaction();
    }

    public static void deleteAppInfo(AppInfo appInfo){
        RealmResults<AppInfo> results = AppInfoController.newInstance().where(AppInfo.class).equalTo("package_name",appInfo.getPackage_name()).findAll();
        realm.beginTransaction();
        results.deleteAllFromRealm();
        realm.commitTransaction();
    }

    public static int getNumberFavourite(){
        RealmResults<AppInfo> results = realm.where(AppInfo.class).findAll();
        return results.size();
    }

    public static void getAllAsync(final Callback<RealmResults<AppInfo>> callback) {
        final Realm realm = Realm.getDefaultInstance();

        final RealmResults<AppInfo> categories = realm.where(AppInfo.class).findAllAsync();
        categories.addChangeListener(new RealmChangeListener<RealmResults<AppInfo>>() {
            @Override
            public void onChange(RealmResults<AppInfo> element) {
                callback.onResult(element);
                categories.removeChangeListener(this);
            }
        });
    }

    public static ArrayList<AppInfo> getAll() {
        final RealmResults<AppInfo> realmResults = AppInfoController.newInstance().where(AppInfo.class).findAll();
        ArrayList<AppInfo> results = new ArrayList<>();
        results.addAll(realmResults);
        return results;
    }
}
