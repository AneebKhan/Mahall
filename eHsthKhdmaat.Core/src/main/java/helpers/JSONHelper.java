package helpers;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import org.json.JSONException;
import org.json.JSONObject;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * Created by zahmed on 2/28/2018.
 */

public class JSONHelper {
    public static JSONObject objectToJsonObject(Object obj) throws IllegalArgumentException, IllegalAccessException, JSONException {
        JSONObject object = new JSONObject();

        Class<?> objClass = obj.getClass();
        Field[] fields = objClass.getDeclaredFields();
        for(Field field : fields) {
            field.setAccessible(true);
            Annotation[] annotations = field.getDeclaredAnnotations();
            for(Annotation annotation : annotations){
                if(annotation instanceof SerializedName){
                    SerializedName myAnnotation = (SerializedName) annotation;
                    String name = myAnnotation.value();
                    Object value = field.get(obj);

                    if(value == null)
                        value = new String("");

                    object.put(name, value);
                }
            }
        }

        return object;
    }

    public static String objectToJsonString(Object object)
    {
        return new Gson().toJson(object);
        //return new GsonBuilder().enableComplexMapKeySerialization().create().toJson(object);
    }

    public static <T> T jsonStringIntoObject(String jsonString, Class<T> castType)
    {
        return new Gson().fromJson(jsonString,
                castType);
    }
}
