package service.local;

import org.json.JSONException;
import java.io.IOException;

import helpers.JSONHelper;
import interfaces.ServiceCallListener;
import utilities.JSONParser;
import utilities.LogUtils;

/**
 * Created by zahmed on 4/9/2018.
 * Veripark Bahrain
 * http://www.veripark.com
 */

public class LocalDataHelper<RES> {
    final String BaseURL = "json/transactions/";
    public void execRequest(Class<RES> _responseClass,
                            final ServiceCallListener _serviceListener, final String transactionName)  {

        final String URLString = BaseURL + transactionName.toLowerCase() + "/response.json";
        JSONParser rdrObj = new JSONParser();
        try {
            String jsonString = rdrObj.getJSONStringFromAsset(URLString);
            RES response = JSONHelper.jsonStringIntoObject(jsonString, _responseClass);
            _serviceListener.onResponse(response);

            LogUtils.LOGD(LogUtils.makeLogTag(this.getClass()), jsonString);
        } catch (IOException e) {
            _serviceListener.onFailure(e.getStackTrace().toString());
            LogUtils.LOGE(LogUtils.makeLogTag(this.getClass()),  e.getStackTrace().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
