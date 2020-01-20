package directives;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;

import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;

import com.ift.ehsthkhdmaatcore.R;
import managers.ResourceManager;


/**
 * Created by zahmed on 12/11/2018.
 * ITF
 */

public class ITFButton extends AppCompatButton {
    private String defaultName = getResources().getString(R.string.font_helvetica_black);
    private int defaultColor = getResources().getColor(R.color.default_font_color);
    @Nullable
    private String fontName;
    @Nullable
    private String iconBeforeText;
    @Nullable
    private String iconAfterText;
    @Nullable
    private int fontColor;
    @Nullable
    private String localizedText;
    @Nullable
    private int fontStyle;

    private int defaultStyle = Typeface.NORMAL;

    public ITFButton(Context context) {
        super(context);
    }

    public ITFButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        applyCustomAttributes(context, attrs);
    }

    public ITFButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        applyCustomAttributes(context, attrs);
    }

    private void applyCustomAttributes(Context context, AttributeSet attrs) {
        TypedArray VBButtonStyles = context.obtainStyledAttributes(attrs,
                R.styleable.ITFButton);

        fontName = VBButtonStyles.getString(R.styleable.ITFButton_fontName);
        fontName = fontName == null ? defaultName : fontName;
        fontStyle = VBButtonStyles.getInt(R.styleable.ITFButton_ITFFontStyle, defaultStyle);
        applyFont();

        fontColor = VBButtonStyles.getColor(R.styleable.ITFButton_fontColor, defaultColor);
        applyFontColor();

        localizedText = VBButtonStyles.getString(R.styleable.ITFButton_localizedText);
        fetchLocalizedResource();

        iconBeforeText = VBButtonStyles.getString(R.styleable.ITFButton_iconBeforeText);
        addIconBeforeText();

        iconAfterText = VBButtonStyles.getString(R.styleable.ITFButton_iconAfterText);
        addIconAfterText();
    }

    private void fetchLocalizedResource() {
        if (localizedText != null)
            this.setText(getResource(localizedText));
    }

    private void applyFont() {
        /*Typeface tf = Typeface.createFromAsset(getContext().getAssets(),
                fontName);
        setTypeface(tf);*/
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

    private void addIconBeforeText() {
        if (iconBeforeText != null)
            this.setText(iconBeforeText + " " + this.getText());
    }

    private void addIconAfterText() {
        if (iconAfterText != null)
            this.setText(this.getText() + " " + iconAfterText);
    }

    private void applyFontColor() {
        setTextColor(fontColor);
    }

    protected String getResource(String key) {
        String resource = ResourceManager.getInstance().getString(key);
        if (resource == null) {
            throw new RuntimeException("key " + key + " has no value");
        }

        return resource;
    }
}
