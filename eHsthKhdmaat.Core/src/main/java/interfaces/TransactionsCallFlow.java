package interfaces;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by zahmed on 4/9/2018.
 * Veripark Bahrain
 * http://www.veripark.com
 */

public interface TransactionsCallFlow<REQ,RES> {
    public void executeTransaction(ServiceCallListener serviceCallListener, String tranasctionName, REQ request, RES response);
    public void getTransactionData(ServiceCallListener serviceCallListener, String transactionName, Type responseClass);
    public void postTransactionData(ServiceCallListener serviceCallListener, String transactionName, REQ requestClass, Class<RES> responseClass);
    public void postTransactionArrayData(ServiceCallListener serviceCallListener, String transactionName, ArrayList<REQ> requestClass, Class<RES> responseClass);
    public void deleteTransactionData(ServiceCallListener serviceCallListener, String transactionName, Class<RES> responseClass);
    public void uploadTransactionData(ServiceCallListener serviceCallListener, String pathUri, String mediaFormat, String transactionName, Class<RES> responseClass);
}
