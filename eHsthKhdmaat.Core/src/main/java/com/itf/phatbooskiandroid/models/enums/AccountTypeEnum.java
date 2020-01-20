package com.itf.phatbooskiandroid.models.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zahmed on 4/7/2018.
 * Veripark Bahrain
 * http://www.veripark.com
 */

public enum AccountTypeEnum {
    Undefined(0),
    Demand(1),
    Term(2),
    Credit(3),
    Investment(4),
    Cheque(5),
    All(6),
    Loan(7),
    Current(8),
    Savings(9),
    GOL(10);

    private int value;
    private static Map map = new HashMap<>();

    private AccountTypeEnum(int value) {
        this.value = value;
    }

    static {
        for (AccountTypeEnum myenum : AccountTypeEnum.values()) {
            map.put(myenum.value, myenum);
        }
    }

    public static AccountTypeEnum valueOf(int pageType) {
        return (AccountTypeEnum) map.get(pageType);
    }

    public int getValue() {
        return value;
    }
}
