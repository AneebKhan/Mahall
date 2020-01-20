package itf.ehsthkhdmaat.mahall.models.service_model.favourites;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RateAndReview {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("point")
    @Expose
    private Integer point;
    @SerializedName("detail")
    @Expose
    private String detail;
    @SerializedName("venueId")
    @Expose
    private Integer venueId;
    @SerializedName("venue")
    @Expose
    private String venue;
    @SerializedName("recordStatusId")
    @Expose
    private Integer recordStatusId;
    @SerializedName("recordStatus")
    @Expose
    private String recordStatus;
    @SerializedName("createdBy")
    @Expose
    private String createdBy;
    @SerializedName("createdOn")
    @Expose
    private String createdOn;
    @SerializedName("modifiedBy")
    @Expose
    private String modifiedBy;
    @SerializedName("modifiedOn")
    @Expose
    private String modifiedOn;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Integer getVenueId() {
        return venueId;
    }

    public void setVenueId(Integer venueId) {
        this.venueId = venueId;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public Integer getRecordStatusId() {
        return recordStatusId;
    }

    public void setRecordStatusId(Integer recordStatusId) {
        this.recordStatusId = recordStatusId;
    }

    public String getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(String recordStatus) {
        this.recordStatus = recordStatus;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(String modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

}