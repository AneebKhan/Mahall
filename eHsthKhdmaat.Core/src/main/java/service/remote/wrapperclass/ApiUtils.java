package service.remote.wrapperclass;


import service.remote.interfaces.APIService;

/**
 * Created by zahmed on 2/28/2018.
 */

public class ApiUtils {

    public static APIService getAPIService(String baseUrl) {

        return RetrofitClient.getClient(baseUrl).create(APIService.class);
    }
}
