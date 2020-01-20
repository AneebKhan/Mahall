package itf.ehsthkhdmaat.mahall.models.service_model.countries_list;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Response {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("phoneCode")
    @Expose
    private String phoneCode;
    @SerializedName("continentId")
    @Expose
    private Integer continentId;
    @SerializedName("defaultContinent")
    @Expose
    private String defaultContinent;
    @SerializedName("defaultLanguageId")
    @Expose
    private Integer defaultLanguageId;
    @SerializedName("defaultLanguage")
    @Expose
    private String defaultLanguage;
    @SerializedName("defaultCurrencyId")
    @Expose
    private Integer defaultCurrencyId;
    @SerializedName("defaultCurrency")
    @Expose
    private String defaultCurrency;
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

    /**
     * No args constructor for use in serialization
     *
     */
    public Response() {
    }

    /**
     *
     * @param continentId
     * @param phoneCode
     * @param modifiedBy
     * @param defaultLanguageId
     * @param defaultCurrency
     * @param defaultContinent
     * @param defaultLanguage
     * @param code
     * @param recordStatus
     * @param createdOn
     * @param id
     * @param modifiedOn
     * @param createdBy
     * @param recordStatusId
     * @param description
     * @param name
     * @param defaultCurrencyId
     */
    public Response(Integer id, String name, String code, String description, String phoneCode, Integer continentId, String defaultContinent, Integer defaultLanguageId, String defaultLanguage, Integer defaultCurrencyId, String defaultCurrency, Integer recordStatusId, String recordStatus, String createdBy, String createdOn, String modifiedBy, String modifiedOn) {
        super();
        this.id = id;
        this.name = name;
        this.code = code;
        this.description = description;
        this.phoneCode = phoneCode;
        this.continentId = continentId;
        this.defaultContinent = defaultContinent;
        this.defaultLanguageId = defaultLanguageId;
        this.defaultLanguage = defaultLanguage;
        this.defaultCurrencyId = defaultCurrencyId;
        this.defaultCurrency = defaultCurrency;
        this.recordStatusId = recordStatusId;
        this.recordStatus = recordStatus;
        this.createdBy = createdBy;
        this.createdOn = createdOn;
        this.modifiedBy = modifiedBy;
        this.modifiedOn = modifiedOn;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoneCode() {
        return phoneCode;
    }

    public void setPhoneCode(String phoneCode) {
        this.phoneCode = phoneCode;
    }

    public Integer getContinentId() {
        return continentId;
    }

    public void setContinentId(Integer continentId) {
        this.continentId = continentId;
    }

    public String getDefaultContinent() {
        return defaultContinent;
    }

    public void setDefaultContinent(String defaultContinent) {
        this.defaultContinent = defaultContinent;
    }

    public Integer getDefaultLanguageId() {
        return defaultLanguageId;
    }

    public void setDefaultLanguageId(Integer defaultLanguageId) {
        this.defaultLanguageId = defaultLanguageId;
    }

    public String getDefaultLanguage() {
        return defaultLanguage;
    }

    public void setDefaultLanguage(String defaultLanguage) {
        this.defaultLanguage = defaultLanguage;
    }

    public Integer getDefaultCurrencyId() {
        return defaultCurrencyId;
    }

    public void setDefaultCurrencyId(Integer defaultCurrencyId) {
        this.defaultCurrencyId = defaultCurrencyId;
    }

    public String getDefaultCurrency() {
        return defaultCurrency;
    }

    public void setDefaultCurrency(String defaultCurrency) {
        this.defaultCurrency = defaultCurrency;
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