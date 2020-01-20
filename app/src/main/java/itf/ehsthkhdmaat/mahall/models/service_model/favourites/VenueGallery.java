package itf.ehsthkhdmaat.mahall.models.service_model.favourites;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VenueGallery {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("venueId")
    @Expose
    private Integer venueId;
    @SerializedName("galleryTypeId")
    @Expose
    private Integer galleryTypeId;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("isDefault")
    @Expose
    private Boolean isDefault;
    @SerializedName("recordStatusId")
    @Expose
    private Integer recordStatusId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getVenueId() {
        return venueId;
    }

    public void setVenueId(Integer venueId) {
        this.venueId = venueId;
    }

    public Integer getGalleryTypeId() {
        return galleryTypeId;
    }

    public void setGalleryTypeId(Integer galleryTypeId) {
        this.galleryTypeId = galleryTypeId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

    public Integer getRecordStatusId() {
        return recordStatusId;
    }

    public void setRecordStatusId(Integer recordStatusId) {
        this.recordStatusId = recordStatusId;
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