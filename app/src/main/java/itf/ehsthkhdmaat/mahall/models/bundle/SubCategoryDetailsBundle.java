package itf.ehsthkhdmaat.mahall.models.bundle;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import itf.ehsthkhdmaat.mahall.models.service_model.venues.Response;

import java.io.Serializable;

public class SubCategoryDetailsBundle implements Serializable {


    @SerializedName("venueId")
    @Expose
    private int venueId;
    @SerializedName("venueName")
    @Expose
    private String venueName;
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
    @SerializedName("geoLatitude")
    @Expose
    private double geoLatitude;
    @SerializedName("geoLongitude")
    @Expose
    private double geoLongitude;
    @SerializedName("responseObj")
    @Expose
    private Response responseObj;

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

    public int getVenueId() {
        return venueId;
    }

    public void setVenueId(int venueId) {
        this.venueId = venueId;
    }

    public String getVenueName() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    public double getGeoLatitude() {
        return geoLatitude;
    }

    public void setGeoLatitude(double geoLatitude) {
        this.geoLatitude = geoLatitude;
    }

    public double getGeoLongitude() {
        return geoLongitude;
    }

    public void setGeoLongitude(double geoLongitude) {
        this.geoLongitude = geoLongitude;
    }

    public Response getResponseObj() {
        return responseObj;
    }

    public void setResponseObj(Response responseObj) {
        this.responseObj = responseObj;
    }
}
