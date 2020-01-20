package utilities;

import android.util.Base64;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.RSAPublicKeySpec;
import javax.crypto.Cipher;

/**
 * Created by zahmed on 12/11/2018.
 * ITF
 */

public class RsaEncryption {

    public static String encryptData(String data, String modulus, String exponent) {

        if (modulus == null || exponent == null)
            return "";
        try {
            byte[] modulusBytes = Base64.decode(modulus.getBytes("UTF-8"),
                    Base64.DEFAULT);
            byte[] exponentBytes = Base64.decode(
                    exponent.getBytes("UTF-8"), Base64.DEFAULT);
            BigInteger e = new BigInteger(1, exponentBytes);
            BigInteger m = new BigInteger(1, modulusBytes);
            RSAPublicKeySpec keySpec = new RSAPublicKeySpec(m, e);
            KeyFactory fact = KeyFactory.getInstance("RSA");
            PublicKey pubKeyn = fact.generatePublic(keySpec);
            Cipher cipher = Cipher
                    .getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, pubKeyn);
            byte[] encryptedByteData = cipher.doFinal(data.getBytes());

            String outputEncrypted = Base64.encodeToString(
                    encryptedByteData, Base64.DEFAULT);

            return outputEncrypted;

        } catch (Exception ee) {
            return "";
        }
    }

}
