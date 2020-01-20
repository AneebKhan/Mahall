package com.itf.phatbooskiandroid.models.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zahmed on 4/7/2018.
 * Veripark Bahrain
 * http://www.veripark.com
 */

public enum AccountSubTypeEnum {
    Undefined(0),
    TopInterest(1),
    MarginTest(2),
    EmployeeAccount(3),
    FundAccount(4),
    StandartMaturityAccount(5),
    ValueDateAccount(6),
    IncreasingInterestedAccount(7),
    StaggeredAccount(8),
    ProgressiveAccount(9),
    IndexedAccount(10),
    CreditSyndicatedAccount(11),
    FundPacket(12),
    BlockedAccount(13),
    BlockedForeignCurrencyAccount(14);

    private int value;
    private static Map map = new HashMap<>();

    private AccountSubTypeEnum(int value) {
        this.value = value;
    }

    static {
        for (AccountSubTypeEnum myenum : AccountSubTypeEnum.values()) {
            map.put(myenum.value, myenum);
        }
    }

    public static AccountSubTypeEnum valueOf(int pageType) {
        return (AccountSubTypeEnum) map.get(pageType);
    }

    public int getValue() {
        return value;
    }
}
