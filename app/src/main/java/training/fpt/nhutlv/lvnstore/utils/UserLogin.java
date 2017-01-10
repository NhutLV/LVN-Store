package training.fpt.nhutlv.lvnstore.utils;

import training.fpt.nhutlv.lvnstore.entities.User;

/**
 * Created by NhutDu on 22/09/2016.
 */
public class UserLogin {

    private static User mUserLogin = null;

    public static User getUserLogin() {
        return mUserLogin;
    }

    public static void setUserLogin(User UserLogin) {
        mUserLogin = UserLogin;
    }
}
