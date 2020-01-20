package itf.ehsthkhdmaat.mahall.fragments;

import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import models.Bundles.SnackBarBundle;

import com.google.gson.reflect.TypeToken;
import com.itf.phatbooskiandroid.adapters.ITFRecyclerViewAdapter;
import com.itf.phatbooskiandroid.classes.CustomApplication;
import com.itf.phatbooskiandroid.enums.TransactionStates;
import com.itf.phatbooskiandroid.fragments.UnAuthenticFragment;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import directives.ITFButton;
import directives.ITFTextView;
import interfaces.BottomAppBarIconChangeListener;
import itf.ehsthkhdmaat.mahall.R;
import itf.ehsthkhdmaat.mahall.adapters.CategoriesListAdapter;
import itf.ehsthkhdmaat.mahall.adapters.DashboardAdapter;
import itf.ehsthkhdmaat.mahall.adapters.DashboardSliderImageAdapter;
import itf.ehsthkhdmaat.mahall.adapters.TopRatedVenuesAdapter;
import itf.ehsthkhdmaat.mahall.adapters.VenueDetailImageAdapter;
import itf.ehsthkhdmaat.mahall.classes.keys.BundleKeys;

import itf.ehsthkhdmaat.mahall.enums.ViewStages;
import itf.ehsthkhdmaat.mahall.interfaces.onFavouriteClickedListener;

import itf.ehsthkhdmaat.mahall.models.bundle.SubCategoryBundle;
import itf.ehsthkhdmaat.mahall.models.bundle.SubCategoryDetailsBundle;
import itf.ehsthkhdmaat.mahall.models.service_model.venue_categories.Response;
import itf.ehsthkhdmaat.mahall.models.service_model.venues.VenueGallery;
import managers.PreferenceManager;
import managers.TransactionManager;
import utilities.DateFormatter;
import utilities.LogUtils;

import static com.itf.phatbooskiandroid.classes.ScreenNames.FAVOURITES_VENUES;
import static com.itf.phatbooskiandroid.classes.ScreenNames.VENUE_DETAILS_FRAGMENT;
import static com.itf.phatbooskiandroid.classes.ScreenNames.VENUE_LIST_FRAGMENT;
import static keys.GetTransactionKeys.ADD_FAVOURITE;
import static keys.GetTransactionKeys.GET_VENUES_PER_CATEGORY;
import static keys.GetTransactionKeys.GET_VENUE_DETAILS;
import static keys.GetTransactionKeys.GET_VENUE_PARENT_CATEGORIES;
import static keys.GetTransactionKeys.REMOVE_FAVOURITE;
import static keys.GetTransactionKeys.TOP_RATED_VENUES;
import static keys.PreferenceKeys.KEY_USERNAME;

/**
 * Created by aneebahmed on 12/16/2018.
 * ITF
 */

public class DashboardMenuFragment extends UnAuthenticFragment implements ITFRecyclerViewAdapter.OnITFRecyclerViewItemClick, onFavouriteClickedListener {

    //region /* ====================================== Interface =============================================== */
    //endregion /* ====================================== Interface ============================================ */

    //region /* ================================== Constant Variable =========================================== */
    @BindString(R.string.features_screen)
    String dashboard;

    private BottomAppBarIconChangeListener listener;

    //endregion  /* ================================== Constant Variable ======================================= */


    //region /* =================================== Class Variable ============================================= */
    @BindView(R.id.menuRecyclerView)
    RecyclerView mRecyclerView;

    @BindView(R.id.categoryRecyclerView)
    RecyclerView categoryRecyclerView;

    @BindView(R.id.viewpager)
    ViewPager viewpager;

    @BindView(R.id.layoutDots)
    LinearLayout layoutDots;

    @BindView(R.id.btnFeaturedMore)
    ITFButton btnFeaturedMore;

    ArrayList<itf.ehsthkhdmaat.mahall.models.service_model.venues.Response> globalArrayList;

    private ITFTextView[] dots;

    TopRatedVenuesAdapter topRatedVenuesAdapter;
    //endregion /* =================================== Class Variable ========================================== */

    //region  /* =================================== Constructors ============================================== */
    public DashboardMenuFragment() {
        // Required empty public constructor
    }

    //endregion /* =================================== Constructors ============================================ */

    //region /* ================================ Getter - Setter Method ======================================== */
    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_dashboard_menu;
    }

    @Override
    protected void setPageMenuData() {

    }

    @Override
    protected String getPageTitle() {
        return dashboard;

    }


    //endregion /* ================================ Getter - Setter Method ===================================== */

    //region /* ================================== Life Cycle Method =========================================== */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this BooskiMartShopListFragment
        View rootview = super.onCreateView(inflater, container, savedInstanceState);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(CustomApplication.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        RecyclerView.LayoutManager mHorizontalLayoutManager = new LinearLayoutManager(CustomApplication.getContext(), LinearLayoutManager.HORIZONTAL, false);
        categoryRecyclerView.setLayoutManager(mHorizontalLayoutManager);
        categoryRecyclerView.setItemAnimator(new DefaultItemAnimator());

        PreferenceManager.getInstance().put(KEY_USERNAME, "Aneeb");

        fetchParentVenueCategories(0, 0, false);
        fetchVenues(1, 10);

        return rootview;
    }

    @Override
    public void onResume() {
        super.onResume();

        if (getContext() instanceof BottomAppBarIconChangeListener) {
            listener = (BottomAppBarIconChangeListener) getContext();
            listener.toggleFabMode(false);
        }
    }

    //endregion /* ================================== Life Cycle Method ======================================== */

    //region /* ============================= Implemented Interface Method ===================================== */


    @Override
    public <T> void onFailure(T errordetail) {
        super.onFailure(errordetail);
        hideIndicator();

    }

    @Override
    public <T> void onResponse(T response) {

        hideIndicator();
        if (response instanceof ArrayList) {

            if (((ArrayList) response).size() > 0 && ((ArrayList) response).get(0) instanceof Response) {
                ArrayList<Response> arrayList = (ArrayList<Response>) response;

                //initViews(arrayList);     // Commented out
                initCategoriesData(arrayList);

                for (Response res : arrayList) {
                    LogUtils.LOGD("Category", res.getName());
                }
            } else if (((ArrayList) response).size() > 0 && ((ArrayList) response).get(0) instanceof itf.ehsthkhdmaat.mahall.models.service_model.venues.Response) {
                // Venue Details Response
                ArrayList<itf.ehsthkhdmaat.mahall.models.service_model.venues.Response> arrayList = (ArrayList<itf.ehsthkhdmaat.mahall.models.service_model.venues.Response>) response;
                globalArrayList = arrayList;
                initFeaturedSlider(arrayList);
                initTopRatedVenues(arrayList);

            }
            /*ArrayList<itf.ehsthkhdmaat.mahall.models.ehkservicemodels.venue_categories.ResponseObject> prvServicesList = (ArrayList<itf.ehsthkhdmaat.mahall.models.ehkservicemodels.venue_categories.ResponseObject>) srvListResponse.getResponseObject();*/
        } else if (response instanceof itf.ehsthkhdmaat.mahall.models.service_model.add_favourites.Response) {
            // Marked Venue Favourite response
            hideIndicator();


            topRatedVenuesAdapter.setFavouriteImage(true);


            SnackBarBundle snackBarBundle = new SnackBarBundle();
            snackBarBundle.setMessage(getString(R.string.snack_bar_text_venue_added));
            snackBarBundle.setSuccess(true);
            snackBarBundle.setAction(TransactionStates.ADDED);
            snackBarBundle.setNavigateTo(FAVOURITES_VENUES);
            snackBarBundle.setNavigationText(getString(R.string.snack_bar_btn_favourite_view));
            showSnackBar(snackBarBundle);


        } else if (response instanceof itf.ehsthkhdmaat.mahall.models.service_model.remove_favourite.Response) {
            // Remove favourite
            hideIndicator();


            topRatedVenuesAdapter.setFavouriteImage(false);


            SnackBarBundle snackBarBundle = new SnackBarBundle();
            snackBarBundle.setMessage(getString(R.string.snack_bar_text_venue_removed));
            snackBarBundle.setSuccess(true);
            snackBarBundle.setAction(TransactionStates.DELETED);
            snackBarBundle.setNavigateTo(FAVOURITES_VENUES);
            snackBarBundle.setNavigationText(getString(R.string.snack_bar_btn_favourite_view));
            showSnackBar(snackBarBundle);

        }

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BottomAppBarIconChangeListener) {

            listener = (BottomAppBarIconChangeListener) context;
            listener.toggleFabMode(false);
        }
    }
    //endregion /* ============================= Implemented Interface Method ================================== */

    //region /* ============================= Implemented Abstract Method ====================================== */
    //endregion /* ============================= Implemented Abstract Method =================================== */

    //region /* ==================================== OnClick Methods =========================================== */
    @Override
    public void onClick(View view, int position, Object item) {
        if (item instanceof Response) {

            Response respObject = (Response) item;
            LogUtils.LOGD(CategoryListFragment.class.getName(), respObject.getName() + " is selected");

            SubCategoryBundle subCategoryBundle = new SubCategoryBundle();
            subCategoryBundle.setCatId(respObject.getId());
            subCategoryBundle.setCatName(respObject.getName());
            subCategoryBundle.setParentCatId(respObject.getParentId());
            subCategoryBundle.setParentCatName(respObject.getParentCategory());

            PreferenceManager.getInstance().put(KEY_USERNAME, "Aneeb");

            Bundle bundle = new Bundle();
            bundle.putSerializable(BundleKeys.SUB_CATEGORY, subCategoryBundle);

            replaceChildFragmentWithDelay(VENUE_LIST_FRAGMENT, true, false, bundle, true);
            //replaceChildFragmentWithDelay(LOGIN_SCREEN, true, false, bundle, true);

        } else if (item instanceof itf.ehsthkhdmaat.mahall.models.service_model.venues.Response) {

            itf.ehsthkhdmaat.mahall.models.service_model.venues.Response respObject = (itf.ehsthkhdmaat.mahall.models.service_model.venues.Response) item;
            LogUtils.LOGD(CategoryListFragment.class.getName(), respObject.getName() + " is selected");

            SubCategoryDetailsBundle subCategoryBundle = new SubCategoryDetailsBundle();
            subCategoryBundle.setVenueId(respObject.getId());
            subCategoryBundle.setVenueName(respObject.getName());
            subCategoryBundle.setCatId(respObject.getCategoryId());
            subCategoryBundle.setCatName(respObject.getCategory());
            subCategoryBundle.setGeoLatitude(respObject.getGeoLocation().getLat());
            subCategoryBundle.setGeoLongitude(respObject.getGeoLocation().getLong());

            Bundle bundle = new Bundle();
            bundle.putSerializable(BundleKeys.SUB_CATEGORY_DETAILS, subCategoryBundle);

            replaceChildFragmentWithDelay(VENUE_DETAILS_FRAGMENT, true, false, bundle, true);
        }
    }
    //endregion /* ==================================== OnClick Methods ======================================== */

    //region /* =================================== User Define Methods ======================================== */
    private void fetchParentVenueCategories(int pageSize, int pageIndex, boolean withPagging) {
        showIndicator();
        Type responseType = new TypeToken<ArrayList<Response>>() {
        }.getType();
        List<Response> responses = new ArrayList<Response>();
        Class c1 = responses.getClass();
        TransactionManager<Response, ArrayList<Response>> trManager = TransactionManager.getInstance();
        trManager.getTransactionData(this, GET_VENUE_PARENT_CATEGORIES + "?pageSize=" + pageSize + "&pageIndex=" + pageIndex + "&withPagging=" + withPagging, responseType);
    }

    private void fetchVenues(int countryId, int pageSize) {
        //showIndicator();
        Type responseType = new TypeToken<ArrayList<itf.ehsthkhdmaat.mahall.models.service_model.venues.Response>>() {
        }.getType();
        List<itf.ehsthkhdmaat.mahall.models.service_model.venues.Response> responses = new ArrayList<itf.ehsthkhdmaat.mahall.models.service_model.venues.Response>();
        Class c1 = responses.getClass();
        TransactionManager<itf.ehsthkhdmaat.mahall.models.service_model.venues.Response, ArrayList<itf.ehsthkhdmaat.mahall.models.service_model.venues.Response>> trManager = TransactionManager.getInstance();
        trManager.getTransactionData(this, TOP_RATED_VENUES + "/" + countryId + "?pageSize=" + pageSize, responseType);
    }

    private void initViews(ArrayList<Response> arrayList) {

        DashboardAdapter myAdapter = new DashboardAdapter(CustomApplication.getContext(), this);
        //mRecyclerView.addItemDecoration(new DividerItemDecoration(CustomApplication.getContext(), LinearLayoutManager.VERTICAL));
        myAdapter.setList(arrayList);
        mRecyclerView.setAdapter(myAdapter);
    }

    private void initTopRatedVenues(ArrayList<itf.ehsthkhdmaat.mahall.models.service_model.venues.Response> arrayList) {

        topRatedVenuesAdapter = new TopRatedVenuesAdapter(CustomApplication.getContext(), this, arrayList);
        //mRecyclerView.addItemDecoration(new DividerItemDecoration(CustomApplication.getContext(), LinearLayoutManager.VERTICAL));
        topRatedVenuesAdapter.setList(arrayList);
        topRatedVenuesAdapter.setListener(this);
        mRecyclerView.setAdapter(topRatedVenuesAdapter);

        //TopRatedVenuesAdapter
    }

    private void initCategoriesData(ArrayList<Response> arrayList) {
        CategoriesListAdapter offersAdapter = new CategoriesListAdapter(CustomApplication.getContext(), this);
        offersAdapter.setList(arrayList);
        categoryRecyclerView.setAdapter(offersAdapter);
    }

    private void initFeaturedSlider(ArrayList<itf.ehsthkhdmaat.mahall.models.service_model.venues.Response> arrayList) {

        initializeImageSlider(arrayList.get(0).getVenueGallery());  // Image Slider

        // adding bottom dots
        addBottomDots(0);

    }

    private void initializeImageSlider(List<VenueGallery> venueGalleryList) {
        DashboardSliderImageAdapter adapterView = new DashboardSliderImageAdapter(getContext(), venueGalleryList);
        viewpager.setAdapter(adapterView);
        viewpager.addOnPageChangeListener(viewPagerPageChangeListener);
    }

    private void addBottomDots(int currentPage) {
        dots = new ITFTextView[globalArrayList.get(0).getVenueGallery().size()];

        layoutDots.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new ITFTextView(getContext());
            dots[i].setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setGravity(Gravity.CENTER | Gravity.BOTTOM);
            //dots[i].setTextSize(35);
            dots[i].setTextSize(TypedValue.COMPLEX_UNIT_SP, getResources().getDimension(R.dimen._14ssp));
            dots[i].setTextColor(ContextCompat.getColor(getContext(), R.color.Gray));
            layoutDots.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(ContextCompat.getColor(getContext(), R.color.Blue));
    }

    private int getItem(int i) {
        return viewpager.getCurrentItem() + i;
    }

    //  viewpager change listener
    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);

            // changing the next button text 'NEXT' / 'GOT IT'
            if (position == 4 - 1) {
                // last page. make button text to GOT IT
                //btnNext.setText(getString(R.string.start));
                //btnSkip.setVisibility(View.GONE);
            } else {
                // still pages are left
                //btnNext.setText(getString(R.string.next));
                //btnSkip.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };

    @Override
    public void addFavouriteClicked(int venueId) {

        itf.ehsthkhdmaat.mahall.models.service_model.add_favourites.Request reqObj = new itf.ehsthkhdmaat.mahall.models.service_model.add_favourites.Request();
        reqObj.setVenueId(venueId);
        reqObj.setRecordStatusId(2);
        reqObj.setCreatedBy(PreferenceManager.getInstance().getString(KEY_USERNAME));
        reqObj.setCreatedOn(DateFormatter.getCurrentDateTime());
        addFavourites(reqObj);

    }

    @Override
    public void removeFavouriteClicked(int venueId) {
        removeFavourite(venueId, PreferenceManager.getInstance().getString(KEY_USERNAME));
    }

    private void addFavourites(itf.ehsthkhdmaat.mahall.models.service_model.add_favourites.Request reqObj) {
        showIndicator();
        TransactionManager<itf.ehsthkhdmaat.mahall.models.service_model.add_favourites.Request, itf.ehsthkhdmaat.mahall.models.service_model.add_favourites.Response> trManager = TransactionManager.getInstance();
        trManager.postTransactionData(this, ADD_FAVOURITE, reqObj, itf.ehsthkhdmaat.mahall.models.service_model.add_favourites.Response.class);
    }

    private void removeFavourite(int venueId, String userId) {
        showIndicator();
        TransactionManager<itf.ehsthkhdmaat.mahall.models.service_model.remove_favourite.Response, itf.ehsthkhdmaat.mahall.models.service_model.remove_favourite.Response> trManager = TransactionManager.getInstance();
        trManager.deleteTransactionData(this, String.format(REMOVE_FAVOURITE, userId, venueId), itf.ehsthkhdmaat.mahall.models.service_model.remove_favourite.Response.class);
    }


    //endregion /* =================================== User Define Methods ===================================== */


}
