package com.itf.phatbooskiandroid.models.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zahmed on 4/7/2018.
 * Veripark Bahrain
 * http://www.veripark.com
 */

public enum OTPTypeEnum {
    Undefined(0),
    SMSOTP(1),
    MobileSignature(2),
    SoftOTP(3),
    HardOTP(4),
    DigitalSignature(5);

    private int value;
    private static Map map = new HashMap<>();

    private OTPTypeEnum(int value) {
        this.value = value;
    }

    static {
        for (OTPTypeEnum myenum : OTPTypeEnum.values()) {
            map.put(myenum.value, myenum);
        }
    }

    public static OTPTypeEnum valueOf(int pageType) {
        return (OTPTypeEnum) map.get(pageType);
    }

    public int getValue() {
        return value;
    }
}
