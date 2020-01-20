package com.itf.phatbooskiandroid.models.uimodels.common;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by zahmed on 3/4/2018.
 */

public class InformationContainer {

    @SerializedName("InformationItemList")
    @Expose
    private List<InformationItemList> informationItemList = null;
    @SerializedName("HeaderText")
    @Expose
    private String headerText;
    @SerializedName("HeaderDisplayType")
    @Expose
    private String headerDisplayType;
    @SerializedName("ContainerNo")
    @Expose
    private int containerNo;
    @SerializedName("TotalCount")
    @Expose
    private int totalCount;
    @SerializedName("ColumnType")
    @Expose
    private String columnType;

    public List<InformationItemList> getInformationItemList() {
        return informationItemList;
    }

    public void setInformationItemList(List<InformationItemList> informationItemList) {
        this.informationItemList = informationItemList;
    }

    public String getHeaderText() {
        return headerText;
    }

    public void setHeaderText(String headerText) {
        this.headerText = headerText;
    }

    public String getHeaderDisplayType() {
        return headerDisplayType;
    }

    public void setHeaderDisplayType(String headerDisplayType) {
        this.headerDisplayType = headerDisplayType;
    }

    public int getContainerNo() {
        return containerNo;
    }

    public void setContainerNo(int containerNo) {
        this.containerNo = containerNo;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

}
