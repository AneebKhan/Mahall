package itf.ehsthkhdmaat.mahall.fragments;

import android.content.Context;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.reflect.TypeToken;
import com.itf.phatbooskiandroid.adapters.ITFRecyclerViewAdapter;
import com.itf.phatbooskiandroid.classes.CustomApplication;
import com.itf.phatbooskiandroid.fragments.UnAuthenticFragment;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindString;
import butterknife.BindView;

import interfaces.BottomAppBarIconChangeListener;
import itf.ehsthkhdmaat.mahall.R;
import itf.ehsthkhdmaat.mahall.adapters.DashboardAdapter;
import itf.ehsthkhdmaat.mahall.classes.keys.BundleKeys;
import itf.ehsthkhdmaat.mahall.models.bundle.SubCategoryBundle;
import itf.ehsthkhdmaat.mahall.models.service_model.venue_categories.Response;
import managers.PreferenceManager;
import managers.TransactionManager;
import utilities.LogUtils;
import viewmodels.DashboardMenuViewModel;

import static com.itf.phatbooskiandroid.classes.ScreenNames.VENUE_LIST_FRAGMENT;
import static keys.GetTransactionKeys.GET_VENUE_PARENT_CATEGORIES;
import static keys.PreferenceKeys.KEY_USERNAME;

/**
 * Created by aneebahmed on 12/16/2018.
 * ITF
 */

public class CategoryListFragment extends UnAuthenticFragment implements ITFRecyclerViewAdapter.OnITFRecyclerViewItemClick {

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

    List<DashboardMenuViewModel> mDashboardMenuViewModelList;
    //endregion /* =================================== Class Variable ========================================== */

    //region  /* =================================== Constructors ============================================== */
    public CategoryListFragment() {
        // Required empty public constructor
    }

    //endregion /* =================================== Constructors ============================================ */

    //region /* ================================ Getter - Setter Method ======================================== */
    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_category_list;
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

        fetchParentVenueCategories(0,0, false);

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

                initViews(arrayList);

                for (Response res : arrayList) {
                    LogUtils.LOGD("Category", res.getName());
                }
            }
            /*ArrayList<itf.ehsthkhdmaat.mahall.models.ehkservicemodels.venue_categories.ResponseObject> prvServicesList = (ArrayList<itf.ehsthkhdmaat.mahall.models.ehkservicemodels.venue_categories.ResponseObject>) srvListResponse.getResponseObject();*/
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
            LogUtils.LOGD(DashboardMenuFragment.class.getName(), respObject.getName() + " is selected");

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

    private void initViews(ArrayList<Response> arrayList) {

        DashboardAdapter myAdapter = new DashboardAdapter(CustomApplication.getContext(), this);
        //mRecyclerView.addItemDecoration(new DividerItemDecoration(CustomApplication.getContext(), LinearLayoutManager.VERTICAL));
        myAdapter.setList(arrayList);
        mRecyclerView.setAdapter(myAdapter);
    }

    //endregion /* =================================== User Define Methods ===================================== */


}
