package training.fpt.nhutlv.lvnstore.utils;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by NhutDu on 30/12/2016.
 */

public class UtilsFragment {

    Context mContext;

    public UtilsFragment(Context mContext) {
        this.mContext = mContext;
    }

    public static void changeFragment(FragmentManager fragmentManager,Fragment fragment, int idLayout){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(idLayout,fragment);
        transaction.commit();
    }

    public static void addFragment(FragmentManager fragmentManager,Fragment fragment, int idLayout){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(idLayout,fragment);
        transaction.commit();
    }
}
