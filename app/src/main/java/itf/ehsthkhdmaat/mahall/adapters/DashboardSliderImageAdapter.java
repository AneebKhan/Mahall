package itf.ehsthkhdmaat.mahall.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.List;

import itf.ehsthkhdmaat.mahall.models.service_model.venues.VenueGallery;
import utilities.UIHelper;

public class DashboardSliderImageAdapter extends PagerAdapter {
    Context mContext;
    List<VenueGallery> venueGalleryList;

    public DashboardSliderImageAdapter(Context context, List<VenueGallery> venueGalleryList) {
        this.mContext = context;
        this.venueGalleryList = venueGalleryList;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((ImageView) object);
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(mContext);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);



        UIHelper.setImageWithGlide(mContext, imageView, venueGalleryList.get(position).getUrl());
        ((ViewPager) container).addView(imageView, 0);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((ImageView) object);
    }

    @Override
    public int getCount() {
        return venueGalleryList.size();
    }
}
