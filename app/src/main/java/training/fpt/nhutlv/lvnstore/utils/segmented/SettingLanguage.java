package training.fpt.nhutlv.lvnstore.utils.segmented;

import android.content.Context;
import android.content.res.Configuration;

import java.util.Locale;

/**
 * Created by NhutDu on 14/01/2017.
 */

public class SettingLanguage {

    Context mContext;
    static String mCurrentLocale;
    public SettingLanguage(Context context) {
        mContext = context;
    }

    public void setLanguage(String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        mContext.getResources().updateConfiguration(config,
                mContext.getResources().getDisplayMetrics());
        mCurrentLocale = language;
    }
}
