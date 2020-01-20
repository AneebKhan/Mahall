package itf.ehsthkhdmaat.mahall.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import models.Bundles.SnackBarBundle;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import com.google.android.material.snackbar.Snackbar;
import com.itf.phatbooskiandroid.activities.MasterActivity;
import com.itf.phatbooskiandroid.enums.TransactionStates;
import com.itf.phatbooskiandroid.fragments.CoreFragment;

import butterknife.BindView;
import directives.ITFButton;
import directives.ITFImageView;
import directives.ITFTextView;
import interfaces.BottomAppBarIconChangeListener;
import interfaces.OnDisplaySnackBarClickedListener;
import itf.ehsthkhdmaat.mahall.R;

import itf.ehsthkhdmaat.mahall.fragments.BottomSheetNavigationFragment;

import itf.ehsthkhdmaat.mahall.interfaces.onNavBottomSheetItemListener;

import managers.ApplicationStateManager;
import utilities.UIHelper;

import static com.itf.phatbooskiandroid.classes.ScreenNames.COUNTRY_SELECT_FRAGMENT;
import static com.itf.phatbooskiandroid.classes.ScreenNames.DEFAULT_SCREEN;

public class DashboardActivity extends MasterActivity implements CoreFragment.OnFragmentInteractionListener, View.OnClickListener, BottomAppBarIconChangeListener, onNavBottomSheetItemListener, OnDisplaySnackBarClickedListener<SnackBarBundle> {

    private BottomAppBar bottomAppBar;

    @BindView(R.id.fab)
    FloatingActionButton fab;

    @BindView(R.id.nestedScroll)
    NestedScrollView nestedScroll;

    @BindView(R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_dashboard;
    }

    @Override
    protected int getContainerId() {
        return R.id.content_area;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String action = intent.getAction();
        Uri data = intent.getData();

        setUpBottomAppBar();

        //click event over FAB
        /*findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DashboardActivity.this, "FAB Clicked.", Toast.LENGTH_SHORT).show();
            }
        });*/

        restoreState(savedInstanceState);

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onNavigateNextFragment(@NonNull String fragmentName, boolean goNext, boolean goBack, Bundle params, boolean addToBackStack) {
        replaceChildFragmentWithDelay(getContainerId(), fragmentName, goNext, goBack, params, addToBackStack);
    }

    @Override
    public void onAuthenticateUserBeforeNavigate(@NonNull String nextScreen, Bundle nextScreenBundle) {
        authenticateUserBeforeNavigate(getContainerId(), nextScreen, nextScreenBundle);
    }

    @Override
    public void setPageTitle(String title) {

    }

    @Override
    public void setSearchIcon(boolean state) {

    }

    /**
     * set up Bottom Bar
     */
    private void setUpBottomAppBar() {
        //find id
        bottomAppBar = findViewById(R.id.bar);

        //set bottom bar to Action bar as it is similar like Toolbar
        setSupportActionBar(bottomAppBar);

        //click event over Bottom bar menu item
        bottomAppBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.bottom_app_bar_menu_share:
                        //Toast.makeText(DashboardActivity.this, "Notification clicked.", Toast.LENGTH_SHORT).show();
                        replaceChildFragmentWithDelay(getContainerId(), COUNTRY_SELECT_FRAGMENT, false, false, null, true);
                        break;
                }
                return false;
            }
        });

        /*//click event over navigation menu like back arrow or hamburger icon
        bottomAppBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //open bottom sheet
                BottomSheetDialogFragment bottomSheetDialogFragment = BottomSheetNavigationFragment.newInstance();
                bottomSheetDialogFragment.show(getSupportFragmentManager(), "Bottom Sheet Dialog Fragment");
            }
        });*/
    }

    //Inflate menu to bottom bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bottom_app_bar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.bottom_app_bar_menu_share:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * method to toggle fab mode
     *
     * @param view
     */
    public void toggleFabMode(View view) {
        //check the fab alignment mode and toggle accordingly
        /*if (bottomAppBar.getFabAlignmentMode() == BottomAppBar.FAB_ALIGNMENT_MODE_END) {
            bottomAppBar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_CENTER);
        } else {
            bottomAppBar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_END);
        }*/

        toggleFabMode(false);
    }

    @Override
    public void toggleFabMode(boolean status) {
        onBurgerIconInBottomBar(status);
        if (!status) {
            bottomAppBar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_CENTER);
        } else {
            bottomAppBar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_END);
        }
    }

    private void restoreState(final @Nullable Bundle savedInstanceState) {
        // This allow us to know if the activity was recreated
        // after orientation change and restore the Toolbar title
        if (savedInstanceState == null) {
            if (ApplicationStateManager.getInstance().getIsCountrySetFirstTime())
                showDefaultFragment();
            else
                showCountryFragment();
        }
    }

    private void showDefaultFragment() {
        replaceChildFragmentWithDelay(getContainerId(), DEFAULT_SCREEN, false, false, null, true);
        //replaceChildFragmentWithDelay(getContainerId(), LOGIN_SCREEN, false, false, null, true);
    }

    private void showTutorialFragment() {
        //replaceChildFragmentWithDelay(getContainerId(), TUTORIAL_SCREEN, false, false, null, true);
    }

    private void showCountryFragment() {
        replaceChildFragmentWithDelay(getContainerId(), COUNTRY_SELECT_FRAGMENT, false, false, null, true);
    }


    public void onBurgerIconInBottomBar(boolean show) {

        if (show) {
            bottomAppBar.setNavigationIcon(R.drawable.ic_menu_on_surface_24dp);
            bottomAppBar.setEnabled(true);
            //click event over navigation menu like back arrow or hamburger icon
            bottomAppBar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //open bottom sheet
                    BottomSheetDialogFragment bottomSheetDialogFragment = BottomSheetNavigationFragment.newInstance();
                    ((BottomSheetNavigationFragment) bottomSheetDialogFragment).setListener(DashboardActivity.this);
                    bottomSheetDialogFragment.show(getSupportFragmentManager(), "Bottom Sheet Dialog Fragment");
                }
            });

            fab.setImageResource(R.drawable.ic_home_white_24dp);

            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    replaceChildFragmentWithDelay(getContainerId(), DEFAULT_SCREEN, false, false, null, true);
                }
            });

        } else {
            bottomAppBar.setNavigationIcon(R.drawable.ic_menu_on_surface_green_24dp);
            bottomAppBar.setNavigationOnClickListener(null);
            fab.setImageResource(R.drawable.ic_menu_on_surface_24dp);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //open bottom sheet
                    BottomSheetDialogFragment bottomSheetDialogFragment = BottomSheetNavigationFragment.newInstance();
                    ((BottomSheetNavigationFragment) bottomSheetDialogFragment).setListener(DashboardActivity.this);
                    bottomSheetDialogFragment.show(getSupportFragmentManager(), "Bottom Sheet Dialog Fragment");
                }
            });

        }

    }

    public void setNestedScroll() {
        nestedScroll.requestDisallowInterceptTouchEvent(true);
    }

    @Override
    public void onNavItemClicked(String navItem) {

        replaceChildFragmentWithDelay(getContainerId(), navItem, false, false, null, true);

    }

    @Override
    public void onSnackBarClicked(SnackBarBundle snackObj) {
        Snackbar snackbar = Snackbar.make(coordinatorLayout, "", Snackbar.LENGTH_LONG);
        Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) snackbar.getView();

        View snackView = getLayoutInflater().inflate(R.layout.snackbar_venue_favourite_success, null);
        RelativeLayout rlContainer = (RelativeLayout) snackView.findViewById(R.id.rlContainer);
        ITFImageView imgStatus = (ITFImageView) snackView.findViewById(R.id.imgStatus);
        ITFButton btnAction = (ITFButton) snackView.findViewById(R.id.btnAction);
        ITFTextView textViewTop = (ITFTextView) snackView.findViewById(R.id.tvStatus);

        RelativeLayout.LayoutParams imgParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );


        if (snackObj.isSuccess()) {
            rlContainer.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.Green));
            if (snackObj.getAction() == TransactionStates.ADDED) {
                imgStatus.setBackgroundResource(R.drawable.ic_check_circle_white_24dp);
            } else if (snackObj.getAction() == TransactionStates.UPDATED) {
                imgStatus.setBackgroundResource(R.drawable.ic_edit_white_24dp);
            } else if (snackObj.getAction() == TransactionStates.DELETED) {
                imgStatus.setBackgroundResource(R.drawable.ic_delete_white_24dp);
            } else if (snackObj.getAction() == TransactionStates.WARNING) {
                imgStatus.setBackgroundResource(R.drawable.ic_warning_white_24dp);
            }

        } else {
            rlContainer.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.Red));
            imgStatus.setBackgroundResource(R.drawable.ic_error_white_24dp);
        }

        if (snackObj.getNavigateTo() != null) {
            btnAction.setVisibility(View.VISIBLE);
            btnAction.setText(snackObj.getNavigationText());
        } else {
            btnAction.setVisibility(View.GONE);
        }
        btnAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceChildFragmentWithDelay(getContainerId(), snackObj.getNavigateTo(), false, false, null, true);
            }
        });

        textViewTop.setText(snackObj.getMessage());
        textViewTop.setTextColor(Color.WHITE);

        layout.setPadding(0, 0, 0, 0);
        layout.addView(snackView, 0);

        imgParams.setMargins(UIHelper.getDimenData(R.dimen._20sdp), 0, UIHelper.getDimenData(R.dimen._10sdp), 0);
        imgParams.addRule(RelativeLayout.CENTER_VERTICAL);
        imgStatus.setLayoutParams(imgParams);
        imgStatus.setPadding(UIHelper.getDimenData(R.dimen.design_snackbar_padding_horizontal), UIHelper.getDimenData(R.dimen.design_snackbar_padding_vertical), UIHelper.getDimenData(R.dimen.design_snackbar_padding_horizontal), UIHelper.getDimenData(R.dimen.design_snackbar_padding_vertical));

        snackbar.show();
    }


}
