package utilities;

import com.google.gson.Gson;

/**
 * Created by zahmed on 12/11/2018.
 * ITF
 */


public class ParsingUtils {

    public static <T> T parsingFromObject(String src,
                                          Class<T> parsingClass) {
        Gson gson = new Gson();
        return gson.fromJson(src, parsingClass);
    }

    public static <T> T parsingFromObject(Object src,
                                  Class<T> parsingClass) {
        Gson gson = new Gson();
        return gson.fromJson(gson.toJson(src), parsingClass);
    }

}
