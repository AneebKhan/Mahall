package itf.ehsthkhdmaat.mahall.models.bundle;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class SubCategoryBundle implements Serializable {


    @SerializedName("catId")
    @Expose
    private int catId;
    @SerializedName("catName")
    @Expose
    private String catName;
    @SerializedName("parentCatId")
    @Expose
    private int parentCatId;
    @SerializedName("parentCatName")
    @Expose
    private String parentCatName;

    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public int getParentCatId() {
        return parentCatId;
    }

    public void setParentCatId(int parentCatId) {
        this.parentCatId = parentCatId;
    }

    public String getParentCatName() {
        return parentCatName;
    }

    public void setParentCatName(String parentCatName) {
        this.parentCatName = parentCatName;
    }
}


