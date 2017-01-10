package training.fpt.nhutlv.lvnstore.utils;

import android.content.Context;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;
import training.fpt.nhutlv.lvnstore.entities.AppInfo;
import training.fpt.nhutlv.lvnstore.event.RemovePositionEvent;
import training.fpt.nhutlv.lvnstore.model.Configuration;
import training.fpt.nhutlv.lvnstore.model.service.AppInfoServiceImpl;

/**
 * Created by NhutDu on 30/12/2016.
 */

public class DataDemo {

    static Context mContext;
    static ArrayList<AppInfo> mApps = new ArrayList<>();
    static Realm realm = Realm.getDefaultInstance();
    public DataDemo(){
    }

    public DataDemo(Context context) {
        mContext = context;
    }

    public ArrayList<AppInfo> checkFavourite(ArrayList<AppInfo> apps){

        ArrayList<AppInfo> temp = new ArrayList<>();
        RealmResults<AppInfo> results = realm.where(AppInfo.class).findAll();
        temp.addAll(results);

        for(AppInfo appInfo:apps){
            for (AppInfo app: temp){
                if(appInfo.getPackage_name().equals(app.getPackage_name())){
                    appInfo.setFavourite(true);
                }
            }
        }

        return apps;
    }


    public static ArrayList<AppInfo> getData(){
        mApps = new ArrayList<>();

        ArrayList<AppInfo> temp = new ArrayList<>();
        RealmResults<AppInfo> results = realm.where(AppInfo.class).findAll();
        temp.addAll(results);

        mApps.add(new AppInfo("com.nhut.a","Free","Social","",4.5f,120,"hello i love you"));
        mApps.add(new AppInfo("com.nhut.b","Message","Sport","",2.5f,120,"hello i love you"));
        mApps.add(new AppInfo("com.nhut.c","ZingMP3","Hello","",5f,120,"hello i love you"));
        mApps.add(new AppInfo("com.nhut.d","Ahihi","Social","",4f,120,"Hello, welcome to facebook, welcome to facebook, welcome to facebook,welcome to facebook, welcome to facebook, welcome to facebook"));
        mApps.add(new AppInfo("com.nhut.e","Facebook","Social","",4.5f,120,"Hello, welcome to facebook, welcome to facebook, welcome to facebook,welcome to facebook, welcome to facebook, welcome to facebook"));
        mApps.add(new AppInfo("com.nhut.f","Facebook","Social","",4.5f,120,"hello i love you"));
        mApps.add(new AppInfo("com.nhut.g","Facebook","Social","",1.5f,120,"hello i love you"));
        mApps.add(new AppInfo("com.nhut.h","Facebook","Social","",2.5f,120,"Hello, welcome to facebook, welcome to facebook, welcome to facebook,welcome to facebook, welcome to facebook, welcome to facebook"));

        for(AppInfo app:mApps){
            if(app.getTitle().equals("Free")){
                app.setFavourite(true);
            }
        }

        return mApps;
    }

    public static ArrayList<AppInfo> getDataFree(){

        ArrayList<AppInfo> temp = new ArrayList<>();
        RealmResults<AppInfo> results = realm.where(AppInfo.class).findAll();
        temp.addAll(results);

        final ArrayList<AppInfo> apps = new ArrayList<>();
        AppInfoServiceImpl service = new AppInfoServiceImpl(mContext);
        service.getListByCategoryName(Configuration.TOP_FREE, 1, new Callback<ArrayList<AppInfo>>() {
            @Override
            public void onResult(ArrayList<AppInfo> appInfos) {
                apps.addAll(appInfos);
            }
        });
//        for(AppInfo app:mApps){
//            if(app.getTitle().equals("Free")){
//                app.setFavourite(true);
//            }
//        }

        return mApps;
    }

    public static  ArrayList<AppInfo> getData1(){
        ArrayList<AppInfo> temp = new ArrayList<>();
        temp.add(new AppInfo("com.nhut.ad","Paid","Social","",4.5f,120,"hello i love you"));
        mApps.add(new AppInfo("com.nhut.ba","Message","Sport","",2.5f,120,"hello i love you"));
        mApps.add(new AppInfo("com.nhut.ca","ZingMP3","Hello","",5f,120,"hello i love you"));
        mApps.add(new AppInfo("com.nhut.da","Ahihi","Social","",4f,120,"Hello, welcome to facebook, welcome to facebook, welcome to facebook,welcome to facebook, welcome to facebook, welcome to facebook"));
        mApps.add(new AppInfo("com.nhut.ea","Facebook","Social","",4.5f,120,"Hello, welcome to facebook, welcome to facebook, welcome to facebook,welcome to facebook, welcome to facebook, welcome to facebook"));
        mApps.add(new AppInfo("com.nhut.fa","Facebook","Social","",4.5f,120,"hello i love you"));
        mApps.add(new AppInfo("com.nhut.ga","Facebook","Social","",1.5f,120,"hello i love you"));
        mApps.add(new AppInfo("com.nhut.ha","Facebook","Social","",2.5f,120,"Hello, welcome to facebook, welcome to facebook, welcome to facebook,welcome to facebook, welcome to facebook, welcome to facebook"));


        return temp;
    }

    public static ArrayList<AppInfo> getData2(){
        ArrayList<AppInfo> temp = new ArrayList<>();
        temp.add(new AppInfo("com.nhut.ccc","Movers Shaker","Social","",4.5f,120,"hello i love you"));
        mApps.add(new AppInfo("com.nhut.bd","Message","Sport","",2.5f,120,"hello i love you"));
        mApps.add(new AppInfo("com.nhut.cd","ZingMP3","Hello","",5f,120,"hello i love you"));
        mApps.add(new AppInfo("com.nhut.dd","Ahihi","Social","",4f,120,"Hello, welcome to facebook, welcome to facebook, welcome to facebook,welcome to facebook, welcome to facebook, welcome to facebook"));
        mApps.add(new AppInfo("com.nhut.ed","Facebook","Social","",4.5f,120,"Hello, welcome to facebook, welcome to facebook, welcome to facebook,welcome to facebook, welcome to facebook, welcome to facebook"));
        mApps.add(new AppInfo("com.nhut.fd","Facebook","Social","",4.5f,120,"hello i love you"));
        mApps.add(new AppInfo("com.nhut.gd","Facebook","Social","",1.5f,120,"hello i love you"));
        mApps.add(new AppInfo("com.nhut.hd","Facebook","Social","",2.5f,120,"Hello, welcome to facebook, welcome to facebook, welcome to facebook,welcome to facebook, welcome to facebook, welcome to facebook"));


        return temp;
    }

    public static ArrayList<AppInfo> getData3(){
        ArrayList<AppInfo> temp = new ArrayList<>();
        temp.add(new AppInfo("com.nhut.accc","Grosssing","Social","",4.5f,120,"hello i love you"));
        mApps.add(new AppInfo("com.nhut.bh","Message","Sport","",2.5f,120,"hello i love you"));
        mApps.add(new AppInfo("com.nhut.hc","ZingMP3","Hello","",5f,120,"hello i love you"));
        mApps.add(new AppInfo("com.nhut.dh","Ahihi","Social","",4f,120,"Hello, welcome to facebook, welcome to facebook, welcome to facebook,welcome to facebook, welcome to facebook, welcome to facebook"));
        mApps.add(new AppInfo("com.nhut.eh","Facebook","Social","",4.5f,120,"Hello, welcome to facebook, welcome to facebook, welcome to facebook,welcome to facebook, welcome to facebook, welcome to facebook"));
        mApps.add(new AppInfo("com.nhut.fh","Facebook","Social","",4.5f,120,"hello i love you"));
        mApps.add(new AppInfo("com.nhut.gh","Facebook","Social","",1.5f,120,"hello i love you"));
        mApps.add(new AppInfo("com.nhut.hh","Facebook","Social","",2.5f,120,"Hello, welcome to facebook, welcome to facebook, welcome to facebook,welcome to facebook, welcome to facebook, welcome to facebook"));


        return temp;
    }


}
