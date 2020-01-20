package itf.ehsthkhdmaat.mahall.fragments;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.Date;

import directives.ITFImageView;
import directives.ITFTextView;
import itf.ehsthkhdmaat.mahall.models.service_model.venues.VenueOffer;

import itf.ehsthkhdmaat.mahall.R;
import utilities.DateFormatter;
import utilities.UIHelper;

/**
 * Created by sonu on 17:07, 10/01/19
 * Copyright (c) 2019 . All rights reserved.
 */
public class BottomSheetVenueOffersFragment extends BottomSheetDialogFragment {

    public static VenueOffer venueObj;

    public static BottomSheetVenueOffersFragment newInstance(VenueOffer obj) {

        venueObj = obj;
        Bundle args = new Bundle();
        BottomSheetVenueOffersFragment fragment = new BottomSheetVenueOffersFragment();
        fragment.setArguments(args);
        return fragment;
    }

    //Bottom Sheet Callback
    private BottomSheetBehavior.BottomSheetCallback mBottomSheetBehaviorCallback = new BottomSheetBehavior.BottomSheetCallback() {

        @Override
        public void onStateChanged(@NonNull View bottomSheet, int newState) {
            if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                dismiss();
            }

        }

        @Override
        public void onSlide(@NonNull View bottomSheet, float slideOffset) {
            //check the slide offset and change the visibility of close button
            if (slideOffset > 0.5) {
                closeButton.setVisibility(View.VISIBLE);
            } else {
                closeButton.setVisibility(View.GONE);
            }
        }
    };

    private ImageView closeButton;

    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        //Get the content View
        View contentView = View.inflate(getContext(), R.layout.bottom_sheet_venue_offers, null);
        dialog.setContentView(contentView);

        ITFTextView tvOfferName = contentView.findViewById(R.id.tvOfferName);
        ITFTextView tvOfferValidity = contentView.findViewById(R.id.tvOfferValidity);
        ITFTextView tvOfferDetails = contentView.findViewById(R.id.tvOfferDetails);
        ITFTextView tvContact = contentView.findViewById(R.id.tvContact);
        ITFTextView tvWebsite = contentView.findViewById(R.id.tvWebsite);
        ITFImageView imgOffer = contentView.findViewById(R.id.imgOffer);


        String startDate = DateFormatter.parseDateTimeFormat(venueObj.getValidFrom().replace("T", " "), DateFormatter.TRANSACTION_DATE_FORMAT, DateFormatter.DISPLAY_DATE_BOTTOM_SHEET_FORMAT);
        String endDate = DateFormatter.parseDateTimeFormat(venueObj.getValidTo().replace("T", " "), DateFormatter.TRANSACTION_DATE_FORMAT, DateFormatter.DISPLAY_DATE_BOTTOM_SHEET_FORMAT);

        tvOfferName.setText(venueObj.getName());
        tvOfferValidity.setText(startDate + " To " + endDate);
        tvOfferDetails.setText(venueObj.getDescription());
        tvContact.setText(venueObj.getContactNumber());
        tvWebsite.setText(venueObj.getWebsiteUrl());

        UIHelper.setImageWithGlide(getContext(), imgOffer, venueObj.getImageUrl());

        closeButton = contentView.findViewById(R.id.close_image_view);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //dismiss bottom sheet
                dismiss();
            }
        });

        //Set the coordinator layout behavior
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) ((View) contentView.getParent()).getLayoutParams();
        CoordinatorLayout.Behavior behavior = params.getBehavior();

        //Set callback
        if (behavior instanceof BottomSheetBehavior) {
            ((BottomSheetBehavior) behavior).setBottomSheetCallback(mBottomSheetBehaviorCallback);
        }
    }

}
