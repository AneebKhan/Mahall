package com.itf.phatbooskiandroid.models.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zahmed on 4/7/2018.
 * Veripark Bahrain
 * http://www.veripark.com
 */

public enum UserRoleEnum {
    Admin(0),
    Poster(1),
    Approver(2),
    Inquirer(3),
    BatchUser(4),
    Retail(5);

    private int value;
    private static Map map = new HashMap<>();

    private UserRoleEnum(int value) {
        this.value = value;
    }

    static {
        for (UserRoleEnum myenum : UserRoleEnum.values()) {
            map.put(myenum.value, myenum);
        }
    }

    public static UserRoleEnum valueOf(int pageType) {
        return (UserRoleEnum) map.get(pageType);
    }

    public int getValue() {
        return value;
    }
}
