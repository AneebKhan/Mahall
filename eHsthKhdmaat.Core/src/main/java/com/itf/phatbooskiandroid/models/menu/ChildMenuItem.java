
package com.itf.phatbooskiandroid.models.menu;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by zahmed on 3/2/2018.
 */

public class ChildMenuItem {

    @SerializedName("ID")
    @Expose
    private int iD;
    @SerializedName("ParentID")
    @Expose
    private int parentID;
    @SerializedName("MenuID")
    @Expose
    private int menuID;
    @SerializedName("MenuActionTypeID")
    @Expose
    private int menuActionTypeID;
    @SerializedName("MenuActionTypeName")
    @Expose
    private String menuActionTypeName;
    @SerializedName("IsActive")
    @Expose
    private boolean isActive;
    @SerializedName("IsNewMenu")
    @Expose
    private boolean isNewMenu;
    @SerializedName("IsActiveInCurrentRole")
    @Expose
    private boolean isActiveInCurrentRole;
    @SerializedName("Title")
    @Expose
    private String title;
    @SerializedName("EnTitle")
    @Expose
    private String enTitle;
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("IsTransaction")
    @Expose
    private boolean isTransaction;
    @SerializedName("IsShortcut")
    @Expose
    private boolean isShortcut;
    @SerializedName("URL")
    @Expose
    private String uRL;
    @SerializedName("OrderIndex")
    @Expose
    private int orderIndex;
    @SerializedName("IsNewColumn")
    @Expose
    private boolean isNewColumn;
    @SerializedName("CssClass")
    @Expose
    private String cssClass;
    @SerializedName("HostProcessCode")
    @Expose
    private int hostProcessCode;
    @SerializedName("ShowInTurkish")
    @Expose
    private boolean showInTurkish;
    @SerializedName("ShowInEnglish")
    @Expose
    private boolean showInEnglish;
    @SerializedName("RoleList")
    @Expose
    private List<Integer> roleList = null;
    @SerializedName("Roles")
    @Expose
    private String roles;
    @SerializedName("ChildMenuItems")
    @Expose
    private List<ChildMenuItem> childMenuItems = null;
    @SerializedName("ResourceItemListTitle")
    @Expose
    private List<ResourceItemListTitle> resourceItemListTitle = null;
    @SerializedName("ResourceItemListDescription")
    @Expose
    private List<ResourceItemListDescription> resourceItemListDescription = null;
    @SerializedName("ShortcutOrderIndex")
    @Expose
    private int shortcutOrderIndex;
    @SerializedName("UseRoles")
    @Expose
    private boolean useRoles;

    public int getID() {
        return iD;
    }

    public void setID(int iD) {
        this.iD = iD;
    }

    public int getParentID() {
        return parentID;
    }

    public void setParentID(int parentID) {
        this.parentID = parentID;
    }

    public int getMenuID() {
        return menuID;
    }

    public void setMenuID(int menuID) {
        this.menuID = menuID;
    }

    public int getMenuActionTypeID() {
        return menuActionTypeID;
    }

    public void setMenuActionTypeID(int menuActionTypeID) {
        this.menuActionTypeID = menuActionTypeID;
    }

    public String getMenuActionTypeName() {
        return menuActionTypeName;
    }

    public void setMenuActionTypeName(String menuActionTypeName) {
        this.menuActionTypeName = menuActionTypeName;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public boolean isIsNewMenu() {
        return isNewMenu;
    }

    public void setIsNewMenu(boolean isNewMenu) {
        this.isNewMenu = isNewMenu;
    }

    public boolean isIsActiveInCurrentRole() {
        return isActiveInCurrentRole;
    }

    public void setIsActiveInCurrentRole(boolean isActiveInCurrentRole) {
        this.isActiveInCurrentRole = isActiveInCurrentRole;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEnTitle() {
        return enTitle;
    }

    public void setEnTitle(String enTitle) {
        this.enTitle = enTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isIsTransaction() {
        return isTransaction;
    }

    public void setIsTransaction(boolean isTransaction) {
        this.isTransaction = isTransaction;
    }

    public boolean isIsShortcut() {
        return isShortcut;
    }

    public void setIsShortcut(boolean isShortcut) {
        this.isShortcut = isShortcut;
    }

    public String getURL() {
        return uRL;
    }

    public void setURL(String uRL) {
        this.uRL = uRL;
    }

    public int getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(int orderIndex) {
        this.orderIndex = orderIndex;
    }

    public boolean isIsNewColumn() {
        return isNewColumn;
    }

    public void setIsNewColumn(boolean isNewColumn) {
        this.isNewColumn = isNewColumn;
    }

    public String getCssClass() {
        return cssClass;
    }

    public void setCssClass(String cssClass) {
        this.cssClass = cssClass;
    }

    public int getHostProcessCode() {
        return hostProcessCode;
    }

    public void setHostProcessCode(int hostProcessCode) {
        this.hostProcessCode = hostProcessCode;
    }

    public boolean isShowInTurkish() {
        return showInTurkish;
    }

    public void setShowInTurkish(boolean showInTurkish) {
        this.showInTurkish = showInTurkish;
    }

    public boolean isShowInEnglish() {
        return showInEnglish;
    }

    public void setShowInEnglish(boolean showInEnglish) {
        this.showInEnglish = showInEnglish;
    }

    public List<Integer> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Integer> roleList) {
        this.roleList = roleList;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public List<ChildMenuItem> getChildMenuItems() {
        return childMenuItems;
    }

    public void setChildMenuItems(List<ChildMenuItem> childMenuItems) {
        this.childMenuItems = childMenuItems;
    }

    public List<ResourceItemListTitle> getResourceItemListTitle() {
        return resourceItemListTitle;
    }

    public void setResourceItemListTitle(List<ResourceItemListTitle> resourceItemListTitle) {
        this.resourceItemListTitle = resourceItemListTitle;
    }

    public List<ResourceItemListDescription> getResourceItemListDescription() {
        return resourceItemListDescription;
    }

    public void setResourceItemListDescription(List<ResourceItemListDescription> resourceItemListDescription) {
        this.resourceItemListDescription = resourceItemListDescription;
    }

    public int getShortcutOrderIndex() {
        return shortcutOrderIndex;
    }

    public void setShortcutOrderIndex(int shortcutOrderIndex) {
        this.shortcutOrderIndex = shortcutOrderIndex;
    }

    public boolean isUseRoles() {
        return useRoles;
    }

    public void setUseRoles(boolean useRoles) {
        this.useRoles = useRoles;
    }

}
