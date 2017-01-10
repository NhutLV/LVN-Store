package training.fpt.nhutlv.lvnstore.model.storage;

import io.realm.Realm;
import io.realm.RealmResults;
import training.fpt.nhutlv.lvnstore.entities.AppInfo;

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
        RealmResults<AppInfo> results = realm.where(AppInfo.class).equalTo("size",appInfo.getSize()).findAll();
        realm.beginTransaction();
        results.deleteAllFromRealm();
        realm.commitTransaction();
    }

    public static int getNumberFavourite(){
        RealmResults<AppInfo> results = realm.where(AppInfo.class).findAll();
        return results.size();
    }
}
