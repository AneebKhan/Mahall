package itf.ehsthkhdmaat.mahall.fragments;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.reflect.TypeToken;
import com.itf.phatbooskiandroid.adapters.ITFRecyclerViewAdapter;
import com.itf.phatbooskiandroid.classes.CustomApplication;
import com.itf.phatbooskiandroid.enums.TransactionStates;
import com.itf.phatbooskiandroid.fragments.AuthenticFragment;
import com.itf.phatbooskiandroid.fragments.UnAuthenticFragment;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import directives.ITFEditText;
import itf.ehsthkhdmaat.mahall.R;
import itf.ehsthkhdmaat.mahall.adapters.FavouritesAdapter;
import itf.ehsthkhdmaat.mahall.classes.keys.BundleKeys;

import itf.ehsthkhdmaat.mahall.interfaces.onFavouriteClickedListener;

import models.Bundles.SnackBarBundle;
import itf.ehsthkhdmaat.mahall.models.bundle.SubCategoryDetailsBundle;
import itf.ehsthkhdmaat.mahall.models.service_model.favourites.Response;
import managers.PreferenceManager;
import managers.TransactionManager;
import utilities.LogUtils;

import static com.itf.phatbooskiandroid.classes.ScreenNames.FAVOURITES_VENUES;
import static com.itf.phatbooskiandroid.classes.ScreenNames.VENUE_DETAILS_FRAGMENT;
import static keys.GetTransactionKeys.GET_FAVOURITES;
import static keys.GetTransactionKeys.REMOVE_FAVOURITE;
import static keys.PreferenceKeys.KEY_USERNAME;

/**
 * Created by aneebahmed on 12/16/2018.
 * ITF
 */

public class FavouritesFragment extends AuthenticFragment implements ITFRecyclerViewAdapter.OnITFRecyclerViewItemClick, TextWatcher, onFavouriteClickedListener {

    //region /* ====================================== Interface =============================================== */
    //endregion /* ====================================== Interface ============================================ */

    //region /* ================================== Constant Variable =========================================== */
    @BindString(R.string.features_screen)
    String dashboard;

    //endregion  /* ================================== Constant Variable ======================================= */

    //region /* =================================== Class Variable ============================================= */
    @BindView(R.id.menuRecyclerView)
    RecyclerView mRecyclerView;

    @BindView(R.id.edtSearchBar)
    ITFEditText edtSearchBar;

    FavouritesAdapter myAdapter;

    //endregion /* =================================== Class Variable ========================================== */

    //region  /* =================================== Constructors ============================================== */
    public FavouritesFragment() {
        // Required empty public constructor
    }

    //endregion /* =================================== Constructors ============================================ */

    //region /* ================================ Getter - Setter Method ======================================== */
    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_favourites;
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

        edtSearchBar.addTextChangedListener(this);

        fetchVenueFavourites(PreferenceManager.getInstance().getString(KEY_USERNAME), 0, false);

        return rootview;
    }

    @Override
    public void onResume() {
        super.onResume();
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

            if(((ArrayList) response).size() > 0 && ((ArrayList) response).get(0) instanceof Response) {
                ArrayList<Response> arrayList = (ArrayList<Response>) response;

                initViews(arrayList);

                for (Response res : arrayList) {
                    LogUtils.LOGD("Category", res.getVenue().getName());
                }
            }

        } else if (response instanceof itf.ehsthkhdmaat.mahall.models.service_model.remove_favourite.Response) {
            // Remove favourite
            hideIndicator();
            myAdapter.removeSelectedItem(myAdapter.getSelectedItemPosition());

            SnackBarBundle snackBarBundle = new SnackBarBundle();
            snackBarBundle.setMessage(getString(R.string.snack_bar_text_venue_removed));
            snackBarBundle.setSuccess(true);
            snackBarBundle.setAction(TransactionStates.DELETED);
            showSnackBar(snackBarBundle);

        }

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
    //endregion /* ============================= Implemented Interface Method ================================== */

    //region /* ============================= Implemented Abstract Method ====================================== */
    //endregion /* ============================= Implemented Abstract Method =================================== */

    //region /* ==================================== OnClick Methods =========================================== */
    @Override
    public void onClick(View view, int position, Object item) {
        if (item instanceof Response) {

            Response respObject = (Response) item;

            SubCategoryDetailsBundle subCategoryBundle = new SubCategoryDetailsBundle();
            subCategoryBundle.setVenueId(respObject.getVenue().getId());
            subCategoryBundle.setVenueName(respObject.getVenue().getName());
            subCategoryBundle.setCatId(respObject.getVenue().getCategoryId());
            subCategoryBundle.setCatName(respObject.getVenue().getCategory());
            subCategoryBundle.setGeoLatitude(respObject.getVenue().getGeoLocation().getLat());
            subCategoryBundle.setGeoLongitude(respObject.getVenue().getGeoLocation().getLong());
            subCategoryBundle.setResponseObj(respObject.getVenue());

            Bundle bundle = new Bundle();
            bundle.putSerializable(BundleKeys.SUB_CATEGORY_DETAILS, subCategoryBundle);

            replaceChildFragmentWithDelay(VENUE_DETAILS_FRAGMENT, true, false, bundle, true);

        }
    }
    //endregion /* ==================================== OnClick Methods ======================================== */

    //region /* =================================== User Define Methods ======================================== */
    private void fetchVenueFavourites(String userName, int pageIndex, boolean withPagging) {
        showIndicator();
        Type responseType = new TypeToken<ArrayList<Response>>() {}.getType();
        List<Response> responses = new ArrayList<Response>();
        Class c1 =responses.getClass();
        TransactionManager<Response, ArrayList<Response>> trManager = TransactionManager.getInstance();
        trManager.getTransactionData(this, GET_FAVOURITES + "/" + userName + "?pageIndex=" + pageIndex + "&withPagging=" + withPagging,  responseType);
    }

    private void initViews(ArrayList<Response> arrayList) {

        myAdapter = new FavouritesAdapter(CustomApplication.getContext(), this, arrayList);
        //mRecyclerView.addItemDecoration(new DividerItemDecoration(CustomApplication.getContext(), LinearLayoutManager.VERTICAL));
        myAdapter.setList(arrayList);
        myAdapter.setListener(this);
        mRecyclerView.setAdapter(myAdapter);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        myAdapter.getFilter().filter(charSequence);
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    @Override
    public void addFavouriteClicked(int venueId) {

    }

    @Override
    public void removeFavouriteClicked(int venueId) {
        removeFavourite(venueId, PreferenceManager.getInstance().getString(KEY_USERNAME));
    }

    private void removeFavourite(int venueId, String userId) {
        showIndicator();
        TransactionManager<itf.ehsthkhdmaat.mahall.models.service_model.remove_favourite.Response, itf.ehsthkhdmaat.mahall.models.service_model.remove_favourite.Response> trManager = TransactionManager.getInstance();
        trManager.deleteTransactionData(this, String.format(REMOVE_FAVOURITE, userId, venueId), itf.ehsthkhdmaat.mahall.models.service_model.remove_favourite.Response.class);
    }

    //endregion /* =================================== User Define Methods ===================================== */


}
