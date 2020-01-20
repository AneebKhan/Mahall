package service.remote.sharedclass;

import com.ift.ehsthkhdmaatcore.BuildConfig;

import java.io.File;
import java.io.IOException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.util.concurrent.TimeUnit;
import com.itf.phatbooskiandroid.classes.CustomApplication;
import configurations.HttpConfig;
import utilities.NetworkUtils;
import okhttp3.Cache;
import okhttp3.CertificatePinner;
import okhttp3.Interceptor;
import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import static com.itf.phatbooskiandroid.classes.CustomApplication.getContext;
import static com.itf.phatbooskiandroid.classes.CustomApplication.sTLSSocketFactory;


/**
 * Created by zahmed on 2/28/2018.
 */

public class HttpClient {

    private static OkHttpClient okHttpClient = null;

    public static OkHttpClient getClient(String baseUrl) {
        if (okHttpClient == null) {

            CertificatePinner certificatePinner = new CertificatePinner.Builder()
                    .add("vc6androidapp.veripark.app", "sha256/cpO1hMkZBRWkUDAPCF3frcUjJ8IvlRsc5XFfKFVdUj4=")
                    .build();

            HttpLoggingInterceptor logging = new HttpLoggingInterceptor().setLevel(BuildConfig.DEBUG
                    ? HttpLoggingInterceptor.Level.BODY
                    : HttpLoggingInterceptor.Level.NONE);

            File cacheDir = new File(CustomApplication.getContext().getCacheDir(), HttpConfig.RESPONSE_CACHE);

            Cache cache = new Cache(CustomApplication.getContext().getCacheDir(), HttpConfig.RESPONSE_CACHE_SIZE);

            // init cookie manager
            CookieHandler cookieHandler = new CookieManager();

            okHttpClient = (baseUrl.contains("https"))
                    ? new OkHttpClient.Builder()
                    .cookieJar(new JavaNetCookieJar(cookieHandler))
                    .cache(cache)
                    .readTimeout(160, TimeUnit.SECONDS)
                    .connectTimeout(160, TimeUnit.SECONDS)
                    .certificatePinner(certificatePinner)
                    .sslSocketFactory(sTLSSocketFactory, sTLSSocketFactory.systemDefaultTrustManager())
                    .addInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
                    .addInterceptor(logging)
                    /*.cache(cache)*/
                    .build()
                    : new OkHttpClient.Builder()
                    .cookieJar(new JavaNetCookieJar(cookieHandler))
                    .readTimeout(160, TimeUnit.SECONDS)
                    .connectTimeout(160, TimeUnit.SECONDS)
                    .cache(cache)
                    .addInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
                   /* .addNetworkInterceptor(mCacheControlInterceptor)
                    .addInterceptor(mCacheControlInterceptor)*/
                    .addInterceptor(logging)
                    /*.cache(cache)*/
                    .build();
        }
        return okHttpClient;
    }

    private static final Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            int maxAge = 2419200; // read from cache for 4-weeks
            int maxStale = 2419200; // tolerate 4-weeks stale

            Response originalResponse = chain.proceed(chain.request());
            if (NetworkUtils.isNetworkAvailable(getContext())) {
                return originalResponse.newBuilder()
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .build();
            } else {
                return originalResponse.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .build();
            }
        }
    };

}
