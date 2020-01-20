package itf.ehsthkhdmaat.mahall.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;

import com.itf.phatbooskiandroid.adapters.ITFRecyclerViewAdapter;

import directives.ITFImageView;
import directives.ITFTextView;
import itf.ehsthkhdmaat.mahall.R;
import itf.ehsthkhdmaat.mahall.models.service_model.venues.RateAndReview;
import utilities.LogUtils;
import utilities.UIHelper;

public class VenueReviewsAdapter extends ITFRecyclerViewAdapter<RateAndReview> {

//region /* region====================================== Interface ========================================= */
//endregion /* region====================================== Interface ====================================== */

//region /* ================================== Constant Variable =========================================== */
//endregion  /* ================================== Constant Variable ======================================= */

    //region /* =================================== Class Variable ============================================= */

//endregion /* =================================== Class Variable ========================================== */

    //region /* /* =================================== Constructors ============================================ */
    public VenueReviewsAdapter(Context context) {
        super(context);
    }

    public VenueReviewsAdapter(Context context, OnITFRecyclerViewItemClick<RateAndReview> listener) {
        super(context, listener);
    }
//endregion /* =================================== Constructors ============================================ */

//region /* ================================ Getter - Setter Method ======================================== */
//endregion /* ================================ Getter - Setter Method ===================================== */

    //region /* ================================== Life Cycle Method =========================================== */
    @Override
    protected View createView(Context context, ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_venue_reviews, viewGroup, false);
        return v;
    }

    @Override
    protected void bindView(RateAndReview item, final ITFRecyclerViewAdapter.ViewHolder viewHolder, int position) {
        if (item != null) {
            RateAndReview dataModel = (RateAndReview) item;
            try {

                ITFImageView imgUser = (ITFImageView) viewHolder.getView(R.id.imgUser);
                ITFTextView tvComment = (ITFTextView) viewHolder.getView(R.id.tvComment);
                RatingBar rbComment = (RatingBar) viewHolder.getView(R.id.rbComment);

                tvComment.setText(dataModel.getDetail());
                rbComment.setRating(dataModel.getPoint());
                UIHelper.setImageWithGlide(getContext(), imgUser, dataModel.getCreatedBy() + "");

            } catch (Exception e) {
                LogUtils.LOGE(this.getClass().getName(), e.getMessage());
            }
        }
    }
    //endregion /* ================================== Life Cycle Method ======================================== */

    //region /* ============================= Implemented Interface Method ===================================== */
    //endregion /* ============================= Implemented Interface Method ================================== */

    //region /* ============================= Implemented Abstract Method ====================================== */
    //endregion /* ============================= Implemented Abstract Method =================================== */

    //region /* ==================================== OnClick Methods =========================================== */
    //endregion /* ==================================== OnClick Methods ======================================== */

    //region /* =================================== User Define Methods ======================================== */
    public void clear() {
        int size = getList().size();
        getList().clear();
        notifyItemRangeRemoved(0, size);
    }
    //endregion /* =================================== User Define Methods ===================================== */


}



