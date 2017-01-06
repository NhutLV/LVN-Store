package training.fpt.nhutlv.lvnstore.model.response;

import com.google.gson.annotations.SerializedName;

import training.fpt.nhutlv.lvnstore.entities.TypeImage;


/**
 * Created by NhutDu on 19/12/2016.
 */

public class APITypeImage {

    //region Properties

    @SerializedName("change_keys")
    private String[] changeKey;

    @SerializedName("images")
    private TypeImage typeImage;

    //endregion

    //region Getters and Setters

    public String[] getChangeKey() {
        return changeKey;
    }

    public void setChangeKey(String[] changeKey) {
        this.changeKey = changeKey;
    }

    public TypeImage getTypeImage() {
        return typeImage;
    }

    public void setTypeImage(TypeImage typeImage) {
        this.typeImage = typeImage;
    }

    //endregion

    //region Constructor

    public APITypeImage(String[] changeKey, TypeImage typeImage) {
        this.changeKey = changeKey;
        this.typeImage = typeImage;
    }

    //endregion
}
