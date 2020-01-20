package directives;

import android.content.Context;

import android.util.AttributeSet;

import androidx.annotation.ColorRes;
import androidx.annotation.DimenRes;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

/**
 * Created by zahmed on 12/11/2018.
 * ITF
 */

public class ITFRecyclerView extends RecyclerView {

    public ITFRecyclerView(Context context) {
        super(context);
    }

    public ITFRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ITFRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setDividerWithColor(@DimenRes int margin, @DimenRes int size, @ColorRes int colorRes) {
        this.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getContext())
                .marginResId(margin, margin)
                .sizeResId(size)
                .colorResId(colorRes)
                .build());
    }



}
