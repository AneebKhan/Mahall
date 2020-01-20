package utilities;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by zahmed on 12/11/2018.
 * ITF
 */

public class NumberFormatter {


    public static String getCurrencyFormat(double amount, String countryCode, String currentLanguageCode) {
        Locale locale = new Locale(currentLanguageCode, countryCode);
        NumberFormat numberFormatted = NumberFormat.getCurrencyInstance(locale);
        String symbol = numberFormatted.getCurrency().getSymbol(locale);

        String formattedNumber = numberFormatted.format(amount);
        String[] splittedNumber = formattedNumber.split(symbol);

        return splittedNumber[0] + " " + splittedNumber[1];
    }

    public static String getCurrencyFormat(double amount)
    {
        Locale locale = new Locale("en","AE");
        NumberFormat numberFormatted  = NumberFormat.getCurrencyInstance(locale);
        String symbol = numberFormatted.getCurrency().getSymbol(locale);

        String formattedNumber = numberFormatted.format(amount);
        String[] splittedNumber = formattedNumber.split(symbol);

        //return splittedNumber[0] + " " + splittedNumber[1];
        return formattedNumber.replace(symbol , symbol + " ");
    }

    private static Locale getLocale(String strCode) {

        for (Locale locale : NumberFormat.getAvailableLocales()) {
            String code = NumberFormat.getCurrencyInstance(locale).getCurrency().getCurrencyCode();
            if (strCode.equals(code)) {
                return locale;
            }
        }
        return null;
    }

    public static double getRoundOffForDoubleValue (double a)
    {

        a = a * 100;
        a = Math.round(a);
        a = a / 100;

        return  a;

    }




}
