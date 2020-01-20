package directives;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.os.Build;

import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.inputmethod.EditorInfo;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;

import com.ift.ehsthkhdmaatcore.R;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.Currency;
import java.util.Locale;
import interfaces.IViewHandler;
import managers.ResourceManager;

/**
 * Created by zahmed on 12/11/2018.
 * ITF
 */

public class ITFCurrencyEditText extends AppCompatEditText implements IViewHandler {
    private String defaultName = getResources().getString(R.string.font_helvetica_black);
    private int defaultColor = getResources().getColor(R.color.default_font_color);
    private int defaultStyle = Typeface.NORMAL;

    @Nullable
    private String fontName;
    @Nullable
    private int fontColor;
    @Nullable
    private int fontStyle;
    @Nullable
    private String localizedHint;
    @Nullable
    private String localizedImeActionLabel;
    @Nullable
    private int specialInputType;

    final static int INPUT_TYPE_NUMERIC = 1;
    final static int INPUT_TYPE_CHARACTER = 2;
    final static int INPUT_TYPE_NONARABIC = 3;
    final static int INPUT_TYPE_SPECIALALPHABETICAL = 4;
    final static int INPUT_TYPE_ALPHABETICAL = 5;
    final static int INPUT_TYPE_ALPHANUMERIC = 6;
    final static int INPUT_TYPE_ADDRESS = 7;
    final static int INPUT_TYPE_USERNAME = 8;
    final static int INPUT_TYPE_CIFNUMBER = 9;
    final static int INPUT_TYPE_PASSWORD = 10;

    private char mGroupDivider;
    private char mMonetaryDivider;
    private String mLocale = "";
    private boolean mShowSymbol;

    private char groupDivider;
    private char monetaryDivider;

    private Locale locale;
    private DecimalFormat numberFormat;

    private int fractionDigit;
    private String currencySymbol;

    public ITFCurrencyEditText(Context context) {
        super(context);
    }

    public ITFCurrencyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        applyCustomAttributes(context, attrs);
        this.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.ITFCurrencyEditText2, 0, 0);

        try {
            if (a.getString(R.styleable.ITFCurrencyEditText2_groupDivider) != null) {
                this.mGroupDivider = a.getString(R.styleable.ITFCurrencyEditText2_groupDivider).charAt(0);
                this.groupDivider = mGroupDivider;
            }

            if (a.getString(R.styleable.ITFCurrencyEditText2_monetaryDivider) != null) {
                this.mMonetaryDivider = a.getString(R.styleable.ITFCurrencyEditText2_monetaryDivider).charAt(0);
                this.monetaryDivider = mMonetaryDivider;
            }

            if (a.getString(R.styleable.ITFCurrencyEditText2_locale) == null)
                this.locale = getDefaultLocale();
            else this.mLocale = a.getString(R.styleable.ITFCurrencyEditText2_locale);

            if (a.getString(R.styleable.ITFCurrencyEditText2_showSymbol) != null)
                this.mShowSymbol = a.getBoolean(R.styleable.ITFCurrencyEditText2_showSymbol, false);

            if (mLocale.equals("")) {
                locale = getDefaultLocale();
            } else {
                if (mLocale.contains("-"))
                    mLocale = mLocale.replace("-", "_");

                String[] l = mLocale.split("_");
                if (l.length > 1) {
                    locale = new Locale(l[0], l[1]);
                } else {
                    locale = new Locale("", mLocale);
                }
            }

            initSettings();
        } finally {
            a.recycle();
        }

        this.addTextChangedListener(onTextChangeListener);
    }

    private void applyCustomAttributes(Context context, AttributeSet attrs) {
        TypedArray VBEditTextStyles = context.obtainStyledAttributes(attrs,
                R.styleable.ITFEditText);

        fontName = VBEditTextStyles.getString(R.styleable.ITFEditText_fontName);
        fontName = fontName == null ? defaultName : fontName;
        fontStyle = VBEditTextStyles.getInt(R.styleable.ITFTextView_ITFFontStyle,defaultStyle);
        applyFont();

        fontColor = VBEditTextStyles.getColor(R.styleable.ITFEditText_fontColor, defaultColor);
        applyFontColor();

        localizedHint = VBEditTextStyles.getString(R.styleable.ITFEditText_localizedHint);
        setLocalizedHint();

        localizedImeActionLabel = VBEditTextStyles.getString(R.styleable.ITFEditText_localizedImeActionLabel);
        fetchLocalizedImeActionLabel();

        specialInputType = VBEditTextStyles.getInt(R.styleable.ITFEditText_specialInputType, -1);
        setCustomInputType();

        String localizedError = VBEditTextStyles.getString(R.styleable.ITFEditText_localizedError);
        setLocalizedError(localizedError);

        String errorText = VBEditTextStyles.getString(R.styleable.ITFEditText_errorText);
        setErrorText(errorText);
    }

    private void applyFont() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(),
                fontName );
        if(fontStyle == ITFEditText.FONT_STYLE_NORMAL)
            setTypeface(tf, Typeface.NORMAL);
        if(fontStyle == ITFEditText.FONT_STYLE_BOLD)
            setTypeface(tf, Typeface.BOLD);
        if(fontStyle == ITFEditText.FONT_STYLE_ITALIC)
            setTypeface(tf, Typeface.ITALIC);
        if(fontStyle == ITFEditText.FONT_STYLE_BOLD_ITALIC)
            setTypeface(tf, Typeface.BOLD_ITALIC);
    }

    private void applyFontColor() {
        setTextColor(fontColor);
    }


    private void fetchLocalizedImeActionLabel() {
        if (localizedImeActionLabel != null)
            this.setImeActionLabel(getResource(localizedImeActionLabel), 0);
    }

    private void setLocalizedHint() {
        if (localizedHint != null)
            this.setHint(getResource(localizedHint));
    }

    protected String getResource(String key) {
        String resource = ResourceManager.getInstance().getString(key);
        if (resource == null) {
            throw new RuntimeException("key " + key + " has no value");
        }

        return resource;
    }

    private void setCustomInputType() {
        if (specialInputType != -1) {
            switch (specialInputType) {
                case ITFCurrencyEditText.INPUT_TYPE_NUMERIC:
                    setInputType(EditorInfo.TYPE_CLASS_NUMBER);
                    //set special rule
                    break;
                case ITFCurrencyEditText.INPUT_TYPE_CHARACTER:
                    setInputType(EditorInfo.TYPE_CLASS_TEXT);
                    //set special rule
                    break;
                case ITFCurrencyEditText.INPUT_TYPE_NONARABIC:
                    setInputType(EditorInfo.TYPE_CLASS_TEXT);
                    //set special rule
                    break;
                case ITFCurrencyEditText.INPUT_TYPE_SPECIALALPHABETICAL:
                    setInputType(EditorInfo.TYPE_CLASS_TEXT);
                    //set special rule
                    break;
                case ITFCurrencyEditText.INPUT_TYPE_ALPHABETICAL:
                    setInputType(EditorInfo.TYPE_CLASS_TEXT);
                    break;
                case ITFCurrencyEditText.INPUT_TYPE_ALPHANUMERIC:
                    setInputType(EditorInfo.TYPE_CLASS_TEXT);
                    break;
                case ITFCurrencyEditText.INPUT_TYPE_ADDRESS:
                    setInputType(EditorInfo.TYPE_CLASS_TEXT);
                    break;
                case ITFCurrencyEditText.INPUT_TYPE_USERNAME:
                    setInputType(EditorInfo.TYPE_CLASS_TEXT);
                    break;
                case ITFCurrencyEditText.INPUT_TYPE_CIFNUMBER:
                    setInputType(EditorInfo.TYPE_CLASS_TEXT);
                    break;
                case ITFCurrencyEditText.INPUT_TYPE_PASSWORD:
                    setInputType(EditorInfo.TYPE_TEXT_VARIATION_PASSWORD);
                    break;
                default:
                    setInputType(EditorInfo.TYPE_CLASS_TEXT);
                    break;
            }
        }
    }

    @Override
    public void setFocus() {
        this.requestFocus();
    }

    @Override
    public void setErrorText(String errorText) {
        if (errorText != null)
            this.setError(errorText);
    }

    @Override
    public void setLocalizedError(String localizedError) {
        if (localizedError != null)
            this.setError(getResource(localizedError));
    }

    /***
     * If user does not provide a valid locale it throws IllegalArgumentException.
     *
     * If throws an IllegalArgumentException the locale sets to default locale
     */
    private void initSettings() {
        boolean success = false;
        while (!success) {
            try {
                fractionDigit = Currency.getInstance(locale).getDefaultFractionDigits();

                DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance(locale);
                if (mGroupDivider > 0)
                    symbols.setGroupingSeparator(mGroupDivider);
                groupDivider = symbols.getGroupingSeparator();

                if (mMonetaryDivider > 0)
                    symbols.setMonetaryDecimalSeparator(mMonetaryDivider);
                monetaryDivider = symbols.getMonetaryDecimalSeparator();

                currencySymbol = symbols.getCurrencySymbol();

                DecimalFormat df = (DecimalFormat) DecimalFormat.getCurrencyInstance(locale);
                numberFormat = new DecimalFormat(df.toPattern(), symbols);

                success = true;
            } catch (IllegalArgumentException e) {
                Log.e(getClass().getCanonicalName(), e.getMessage());
                locale = getDefaultLocale();
            }
        }
    }

    private Locale getDefaultLocale() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            return getContext().getResources().getConfiguration().getLocales().get(0);
        else
            return getContext().getResources().getConfiguration().locale;
    }

    /***
     *It resets text currently displayed If user changes separators or locale etc.
     */
    private void resetText() {
        String s = getText().toString();
        if (s.isEmpty()) {
            initSettings();
            return;
        }

        s = s.replace(groupDivider, '\u0020').replace(monetaryDivider, '\u0020')
                .replace(".", "").replace(" ", "")
                .replace(currencySymbol, "").trim();
        try {
            initSettings();
            s = format(s);
            removeTextChangedListener(onTextChangeListener);
            setText(s);
            setSelection(s.length());
            addTextChangedListener(onTextChangeListener);
        } catch (ParseException e) {
            Log.e(getClass().getCanonicalName(), e.getMessage());
        }
    }

    private TextWatcher onTextChangeListener = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (s.length() == 0)
                return;

            removeTextChangedListener(this);

            /***
             * Clear input to get clean text before format
             * '\u0020' is empty character
             */
            String text = s.toString();
            text = text.replace(groupDivider, '\u0020').replace(monetaryDivider, '\u0020')
                    .replace(".", "").replace(" ", "")
                    .replace(currencySymbol, "").trim();
            try {
                text = format(text);
            } catch (ParseException e) {
                Log.e(getClass().getCanonicalName(), e.getMessage());
            }

            setText(text);
            setSelection(text.length());

            addTextChangedListener(this);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private String format(String text) throws ParseException {
        if (mShowSymbol)
            return numberFormat.format(Double.parseDouble(text) / Math.pow(10, fractionDigit));
        else
            return numberFormat.format(Double.parseDouble(text) / Math.pow(10, fractionDigit)).replace(currencySymbol, "");
    }

    /***
     * returns the decimal separator for current locale
     * for example; input value 1,234.56
     *              returns ','
     *
     * @return decimal separator char
     */
    public char getGroupDivider() {
        return groupDivider;
    }

    /***
     * sets how to divide decimal value and fractions
     * for example; If you want formatting like this
     *              for input value 1,234.56
     *              set ','
     * @param groupDivider char
     */
    public void setGroupDivider(char groupDivider) {
        this.mGroupDivider = groupDivider;
        resetText();
    }

    /***
     * returns the monetary separator for current locale
     * for example; input value 1,234.56
     *              returns '.'
     *
     * @return monetary separator char
     */
    public char getMonetaryDivider() {
        return monetaryDivider;
    }

    /***
     * sets how to divide decimal value and fractions
     * for example; If you want formatting like this
     *              for input value 1,234.56
     *              set '.'
     * @param monetaryDivider char
     */
    public void setMonetaryDivider(char monetaryDivider) {
        this.mMonetaryDivider = monetaryDivider;
        resetText();
    }

    /***
     *
     * @return current locale
     */
    public Locale getLocale() {
        return locale;
    }

    /***
     * Sets locale which desired currency format
     *
     * @param locale
     */
    public void setLocale(Locale locale) {
        this.locale = locale;
        resetText();
    }

    /**
     *
     * @return true if currency symbol of current locale is showing
     */
    public boolean showSymbol() {
        return this.mShowSymbol;
    }

    /***
     * Sets if currency symbol of current locale shows
     *
     * @param showSymbol
     */
    public void showSymbol(boolean showSymbol) {
        this.mShowSymbol = showSymbol;
        resetText();
    }

    /**
     *
     *  @return double value for current text
     */
    public double getCurrencyDouble() throws ParseException {
        String text = getText().toString();
        text = text.replace(groupDivider, '\u0020').replace(monetaryDivider, '\u0020')
                .replace(".", "").replace(" ", "")
                .replace(currencySymbol, "").trim();

        if (showSymbol())
            return Double.parseDouble(text.replace(currencySymbol, "")) / Math.pow(10, fractionDigit);
        else return Double.parseDouble(text) / Math.pow(10, fractionDigit);
    }

    /**
     *
     *  @return String value for current text
     */
    public String getCurrencyText() throws ParseException {
        if (showSymbol())
            return getText().toString().replace(currencySymbol, "");
        else return getText().toString();
    }
}