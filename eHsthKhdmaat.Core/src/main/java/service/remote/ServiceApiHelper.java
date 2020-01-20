package service.remote;

import com.google.gson.JsonObject;
import com.ift.ehsthkhdmaatcore.BuildConfig;

import org.json.JSONObject;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;

import interfaces.ServiceCallListener;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import service.remote.wrapperclass.ApiUtils;
import service.remote.wrapperclass.RetrofitAPICallback;
import retrofit2.Call;


/**
 * Created by zahmed on 4/9/2018.
 */

public class ServiceApiHelper {
    final String BaseURL = BuildConfig.URL_BASE;

    public <REQ, RES> void execRequest(Type _responseClass,
                                       final ServiceCallListener _serviceListener, final String transactionName) {

        //final String URLString = BaseURL + transactionName.toLowerCase() + "/response.json";
        final String URLString = BaseURL + transactionName.toLowerCase();
        Call<Object> call = ApiUtils.getAPIService(BaseURL).getTransactionData(URLString);

        call.clone().enqueue(new RetrofitAPICallback(_serviceListener, _responseClass));
    }

    public <REQ, RES> void execPostRequest(Class<RES> _responseClass,
                                           final ServiceCallListener _serviceListener, final String transactionName, REQ objData) {

        //final String URLString = BaseURL + transactionName.toLowerCase() + "/response.json";
        final String URLString = BaseURL + transactionName.toLowerCase();
        Call<Object> call = ApiUtils.getAPIService(BaseURL).postServiceCall(URLString, objData);

        call.clone().enqueue(new RetrofitAPICallback(_serviceListener, _responseClass));
    }

    public <REQ, RES> void execPostArrayRequest(Class<RES> _responseClass,
                                                final ServiceCallListener _serviceListener, final String transactionName, ArrayList<REQ> objData) {

        //final String URLString = BaseURL + transactionName.toLowerCase() + "/response.json";
        final String URLString = BaseURL + transactionName.toLowerCase();
        Call<Object> call = ApiUtils.getAPIService(BaseURL).postArrayServiceCall(URLString, objData);

        call.clone().enqueue(new RetrofitAPICallback(_serviceListener, _responseClass));
    }

    public void cancelRequest() {
        //call.cancel();
    }

    public <REQ, RES> void execDeleteRequest(Class<RES> _responseClass,
                                             final ServiceCallListener _serviceListener, final String transactionName) {

        //final String URLString = BaseURL + transactionName.toLowerCase() + "/response.json";
        final String URLString = BaseURL + transactionName.toLowerCase();
        Call<Object> call = ApiUtils.getAPIService(BaseURL).deleteServiceCall(URLString);

        call.clone().enqueue(new RetrofitAPICallback(_serviceListener, _responseClass));
    }

    public static void cancelAllRequest() {
        //HttpClient.getClient(BuildConfig.WEBSERVERURL).dispatcher().cancelAll();
    }

    public <REQ, RES> void execUploadRequest(Class<RES> _responseClass,
                                             final ServiceCallListener _serviceListener, String pathUri, String mediaFormat, final String transactionName) {

        /*//final String URLString = BaseURL + transactionName.toLowerCase() + "/response.json";
        final String URLString = BaseURL + transactionName.toLowerCase();

        //pass it like this
        File file = new File(pathUri);
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);

        // MultipartBody.Part is used to send also the actual file name
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("files", file.getName(), requestFile);

        // add another part within the multipart request
        RequestBody requestKeyName = RequestBody.create(MediaType.parse("multipart/form-data"), "files");*/

        String split[] = transactionName.split("/");

        File file = new File(pathUri);

        // Parsing any Media type file
        //RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/png"), file);
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/" + mediaFormat + ""), file);
        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("files", file.getName(), requestBody);

        //Call<Object> call = ApiUtils.getAPIService(BaseURL).uploadPetImage(split[2], Integer.parseInt(split[3]), Boolean.parseBoolean(split[4]), fileToUpload);
        Call<Object> call = ApiUtils.getAPIService(BaseURL).uploadFiles(transactionName, fileToUpload);
        call.clone().enqueue(new RetrofitAPICallback(_serviceListener, _responseClass));
    }

}
