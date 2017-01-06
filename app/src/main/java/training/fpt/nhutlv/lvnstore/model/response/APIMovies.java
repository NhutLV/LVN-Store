package training.fpt.nhutlv.lvnstore.model.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import training.fpt.nhutlv.lvnstore.entities.Movies;


/**
 * Created by NhutDu on 20/12/2016.
 */

public class APIMovies {

    //region Properties

    @SerializedName("page")
    private int page ;

    @SerializedName("results")
    private ArrayList<Movies> listMovies;

    @SerializedName("total_results")
    private int totalResults;

    @SerializedName("total_pages")
    private int totalPages;

    //endregion

    //region Getters and Setters

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public ArrayList<Movies> getListMovies() {
        return listMovies;
    }

    public void setListMovies(ArrayList<Movies> listMovies) {
        this.listMovies = listMovies;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    //endregion

    //region Constructors

    public APIMovies(int page, ArrayList<Movies> listMovies, int totalResults, int totalPages) {
        this.page = page;
        this.listMovies = listMovies;
        this.totalResults = totalResults;
        this.totalPages = totalPages;
    }

    //endregion
}
