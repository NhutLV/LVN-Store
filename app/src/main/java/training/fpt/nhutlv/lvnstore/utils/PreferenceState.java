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
    public static final String PREF_RATING ="RATING";
    public static final String PREF_YEAR ="YEAR";
    public static final String PREF_LANGUAGE ="LANGUAGE";
    public static final String PREF_SORT ="SORT_BY";
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

    public int getStateFragment(){
        int id = mSharedPreferences.getInt(PREF_STATE_FRAGMENT,0);
        return id;
    }

    public void saveStateShow(int state){
        mEditor.putInt(PREF_STATE_SHOW,state);
        mEditor.commit();
    }

    public int getStateShow(){
        int show = mSharedPreferences.getInt(PREF_STATE_SHOW,0);
        return show;
    }

    public void saveRating(int rating){
        mEditor.putInt(PREF_RATING,rating);
        mEditor.commit();
    }

    public int getRating(){
        int rating = mSharedPreferences.getInt(PREF_RATING,0);
        return rating;
    }

}
