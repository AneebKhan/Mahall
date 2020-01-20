package service.remote.wrapperclass;

import android.util.Log;

import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.net.SocketTimeoutException;
import interfaces.ServiceCallListener;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

/**
 * Created by zahmed on 4/9/2018.
 */


public class RetrofitAPICallback<T> implements Callback<T> {
    private ServiceCallListener mCallListener;
    private Type mType;

    public RetrofitAPICallback(ServiceCallListener callListener,  Type type) {
        super();
        mCallListener = callListener;
        mType = type;
    }


    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful()) {
            //   User user = response.body();
            Gson gson = new Gson();
            String json = gson.toJson(response.body());

            mCallListener.onResponse(gson.fromJson(json,mType));
        } else {
            int statusCode = response.code();
            // handle request errors yourself
            ResponseBody errorBody = response.errorBody();

            mCallListener.onFailure(new Throwable(errorBody.toString()));
        }

    }

    @Override
    public void onFailure(Call<T> call, Throwable throwable) {
        // handle execution failures like no internet connectivity

        if(call.isCanceled()) {
            Log.e(TAG, "request was aborted");
        }else {
            Log.e(TAG, "Unable to submit post to API.");


            mCallListener.onFailure(throwable);

            if (throwable != null && throwable instanceof SocketTimeoutException) {
                //showToast("Socket Timeout. Please Try Again.");
            }
        }
    }
}
