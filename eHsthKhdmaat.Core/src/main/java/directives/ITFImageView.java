package directives;

import android.content.Context;
import android.content.res.TypedArray;

import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.ift.ehsthkhdmaatcore.R;
import java.io.File;
import jp.wasabeef.glide.transformations.CropTransformation;


/**
 * Created by zahmed on 12/11/2018.
 * ITF
 */
public class ITFImageView extends AppCompatImageView {

    public ITFImageView(Context context) {
        super(context);
    }

    public ITFImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        applyCustomAttributes(context, attrs);
    }

    public ITFImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        applyCustomAttributes(context, attrs);
    }

    private void applyCustomAttributes(Context context, AttributeSet attrs) {
        TypedArray vbTextViewStyles = context.obtainStyledAttributes(attrs,
                R.styleable.ITFImageView);

    }

    /**
     * This method is used to set the preview bitmap.
     *
     * @param previewPath - Preview Bitmap
     */
    public void setPreviewBitmap(String previewPath)
    {
        if (previewPath != null) {
            // Set the preview image via Glide Library
            Glide.with(ITFImageView.this)
                    .load(new File(previewPath)) // Uri of the picture
                    .into(ITFImageView.this);
        }
    }


    /**
     * This method is used to set the preview bitmap.
     *
     * @param previewPath - Preview Bitmap
     */
    public void setPreviewBitmapWithCenterCrop(String previewPath)
    {
        if (previewPath != null) {
            // Set the preview image via Glide Library
            Glide.with(ITFImageView.this)
                    .load(new File(previewPath)) // Uri of the picture
                    .apply(new RequestOptions().transforms(new CropTransformation(300, 200, CropTransformation.CropType.CENTER)))
                    .into(ITFImageView.this);
        }
    }
}
