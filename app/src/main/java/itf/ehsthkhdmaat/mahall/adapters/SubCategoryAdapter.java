package itf.ehsthkhdmaat.mahall.adapters;

import android.content.Context;
import android.location.Location;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.RatingBar;

import com.itf.phatbooskiandroid.adapters.ITFRecyclerViewAdapter;
import com.itf.phatbooskiandroid.classes.CustomApplication;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import directives.ITFImageView;
import directives.ITFTextView;
import itf.ehsthkhdmaat.mahall.R;
import itf.ehsthkhdmaat.mahall.interfaces.onFavouriteClickedListener;
import itf.ehsthkhdmaat.mahall.models.service_model.venues.Response;
import utilities.GPSTracker;
import utilities.LogUtils;
import utilities.NumberFormatter;

public class SubCategoryAdapter extends ITFRecyclerViewAdapter<Response> {

//region /* region====================================== Interface ========================================= */
//endregion /* region====================================== Interface ====================================== */

//region /* ================================== Constant Variable =========================================== */
//endregion  /* ================================== Constant Variable ======================================= */

    //region /* =================================== Class Variable ============================================= */
    private ArrayList<Response> filteredData = null;
    private ArrayList<Response> originalData = null;
    private ItemFilter mFilter = new ItemFilter();
    private onFavouriteClickedListener favouriteListener;

    //
    private Double tempSrcLat = 24.903954;
    private Double tempSrcLng = 67.081861;

    private double tempDestLat , tempDestLng;
    private float totalDistance = 0;
    private double mileToKm = 0.62;
    private Location src , dest;
    private double a = 10000;

//endregion /* =================================== Class Variable ========================================== */

    //region /* /* =================================== Constructors ============================================ */
    public SubCategoryAdapter(Context context) {
        super(context);
    }

    public SubCategoryAdapter(Context context, OnITFRecyclerViewItemClick<Response> listener, ArrayList<Response> datalist) {
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
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_sub_category_item, viewGroup, false);
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
                ITFTextView tvDistance = (ITFTextView) viewHolder.getView(R.id.tvDistance);
                tvCatName.setText(dataModel.getName());



                LogUtils.LOGD("GPS" , "lat "+GPSTracker.getCurrentLatitude());
                LogUtils.LOGD("GPS" , "lng "+GPSTracker.getCurrLongitude());



                src = new Location("");
                dest = new Location("");

                tempDestLat = dataModel.getGeoLocation().getLat();
                tempDestLng = dataModel.getGeoLocation().getLong();


                src.setLatitude(tempSrcLat);
                src.setLongitude(tempSrcLng);

                dest.setLatitude(dataModel.getGeoLocation().getLat());
                dest.setLongitude(dataModel.getGeoLocation().getLong());


                double nearByDist = GPSTracker.distanceBetweenSourceAndVenue(src , dest);
                if (nearByDist >= 1000 && nearByDist<=10000)
                {

                    nearByDist = GPSTracker.convertMetreToKm(nearByDist);
                    String strDistanceInkm = NumberFormatter.getRoundOffForDoubleValue(nearByDist)+" Km";

                    tvDistance.setText(strDistanceInkm);

                }
                else if (nearByDist > 10000)
                {

                    nearByDist = GPSTracker.convertMetersToMiles(nearByDist);
                    String strDistanceInMiles =  NumberFormatter.getRoundOffForDoubleValue(nearByDist)+" M";
                    tvDistance.setText(strDistanceInMiles);

                }

                else
                {

                    String strDistanceInMeter =  NumberFormatter.getRoundOffForDoubleValue(nearByDist)+" m";
                    tvDistance.setText(strDistanceInMeter);
                }



                if (new Float(dataModel.getAverageRating()) != null)
                    rbCatRating.setRating(dataModel.getAverageRating());

                imgMarkFavourite.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        favouriteListener.addFavouriteClicked(dataModel.getId());
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
                if (row.getName().toLowerCase().contains(filterString.toLowerCase())) {
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


    //endregion /* =================================== User Define Methods ===================================== */


}



