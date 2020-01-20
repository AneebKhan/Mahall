package managers;

import android.content.Context;
import android.content.SharedPreferences;
import com.itf.phatbooskiandroid.classes.CustomApplication;

/**
 * Created by zahmed on 12/11/2018.
 * ITF
 */

public class ResourceManager {
    private static final String SETTINGS_NAME = "resource_list";
    private static ResourceManager sSharedPrefs;
    private SharedPreferences mPref;
    private SharedPreferences.Editor mEditor;

    private ResourceManager(Context context) {
        mPref = context.getSharedPreferences(SETTINGS_NAME, Context.MODE_PRIVATE);
    }

    public static ResourceManager getInstance(Context context) {
        if (sSharedPrefs == null) {
            sSharedPrefs = new ResourceManager(context.getApplicationContext());
        }
        return sSharedPrefs;
    }

    public static ResourceManager getInstance() {
        if (sSharedPrefs != null) {
            return sSharedPrefs;
        }
        else
        {
            return getInstance(CustomApplication.getContext());
        }
    }

    public String getString(String key, String defaultValue) {
        return mPref.getString(key  + "+" + CustomApplication.Current_Culture, defaultValue);
    }

    public String getString(String key) {
        return mPref.getString(key + "+" + CustomApplication.Current_Culture, null);
    }

    public boolean setString(String key, String value) {
        return  mPref.edit().putString(key + "+" + CustomApplication.Current_Culture, value).commit();
    }

    /*public void fetchResources(ServiceCallListener serviceCallListener)
    {
        TransactionManager<ResourceList,ResourceList> trManager = TransactionManager.getInstance();
        trManager.getTransactionData(serviceCallListener,GET_RESOURCE_LIST,ResourceList.class);
    }

    public void saveResourcesToPreference(java.util.List<List> resourceList)
    {
        //clear all keys from shared preferences before inserting new list
        clear();

        edit();
        for (List list:
             resourceList) {
            mEditor.putString(list.getKey() + "+" + list.getCulture(), list.getValue());
        }
        commit();
    }*/


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
