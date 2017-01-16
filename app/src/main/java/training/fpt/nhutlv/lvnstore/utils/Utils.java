package training.fpt.nhutlv.lvnstore.utils;

import android.content.Context;
import android.content.res.Configuration;

import java.util.Locale;

/**
 * Created by NhutDu on 03/01/2017.
 */

public class Utils {

    private Context mContext;
    public static String mCurrentLocale;
    public Utils(Context context) {
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
