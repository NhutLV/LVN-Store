package training.fpt.nhutlv.lvnstore.entities;

import java.util.Date;
import java.util.List;

/**
 * Created by HCD-Fresher039 on 12/27/2016.
 */

public class AppInfo {

    private String title;
    private String category;
    private String description;
    private Date create;
    private int downloads_min;
    private int downloads_max;
    private double price_numeric;
    private String promo_video;
    private float rating;
    private int number_rating;
    private String short_desc;
    private int size;
    private List<String> screenshots;
    private String icon;
    private String developer;
    private String website;
    private String what_is_new;
    private Date market_update;
    private String package_name;

    //region Getters and Setters

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

    public Date getCreate() {
        return create;
    }

    public void setCreate(Date create) {
        this.create = create;
    }

    public int getDownloads_min() {
        return downloads_min;
    }

    public void setDownloads_min(int downloads_min) {
        this.downloads_min = downloads_min;
    }

    public int getDownloads_max() {
        return downloads_max;
    }

    public void setDownloads_max(int downloads_max) {
        this.downloads_max = downloads_max;
    }

    public double getPrice_numeric() {
        return price_numeric;
    }

    public void setPrice_numeric(double price_numeric) {
        this.price_numeric = price_numeric;
    }

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

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<String> getScreenshots() {
        return screenshots;
    }

    public void setScreenshots(List<String> screenshots) {
        this.screenshots = screenshots;
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

    //endregion
    //region Constructors

    public AppInfo(String title, String category, String icon, float rating, int number_rating, String short_desc) {
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

    //endregion

}
