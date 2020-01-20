package utilities;

import android.text.TextUtils;
import android.util.Base64;

/**
 * Created by zahmed on 12/11/2018.
 * ITF
 */

public class StringUtils {

    private static final char[] turkishChars = new char[] {0x131, 0x130, 0xFC, 0xDC, 0xF6, 0xD6, 0x15F, 0x15E, 0xE7, 0xC7, 0x11F, 0x11E};
    private static final char[] englishChars = new char[] {'i', 'I', 'u', 'U', 'o', 'O', 's', 'S', 'c', 'C', 'g', 'G'};

    public static String join(String delimiter, int[] items) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < items.length; i++) {
            int item = items[i];
            sb.append(Integer.toString(item));
            if (i < items.length - 1) {
                sb.append(delimiter);
            }
        }
        return sb.toString();
    }

    public static Boolean nullOrEmpty(String value) {
        if (value == null) {
            return true;
        }
        return value.trim().isEmpty();
    }

    public static boolean contains(String sourceText, String searchText) {
        return StringUtils.searchContains(sourceText, searchText);
    }

    public static String base64Encode(byte[] bytes) {
        return Base64.encodeToString(bytes, Base64.NO_WRAP);
    }

    public static byte[] base64Decode(String base64) {
        return Base64.decode(base64, Base64.NO_WRAP);
    }

    public static boolean isEmail(String email) {
        if (email == null || TextUtils.isEmpty(email)) {
            return false;
        }
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    /***
     * String.valueOf() metodundan farkı String.valueOf() metodunda null ise geriye string olarak "null" değeri döndürülürken bu metodda "" döndürülüyor.
     * @param object object
     * @return toString() hali
     */
    public static String toString(Object object) {
        if (object == null) {
            return "";
        }
        return object.toString();
    }

    public static String toString(Object object, String defaultValue) {
        if (object == null) {
            return defaultValue;
        }
        final String result = object.toString();
        return result.isEmpty() ? defaultValue : result;
    }


    public static String upperCaseAllFirst(String value) {
        if (value == null || value.isEmpty()) {
            return value;
        }
        char[] array = value.toCharArray();
        // Uppercase first letter.
        array[0] = Character.toUpperCase(array[0]);

        // Uppercase all letters that follow a whitespace character.
        for (int i = 1; i < array.length; i++) {
            if (Character.isWhitespace(array[i - 1])) {
                array[i] = Character.toUpperCase(array[i]);
            }
        }
        // Result.
        return new String(array);
    }

    public static String toTitleCase(String value) {

        if (value == null) {
            return null;
        }

        boolean space = true;
        StringBuilder builder = new StringBuilder(value);
        final int length = builder.length();

        for (int i = 0; i < length; ++i) {
            char c = builder.charAt(i);
            if (space) {
                if (!Character.isWhitespace(c)) {
                    // Convert to title case and switch out of whitespace mode.
                    builder.setCharAt(i, Character.toTitleCase(c));
                    space = false;
                }
            } else if (Character.isWhitespace(c)) {
                space = true;
            } else {
                builder.setCharAt(i, Character.toLowerCase(c));
            }
        }

        return builder.toString();
    }

    public static boolean search(String sourceText, String searchText) {
        return StringUtils.searchContains(sourceText, searchText);
    }

    private static boolean searchContains(String sourceText, String searchText) {
        if (sourceText == null || searchText == null) {
            return false;
        }

        sourceText = sourceText.toLowerCase();
        searchText = searchText.toLowerCase();

        return clearTurkishChars(sourceText).contains(clearTurkishChars(searchText));
    }

    private static String clearTurkishChars(String str) {
        String ret = str;
        int length = turkishChars.length;
        for (int i = 0; i < length; i++) {
            ret = ret.replace(turkishChars[i], englishChars[i]);
        }
        return ret;
    }
}
