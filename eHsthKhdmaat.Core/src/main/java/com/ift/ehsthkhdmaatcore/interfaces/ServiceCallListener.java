package com.ift.ehsthkhdmaatcore.interfaces;

/**
 * Created by zahmed on 4/9/2018.
 * Veripark Bahrain
 * http://www.veripark.com
 */

public interface ServiceCallListener {
    <T> void onFailure(T errordetail);
    <T> void onResponse(T response);
}
