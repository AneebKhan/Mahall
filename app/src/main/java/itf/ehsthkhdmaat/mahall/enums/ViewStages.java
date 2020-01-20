package itf.ehsthkhdmaat.mahall.enums;

import com.google.gson.annotations.SerializedName;

public enum ViewStages {

    @SerializedName("List View")
    LISTVIEW("List View"),

    @SerializedName("Grid View")
    GRIDVIEW("Grid View"),

    @SerializedName("Map View")
    MAPVIEW("Map View");

    private String string;

    ViewStages(String string) {
        this.string = string;
    }

    public String getStage() {
        return string;
    }

}
