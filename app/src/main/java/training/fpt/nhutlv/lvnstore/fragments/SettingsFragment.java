package training.fpt.nhutlv.lvnstore.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;

import org.greenrobot.eventbus.EventBus;

import training.fpt.nhutlv.lvnstore.R;
import training.fpt.nhutlv.lvnstore.event.ChangeLanguageEvent;
import training.fpt.nhutlv.lvnstore.lib.PreferenceFragment;
import training.fpt.nhutlv.lvnstore.utils.PreferenceState;
import training.fpt.nhutlv.lvnstore.utils.SeekBarDialog;
import training.fpt.nhutlv.lvnstore.utils.StateShow;

/**
 * Created by LamNT17 on 12/3/2015.
 */
public class SettingsFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {

    public static final String KEY_SORT = "pref_sort";
    public static final String KEY_CATEGORY = "pref_category";
    public static final String KEY_RATE = "pref_rating";
    public static final String KEY_CATEGORY_SHOW = "pref_category_show";
    public static final String KEY_RELEASE_YEAR = "pref_release_year";
    public static final String KEY_LANGUAGE = "pref_language";
    public static final String IS_CHANGE = "_is_change";
    public static final String[] listKeys = {KEY_CATEGORY, KEY_RATE, KEY_CATEGORY_SHOW, KEY_SORT,KEY_LANGUAGE};

    SharedPreferences pref;
    SettingsFragment mSettingsFragment;
    Preference preferenceRate;

    @Override
    public void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setHasOptionsMenu(true);
        addPreferencesFromResource(R.xml.pref_setting);

        mSettingsFragment = this;
        preferenceRate = getPreferenceManager().findPreference(KEY_RATE);
        preferenceRate.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                new SeekBarDialog().setSettingsFragment(mSettingsFragment).show(getFragmentManager(), "Select rate");
                return false;
            }
        });

        pref = PreferenceManager.getDefaultSharedPreferences(getContext());
        pref.registerOnSharedPreferenceChangeListener(this);

        // Set summary at initial
        ListPreference listCategory = (ListPreference) findPreference(KEY_CATEGORY);
        String[] category = getResources().getStringArray(R.array.list_category);
        listCategory.setSummary(category[Integer.parseInt(pref.getString(KEY_CATEGORY, "0"))]);

        Preference rateFrom = (Preference) findPreference(KEY_RATE);
        rateFrom.setSummary(pref.getString(KEY_RATE, "0"));

        EditTextPreference releaseYear = (EditTextPreference) findPreference(KEY_RELEASE_YEAR);
        releaseYear.setSummary(pref.getString(KEY_RELEASE_YEAR, "0"));

        ListPreference listCategoryShow = (ListPreference) findPreference(KEY_CATEGORY_SHOW);
        String[] categoryShow = getResources().getStringArray(R.array.list_category_show);
        listCategoryShow.setSummary(categoryShow[Integer.parseInt(pref.getString(KEY_CATEGORY_SHOW, "0"))]);

        ListPreference listLanguage = (ListPreference) findPreference(KEY_LANGUAGE);
        String[] language = getResources().getStringArray(R.array.list_language);
        listLanguage.setSummary(language[Integer.parseInt(pref.getString(KEY_LANGUAGE, "0"))]);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private int rate;
    public void setRate(int rate) {
        this.rate = rate;
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(KEY_RATE, rate + "");
        editor.commit();
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        switch (key) {
            case KEY_CATEGORY:
            case KEY_CATEGORY_SHOW:
                ListPreference list = (ListPreference) findPreference(key);
                list.setSummary(list.getEntry());
                StateShow.setCategory(new PreferenceState(getActivity()).getStateFragment());
                break;
            case KEY_LANGUAGE:
                ListPreference listLanguage = (ListPreference) findPreference(key);
                listLanguage.setSummary(listLanguage.getEntry());
                Intent intent = getActivity().getIntent();
                getActivity().finish();
                startActivity(intent);
                break;
            case KEY_RELEASE_YEAR:
                EditTextPreference editTextPreference = (EditTextPreference) findPreference(key);
                editTextPreference.setSummary(editTextPreference.getText());
                break;
            case KEY_RATE:
                preferenceRate.setSummary(rate + "");
                break;
            default: return;
        }
        editor.putBoolean(key + IS_CHANGE, true);
        editor.commit();
    }

    @Override
    public void onDestroy() {
        pref.unregisterOnSharedPreferenceChangeListener(this);
        super.onDestroy();
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        if(menu.findItem(R.id.list_menu)!=null)
            menu.findItem(R.id.list_menu).setVisible(false);

        menu.findItem(R.id.drop_down).setVisible(false);
        menu.findItem(R.id.setting).setVisible(false);
        menu.removeItem(R.id.list_menu);
        menu.removeItem(R.id.gird_menu);
    }

}