package utilities;

/**
 * Created by zahmed on 12/11/2018.
 * ITF
 */

public class MaskingUtils {
    public static String getMaskedMobileNumber(String plainNumber)
    {
        String maskedNumber = plainNumber;
        if (maskedNumber != null || maskedNumber.trim() != "") {
            maskedNumber = maskedNumber.replaceAll("\\d(?=\\d{4})", "*");
        }
        return maskedNumber;
    }

    public static String getMaskedCardNumber(String plainNumber)
    {
        //TODO: please change masking for cards
        String maskedNumber = plainNumber;
        if (maskedNumber != null || maskedNumber.trim() != "") {
            maskedNumber = maskedNumber.replaceAll("\\d(?=\\d{4})", "*");
        }
        return maskedNumber;
    }
}
