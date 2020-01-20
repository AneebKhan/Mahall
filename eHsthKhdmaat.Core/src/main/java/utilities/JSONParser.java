package utilities;

import org.json.JSONArray;
import org.json.JSONException;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import com.itf.phatbooskiandroid.classes.CustomApplication;

/**
 * Created by zahmed on 12/11/2018.
 * ITF
 */
public class JSONParser {

    public JSONParser() {

    }

    public JSONArray getJSONFromUrl(String url) throws JSONException, IOException {

            HttpURLConnection.setFollowRedirects(true);
            final HttpURLConnection connection = (HttpURLConnection) new URL(
                    url).openConnection();
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        connection.setDoOutput(false);
        connection.setReadTimeout(20000);
        connection.setRequestProperty("Connection", "keep-alive");
        connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:16.0) Gecko/20100101 Firefox/16.0");
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(5000);

            if (connection.getResponseCode() == 200) {
                BufferedInputStream in = new BufferedInputStream(connection.getInputStream());
                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    System.out.println(responseCode);
                }
                StringBuffer buffer = new StringBuffer();
                int chars_read;
                //int total = 0;
                while ((chars_read = in.read()) != -1)
                {
                    char g = (char) chars_read;
                    buffer.append(g);
                }
                final String page = buffer.toString();

                final JSONArray jArr = new JSONArray(buffer.toString());
                return jArr;
            } else {
                throw new RuntimeException("Unable to connect host to load json, please check url.");
            }
    }

    public JSONArray getJSONArrayFromAsset(String filenameWithExtension) throws IOException, JSONException {
        String json = null;

        InputStream is = CustomApplication.getContext().getAssets().open(filenameWithExtension);
        int size = is.available();
        byte[] buffer = new byte[size];
        is.read(buffer);
        is.close();
        json = new String(buffer, "UTF-8");

        JSONArray jArr = new JSONArray(json);
        return jArr;
    }

    public String getJSONStringFromAsset(String filenameWithExtension) throws IOException, JSONException {
        String json = null;

        InputStream is = CustomApplication.getContext().getAssets().open(filenameWithExtension);
        int size = is.available();
        byte[] buffer = new byte[size];
        is.read(buffer);
        is.close();
        json = new String(buffer, "UTF-8");

        return json;
    }
}
