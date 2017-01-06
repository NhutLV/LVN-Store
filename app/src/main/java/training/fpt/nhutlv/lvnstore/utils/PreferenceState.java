package training.fpt.nhutlv.lvnstore.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by HCD-Fresher039 on 12/29/2016.
 */

public class PreferenceState {

    private Context mContext;

    public static final String PREF_STATE_FRAGMENT ="STATE_FRAGMENT";
    public static final String PREF_STATE_SHOW ="STATE_SHOW";
    public static final String MY_SHARE ="My_SharePreference";
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    public PreferenceState(Context context){
        mContext = context;
        mSharedPreferences = mContext.getSharedPreferences(MY_SHARE,Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
    }

    public void saveStateFragment(int id){
        mEditor.putInt(PREF_STATE_FRAGMENT,id);
        mEditor.commit();
    }

    public void saveStateShow(int state){
        mEditor.putInt(PREF_STATE_SHOW,state);
        mEditor.commit();
    }

    public int getStateFragment(){
        int id = mSharedPreferences.getInt(PREF_STATE_FRAGMENT,0);
        return id;
    }

    public int getStateShow(){
        int show = mSharedPreferences.getInt(PREF_STATE_SHOW,0);
        return show;
    }

}
