package service.remote.wrapperclass;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import service.remote.sharedclass.HttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by zahmed on 2/28/2018.
 */

public class RetrofitClient {

    private static Retrofit retrofit = null;

    public static Retrofit getClient(String baseUrl) {
        if (retrofit==null) {

            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.setPrettyPrinting();
            gsonBuilder.excludeFieldsWithoutExposeAnnotation();
            gsonBuilder.serializeNulls();
            Gson gson = gsonBuilder.create();

            Retrofit.Builder builder =
                    new Retrofit.Builder()
                            .baseUrl(baseUrl)
                            .addConverterFactory(GsonConverterFactory
                                    .create(gson));

            retrofit = builder.client(HttpClient.getClient(baseUrl)).build();
        }
        return retrofit;
    }
}
