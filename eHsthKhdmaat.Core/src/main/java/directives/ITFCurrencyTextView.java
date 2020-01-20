package directives;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Typeface;

import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import com.ift.ehsthkhdmaatcore.R;
import managers.ResourceManager;
import utilities.NumberFormatter;


/**
 * Created by zahmed on 12/11/2018.
 * ITF
 */

public class ITFCurrencyTextView extends AppCompatTextView {

    private String defaultName = getResources().getString(R.string.font_helvetica_black);
    private int defaultColor = getResources().getColor(R.color.default_font_color);
    private int defaultStyle = Typeface.NORMAL;

    @Nullable
    private String fontName;
    @Nullable
    private int fontColor;
    @Nullable
    private int fontStyle;

    public ITFCurrencyTextView(Context context) {
        super(context);
    }

    public ITFCurrencyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        applyCustomAttributes(context, attrs);
    }

    public ITFCurrencyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        applyCustomAttributes(context, attrs);
    }

    private void applyCustomAttributes(Context context, AttributeSet attrs) {
        TypedArray vbTextViewStyles = context.obtainStyledAttributes(attrs,
                R.styleable.ITFTextView);

        fontName = vbTextViewStyles.getString(R.styleable.ITFTextView_fontName);
        fontName = fontName == null ? defaultName : fontName;

        fontStyle = vbTextViewStyles.getInt(R.styleable.ITFTextView_ITFFontStyle,defaultStyle);
        applyFont();

        fontColor = vbTextViewStyles.getColor(R.styleable.ITFTextView_fontColor, defaultColor);
        applyFontColor();

    }

    private void setText(String text) {
            super.setText(text);
    }

    private void applyFont() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(),
                fontName );
        if(fontStyle == ITFTextView.FONT_STYLE_NORMAL)
            setTypeface(tf, Typeface.NORMAL);
        if(fontStyle == ITFTextView.FONT_STYLE_BOLD)
            setTypeface(tf, Typeface.BOLD);
        if(fontStyle == ITFTextView.FONT_STYLE_ITALIC)
            setTypeface(tf, Typeface.ITALIC);
        if(fontStyle == ITFTextView.FONT_STYLE_BOLD_ITALIC)
            setTypeface(tf, Typeface.BOLD_ITALIC);
    }

    private void applyFontColor() {
        setTextColor(fontColor);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    protected String getResource(String key) {
        String resource = ResourceManager.getInstance().getString(key);
        if (resource == null) {
            //throw new RuntimeException("key " + key + " has no value");
            return key;
        }

        return resource;
    }

    public void setText(double amount, String countryCode, String currentLanguageCode) {
        this.setText(NumberFormatter.getCurrencyFormat(amount,countryCode,currentLanguageCode));
    }


    public void setText(double amount) {
        this.setText(NumberFormatter.getCurrencyFormat(amount));
    }

}
