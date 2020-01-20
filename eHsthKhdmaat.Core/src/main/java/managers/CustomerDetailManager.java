package managers;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.itf.phatbooskiandroid.classes.CustomApplication;


/**
 * Created by zahmed on 12/17/2018.
 * ITF
 */


public class CustomerDetailManager <CUST> {
    private static final String SETTINGS_NAME = "cusotmer_detail";
    private String CUSTOMER_DETAIL_KEY = "customer_detail";
    private static CustomerDetailManager sSharedPrefs;
    private SharedPreferences mPref;
    private SharedPreferences.Editor mEditor;
    private CUST CustomerObject;
    private Class<CUST> type;

    private CustomerDetailManager(Context context, Class<CUST> type) {
        mPref = context.getSharedPreferences(SETTINGS_NAME, Context.MODE_PRIVATE);
        this.type = type;
    }

    public static <CUST>  CustomerDetailManager getInstance(Context context, Class<CUST> type) {
        if (sSharedPrefs == null) {
            sSharedPrefs = new CustomerDetailManager(context.getApplicationContext(),type);
        }
        return sSharedPrefs;
    }

    public static <CUST> CustomerDetailManager getInstance(Class<CUST> type) {
        if (sSharedPrefs != null) {
            return sSharedPrefs;
        }
        else
        {
            return getInstance(CustomApplication.getContext(),type);
        }
    }

    public void setCustomerDetail(CUST customerDetail)
    {
        Gson gson = new Gson();
        String json = gson.toJson(customerDetail);
        //clear all keys from shared preferences before inserting new list
        clear();

        edit();
        mEditor.putString(CUSTOMER_DETAIL_KEY, json);
        commit();
    }

    public CUST getCustomerDetail()
    {
        Gson gson = new Gson();
        String json = mPref.getString(CUSTOMER_DETAIL_KEY, "");
        if(CustomerObject == null)
            CustomerObject = gson.fromJson(json, type);
        return CustomerObject;
    }

    public Class<CUST> getType() {
        return this.type;
    }

    public void edit() {
        mEditor = mPref.edit();
    }

    public void commit() {
        mEditor.commit();
        mEditor = null;
    }

    public void clear() {
        doEdit();
        mEditor.clear();
        doCommit();
    }

    private void doEdit() {
        if (mEditor == null) {
            mEditor = mPref.edit();
        }
    }

    private void doCommit() {
        if (mEditor != null) {
            mEditor.commit();
            mEditor = null;
        }
    }
}
