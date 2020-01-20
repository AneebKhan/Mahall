package itf.ehsthkhdmaat.mahall.fragments;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;
import com.google.android.material.chip.ChipGroup;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.itf.phatbooskiandroid.adapters.ITFRecyclerViewAdapter;
import com.itf.phatbooskiandroid.classes.CustomApplication;
import com.itf.phatbooskiandroid.fragments.UnAuthenticFragment;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import itf.ehsthkhdmaat.mahall.adapters.CountryAdapter;
import itf.ehsthkhdmaat.mahall.models.service_model.countries_list.Response;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;
import directives.ITFEditText;
import itf.ehsthkhdmaat.mahall.R;
import managers.ApplicationStateManager;
import managers.PreferenceManager;
import managers.TransactionManager;
import utilities.LogUtils;
import utilities.ToastUtilities;

import static com.itf.phatbooskiandroid.classes.ScreenNames.DEFAULT_SCREEN;
import static com.itf.phatbooskiandroid.classes.ScreenNames.FORGET_PASSWORD_SCREEN;
import static com.itf.phatbooskiandroid.classes.ScreenNames.SIGN_UP_SCREEN;
import static com.itf.phatbooskiandroid.classes.ScreenNames.VENUE_LIST_FRAGMENT;
import static keys.GetTransactionKeys.GET_COUNTRIES;
import static keys.PreferenceKeys.KEY_USER;
import static keys.PreferenceKeys.KEY_USER_COUNTRY;
import static keys.PreferenceKeys.KEY_USER_COUNTRY_ID;

/**
 * Created by aneebahmed on 12/16/2018.
 * ITF
 */

public class CountrySelectFragment extends UnAuthenticFragment implements ITFRecyclerViewAdapter.OnITFRecyclerViewItemClick, TextWatcher {

    @BindView(R.id.menuRecyclerView)
    RecyclerView mRecyclerView;

    @BindView(R.id.etCountry)
    ITFEditText etCountry;

    View rootview;

    CountryAdapter myAdapter;
    //region /* ====================================== Interface =============================================== */
    //endregion /* ====================================== Interface ============================================ */

    //region /* ================================== Constant Variable =========================================== */
    @BindString(R.string.features_screen)
    String dashboard;

    //endregion  /* ================================== Constant Variable ======================================= */

    //region /* =================================== Class Variable ============================================= */


    //endregion /* =================================== Class Variable ========================================== */

    //region  /* =================================== Constructors ============================================== */
    public CountrySelectFragment() {
        // Required empty public constructor
    }

    //endregion /* =================================== Constructors ============================================ */

    //region /* ================================ Getter - Setter Method ======================================== */
    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_country_select;
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
        rootview = super.onCreateView(inflater, container, savedInstanceState);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(CustomApplication.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        etCountry.addTextChangedListener(this);
        fetchCountries(0, false);
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

            if (((ArrayList) response).size() > 0 && ((ArrayList) response).get(0) instanceof Response) {
                ArrayList<Response> arrayList = (ArrayList<Response>) response;

                initViews(arrayList);

                for (Response res : arrayList) {
                    LogUtils.LOGD("Category", res.getName());
                }
            }

        }

    }

    private void initViews(ArrayList<Response> arrayList) {

        myAdapter = new CountryAdapter(CustomApplication.getContext(), this, arrayList);
        //mRecyclerView.addItemDecoration(new DividerItemDecoration(CustomApplication.getContext(), LinearLayoutManager.VERTICAL));
        myAdapter.setList(arrayList);
        mRecyclerView.setAdapter(myAdapter);


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
    //endregion /* ============================= Implemented Interface Method ================================== */

    //region /* ============================= Implemented Abstract Method ====================================== */
    //endregion /* ============================= Implemented Abstract Method =================================== */

    //region /* ==================================== OnClick Methods =========================================== */
    //endregion /* ==================================== OnClick Methods ======================================== */

    //region /* =================================== User Define Methods ======================================== */
    private void fetchCountries(int pageIndex, boolean withPagging) {
        showIndicator();
        Type responseType = new TypeToken<ArrayList<Response>>() {
        }.getType();
        List<Response> responses = new ArrayList<Response>();
        Class c1 = responses.getClass();
        TransactionManager<Response, ArrayList<Response>> trManager = TransactionManager.getInstance();
        trManager.getTransactionData(this, GET_COUNTRIES + "?pageIndex=" + pageIndex + "&withPagging=" + withPagging, responseType);
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
    public void onClick(View view, int position, Object item) {

        if (item instanceof Response) {

            Response respObject = (Response) item;

            Gson gson = new Gson();
            String userJson = gson.toJson(respObject);
            PreferenceManager.getInstance().put(KEY_USER_COUNTRY, userJson);
            PreferenceManager.getInstance().put(KEY_USER_COUNTRY_ID, respObject.getId());

            ApplicationStateManager.getInstance().setIsCountrySetFirstTime(true);

            replaceChildFragmentWithDelay(DEFAULT_SCREEN, true, false, null, true);


        }

    }
    //endregion /* =================================== User Define Methods ===================================== */


}
