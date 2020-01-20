package itf.ehsthkhdmaat.mahall.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.itf.phatbooskiandroid.adapters.ITFRecyclerViewAdapter;

import directives.ITFTextView;
import itf.ehsthkhdmaat.mahall.R;
import itf.ehsthkhdmaat.mahall.models.service_model.venue_categories.Response;
import utilities.LogUtils;
import utilities.UIHelper;

public class DashboardAdapter extends ITFRecyclerViewAdapter<Response> {

//region /* region====================================== Interface ========================================= */
//endregion /* region====================================== Interface ====================================== */

//region /* ================================== Constant Variable =========================================== */
//endregion  /* ================================== Constant Variable ======================================= */

//region /* =================================== Class Variable ============================================= */
//endregion /* =================================== Class Variable ========================================== */

    //region /* /* =================================== Constructors ============================================ */
    public DashboardAdapter(Context context) {
        super(context);
    }

    public DashboardAdapter(Context context, OnITFRecyclerViewItemClick<Response> listener) {
        super(context, listener);
    }
//endregion /* =================================== Constructors ============================================ */

//region /* ================================ Getter - Setter Method ======================================== */
//endregion /* ================================ Getter - Setter Method ===================================== */

    //region /* ================================== Life Cycle Method =========================================== */
    @Override
    protected View createView(Context context, ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_dashboard_item, viewGroup, false);
        return v;
    }

    @Override
    protected void bindView(Response item, final ITFRecyclerViewAdapter.ViewHolder viewHolder, int position) {
        if (item != null) {
            Response dataModel = (Response) item;
            try {

                ITFTextView tvCatName = (ITFTextView)viewHolder.getView(R.id.tvCatName);
                de.hdodenhof.circleimageview.CircleImageView viewCatImage = (de.hdodenhof.circleimageview.CircleImageView)viewHolder.getView(R.id.viewCatImage);

                tvCatName.setText(dataModel.getName());
                UIHelper.setImageWithGlide(getContext(), viewCatImage, dataModel.getImageUrl());


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
    //endregion /* =================================== User Define Methods ===================================== */


}



