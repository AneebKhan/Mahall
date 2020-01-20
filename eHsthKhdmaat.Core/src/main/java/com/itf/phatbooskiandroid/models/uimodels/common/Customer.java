
package com.itf.phatbooskiandroid.models.uimodels.common;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import com.itf.phatbooskiandroid.models.enums.CustomerStatusEnum;
import com.itf.phatbooskiandroid.models.enums.CustomerSubTypeEnum;
import com.itf.phatbooskiandroid.models.enums.CustomerTypeEnum;
import com.itf.phatbooskiandroid.models.enums.LogonTypeEnum;
import com.itf.phatbooskiandroid.models.enums.TransactionNotificationContactType;
import com.itf.phatbooskiandroid.models.enums.UserRoleEnum;

public class Customer {

    @SerializedName("FullName")
    @Expose
    private String fullName;
    @SerializedName("DefaultPhoneNumber")
    @Expose
    private String defaultPhoneNumber;
    @SerializedName("SelectedPhoneNumber")
    @Expose
    private String selectedPhoneNumber;
    @SerializedName("GroupLevel")
    @Expose
    private String groupLevel;
    @SerializedName("GroupLevelID")
    @Expose
    private int groupLevelID;
    @SerializedName("CustomerID")
    @Expose
    private String customerID;
    @SerializedName("UserId")
    @Expose
    private int userId;
    @SerializedName("UserName")
    @Expose
    private String userName;
    @SerializedName("FirstName")
    @Expose
    private String firstName;
    @SerializedName("SecondName")
    @Expose
    private String secondName;
    @SerializedName("LastName")
    @Expose
    private String lastName;
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("MobilPhoneNumber")
    @Expose
    private String mobilPhoneNumber;
    @SerializedName("TaxNo")
    @Expose
    private String taxNo;
    @SerializedName("MobileNumbers")
    @Expose
    private List<MobileNumber> mobileNumbers = null;
    @SerializedName("UserRole")
    @Expose
    private UserRole userRole;
    @SerializedName("LastLoginInfo")
    @Expose
    private LastLoginInfo lastLoginInfo;
    @SerializedName("OTPSettings")
    @Expose
    private OTPSettings oTPSettings;
    @SerializedName("SecurityImageId")
    @Expose
    private String securityImageId;
    @SerializedName("SecurityMessage")
    @Expose
    private String securityMessage;
    @SerializedName("AuthorizationInfo")
    @Expose
    private AuthorizationInfo authorizationInfo;
    @SerializedName("BirhtDate")
    @Expose
    private String birhtDate;
    @SerializedName("CallCenterAuthorizationRoleId")
    @Expose
    private int callCenterAuthorizationRoleId;
    @SerializedName("PassportNo")
    @Expose
    private String passportNo;
    @SerializedName("IsMigrated")
    @Expose
    private boolean isMigrated;
    @SerializedName("RedirectToRegistrationFlow")
    @Expose
    private boolean redirectToRegistrationFlow;
    @SerializedName("IsPersonel")
    @Expose
    private boolean isPersonel;
    @SerializedName("IsStaff")
    @Expose
    private boolean isStaff;
    @SerializedName("LogonType")
    @Expose
    private LogonTypeEnum logonType;
    @SerializedName("RegisteredDate")
    @Expose
    private String registeredDate;
    @SerializedName("SecondFactorAuthenticationPreference")
    @Expose
    private int secondFactorAuthenticationPreference;
    @SerializedName("CustomerAddresses")
    @Expose
    private List<CustomerAddress> customerAddresses = null;
    @SerializedName("Salary")
    @Expose
    private double salary;
    @SerializedName("RelationshipManagerID")
    @Expose
    private String relationshipManagerID;
    @SerializedName("RelationshipManagerName")
    @Expose
    private String relationshipManagerName;
    @SerializedName("SystemDate")
    @Expose
    private String systemDate;
    @SerializedName("Employer")
    @Expose
    private String employer;
    @SerializedName("LoyaltyID")
    @Expose
    private String loyaltyID;
    @SerializedName("Title")
    @Expose
    private String title;
    @SerializedName("IsFirstTimeLogin")
    @Expose
    private boolean isFirstTimeLogin;
    @SerializedName("CustomerPreferredContactType")
    @Expose
    private TransactionNotificationContactType customerPreferredContactType;
    @SerializedName("UserPasswordInfo")
    @Expose
    private UserPasswordInfo userPasswordInfo;
    @SerializedName("CustomerSegment")
    @Expose
    private String customerSegment;
    @SerializedName("RoleName")
    @Expose
    private UserRoleEnum roleName;
    @SerializedName("CustomerType")
    @Expose
    private CustomerTypeEnum customerType;
    @SerializedName("CustomerSubType")
    @Expose
    private CustomerSubTypeEnum customerSubType;
    @SerializedName("CustomerStatus")
    @Expose
    private CustomerStatusEnum customerStatus;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDefaultPhoneNumber() {
        return defaultPhoneNumber;
    }

    public void setDefaultPhoneNumber(String defaultPhoneNumber) {
        this.defaultPhoneNumber = defaultPhoneNumber;
    }

    public String getSelectedPhoneNumber() {
        return selectedPhoneNumber;
    }

    public void setSelectedPhoneNumber(String selectedPhoneNumber) {
        this.selectedPhoneNumber = selectedPhoneNumber;
    }

    public String getGroupLevel() {
        return groupLevel;
    }

    public void setGroupLevel(String groupLevel) {
        this.groupLevel = groupLevel;
    }

    public int getGroupLevelID() {
        return groupLevelID;
    }

    public void setGroupLevelID(int groupLevelID) {
        this.groupLevelID = groupLevelID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobilPhoneNumber() {
        return mobilPhoneNumber;
    }

    public void setMobilPhoneNumber(String mobilPhoneNumber) {
        this.mobilPhoneNumber = mobilPhoneNumber;
    }

    public String getTaxNo() {
        return taxNo;
    }

    public void setTaxNo(String taxNo) {
        this.taxNo = taxNo;
    }

    public List<MobileNumber> getMobileNumbers() {
        return mobileNumbers;
    }

    public void setMobileNumbers(List<MobileNumber> mobileNumbers) {
        this.mobileNumbers = mobileNumbers;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public LastLoginInfo getLastLoginInfo() {
        return lastLoginInfo;
    }

    public void setLastLoginInfo(LastLoginInfo lastLoginInfo) {
        this.lastLoginInfo = lastLoginInfo;
    }

    public OTPSettings getOTPSettings() {
        return oTPSettings;
    }

    public void setOTPSettings(OTPSettings oTPSettings) {
        this.oTPSettings = oTPSettings;
    }

    public String getSecurityImageId() {
        return securityImageId;
    }

    public void setSecurityImageId(String securityImageId) {
        this.securityImageId = securityImageId;
    }

    public String getSecurityMessage() {
        return securityMessage;
    }

    public void setSecurityMessage(String securityMessage) {
        this.securityMessage = securityMessage;
    }

    public AuthorizationInfo getAuthorizationInfo() {
        return authorizationInfo;
    }

    public void setAuthorizationInfo(AuthorizationInfo authorizationInfo) {
        this.authorizationInfo = authorizationInfo;
    }

    public String getBirhtDate() {
        return birhtDate;
    }

    public void setBirhtDate(String birhtDate) {
        this.birhtDate = birhtDate;
    }

    public int getCallCenterAuthorizationRoleId() {
        return callCenterAuthorizationRoleId;
    }

    public void setCallCenterAuthorizationRoleId(int callCenterAuthorizationRoleId) {
        this.callCenterAuthorizationRoleId = callCenterAuthorizationRoleId;
    }

    public String getPassportNo() {
        return passportNo;
    }

    public void setPassportNo(String passportNo) {
        this.passportNo = passportNo;
    }

    public boolean isIsMigrated() {
        return isMigrated;
    }

    public void setIsMigrated(boolean isMigrated) {
        this.isMigrated = isMigrated;
    }

    public boolean isRedirectToRegistrationFlow() {
        return redirectToRegistrationFlow;
    }

    public void setRedirectToRegistrationFlow(boolean redirectToRegistrationFlow) {
        this.redirectToRegistrationFlow = redirectToRegistrationFlow;
    }

    public boolean isIsPersonel() {
        return isPersonel;
    }

    public void setIsPersonel(boolean isPersonel) {
        this.isPersonel = isPersonel;
    }

    public boolean isIsStaff() {
        return isStaff;
    }

    public void setIsStaff(boolean isStaff) {
        this.isStaff = isStaff;
    }

    public LogonTypeEnum getLogonType() {
        return logonType;
    }

    public void setLogonType(LogonTypeEnum logonType) {
        this.logonType = logonType;
    }

    public String getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(String registeredDate) {
        this.registeredDate = registeredDate;
    }

    public int getSecondFactorAuthenticationPreference() {
        return secondFactorAuthenticationPreference;
    }

    public void setSecondFactorAuthenticationPreference(int secondFactorAuthenticationPreference) {
        this.secondFactorAuthenticationPreference = secondFactorAuthenticationPreference;
    }

    public List<CustomerAddress> getCustomerAddresses() {
        return customerAddresses;
    }

    public void setCustomerAddresses(List<CustomerAddress> customerAddresses) {
        this.customerAddresses = customerAddresses;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getRelationshipManagerID() {
        return relationshipManagerID;
    }

    public void setRelationshipManagerID(String relationshipManagerID) {
        this.relationshipManagerID = relationshipManagerID;
    }

    public String getRelationshipManagerName() {
        return relationshipManagerName;
    }

    public void setRelationshipManagerName(String relationshipManagerName) {
        this.relationshipManagerName = relationshipManagerName;
    }

    public String getSystemDate() {
        return systemDate;
    }

    public void setSystemDate(String systemDate) {
        this.systemDate = systemDate;
    }

    public String getEmployer() {
        return employer;
    }

    public void setEmployer(String employer) {
        this.employer = employer;
    }

    public String getLoyaltyID() {
        return loyaltyID;
    }

    public void setLoyaltyID(String loyaltyID) {
        this.loyaltyID = loyaltyID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isIsFirstTimeLogin() {
        return isFirstTimeLogin;
    }

    public void setIsFirstTimeLogin(boolean isFirstTimeLogin) {
        this.isFirstTimeLogin = isFirstTimeLogin;
    }

    public TransactionNotificationContactType getCustomerPreferredContactType() {
        return customerPreferredContactType;
    }

    public void setCustomerPreferredContactType(TransactionNotificationContactType customerPreferredContactType) {
        this.customerPreferredContactType = customerPreferredContactType;
    }

    public UserPasswordInfo getUserPasswordInfo() {
        return userPasswordInfo;
    }

    public void setUserPasswordInfo(UserPasswordInfo userPasswordInfo) {
        this.userPasswordInfo = userPasswordInfo;
    }

    public String getCustomerSegment() {
        return customerSegment;
    }

    public void setCustomerSegment(String customerSegment) {
        this.customerSegment = customerSegment;
    }

    public UserRoleEnum getRoleName() {
        return roleName;
    }

    public void setRoleName(UserRoleEnum roleName) {
        this.roleName = roleName;
    }

    public CustomerTypeEnum getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerTypeEnum customerType) {
        this.customerType = customerType;
    }

    public CustomerSubTypeEnum getCustomerSubType() {
        return customerSubType;
    }

    public void setCustomerSubType(CustomerSubTypeEnum customerSubType) {
        this.customerSubType = customerSubType;
    }

    public CustomerStatusEnum getCustomerStatus() {
        return customerStatus;
    }

    public void setCustomerStatus(CustomerStatusEnum customerStatus) {
        this.customerStatus = customerStatus;
    }

}