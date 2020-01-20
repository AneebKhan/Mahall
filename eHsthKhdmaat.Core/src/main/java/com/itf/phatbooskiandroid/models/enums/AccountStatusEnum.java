package com.itf.phatbooskiandroid.models.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zahmed on 4/7/2018.
 * Veripark Bahrain
 * http://www.veripark.com
 */

public enum AccountStatusEnum {
    Undefined(0),
    IncompleteNewAccount(1),
    Active(2),
    Inactive(3),
    Dormant(4),
    PendingClosed(5),
    Closed(6),
    Matured(7),
    TaggedforClosure(8),
    PostTransactionsOnlywithOverride(9),
    New(10),
    PastDue(11),
    Frozen(12),
    PaidOff(13),
    FullyChargedOff(14),
    PartiallyChargedOff(15),
    InFullLitigation(16),
    InPartialLitigation(17),
    PendingforClosure(18),
    OverDue(19),
    NonAccruingandAcceptingPayments(20),
    NonAccruingAndNotAcceptingPayments(21),
    PartiallyCONonAccruAcceptingPmt(22),
    PartiallyCONonAccruNotAcceptingPmt(23),
    Escheated(24),
    Incomplete(25),
    Locked(26),
    Unfunded(27);


    private int value;
    private static Map map = new HashMap<>();

    private AccountStatusEnum(int value) {
        this.value = value;
    }

    static {
        for (AccountStatusEnum myenum : AccountStatusEnum.values()) {
            map.put(myenum.value, myenum);
        }
    }

    public static AccountStatusEnum valueOf(int pageType) {
        return (AccountStatusEnum) map.get(pageType);
    }

    public int getValue() {
        return value;
    }
}
