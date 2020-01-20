package com.itf.phatbooskiandroid.models.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zahmed on 4/7/2018.
 * Veripark Bahrain
 * http://www.veripark.com
 */

public enum EmailCategoryEnum {
    Complaint(0),
    Query(1),
    Feedback(2);

    private int value;
    private static Map map = new HashMap<>();

    private EmailCategoryEnum(int value) {
        this.value = value;
    }

    static {
        for (EmailCategoryEnum myenum : EmailCategoryEnum.values()) {
            map.put(myenum.value, myenum);
        }
    }

    public static EmailCategoryEnum valueOf(int pageType) {
        return (EmailCategoryEnum) map.get(pageType);
    }

    public int getValue() {
        return value;
    }
}
