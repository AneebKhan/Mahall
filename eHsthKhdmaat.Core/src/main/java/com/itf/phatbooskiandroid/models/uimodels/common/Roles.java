package com.itf.phatbooskiandroid.models.uimodels.common;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by zahmed on 3/4/2018.
 */

public class Roles {

    @SerializedName("List")
    @Expose
    private List<Integer> list = null;
    @SerializedName("ParentList")
    @Expose
    private List<Object> parentList = null;

    public List<Integer> getList() {
        return list;
    }

    public void setList(List<Integer> list) {
        this.list = list;
    }

    public List<Object> getParentList() {
        return parentList;
    }

    public void setParentList(List<Object> parentList) {
        this.parentList = parentList;
    }

}
