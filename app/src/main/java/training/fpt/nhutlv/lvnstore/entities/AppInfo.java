package training.fpt.nhutlv.lvnstore.entities;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by HCD-Fresher039 on 12/27/2016.
 */

public class AppInfo extends RealmObject implements Parcelable{
    @PrimaryKey
    private String package_name;

    private String title;
    private String category;
    private String description;
    private String created;
    private long downloads_min;
    private long downloads_max;
    private double price_numeric;
    private String promo_video;
    private float rating;
    private int number_rating;
    private String short_desc;

    private long size;
    private RealmList<RealmString> screenshots;
    private String icon;
    private String developer;
    private String website;
    private String what_is_new;
    private String market_url;
    private Date market_update;
    private boolean isFavourite;
    byte [] mImageIcon;

    RealmList<RealmArrayByte> mScreenShotImage;

    //region Getters and Setters

    protected AppInfo(Parcel in) {
        package_name = in.readString();
        title = in.readString();
        category = in.readString();
        description = in.readString();
        created = in.readString();
        downloads_min = in.readLong();
        downloads_max = in.readLong();
        price_numeric = in.readDouble();
        promo_video = in.readString();
        rating = in.readFloat();
        number_rating = in.readInt();
        short_desc = in.readString();
        size = in.readLong();
        icon = in.readString();
        developer = in.readString();
        website = in.readString();
        what_is_new = in.readString();
        market_url = in.readString();
        isFavourite = in.readByte() != 0;
        mImageIcon = in.createByteArray();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(package_name);
        dest.writeString(title);
        dest.writeString(category);
        dest.writeString(description);
        dest.writeString(created);
        dest.writeLong(downloads_min);
        dest.writeLong(downloads_max);
        dest.writeDouble(price_numeric);
        dest.writeString(promo_video);
        dest.writeFloat(rating);
        dest.writeInt(number_rating);
        dest.writeString(short_desc);
        dest.writeLong(size);
        dest.writeString(icon);
        dest.writeString(developer);
        dest.writeString(website);
        dest.writeString(what_is_new);
        dest.writeString(market_url);
        dest.writeByte((byte) (isFavourite ? 1 : 0));
        dest.writeByteArray(mImageIcon);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<AppInfo> CREATOR = new Creator<AppInfo>() {
        @Override
        public AppInfo createFromParcel(Parcel in) {
            return new AppInfo(in);
        }

        @Override
        public AppInfo[] newArray(int size) {
            return new AppInfo[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public long getDownloads_max() {
        return downloads_max;
    }

    public void setDownloads_max(long downloads_max) {
        this.downloads_max = downloads_max;
    }

    public long getDownloads_min() {
        return downloads_min;
    }

    public void setDownloads_min(long downloads_min) {
        this.downloads_min = downloads_min;
    }

    public double getPrice_numeric() {
        return price_numeric;
    }

    public void setPrice_numeric(double price_numeric) {
        this.price_numeric = price_numeric;
    }

//    public RealmList<RealmString> getScreenshots() {
//        return screenshots;
//    }
//
//    public void setScreenshots(RealmList<RealmString> screenshots) {
//        this.screenshots = screenshots;
//    }

    public String getPromo_video() {
        return promo_video;
    }

    public void setPromo_video(String promo_video) {
        this.promo_video = promo_video;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getNumber_rating() {
        return number_rating;
    }

    public void setNumber_rating(int number_rating) {
        this.number_rating = number_rating;
    }

    public String getShort_desc() {
        return short_desc;
    }

    public void setShort_desc(String short_desc) {
        this.short_desc = short_desc;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getWhat_is_new() {
        return what_is_new;
    }

    public void setWhat_is_new(String what_is_new) {
        this.what_is_new = what_is_new;
    }

    public Date getMarket_update() {
        return market_update;
    }

    public void setMarket_update(Date market_update) {
        this.market_update = market_update;
    }

    public String getPackage_name() {
        return package_name;
    }

    public void setPackage_name(String package_name) {
        this.package_name = package_name;
    }

    public boolean isFavourite() {
        return isFavourite;
    }

    public void setFavourite(boolean favourite) {
        isFavourite = favourite;
    }

    public RealmList<RealmString> getScreenshots() {
        return screenshots;
    }

    public void setScreenshots(RealmList<RealmString> screenshots) {
        this.screenshots = screenshots;
    }

    public String getMarket_url() {
        return market_url;
    }

    public void setMarket_url(String market_url) {
        this.market_url = market_url;
    }

    public byte[] getImageIcon() {
        return mImageIcon;
    }

    public void setImageIcon(byte[] imageIcon) {
        mImageIcon = imageIcon;
    }

    public RealmList<RealmArrayByte> getScreenShotImage() {
        return mScreenShotImage;
    }

    public void setScreenShotImage(RealmList<RealmArrayByte> screenShotImage) {
        mScreenShotImage = screenShotImage;
    }

    //endregion
    //region Constructors

    public AppInfo(String package_name,String title, String category, String icon, float rating, int number_rating, String short_desc) {
        this.package_name = package_name;
        this.title = title;
        this.category = category;
        this.icon = icon;
        this.rating = rating;
        this.number_rating = number_rating;
        this.short_desc = short_desc;
    }

    public AppInfo(String title, String icon) {
        this.title = title;
        this.icon = icon;
    }

    public AppInfo() {
    }

    //endregion

}
