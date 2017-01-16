package training.fpt.nhutlv.lvnstore.utils;

import training.fpt.nhutlv.lvnstore.entities.User;

/**
 * Created by NhutDu on 22/09/2016.
 */
public class StateShow {

    private static int mStateShow = 0;

    public static int getStateShow() {
        return mStateShow;
    }

    public static void setStateShow(int stateShow) {
        mStateShow = stateShow;
    }

    private static int mCategory = 0;

    public static int getCategory() {
        return mCategory;
    }

    public static void setCategory(int category) {
        mCategory = category;
    }
}
