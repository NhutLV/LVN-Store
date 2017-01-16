package training.fpt.nhutlv.lvnstore.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import training.fpt.nhutlv.lvnstore.fragments.SettingsFragment;

/**
 * Created by HCD-Fresher039 on 12/29/2016.
 */

public class PreferenceState {

    private Context mContext;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    public PreferenceState(Context context){
        mContext = context;
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        mEditor = mSharedPreferences.edit();
    }

    public void saveStateFragment(int id){
        mEditor.putInt(SettingsFragment.KEY_CATEGORY,id);
        mEditor.commit();
    }

    public int getStateFragment(){
        int id = Integer.parseInt(mSharedPreferences.getString(SettingsFragment.KEY_CATEGORY,"0"));
        return id;
    }

    public void saveStateShow(int state){
        mEditor.putInt(SettingsFragment.KEY_CATEGORY_SHOW,state);
        mEditor.commit();
    }

    public int getStateShow(){
        int show = Integer.parseInt(mSharedPreferences.getString(SettingsFragment.KEY_CATEGORY_SHOW,"0"));
        return show;
    }

    public void saveLanguage(int language){
        mEditor.putInt(SettingsFragment.KEY_LANGUAGE,language);
        mEditor.commit();
    }

    public int getLanguage(){
        int language = Integer.parseInt(mSharedPreferences.getString(SettingsFragment.KEY_LANGUAGE,"0"));
        return language;
    }

    public void setDefault(){
        mEditor.putString(SettingsFragment.KEY_CATEGORY_SHOW,"0");
        mEditor.putString(SettingsFragment.KEY_CATEGORY,"0");
        mEditor.commit();
    }
}
