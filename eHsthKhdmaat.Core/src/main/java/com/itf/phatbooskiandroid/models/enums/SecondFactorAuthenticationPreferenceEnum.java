package com.itf.phatbooskiandroid.models.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zahmed on 4/7/2018.
 * Veripark Bahrain
 * http://www.veripark.com
 */

public enum SecondFactorAuthenticationPreferenceEnum {
    Undefined(0),
    Always(1),
    LocationChanged(2),
    Never(3);

    private int value;
    private static Map map = new HashMap<>();

    private SecondFactorAuthenticationPreferenceEnum(int value) {
        this.value = value;
    }

    static {
        for (SecondFactorAuthenticationPreferenceEnum myenum : SecondFactorAuthenticationPreferenceEnum.values()) {
            map.put(myenum.value, myenum);
        }
    }

    public static SecondFactorAuthenticationPreferenceEnum valueOf(int pageType) {
        return (SecondFactorAuthenticationPreferenceEnum) map.get(pageType);
    }

    public int getValue() {
        return value;
    }
}
