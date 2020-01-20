package itf.ehsthkhdmaat.mahall.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.RatingBar;

import com.itf.phatbooskiandroid.adapters.ITFRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import directives.ITFImageView;
import directives.ITFTextView;
import itf.ehsthkhdmaat.mahall.R;
import itf.ehsthkhdmaat.mahall.interfaces.onFavouriteClickedListener;
import itf.ehsthkhdmaat.mahall.models.service_model.favourites.Response;
import utilities.LogUtils;

public class FavouritesAdapter extends ITFRecyclerViewAdapter<Response> {

//region /* region====================================== Interface ========================================= */
//endregion /* region====================================== Interface ====================================== */

//region /* ================================== Constant Variable =========================================== */
//endregion  /* ================================== Constant Variable ======================================= */

    //region /* =================================== Class Variable ============================================= */
    private ArrayList<Response> filteredData = null;
    private ArrayList<Response> originalData = null;
    private ItemFilter mFilter = new ItemFilter();
    private onFavouriteClickedListener favouriteListener;
    private ITFImageView mSelectedImage;
    private Response selectedDataModel;
    private int selectedPosition = -1;
//endregion /* =================================== Class Variable ========================================== */

    //region /* /* =================================== Constructors ============================================ */
    public FavouritesAdapter(Context context) {
        super(context);
    }

    public FavouritesAdapter(Context context, OnITFRecyclerViewItemClick<Response> listener, ArrayList<Response> datalist) {
        super(context, listener);
        this.originalData = datalist;
        this.filteredData = datalist;
    }
//endregion /* =================================== Constructors ============================================ */

//region /* ================================ Getter - Setter Method ======================================== */
//endregion /* ================================ Getter - Setter Method ===================================== */

    //region /* ================================== Life Cycle Method =========================================== */
    @Override
    protected View createView(Context context, ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_favourites_item, viewGroup, false);
        return v;
    }

    @Override
    protected void bindView(Response item, final ITFRecyclerViewAdapter.ViewHolder viewHolder, int position) {
        if (item != null) {
            Response dataModel = (Response) item;
            try {

                ITFTextView tvCatName = (ITFTextView) viewHolder.getView(R.id.tvCatName);
                ITFImageView imgMarkFavourite = (ITFImageView) viewHolder.getView(R.id.imgMarkFavourite);
                RatingBar rbCatRating = (RatingBar) viewHolder.getView(R.id.rbCatRating);

                tvCatName.setText(dataModel.getVenue().getName());

                if (new Float(dataModel.getVenue().getAverageRating()) != null)
                    rbCatRating.setRating(new Float(dataModel.getVenue().getAverageRating()));

                imgMarkFavourite.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        selectedPosition = position;
                        mSelectedImage = (ITFImageView) v;
                        selectedDataModel = dataModel;
                        favouriteListener.removeFavouriteClicked(dataModel.getVenue().getId());
                    }
                });


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
    public Filter getFilter() {
        return mFilter;
    }

    private class ItemFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            String filterString = constraint.toString().toLowerCase();

            FilterResults results = new FilterResults();

            final List<Response> list = originalData;

            int count = list.size();
            //final ArrayList<String> nlist = new ArrayList<String>(count);
            final ArrayList<Response> nlist = new ArrayList<Response>(count);


            for (Response row : originalData) {
                if (row.getVenue().getName().toLowerCase().contains(filterString.toLowerCase())) {
                    nlist.add(row);
                }
            }

            results.values = nlist;
            results.count = nlist.size();

            return results;


        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            filteredData = (ArrayList<Response>) results.values;
            setList(filteredData);
            notifyDataSetChanged();


        }

    }

    public void setListener(onFavouriteClickedListener listener) {
        this.favouriteListener = listener;
    }

    public int getSelectedItemPosition() {
        return selectedPosition;
    }

    public void removeSelectedItem(int selectedPosition) {

        getList().remove(selectedPosition);
        notifyItemRemoved(selectedPosition);
    }


    //endregion /* =================================== User Define Methods ===================================== */


}



