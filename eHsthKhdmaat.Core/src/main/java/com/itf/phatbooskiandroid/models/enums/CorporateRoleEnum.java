package com.itf.phatbooskiandroid.models.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zahmed on 4/4/2018.
 * Veripark Bahrain
 * http://www.veripark.com
 */

public enum CorporateRoleEnum {
    Admin(0),
    Poster(1),
    Approver(2),
    Inquirer(3),
    BatchUser(4);

    private int value;
    private static Map map = new HashMap<>();

    private CorporateRoleEnum(int value) {
        this.value = value;
    }

    static {
        for (CorporateRoleEnum myenum : CorporateRoleEnum.values()) {
            map.put(myenum.value, myenum);
        }
    }

    public static CorporateRoleEnum valueOf(int pageType) {
        return (CorporateRoleEnum) map.get(pageType);
    }

    public int getValue() {
        return value;
    }
}
