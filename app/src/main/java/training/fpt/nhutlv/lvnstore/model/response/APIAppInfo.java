package training.fpt.nhutlv.lvnstore.model.response;

import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

import training.fpt.nhutlv.lvnstore.entities.AppInfo;

/**
 * Created by NhutDu on 05/01/2017.
 */

public class APIAppInfo {

    @SerializedName("list_name")
    String mListName;

    @SerializedName("cat_key")
    String mCatKey;

    @SerializedName("category_name")
    String mCategoryName;

    @SerializedName("country")
    String mCountry;

    @SerializedName("date")
    Date mDate;

    @SerializedName("app_list")
    ArrayList<AppInfo> mListApp;

    @SerializedName("limit")
    int mLimit;

    @SerializedName("number_results")
    int mNumberResults;

    @SerializedName("has_next")
    boolean mHasNext;

    @SerializedName("page")
    int mPage;

    @SerializedName("num_pages")
    int mNumPages;

    public String getListName() {
        return mListName;
    }

    public void setListName(String listName) {
        mListName = listName;
    }

    public String getCatKey() {
        return mCatKey;
    }

    public void setCatKey(String catKey) {
        mCatKey = catKey;
    }

    public String getCategoryName() {
        return mCategoryName;
    }

    public void setCategoryName(String categoryName) {
        mCategoryName = categoryName;
    }

    public String getCountry() {
        return mCountry;
    }

    public void setCountry(String country) {
        mCountry = country;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public ArrayList<AppInfo> getListApp() {
        return mListApp;
    }

    public void setListApp(ArrayList<AppInfo> listApp) {
        mListApp = listApp;
    }

    public int getLimit() {
        return mLimit;
    }

    public void setLimit(int limit) {
        mLimit = limit;
    }

    public int getNumberResults() {
        return mNumberResults;
    }

    public void setNumberResults(int numberResults) {
        mNumberResults = numberResults;
    }

    public boolean isHasNext() {
        return mHasNext;
    }

    public void setHasNext(boolean hasNext) {
        mHasNext = hasNext;
    }

    public int getPage() {
        return mPage;
    }

    public void setPage(int page) {
        mPage = page;
    }

    public int getNumPages() {
        return mNumPages;
    }

    public void setNumPages(int numPages) {
        mNumPages = numPages;
    }

}
