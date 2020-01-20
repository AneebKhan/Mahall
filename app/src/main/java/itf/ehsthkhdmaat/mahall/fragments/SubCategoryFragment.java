package itf.ehsthkhdmaat.mahall.fragments;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.drawable.GradientDrawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.reflect.TypeToken;
import com.itf.phatbooskiandroid.adapters.ITFRecyclerViewAdapter;
import com.itf.phatbooskiandroid.classes.CustomApplication;
import com.itf.phatbooskiandroid.fragments.UnAuthenticFragment;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnItemSelected;
import directives.ITFEditText;
import directives.ITFTextView;
import interfaces.BottomAppBarIconChangeListener;
import itf.ehsthkhdmaat.mahall.R;
import itf.ehsthkhdmaat.mahall.activities.DashboardActivity;
import itf.ehsthkhdmaat.mahall.adapters.DashboardAdapter;
import itf.ehsthkhdmaat.mahall.adapters.SubCategoryAdapter;

import itf.ehsthkhdmaat.mahall.adapters.VenueGridViewAdapter;
import itf.ehsthkhdmaat.mahall.classes.keys.BundleKeys;
import itf.ehsthkhdmaat.mahall.enums.ViewStages;
import itf.ehsthkhdmaat.mahall.models.bundle.SubCategoryBundle;
import itf.ehsthkhdmaat.mahall.models.bundle.SubCategoryDetailsBundle;
import itf.ehsthkhdmaat.mahall.models.service_model.venue_categories.Response;

import managers.PreferenceManager;
import managers.TransactionManager;
import utilities.LogUtils;
import utilities.ToastUtilities;
import utilities.UIHelper;

import static com.itf.phatbooskiandroid.classes.ScreenNames.FEATURES_SCREEN;

import static com.itf.phatbooskiandroid.classes.ScreenNames.VENUE_DETAILS_FRAGMENT;
import static keys.GetTransactionKeys.GET_VENUES_PER_CATEGORY;
import static keys.GetTransactionKeys.GET_VENUE_PARENT_CATEGORIES;
import static keys.PreferenceKeys.KEY_CUSTOMER_ID;

/**
 * Created by aneebahmed on 12/16/2018.
 * ITF
 */

public class SubCategoryFragment extends UnAuthenticFragment implements ITFRecyclerViewAdapter.OnITFRecyclerViewItemClick,
        GoogleMap.OnMyLocationButtonClickListener,
        GoogleMap.OnMyLocationClickListener,
        OnMapReadyCallback,
        LocationListener, TextWatcher {

    //region /* ====================================== Interface =============================================== */
    //endregion /* ====================================== Interface ============================================ */

    //region /* ================================== Constant Variable =========================================== */
    @BindString(R.string.venue_sub_category)
    String subCategory;

    @BindView(R.id.tabs)
    TabLayout tabLayout;

    @BindView(R.id.viewSpinner)
    Spinner spView;

    private BottomAppBarIconChangeListener listener;

    SubCategoryBundle subCategoryBundle;

    VenueGridViewAdapter gridAdapter;
    SubCategoryAdapter listAdapter;

    ArrayList<itf.ehsthkhdmaat.mahall.models.service_model.venues.Response> arrMapList;
    ArrayList<itf.ehsthkhdmaat.mahall.models.service_model.venues.Response> allArrListData;
    ArrayList<itf.ehsthkhdmaat.mahall.models.service_model.venues.Response> tabSelectedArrData;

    /* Map Variables */
    private FusedLocationProviderClient fusedLocationClient;
    boolean isPlayServiceAvailable = false;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private boolean mPermissionDenied = false;
    String[] strSplitGeoLocation;
    String strServiceProviderLat = "", strServiceProviderLng = "";
    Double srcLat = 0.0, srcLng = 0.0;
    private GoogleMap mMap;
    private SupportMapFragment mMapFragment;
    SupportMapFragment mMapView;
    Marker marker;
    private LocationManager locationManager;
    LatLng latLng;
    Double doubleLatitude = 0.0, doubleLongitude = 0.0;
    MapViewFragment mSupportMapFragment;
    //endregion  /* ================================== Constant Variable ======================================= */


    //region /* =================================== Class Variable ============================================= */
    @BindView(R.id.subCategoryRecyclerView)
    RecyclerView mRecyclerView;

    @BindView(R.id.edtSearchBar)
    ITFEditText edtSearchBar;

    //endregion /* =================================== Class Variable ========================================== */

    //region  /* =================================== Constructors ============================================== */
    public SubCategoryFragment() {
        // Required empty public constructor
    }

    //endregion /* =================================== Constructors ============================================ */

    //region /* ================================ Getter - Setter Method ======================================== */
    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_venue_list;
    }

    @Override
    protected void setPageMenuData() {

    }

    @Override
    protected String getPageTitle() {
        return subCategory;

    }


    //endregion /* ================================ Getter - Setter Method ===================================== */

    //region /* ================================== Life Cycle Method =========================================== */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            subCategoryBundle = (SubCategoryBundle) getArguments().getSerializable(BundleKeys.SUB_CATEGORY);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this BooskiMartShopListFragment
        View rootview = super.onCreateView(inflater, container, savedInstanceState);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(CustomApplication.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        edtSearchBar.addTextChangedListener(this);


        spView.post(new Runnable() {
            public void run() {
                spView.setOnItemSelectedListener(new MyOnItemSelectedListener());
            }
        });

        initiateMapData();
        fetchVenueCategories(subCategoryBundle.getCatId(), 0, 0, false);


        return rootview;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getContext() instanceof BottomAppBarIconChangeListener) {
            listener = (BottomAppBarIconChangeListener) getContext();
            listener.toggleFabMode(true);
        }
        if (!(edtSearchBar.getText().toString().equals(""))) {
            edtSearchBar.setText("");
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {


            Log.d("AllServiceProviderFrag", "requestCode: " + requestCode);

            switch (requestCode) {

                case LOCATION_PERMISSION_REQUEST_CODE:

                    enableMyLocation();

                    break;

            }

        } else if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_DENIED) {


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

        //hideIndicator();
        if (response instanceof ArrayList) {

            if (((ArrayList) response).size() > 0 && ((ArrayList) response).get(0) instanceof Response) {
                ArrayList<Response> arrayList = (ArrayList<Response>) response;

                initTabsViews(arrayList);

                fetchVenues(subCategoryBundle.getCatId(), 0, false);

                for (Response res : arrayList) {
                    LogUtils.LOGD("Category", res.getName());
                }
            } else if (((ArrayList) response).size() > 0 && ((ArrayList) response).get(0) instanceof itf.ehsthkhdmaat.mahall.models.service_model.venues.Response) {
                hideIndicator();
                ArrayList<itf.ehsthkhdmaat.mahall.models.service_model.venues.Response> arrayList = (ArrayList<itf.ehsthkhdmaat.mahall.models.service_model.venues.Response>) response;
                arrMapList = arrayList;
                allArrListData = arrayList;
                initVenueView(arrayList);

            }
        }

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BottomAppBarIconChangeListener) {

            listener = (BottomAppBarIconChangeListener) context;
            listener.toggleFabMode(true);
        }
    }
    //endregion /* ============================= Implemented Interface Method ================================== */

    //region /* ============================= Implemented Abstract Method ====================================== */
    //endregion /* ============================= Implemented Abstract Method =================================== */

    //region /* ==================================== OnClick Methods =========================================== */
    @Override
    public void onClick(View view, int position, Object item) {
        if (item instanceof itf.ehsthkhdmaat.mahall.models.service_model.venues.Response) {

            itf.ehsthkhdmaat.mahall.models.service_model.venues.Response respObject = (itf.ehsthkhdmaat.mahall.models.service_model.venues.Response) item;
            LogUtils.LOGD(DashboardMenuFragment.class.getName(), respObject.getName() + " is selected");

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
    private void fetchVenueCategories(int parentCategoryId, int pageSize, int pageIndex, boolean withPagging) {
        showIndicator();
        Type responseType = new TypeToken<ArrayList<Response>>() {
        }.getType();
        List<Response> responses = new ArrayList<Response>();
        Class c1 = responses.getClass();
        TransactionManager<Response, ArrayList<Response>> trManager = TransactionManager.getInstance();
        trManager.getTransactionData(this, GET_VENUE_PARENT_CATEGORIES + "?ids=" + parentCategoryId + "&pageSize=" + pageSize + "&pageIndex=" + pageIndex + "&withPagging=" + withPagging, responseType);
    }

    private void fetchVenues(int categoryId, int pageIndex, boolean withPagging) {
        //showIndicator();
        Type responseType = new TypeToken<ArrayList<itf.ehsthkhdmaat.mahall.models.service_model.venues.Response>>() {
        }.getType();
        List<itf.ehsthkhdmaat.mahall.models.service_model.venues.Response> responses = new ArrayList<itf.ehsthkhdmaat.mahall.models.service_model.venues.Response>();
        Class c1 = responses.getClass();
        TransactionManager<itf.ehsthkhdmaat.mahall.models.service_model.venues.Response, ArrayList<itf.ehsthkhdmaat.mahall.models.service_model.venues.Response>> trManager = TransactionManager.getInstance();
        trManager.getTransactionData(this, GET_VENUES_PER_CATEGORY + "/" + categoryId + "?pageIndex=" + pageIndex + "&withPagging=" + withPagging, responseType);
    }

    private void initTabsViews(ArrayList<Response> arrayList) {

        tabLayout.addTab(tabLayout.newTab().setText("All").setTag(null));

        for (int k = 0; k < arrayList.size(); k++) {
            tabLayout.addTab(tabLayout.newTab().setText("" + arrayList.get(k).getName()).setTag(arrayList.get(k)));
        }

        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            View root = tabLayout.getChildAt(i);
            if (root instanceof LinearLayout) {
                ((LinearLayout) root).setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
                GradientDrawable drawable = new GradientDrawable();
                drawable.setColor(ContextCompat.getColor(getContext(), R.color.Gray));
                drawable.setSize(2, 1);
                ((LinearLayout) root).setDividerPadding(10);
                ((LinearLayout) root).setDividerDrawable(drawable);
            }
        }

        if (tabLayout.getTabCount() <= 4) {
            tabLayout.setTabMode(TabLayout.MODE_FIXED);
        } else {
            tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        }

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getTag() == null) {

                    tabSelectedArrData = allArrListData;
                    toggleDataView(spView.getSelectedItem().toString(), tabSelectedArrData);

                } else if (tab.getTag() != null) {
                    Response respObj = (Response) tab.getTag();
                    tabSelectedArrData = getListDataOnTabSelected(respObj.getId());
                    toggleDataView(spView.getSelectedItem().toString(), tabSelectedArrData);

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private void initVenueView(ArrayList<itf.ehsthkhdmaat.mahall.models.service_model.venues.Response> arrayList) {
        listAdapter = new SubCategoryAdapter(CustomApplication.getContext(), this, arrayList);
        //mRecyclerView.addItemDecoration(new DividerItemDecoration(CustomApplication.getContext(), LinearLayoutManager.VERTICAL));
        listAdapter.setList(arrayList);
        mRecyclerView.setAdapter(listAdapter);

        gridAdapter = new VenueGridViewAdapter(CustomApplication.getContext(), this, arrayList);
        gridAdapter.setList(arrayList);

        tabSelectedArrData = arrayList;
    }

    @Override
    public void onLocationChanged(Location location) {
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 10);
        mMap.animateCamera(cameraUpdate);
        locationManager.removeUpdates(this);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    @Override
    public boolean onMyLocationButtonClick() {
        return false;
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {
        enableMyLocation();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        MapsInitializer.initialize(CustomApplication.getContext());
        mMap = googleMap;

        checkLocationPermission();

    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if (spView.getSelectedItem().toString().equals(ViewStages.LISTVIEW.getStage()) && listAdapter != null) {
            listAdapter.getFilter().filter(charSequence);
        } else if (spView.getSelectedItem().toString().equals(ViewStages.GRIDVIEW.getStage()) && gridAdapter != null) {
            gridAdapter.getFilter().filter(charSequence);
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }


    public class MyOnItemSelectedListener implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

            if (tabSelectedArrData != null) {
                String selected = parent.getItemAtPosition(pos).toString();
                toggleDataView(selected, tabSelectedArrData);
            }

        }

        public void onNothingSelected(AdapterView parent) {
            // Do nothing.
        }
    }


    /**
     * Enables the My Location layer if the fine location permission has been granted.
     */
    private void enableMyLocation() {
        if (ContextCompat.checkSelfPermission(CustomApplication.getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission to access the location is missing.
//            PermissionUtils.requestPermission(((DashboardActivity) getActivity()), LOCATION_PERMISSION_REQUEST_CODE,
//                    Manifest.permission.ACCESS_FINE_LOCATION, true);

        } else if (mMap != null) {
            // Access to the location has been granted to the app.


            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setZoomControlsEnabled(true);
            mMap.setOnMyLocationButtonClickListener(this);
            mMap.setOnMyLocationClickListener(this);

            fusedLocationClient = LocationServices.getFusedLocationProviderClient(CustomApplication.getContext());

            fusedLocationClient.getLastLocation()
                    .addOnSuccessListener(((DashboardActivity) getActivity()), new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            // Got last known location. In some rare situations this can be null.
                            if (location != null) {

                                srcLat = location.getLatitude();
                                srcLng = location.getLongitude();

                                latLng = new LatLng(location.getLatitude(), location.getLongitude());

                                CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 16);
                                mMap.animateCamera(cameraUpdate);


                            }
                        }
                    });


        }


    }

    public void checkLocationPermission() {

        int permissionCheck = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION);

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
        } else {

            enableMyLocation();

        }

    }

    private void checkGooglePlayServicesAvailable() {

        final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(CustomApplication.getContext());
        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                apiAvailability.getErrorDialog(((DashboardActivity) getActivity()), resultCode, PLAY_SERVICES_RESOLUTION_REQUEST)
                        .show();
            } else {

                isPlayServiceAvailable = false;
                Log.d("AllServiceProvidersMap", "This device is not supported.");
                UIHelper.showToast(CustomApplication.getContext(), "This device doesn't support Google Play Services");
                replaceChildFragmentWithDelay(FEATURES_SCREEN, false, true, null, true);

            }
        }

        isPlayServiceAvailable = true;
        Log.d("AllServiceProvidersMap", "This device is supported.");

    }

    private void initiateMapData() {
        //mMapFragment = ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map));
        mSupportMapFragment = (MapViewFragment) getChildFragmentManager().findFragmentById(R.id.map);
        //mMapFragment.getMapAsync(this);
        mSupportMapFragment.getMapAsync(this);



        if(mSupportMapFragment != null)
            mSupportMapFragment.setListener(new MapViewFragment.OnTouchListener() {
                @Override
                public void onTouch() {
                    //scrollView.requestDisallowInterceptTouchEvent(true);
                    ((DashboardActivity)getActivity()).setNestedScroll();
                }
            });

        //mMapFragment.getView().setVisibility(View.GONE);
        mSupportMapFragment.getView().setVisibility(View.GONE);

        checkGooglePlayServicesAvailable();

        mMapView = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);

        if (isPlayServiceAvailable == true) {

            if (mMapView != null) {
                mMapView.onCreate(null);
                mMapView.onResume();
                mMapView.getMapAsync(this);

                fusedLocationClient = LocationServices.getFusedLocationProviderClient(CustomApplication.getContext());

            }

        }
    }

    private void initMarkersViews(ArrayList<itf.ehsthkhdmaat.mahall.models.service_model.venues.Response> arrayList) {

        mMap.clear();

        for (int i = 0; i < arrayList.size(); i++) {

            try {

                createMarker(arrayList.get(i).getId(), arrayList.get(i).getGeoLocation().getLat(), arrayList.get(i).getGeoLocation().getLong(), arrayList.get(i).getName(), arrayList.get(i).getDetail());
            } catch (Exception e) {
                e.printStackTrace();

            }

        }

    }

    private Marker createMarker(int venueId, double latitude, double longitude, String title, String snippet) {


        marker = mMap.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude))
                .anchor(0.5f, 0.5f)
                .title(title)
                .snippet(snippet));

        //marker = mMap.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)).title(title));


        /*MapInfoWindowModel mapInfoWindowModel = new MapInfoWindowModel();
        mapInfoWindowModel.setCompanyName(title);
        mapInfoWindowModel.setCompanyUserName(snippet);

        AllServiceProvidersInfoWindowGoogleMap infoWindowGoogleMapAdapter = new AllServiceProvidersInfoWindowGoogleMap(((DashboardActivity) getActivity()));

        mMap.setInfoWindowAdapter(infoWindowGoogleMapAdapter);
        marker.setTag(customerId);*/


        return marker;
    }

    public ArrayList<itf.ehsthkhdmaat.mahall.models.service_model.venues.Response> getListDataOnTabSelected(int subCatId) {
        ArrayList<itf.ehsthkhdmaat.mahall.models.service_model.venues.Response> respObj = new ArrayList<itf.ehsthkhdmaat.mahall.models.service_model.venues.Response>();
        if (subCatId == 0)
            return respObj;


        if (allArrListData != null) {

            for (itf.ehsthkhdmaat.mahall.models.service_model.venues.Response d : allArrListData) {
                if (d.getCategoryId() != null && d.getCategoryId() == subCatId) {
                    //respObj = d;
                    respObj.add(d);
                    //break;
                }
            }
        }

        return respObj;
    }


    private void toggleDataView(String selectedSpinner, ArrayList<itf.ehsthkhdmaat.mahall.models.service_model.venues.Response> arrList) {

        if (selectedSpinner.equals(ViewStages.LISTVIEW.getStage())) {
            mSupportMapFragment.getView().setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(CustomApplication.getContext());
            mRecyclerView.setLayoutManager(mLayoutManager);
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
            listAdapter = new SubCategoryAdapter(CustomApplication.getContext(), this, arrList);
            listAdapter.setList(arrList);
            mRecyclerView.setAdapter(listAdapter);
            listAdapter.notifyDataSetChanged();
        } else if (selectedSpinner.equals(ViewStages.GRIDVIEW.getStage())) {
            //mMapFragment.getView().setVisibility(View.GONE);
            mSupportMapFragment.getView().setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);
            int columns = 2;
            mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), columns));
            gridAdapter = new VenueGridViewAdapter(CustomApplication.getContext(), this, arrList);
            gridAdapter.setList(arrList);
            mRecyclerView.setAdapter(gridAdapter);
            gridAdapter.notifyDataSetChanged();
        } else if (selectedSpinner.equals(ViewStages.MAPVIEW.getStage())) {
            mRecyclerView.setVisibility(View.GONE);
            //mMapFragment.getView().setVisibility(View.VISIBLE);
            mSupportMapFragment.getView().setVisibility(View.VISIBLE);
            initMarkersViews(arrList);
        }

    }


    //endregion /* =================================== User Define Methods ===================================== */


}
