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

/**
 * Created by zahmed on 12/11/2018.
 * ITF
 */
public class ITFTextView extends AppCompatTextView {

    final static int FONT_STYLE_NORMAL = 1;
    final static int FONT_STYLE_BOLD = 2;
    final static int FONT_STYLE_ITALIC = 3;
    final static int FONT_STYLE_BOLD_ITALIC = 4;

    private String defaultName = getResources().getString(R.string.font_helvetica_black);
    private int defaultStyle = Typeface.NORMAL;
    private int defaultColor = getResources().getColor(R.color.default_font_color);
    @Nullable
    private String fontName;
    @Nullable
    private int fontStyle;
    @Nullable
    private int fontColor;
    @Nullable
    private String localizedText;

    public ITFTextView(Context context) {
        super(context);
    }


    public ITFTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        applyCustomAttributes(context, attrs);
    }

    public ITFTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        applyCustomAttributes(context, attrs);
    }

    private void applyCustomAttributes(Context context, AttributeSet attrs) {
        TypedArray vbTextViewStyles = context.obtainStyledAttributes(attrs,
                R.styleable.ITFTextView);

        fontName = vbTextViewStyles.getString(R.styleable.ITFTextView_fontName);
        fontName = fontName == null ? defaultName : fontName;

        fontStyle = vbTextViewStyles.getInt(R.styleable.ITFTextView_ITFFontStyle, defaultStyle);
        applyFont();

        fontColor = vbTextViewStyles.getColor(R.styleable.ITFTextView_fontColor, defaultColor);
        applyFontColor();

        localizedText = vbTextViewStyles.getString(R.styleable.ITFTextView_localizedText);
        fetchLocalizedResource();

        String iconBeforeText = vbTextViewStyles.getString(R.styleable.ITFTextView_iconBeforeText);
        setIconBeforeText(iconBeforeText);

        String iconAfterText = vbTextViewStyles.getString(R.styleable.ITFTextView_iconAfterText);
        setIconAfterText(iconAfterText);

    }

    private void setText(String text) {
        super.setText(text);
    }

    private void fetchLocalizedResource() {
        if (localizedText != null)
            this.setText(getResource(localizedText));
    }

    private void applyFont() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(),
                fontName);
        if (fontStyle == ITFTextView.FONT_STYLE_NORMAL)
            setTypeface(tf, Typeface.NORMAL);
        if (fontStyle == ITFTextView.FONT_STYLE_BOLD)
            setTypeface(tf, Typeface.BOLD);
        if (fontStyle == ITFTextView.FONT_STYLE_ITALIC)
            setTypeface(tf, Typeface.ITALIC);
        if (fontStyle == ITFTextView.FONT_STYLE_BOLD_ITALIC)
            setTypeface(tf, Typeface.BOLD_ITALIC);
    }

    public void setIconBeforeText(String iconBeforeText) {
        if (iconBeforeText != null)
            this.setText(iconBeforeText + " " + this.getText());
    }

    public void setIconAfterText(String iconAfterText) {
        if (iconAfterText != null)
            this.setText(this.getText() + " " + iconAfterText);
    }

    private void applyFontColor() {
        setTextColor(fontColor);
    }

    public void setFontColor(int color) {
        setTextColor(color);
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

}
