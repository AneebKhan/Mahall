package itf.ehsthkhdmaat.mahall.models.service_model.venue_details;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GeoLocation {

    @SerializedName("lat")
    @Expose
    private Double lat;
    @SerializedName("long")
    @Expose
    private Double _long;
    @SerializedName("stSrid")
    @Expose
    private Integer stSrid;

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLong() {
        return _long;
    }

    public void setLong(Double _long) {
        this._long = _long;
    }

    public Integer getStSrid() {
        return stSrid;
    }

    public void setStSrid(Integer stSrid) {
        this.stSrid = stSrid;
    }

}