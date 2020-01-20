package managers;

import com.ift.ehsthkhdmaatcore.BuildConfig;
import com.ift.ehsthkhdmaatcore.R;
import com.itf.phatbooskiandroid.classes.CustomApplication;
import com.itf.phatbooskiandroid.enums.BuildType;
import com.itf.phatbooskiandroid.models.menu.ChildMenuItem;
import com.itf.phatbooskiandroid.models.uimodels.base.BaseTransactionFlow;
import com.itf.phatbooskiandroid.models.uimodels.common.Item;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


import interfaces.ServiceCallListener;
import interfaces.TransactionsCallFlow;

import keys.GetTransactionKeys;
import service.local.LocalDataHelper;
import service.remote.ServiceApiHelper;
import utilities.ToastUtilities;

/**
 * Created by zahmed on 8/12/2018.
 * Veripark Bahrain
 * http://www.veripark.com
 */

public class TransactionManager<REQ, RES> implements TransactionsCallFlow<REQ, RES> {
    private static TransactionManager instance = null;

    // variable of type String
    public String s;

    List<ChildMenuItem> menuList;
    static List<String> authenticCallsList = new ArrayList<String>();

    // private constructor restricted to this class itself
    private TransactionManager() {
        s = "Hello I am a string part of Singleton class";
        addAuthenticateCallsData();
    }

    // static method to create instance of Singleton class
    public static TransactionManager getInstance() {
        if (instance == null)
            instance = new TransactionManager();

        return instance;
    }

    @Override
    public void executeTransaction(ServiceCallListener serviceCallListener, String transactionName, REQ request, RES response) {
        BaseTransactionFlow transactionFlow = new BaseTransactionFlow();
        Item<REQ, RES> item = new Item<REQ, RES>();
        item.setRequest(request);
        item.setResponse(response);
        transactionFlow.setItem(item);

        if (BuildConfig.BUILD_TYPE.toLowerCase().equalsIgnoreCase(BuildType.OFFLINE.toString())) {
            //call data from DB
            LocalDataHelper localDataHelper = new LocalDataHelper();
            localDataHelper.execRequest(transactionFlow.getClass(), serviceCallListener, transactionName);
        } else {
            ServiceApiHelper serviceApiHelper = new ServiceApiHelper();
            serviceApiHelper.execRequest(transactionFlow.getClass(), serviceCallListener, transactionName);
        }
    }

    @Override
    public void getTransactionData(ServiceCallListener serviceCallListener, String transactionName, Type responseClass) {

        /*if (BuildConfig.BUILD_TYPE.equalsIgnoreCase(BuildType.OFFLINE.toString())) {
            //call data from DB
            LocalDataHelper localDataHelper = new LocalDataHelper();
            localDataHelper.execRequest(responseClass, serviceCallListener, transactionName);
        } else {*/
        ServiceApiHelper serviceApiHelper = new ServiceApiHelper();
        serviceApiHelper.execRequest(responseClass, serviceCallListener, transactionName);
        //}
    }

    @Override
    public void postTransactionData(ServiceCallListener serviceCallListener, String transactionName, REQ requestObj, Class<RES> responseClass) {

        if (ApplicationStateManager.getInstance().getIsAuthenticated() == false && checkTransactionIsAuthentic(transactionName)) {
            serviceCallListener.onFailure(new Throwable(CustomApplication.getContext().getString(R.string.error_transaction_not_authentic)));
        } else {
            ServiceApiHelper serviceApiHelper = new ServiceApiHelper();
            serviceApiHelper.execPostRequest(responseClass, serviceCallListener, transactionName, requestObj);
        }
    }

    @Override
    public void postTransactionArrayData(ServiceCallListener serviceCallListener, String transactionName, ArrayList<REQ> requestObj, Class<RES> responseClass) {
        if (ApplicationStateManager.getInstance().getIsAuthenticated() == false && checkTransactionIsAuthentic(transactionName)) {
            serviceCallListener.onFailure(new Throwable(CustomApplication.getContext().getString(R.string.error_transaction_not_authentic)));
        } else {
            ServiceApiHelper serviceApiHelper = new ServiceApiHelper();
            serviceApiHelper.execPostArrayRequest(responseClass, serviceCallListener, transactionName, requestObj);
        }
    }

    @Override
    public void deleteTransactionData(ServiceCallListener serviceCallListener, String transactionName, Class<RES> responseClass) {
        if (ApplicationStateManager.getInstance().getIsAuthenticated() == false && checkTransactionIsAuthentic(transactionName)) {
            serviceCallListener.onFailure(new Throwable(CustomApplication.getContext().getString(R.string.error_transaction_not_authentic)));
        } else {
            ServiceApiHelper serviceApiHelper = new ServiceApiHelper();
            serviceApiHelper.execDeleteRequest(responseClass, serviceCallListener, transactionName);
        }
    }

    @Override
    public void uploadTransactionData(ServiceCallListener serviceCallListener, String pathUri, String mediaFormat, String transactionName, Class<RES> responseClass) {
        if (ApplicationStateManager.getInstance().getIsAuthenticated() == false && checkTransactionIsAuthentic(transactionName)) {
            serviceCallListener.onFailure(new Throwable(CustomApplication.getContext().getString(R.string.error_transaction_not_authentic)));
        } else {
            ServiceApiHelper serviceApiHelper = new ServiceApiHelper();
            serviceApiHelper.execUploadRequest(responseClass, serviceCallListener, pathUri, mediaFormat, transactionName);
        }
    }

/*  @Override
  public void uploadTransactionData(ServiceCallListener serviceCallListener, String pathUri, String transactionName, Class<RES> responseClass) {
    ServiceApiHelper serviceApiHelper = new ServiceApiHelper();
    serviceApiHelper.execUploadRequest(responseClass, serviceCallListener, pathUri, transactionName);
  }*/

    private void addAuthenticateCallsData() {
        authenticCallsList.add(GetTransactionKeys.ADD_FAVOURITE);
        authenticCallsList.add(GetTransactionKeys.REMOVE_FAVOURITE);
        authenticCallsList.add(GetTransactionKeys.SUBMIT_REVIEW);
    }

    private boolean checkTransactionIsAuthentic(String transactionName) {

        for (String item : authenticCallsList) {
            if (item.toLowerCase().toString().equals(transactionName.toLowerCase().toString())) {
                return true;
            }
        }

        return false;
    }

}
