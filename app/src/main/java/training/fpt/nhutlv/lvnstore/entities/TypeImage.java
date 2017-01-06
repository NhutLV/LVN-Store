package training.fpt.nhutlv.lvnstore.entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by NhutDu on 19/12/2016.
 */

public class TypeImage{

    //region Properties

    @SerializedName("base_url")
    private String baseURL;

    @SerializedName("secure_base_url")
    private String secureBaseURL;

    @SerializedName("backdrop_sizes")
    private String [] backdropSizes;

    @SerializedName("logo_sizes")
    private String [] logoSizes;

    @SerializedName("poster_sizes")
    private String [] posterSizes;

    @SerializedName("profile_sizes")
    private String [] profileSizes;

    @SerializedName("still_sizes")
    private String [] stillSizes;

    //endregion

    //region Getters and Setters

    public String getBaseURL() {
        return baseURL;
    }

    public void setBaseURL(String baseURL) {
        this.baseURL = baseURL;
    }

    public String getSecureBaseURL() {
        return secureBaseURL;
    }

    public void setSecureBaseURL(String secureBaseURL) {
        this.secureBaseURL = secureBaseURL;
    }

    public String[] getBackdropSizes() {
        return backdropSizes;
    }

    public void setBackdropSizes(String[] backdropSizes) {
        this.backdropSizes = backdropSizes;
    }

    public String[] getLogoSizes() {
        return logoSizes;
    }

    public void setLogoSizes(String[] logoSizes) {
        this.logoSizes = logoSizes;
    }

    public String[] getPosterSizes() {
        return posterSizes;
    }

    public void setPosterSizes(String[] posterSizes) {
        this.posterSizes = posterSizes;
    }

    public String[] getProfileSizes() {
        return profileSizes;
    }

    public void setProfileSizes(String[] profileSizes) {
        this.profileSizes = profileSizes;
    }

    public String[] getStillSizes() {
        return stillSizes;
    }

    public void setStillSizes(String[] stillSizes) {
        this.stillSizes = stillSizes;
    }

    //region

    //region Constructors

    public TypeImage() {
    }

    public TypeImage(String baseURL, String[] backdropSizes, String[] posterSizes) {
        this.baseURL = baseURL;
        this.backdropSizes = backdropSizes;
        this.posterSizes = posterSizes;
    }

    //endregion
}
