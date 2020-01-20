package service.remote.interfaces;

import android.database.Observable;

import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;
import service.remote.wrapperclass.RetrofitAPICallback;

/**
 * Created by zahmed on 4/9/2018.
 */

public interface APIService<REQ> {

    //following Principles of REST created following service call types

    @GET
    Call<Object> getTransactionData(@Url String url);

    @GET
    Call<Object> getData(@Url String url, @Query("v") String version, @Query("transactionName") String cultureCode, @Query("channel") int transactionName);

    @GET
    Call<Object> getData(@Url String url, @Query("v") String version, @Query("cultureCode") String cultureCode);

    @GET
    Call<Object> getData(@Url String url, @Query("v") String version);

    @GET
    void getData(@Url String url, @Query("v") String version, @Query("cultureCode") String cultureCode, RetrofitAPICallback<Object> callback);

    @POST
    Call<Object> postServiceCall(@Url String url, @Body Object dataObject);

    @POST
    Call<Object> postArrayServiceCall(@Url String url, @Body ArrayList dataObject);

    @DELETE
    Call<Object> deleteServiceCall(@Url String url);

    @PUT
    Call<Object> putServiceCall(@Url String url, @Body Object dataObject);

    @DELETE
    Call<Object> deleteServiceCall(@Url String url, @Body Object dataObject);
    //endregion unused service calls

    @Multipart
    @POST
    Call<Object> uploadDocuments(@Part MultipartBody.Part image, @Part("files") RequestBody name);

    @Multipart
    @POST("Environment/UploadFiles")
    Call<Object> uploadImageFile(@Part MultipartBody.Part file);

    @Multipart
    @POST
    Call<Object> uploadFiles(@Url String url, @Part MultipartBody.Part file);

    @Multipart
    @POST("Environment/UploadPetImagesFilesAsync/{customerId}/{petId}/{isProfile}")
    Call<Object> uploadPetImage(@Path("customerId") String customerId, @Path("petId") int petId, @Path("isProfile") boolean isProfile, @Part MultipartBody.Part file);

    /*@Multipart
    @POST("Environment/UploadPetImagesFilesAsync/{customerId}/{petId}/{isProfile}")
    Call<Object> uploadPetImage(@Path("customerId") String customerId, @Path("petId") int petId, @Path("isProfile") boolean isProfile, @Part MultipartBody.Part file);*/

}
