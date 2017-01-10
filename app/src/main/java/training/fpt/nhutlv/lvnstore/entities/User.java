package training.fpt.nhutlv.lvnstore.entities;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

/**
 * Created by NhutDu on 01/01/2017.
 */

public class User extends RealmObject{

    //region Properties
    @PrimaryKey
    private String mEmail;

    private String mName;

    @Ignore
    private Date mBirthday;
    private boolean mGender;
    @Ignore
    private byte[] mAvatar;
    private String mPassword;

    //endregion
    //region Getters and Setters

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public Date getBirthday() {
        return mBirthday;
    }

    public void setBirthday(Date birthday) {
        mBirthday = birthday;
    }

    public boolean isGender() {
        return mGender;
    }

    public void setGender(boolean gender) {
        mGender = gender;
    }

    public byte[] getAvatar() {
        return mAvatar;
    }

    public void setAvatar(byte[] avatar) {
        mAvatar = avatar;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    //endregion
    //region Constructors

    public User(int id, String name, String email, Date birthday, boolean gender, byte[] avatar) {
        mName = name;
        mEmail = email;
        mBirthday = birthday;
        mGender = gender;
        mAvatar = avatar;
    }

    public User() {
    }

    //endregion

}
