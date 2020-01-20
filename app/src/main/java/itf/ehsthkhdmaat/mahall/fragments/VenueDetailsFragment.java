package itf.ehsthkhdmaat.mahall.fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.gson.reflect.TypeToken;
import com.itf.phatbooskiandroid.adapters.ITFRecyclerViewAdapter;
import com.itf.phatbooskiandroid.classes.CustomApplication;
import com.itf.phatbooskiandroid.enums.TransactionStates;
import com.itf.phatbooskiandroid.fragments.UnAuthenticFragment;
import models.Bundles.SnackBarBundle;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;
import directives.ITFButton;
import directives.ITFEditText;
import directives.ITFImageView;
import directives.ITFTextView;
import interfaces.BottomAppBarIconChangeListener;
import itf.ehsthkhdmaat.mahall.BuildConfig;
import itf.ehsthkhdmaat.mahall.R;
import itf.ehsthkhdmaat.mahall.adapters.VenueDetailImageAdapter;
import itf.ehsthkhdmaat.mahall.adapters.VenueOffersAdapter;
import itf.ehsthkhdmaat.mahall.adapters.VenueReviewsAdapter;
import itf.ehsthkhdmaat.mahall.classes.keys.BundleKeys;


import itf.ehsthkhdmaat.mahall.models.bundle.SubCategoryDetailsBundle;
import itf.ehsthkhdmaat.mahall.models.service_model.venues.RateAndReview;
import itf.ehsthkhdmaat.mahall.models.service_model.venues.Response;
import itf.ehsthkhdmaat.mahall.models.service_model.venues.VenueGallery;
import itf.ehsthkhdmaat.mahall.models.service_model.venues.VenueOffer;
import itf.ehsthkhdmaat.mahall.models.service_model.add_review.Request;
import managers.PreferenceManager;
import managers.TransactionManager;
import utilities.DateFormatter;
import utilities.LogUtils;

import static com.itf.phatbooskiandroid.classes.ScreenNames.FAVOURITES_VENUES;
import static keys.GetTransactionKeys.ADD_FAVOURITE;
import static keys.GetTransactionKeys.CHECK_IS_FAVOURITE;
import static keys.GetTransactionKeys.GET_REVIEWS_PER_VENUE;
import static keys.GetTransactionKeys.GET_VENUE_DETAILS;
import static keys.GetTransactionKeys.REMOVE_FAVOURITE;
import static keys.GetTransactionKeys.SUBMIT_REVIEW;
import static keys.PreferenceKeys.KEY_USERNAME;

/**
 * Created by aneebahmed on 12/16/2018.
 * ITF
 */

public class VenueDetailsFragment extends UnAuthenticFragment implements ITFRecyclerViewAdapter.OnITFRecyclerViewItemClick {

    //region /* ====================================== Interface =============================================== */
    //endregion /* ====================================== Interface ============================================ */

    //region /* ================================== Constant Variable =========================================== */
    @BindString(R.string.venue_sub_category)
    String subCategory;

    @BindView(R.id.tvVenueName)
    ITFTextView tvVenueName;

    @BindView(R.id.tvVenueDetail)
    ITFTextView tvVenueDetail;

    @BindView(R.id.tvVenueWebsite)
    ITFTextView tvVenueWebsite;

    @BindView(R.id.tvVenueContact)
    ITFTextView tvVenueContact;

    @BindView(R.id.tvVenueAddress)
    ITFTextView tvVenueAddress;

    @BindView(R.id.viewpager)
    ViewPager viewpager;

    @BindView(R.id.layoutDots)
    LinearLayout layoutDots;

    @BindView(R.id.rbCatRating)
    RatingBar rbCatRating;

    @BindView(R.id.imgShare)
    ITFImageView imgShare;

    @BindView(R.id.imgFavourite)
    ITFImageView imgFavourite;

    @BindView(R.id.rlVenueOffersRecyclerView)
    RecyclerView venueRecyclerView;

    @BindView(R.id.rlVenueReviewsRecyclerView)
    RecyclerView rlVenueReviewsRecyclerView;

    private ITFTextView[] dots;

    private BottomAppBarIconChangeListener listener;

    ArrayList<Response> globalArrayList;

    Dialog reviewDialog;

    boolean isMarkedFavourite = false;

    LayoutInflater parentInflater;
    ViewGroup parentContainer;

    //endregion  /* ================================== Constant Variable ======================================= */


    //region /* =================================== Class Variable ============================================= */
    SubCategoryDetailsBundle subCategoryDetailsBundle;
    //endregion /* =================================== Class Variable ========================================== */

    //region  /* =================================== Constructors ============================================== */
    public VenueDetailsFragment() {
        // Required empty public constructor
    }

    //endregion /* =================================== Constructors ============================================ */

    //region /* ================================ Getter - Setter Method ======================================== */
    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_venue_details;
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
            subCategoryDetailsBundle = (SubCategoryDetailsBundle) getArguments().getSerializable(BundleKeys.SUB_CATEGORY_DETAILS);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this BooskiMartShopListFragment
        View rootview = super.onCreateView(inflater, container, savedInstanceState);
        parentInflater = inflater;
        parentContainer = container;

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(CustomApplication.getContext(), LinearLayoutManager.HORIZONTAL, false);
        venueRecyclerView.setLayoutManager(mLayoutManager);
        venueRecyclerView.setItemAnimator(new DefaultItemAnimator());

        RecyclerView.LayoutManager mLayoutManagerReview = new LinearLayoutManager(CustomApplication.getContext());
        rlVenueReviewsRecyclerView.setLayoutManager(mLayoutManagerReview);
        rlVenueReviewsRecyclerView.setItemAnimator(new DefaultItemAnimator());

        fetchVenueDetails(subCategoryDetailsBundle.getVenueId(), PreferenceManager.getInstance().getString(KEY_USERNAME), 0, false);
        //fetchIsMarkedFavourite(subCategoryDetailsBundle.getVenueId(), PreferenceManager.getInstance().getString(KEY_USERNAME));

        /*VenueDetailImageAdapter adapterView = new VenueDetailImageAdapter(getContext(), subCategoryDetailsBundle.getResponseObject().getVenueGallery());
        viewpager.setAdapter(adapterView);
        viewpager.addOnPageChangeListener(viewPagerPageChangeListener);

        initViews(subCategoryDetailsBundle.getResponseObject());

        // adding bottom dots
        addBottomDots(0);*/

        /*imgFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create the Snackbar
                Snackbar snackbar = Snackbar.make(container, "", Snackbar.LENGTH_LONG);
                // Get the Snackbar's layout view
                Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) snackbar.getView();
                // Hide the text
        *//*ITFTextView textView = (ITFTextView) layout.findViewById(R.id.tvStatus);
        textView.setVisibility(View.INVISIBLE);*//*

                // Inflate our custom view
                View snackView = inflater.inflate(R.layout.snackbar_venue_favourite_success, null);
                // Configure the view
                ITFImageView imgStatus = (ITFImageView) snackView.findViewById(R.id.imgStatus);
                //imgStatus.setImageBitmap(R.drawable.ic_check_circle_white_24dp);
                ITFTextView textViewTop = (ITFTextView) snackView.findViewById(R.id.tvStatus);
                textViewTop.setText("Success");
                textViewTop.setTextColor(Color.WHITE);

                //If the view is not covering the whole snackbar layout, add this line
                //layout.setPadding(0,0,0,(int) (getResources().getDimension(R.dimen._60sdp) / getResources().getDisplayMetrics().density));
                layout.setPadding(0,0,0, 0);

                // Add the view to the Snackbar's layout
                layout.addView(snackView, 0);
                // Show the Snackbar
                snackbar.show();
            }
        });*/

        return rootview;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getContext() instanceof BottomAppBarIconChangeListener) {
            listener = (BottomAppBarIconChangeListener) getContext();
            listener.toggleFabMode(true);
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
                // Venue Details Response
                ArrayList<Response> arrayList = (ArrayList<Response>) response;
                globalArrayList = arrayList;
                initViews(arrayList);

            } else if (((ArrayList) response).size() > 0 && ((ArrayList) response).get(0) instanceof RateAndReview) {
                // Rate and Review response list
                ArrayList<RateAndReview> arrayList = (ArrayList<RateAndReview>) response;
                initVenueReviews(arrayList);
            } else if (((ArrayList) response).size() > 0 && ((ArrayList) response).get(0) instanceof itf.ehsthkhdmaat.mahall.models.service_model.favourites.Response) {
                // Response of marked favourite
                ArrayList<itf.ehsthkhdmaat.mahall.models.service_model.favourites.Response> arrayList = (ArrayList<itf.ehsthkhdmaat.mahall.models.service_model.favourites.Response>) response;
                isMarkedFavourite = true;
                markFavourite(true);
            }
        } else if (response instanceof itf.ehsthkhdmaat.mahall.models.service_model.add_review.Response) {
                // Response after review added successfully
            reviewDialog.dismiss();
            fetchReviewsPerVenue(subCategoryDetailsBundle.getVenueId(), 0, 0, false);
        } else if (response instanceof itf.ehsthkhdmaat.mahall.models.service_model.add_favourites.Response) {
            // Marked Venue Favourite response
            isMarkedFavourite = true;
            markFavourite(true);

            SnackBarBundle snackBarBundle = new SnackBarBundle();
            snackBarBundle.setMessage(getString(R.string.snack_bar_text_venue_added));
            snackBarBundle.setSuccess(true);
            snackBarBundle.setAction(TransactionStates.ADDED);
            showSnackBar(snackBarBundle);

        } else if (response instanceof itf.ehsthkhdmaat.mahall.models.service_model.remove_favourite.Response) {
            // Remove favourite
            isMarkedFavourite = false;
            markFavourite(false);

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

        if (item instanceof VenueOffer) {

            VenueOffer respObject = (VenueOffer) item;
            LogUtils.LOGD(DashboardMenuFragment.class.getName(), respObject.getName() + " is selected");

            //open bottom sheet
            BottomSheetVenueOffersFragment bottomSheetDialogFragment = BottomSheetVenueOffersFragment.newInstance(respObject);
            bottomSheetDialogFragment.show(getFragmentManager(), "Bottom Sheet Dialog Fragment");


        }
    }

    @OnClick(R.id.imgShare)
    public void onShareClicked() {
        /*Intent share = new Intent(android.content.Intent.ACTION_SEND);
        share.setType("text/plain");
        share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);

        // Add data to the intent, the receiving app will decide
        // what to do with it.
        share.putExtra(Intent.EXTRA_SUBJECT, "Mahall");
        share.putExtra(Intent.EXTRA_TEXT, subCategoryDetailsBundle.getVenueName());

        startActivity(Intent.createChooser(share, "Share link!"));*/

        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Mahall");
        String shareMessage = "\nLet me recommend you this application\n\n";
        shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID + "\n\n";
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
        startActivity(Intent.createChooser(shareIntent, "Share link!"));
    }

    @OnClick(R.id.imgFavourite)
    public void onFavouriteClicked() {

        if (isMarkedFavourite) {

            removeFavourite(subCategoryDetailsBundle.getVenueId(), PreferenceManager.getInstance().getString(KEY_USERNAME));

        } else {
            itf.ehsthkhdmaat.mahall.models.service_model.add_favourites.Request reqObj = new itf.ehsthkhdmaat.mahall.models.service_model.add_favourites.Request();
            reqObj.setVenueId(subCategoryDetailsBundle.getVenueId());
            reqObj.setRecordStatusId(2);
            reqObj.setCreatedBy(PreferenceManager.getInstance().getString(KEY_USERNAME));
            reqObj.setCreatedOn(DateFormatter.getCurrentDateTime());
            addFavourites(reqObj);
        }

    }

    @OnClick(R.id.txtWriteReview)
    public void onWriteReviewClicked() {

        reviewDialog = new Dialog(getContext());
        reviewDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        reviewDialog.setCancelable(false);
        reviewDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        reviewDialog.setContentView(R.layout.dialog_write_review);

        ITFImageView dialogButton = (ITFImageView) reviewDialog.findViewById(R.id.imgClose);
        ITFEditText edtReview = (ITFEditText) reviewDialog.findViewById(R.id.edtReview);
        RatingBar rbReview = (RatingBar) reviewDialog.findViewById(R.id.rbReview);
        ITFButton btnSubmit = (ITFButton) reviewDialog.findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtReview.getText().toString().isEmpty()) {

                    SnackBarBundle snackBarBundle = new SnackBarBundle();
                    snackBarBundle.setMessage(getString(R.string.dialog_write_review_text_empty));
                    snackBarBundle.setSuccess(true);
                    snackBarBundle.setAction(TransactionStates.ADDED);
                    showSnackBar(snackBarBundle);

                    return;
                }

                Request reqObj = new Request();
                reqObj.setVenueId(subCategoryDetailsBundle.getVenueId());
                reqObj.setDetail(edtReview.getText().toString());
                reqObj.setPoint(rbReview.getRating());
                reqObj.setCreatedBy(PreferenceManager.getInstance().getString(KEY_USERNAME));
                reqObj.setCreatedOn(DateFormatter.getCurrentDateTime());
                reqObj.setRecordStatusId(1);

                postReviewData(reqObj);

            }
        });


        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reviewDialog.dismiss();
            }
        });


        reviewDialog.show();
    }


    //endregion /* ==================================== OnClick Methods ======================================== */

    //region /* =================================== User Define Methods ======================================== */
    private void fetchVenueDetails(int venueId, String userName, int pageIndex, boolean withPagging) {
        showIndicator();
        Type responseType = new TypeToken<ArrayList<Response>>() {
        }.getType();
        List<Response> responses = new ArrayList<Response>();
        Class c1 = responses.getClass();
        TransactionManager<Response, ArrayList<Response>> trManager = TransactionManager.getInstance();
        //trManager.getTransactionData(this, GET_VENUE_DETAILS + "/?ids=" + venueId + "&username=" + userName + "&pageIndex=" + pageIndex + "&withPagging=" + withPagging, responseType);
        trManager.getTransactionData(this, GET_VENUE_DETAILS + "?ids=" + venueId, responseType);
    }

    private void postReviewData(Request reqObj) {
        showIndicator();
        TransactionManager<Request, itf.ehsthkhdmaat.mahall.models.service_model.add_review.Response> trManager = TransactionManager.getInstance();
        trManager.postTransactionData(this, SUBMIT_REVIEW, reqObj, itf.ehsthkhdmaat.mahall.models.service_model.add_review.Response.class);
    }

    private void fetchReviewsPerVenue(int venueId, int pageSize, int pageIndex, boolean withPagging) {
        showIndicator();
        Type responseType = new TypeToken<ArrayList<RateAndReview>>() {
        }.getType();
        List<RateAndReview> responses = new ArrayList<RateAndReview>();
        Class c1 = responses.getClass();
        TransactionManager<RateAndReview, ArrayList<RateAndReview>> trManager = TransactionManager.getInstance();
        trManager.getTransactionData(this, GET_REVIEWS_PER_VENUE + "/" + venueId + "?pageSize=" + pageSize + "&pageIndex=" + pageIndex + "&withPagging=" + withPagging, responseType);
    }

    private void addFavourites(itf.ehsthkhdmaat.mahall.models.service_model.add_favourites.Request reqObj) {
        showIndicator();
        TransactionManager<itf.ehsthkhdmaat.mahall.models.service_model.add_favourites.Request, itf.ehsthkhdmaat.mahall.models.service_model.add_favourites.Response> trManager = TransactionManager.getInstance();
        trManager.postTransactionData(this, ADD_FAVOURITE, reqObj, itf.ehsthkhdmaat.mahall.models.service_model.add_favourites.Response.class);
    }

    private void fetchIsMarkedFavourite(int venueId, String userId) {
        //showIndicator();
        Type responseType = new TypeToken<ArrayList<itf.ehsthkhdmaat.mahall.models.service_model.favourites.Response>>() {
        }.getType();
        List<itf.ehsthkhdmaat.mahall.models.service_model.favourites.Response> responses = new ArrayList<itf.ehsthkhdmaat.mahall.models.service_model.favourites.Response>();
        Class c1 = responses.getClass();
        TransactionManager<itf.ehsthkhdmaat.mahall.models.service_model.favourites.Response, ArrayList<itf.ehsthkhdmaat.mahall.models.service_model.favourites.Response>> trManager = TransactionManager.getInstance();
        trManager.getTransactionData(this, String.format(CHECK_IS_FAVOURITE, userId, venueId), responseType);
    }

    private void removeFavourite(int venueId, String userId) {
        showIndicator();
        TransactionManager<itf.ehsthkhdmaat.mahall.models.service_model.remove_favourite.Response, itf.ehsthkhdmaat.mahall.models.service_model.remove_favourite.Response> trManager = TransactionManager.getInstance();
        trManager.deleteTransactionData(this, String.format(REMOVE_FAVOURITE, userId, venueId), itf.ehsthkhdmaat.mahall.models.service_model.remove_favourite.Response.class);
    }

    private void initViews(ArrayList<Response> arrayList) {

        tvVenueName.setText(arrayList.get(0).getName());
        tvVenueDetail.setText(arrayList.get(0).getDetail() + "");
        tvVenueWebsite.setText(arrayList.get(0).getWebsiteUrl() + "");
        tvVenueContact.setText(arrayList.get(0).getContactNumber() + "");
        tvVenueAddress.setText(arrayList.get(0).getAddress() + "");
        rbCatRating.setRating(arrayList.get(0).getAverageRating());

        if (arrayList.get(0).getFavourite()) {
            isMarkedFavourite = true;
            markFavourite(true);
        }

        initializeImageSlider(arrayList.get(0).getVenueGallery());  // Image Slider
        initVenueOfferData(arrayList.get(0).getVenueOffer());   // Offers Data
        initVenueReviews(arrayList.get(0).getRateAndReview());  // Review Data

        // adding bottom dots
        addBottomDots(0);

    }

    private void markFavourite(boolean checked) {

        if (checked) {
            imgFavourite.setBackgroundResource(R.drawable.ic_favorite_black_24dp);
        } else {
            imgFavourite.setBackgroundResource(R.drawable.ic_favorite_border_black_24dp);
        }


    }

    private void addBottomDots(int currentPage) {
        dots = new ITFTextView[globalArrayList.get(0).getVenueGallery().size()];

        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
        int[] colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);

        layoutDots.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new ITFTextView(getContext());
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
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

    @OnClick(R.id.txtShowDirection)
    public void mapDirectionClicked() {
        if (new Double(subCategoryDetailsBundle.getGeoLatitude()) != null) {
            String url = "https://www.google.com/maps/dir/?api=1&destination=" + subCategoryDetailsBundle.getGeoLatitude() + "," + subCategoryDetailsBundle.getGeoLongitude() + "&travelmode=driving";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        } else {
            Toast.makeText(CustomApplication.getContext(), "No Data available for directions", Toast.LENGTH_SHORT).show();
        }
    }

    private void initializeImageSlider(List<VenueGallery> venueGalleryList) {
        VenueDetailImageAdapter adapterView = new VenueDetailImageAdapter(getContext(), venueGalleryList);
        viewpager.setAdapter(adapterView);
        viewpager.addOnPageChangeListener(viewPagerPageChangeListener);
    }

    private void initVenueOfferData(List<VenueOffer> arrList) {
        VenueOffersAdapter offersAdapter = new VenueOffersAdapter(CustomApplication.getContext(), this);
        offersAdapter.setList(arrList);
        venueRecyclerView.setAdapter(offersAdapter);
    }

    private void initVenueReviews(List<RateAndReview> arrList) {
        VenueReviewsAdapter offersAdapter = new VenueReviewsAdapter(CustomApplication.getContext(), this);
        offersAdapter.setList(arrList);
        rlVenueReviewsRecyclerView.setAdapter(offersAdapter);
    }

    //endregion /* =================================== User Define Methods ===================================== */


}
