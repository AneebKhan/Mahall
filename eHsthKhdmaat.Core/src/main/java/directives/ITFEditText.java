package directives;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;

import android.util.AttributeSet;
import android.view.inputmethod.EditorInfo;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;

import com.ift.ehsthkhdmaatcore.R;

import interfaces.IViewHandler;
import managers.ResourceManager;


/**
 * Created by zahmed on 12/11/2018.
 * ITF
 */

public class ITFEditText extends AppCompatEditText implements IViewHandler {

    final static int FONT_STYLE_NORMAL = 1;
    final static int FONT_STYLE_BOLD = 2;
    final static int FONT_STYLE_ITALIC = 3;
    final static int FONT_STYLE_BOLD_ITALIC = 4;

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

    private String defaultName = getResources().getString(R.string.font_helvetica_black);
    private int defaultColor = getResources().getColor(R.color.default_font_color);
    private int defaultStyle = Typeface.NORMAL;

    @Nullable
    private String fontName;
    @Nullable
    private int fontStyle;
    @Nullable
    private int fontColor;
    @Nullable
    private String localizedHint;
    @Nullable
    private String localizedImeActionLabel;
    @Nullable
    private int specialInputType;

    public ITFEditText(Context context) {
        super(context);
    }

    public ITFEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        applyCustomAttributes(context, attrs);
    }

    public ITFEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        applyCustomAttributes(context, attrs);
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
                case ITFEditText.INPUT_TYPE_NUMERIC:
                    setInputType(EditorInfo.TYPE_CLASS_NUMBER);
                    //set special rule
                    break;
                case ITFEditText.INPUT_TYPE_CHARACTER:
                    setInputType(EditorInfo.TYPE_CLASS_TEXT);
                    //set special rule
                    break;
                case ITFEditText.INPUT_TYPE_NONARABIC:
                    setInputType(EditorInfo.TYPE_CLASS_TEXT);
                    //set special rule
                    break;
                case ITFEditText.INPUT_TYPE_SPECIALALPHABETICAL:
                    setInputType(EditorInfo.TYPE_CLASS_TEXT);
                    //set special rule
                    break;
                case ITFEditText.INPUT_TYPE_ALPHABETICAL:
                    setInputType(EditorInfo.TYPE_CLASS_TEXT);
                    break;
                case ITFEditText.INPUT_TYPE_ALPHANUMERIC:
                    setInputType(EditorInfo.TYPE_CLASS_TEXT);
                    break;
                case ITFEditText.INPUT_TYPE_ADDRESS:
                    setInputType(EditorInfo.TYPE_CLASS_TEXT);
                    break;
                case ITFEditText.INPUT_TYPE_USERNAME:
                    setInputType(EditorInfo.TYPE_CLASS_TEXT);
                    break;
                case ITFEditText.INPUT_TYPE_CIFNUMBER:
                    setInputType(EditorInfo.TYPE_CLASS_TEXT);
                    break;
                case ITFEditText.INPUT_TYPE_PASSWORD:
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

    public boolean isEmpty() {
        return getText().toString().isEmpty();
    }
}