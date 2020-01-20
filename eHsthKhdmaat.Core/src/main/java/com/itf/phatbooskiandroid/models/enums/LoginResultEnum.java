package com.itf.phatbooskiandroid.models.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zahmed on 4/7/2018.
 * Veripark Bahrain
 * http://www.veripark.com
 */

public enum LoginResultEnum {
    Undefined(0),
    HoldUser(1),
    WrongUserCode(2),
    WrongPasswordOrUserCode(3),
    LockedPassword(4),
    FirstLevelSuccess(5),
    WrongPin(6),
    UndefinedPin(7),
    LockedPin(8),
    SecondLevelSuccess(9),
    HasNoSecurityImage(10),
    SecurityImageSuccess(11),
    WrongSecurityImage(12),
    SecurityImageSelectSuccess(13),
    PinChangeSuccess(14),
    WrongOldPin(15),
    InvalidPin(16),
    PasswordChangeSuccess(17),
    WrongOldPassword(18),
    InvalidPassword(19),
    AccessRestrictionError(20),
    IPRestrictionError(21),
    GeographicIPRestrictionError(22),
    Successful(23),
    SecurityWarningConfirmed(24),
    ImageSelectionSucess(25),
    OTPSelectionSucess(26),
    OTPSecurityFail(27),
    OTPSecuritySuccess(28),
    OtpDeviceSync(29),
    OldPinUsedError(30),
    ValidationSuccess(31),
    CustomerInfoSuccess(32),
    CustomerInfoError(33),
    UserNameChangeSuccess(34),
    UserNameChangeError(35),
    UserNameUsedError(36),
    UserNameEqualsPassword(37),
    LoginTimeOutAfterEnrollment(38),
    SecretQuestionAnswerSuccess(39),
    SecretQuestionAnswerFail(40),
    PassiveUser(41),
    BlackListError(42),
    OTPSmsPasswordExpired(43),
    OTPSmsWrongPassword(44),
    OTPSoftWrongPassword(45),
    MasakFail(46),
    MasakSuccess(47),
    SPKFail(48),
    SPKSuccess(49),
    OTPHardWrongPassword(50),
    HardOTPSuccess(51),
    SmsOTPSuccess(52),
    SoftOTPSuccess(53),
    LoginByUserCode(54),
    LoginByCard(55),
    LoginByTCID(56),
    SecurityImageSelectFail(57),
    OTPSelectionFail(58),
    LogoutSuccess(59),
    RedirectURL(60),
    DateHourRestrictionError(61),
    InstantPinIncompatibleCard(62),
    InstantPasswordError(63),
    InstantPasswordException(64),
    CustomerBranchIPAccessDenied(65),
    HasNoAccount(66),
    AgeRestriction(67),
    BirhtDateValidationFail(68),
    BirthDateValidationSuccess(69),
    SoftTokenFail(70),
    SoftTokenSuccess(71),
    CheckPasswordWithBankTechnique(72),
    CheckPasswordWithAPI(73),
    InactiveUser(74),
    BlockedUser(75),
    Unsubscribed(76),
    TimeRestriction(77),
    DayRestriction(78),
    TermsAndConditionsSuccess(79),
    TermsAndConditionsError(80);

    private int value;
    private static Map map = new HashMap<>();

    private LoginResultEnum(int value) {
        this.value = value;
    }

    static {
        for (LoginResultEnum myenum : LoginResultEnum.values()) {
            map.put(myenum.value, myenum);
        }
    }

    public static LoginResultEnum valueOf(int pageType) {
        return (LoginResultEnum) map.get(pageType);
    }

    public int getValue() {
        return value;
    }
}
