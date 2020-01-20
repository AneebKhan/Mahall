package utilities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.DimenRes;
import androidx.annotation.IdRes;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.material.snackbar.Snackbar;
import com.ift.ehsthkhdmaatcore.R;
import com.itf.phatbooskiandroid.classes.CustomApplication;

import static android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS;


/**
 * Created by Aneeb on 24-Jan-18.
 */

public class UIHelper {

    private final Activity activity;

    public UIHelper(Activity activity) {
        this.activity = activity;

    }

    public  static double currentLat = 0.0;
    public static double currentLng = 0.0;

    public FusedLocationProviderClient fusedLocationClient;




    public static void showSnackBarShort(View view, String message) {
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show();
    }

    public static void showSnackBarLong(View view, String message) {

        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show();
    }

    public static void showLongToastInCenter(Context ctx, int messageId) {
        Toast toast = Toast.makeText(ctx, messageId, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public static void showLongToastInCenter(Context ctx, String message) {

        Toast toast = Toast.makeText(ctx, message + "", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public static void showShortToastInCenter(Context ctx, String message) {

        Toast toast = Toast.makeText(ctx, message + "", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public static void ShowToastShort(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static int getDimenData(@DimenRes int dimension) {
        return (int) (CustomApplication.getContext().getResources().getDimension(dimension) / CustomApplication.getContext().getResources().getDisplayMetrics().density);
    }

    // for getting devices current lattitude and longitude region start


    /**
     * Function to get the user's current location
     *
     * @return
     */
//    public static Location getLocation(Activity activity) {
//        try {
//
//
//            fusedLocationClient = LocationServices.getFusedLocationProviderClient(CustomApplication.getContext());
//
//
//            locationManager = (LocationManager) activity
//                    .getSystemService(Context.LOCATION_SERVICE);
//
//            // getting GPS status
//            isGPSEnabled = locationManager
//                    .isProviderEnabled(LocationManager.GPS_PROVIDER);
//
//            Log.d("isGPSEnabled", "=" + isGPSEnabled);
//
//            // getting network status
//            isNetworkEnabled = locationManager
//                    .isProviderEnabled(LocationManager.NETWORK_PROVIDER);
//
//            Log.d("isNetworkEnabled", "=" + isNetworkEnabled);
//
//            if (isGPSEnabled == false && isNetworkEnabled == false) {
//                // no network provider is enabled
//            } else {
//
//                canGetLocation = true;
//                if (isNetworkEnabled  || isGPSEnabled) {
//
//
//                    if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                        // TODO: Consider calling
//                        //    ActivityCompat#requestPermissions
//                        // here to request the missing permissions, and then overriding
//                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                        //                                          int[] grantResults)
//                        // to handle the case where the user grants the permission. See the documentation
//                        // for ActivityCompat#requestPermissions for more details.
//                        return null;
//                    }
//                    fusedLocationClient.getLastLocation()
//                            .addOnSuccessListener(activity, new OnSuccessListener<Location>() {
//                                @Override
//                                public void onSuccess(Location location) {
//                                    // Got last known location. In some rare situations this can be null.
//                                    if (location != null)
//                                    {
//
//
//                                        srcLat  = location.getLatitude();
//                                        srcLng = location.getLongitude();
//
//                                        Log.d("UIHelper","Lat"+srcLat);
//                                        Log.d("UIHelper","Lng"+srcLng);
//
//
//
//                                    }
//                                }
//                            });
//
//                }
//
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return locationService;
//    }


    // region end


  ///////////////////
  public static void buildAlertMessageNoGps(final Context context) {
      AlertDialog.Builder  builder = new AlertDialog.Builder(context,R.style.MyDialogTheme);
      builder.setMessage("Your GPS seems to be disabled, do you want to enable it?")
              .setCancelable(false)
              .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                  public void onClick(final DialogInterface dialog, final int id) {
                      context.startActivity(new Intent(ACTION_LOCATION_SOURCE_SETTINGS));
                  }
              })
              .setNegativeButton("No", new DialogInterface.OnClickListener() {
                  public void onClick(final DialogInterface dialog, final int id) {
                      dialog.cancel();

                  }
              });
      final AlertDialog alert = builder.create();
      alert.show();



  }

    ////////////////////





    public static void setImageWithGlide(Context ctx, final ImageView imageView, String imgUrl) {

//        if (!Utils.isEmptyOrNull(uri)) {
        /*Glide.with(ctx).load(imgUrl).apply(new RequestOptions().override(height,width)).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                Log.d("onError", "onError");
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                return false;
            }


        }).into(imageView);*/
        Glide.with(ctx).load(imgUrl).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                Log.d("onError", "onError");
                imageView.setBackgroundResource(R.drawable.icon_profile);
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                return false;
            }


        }).into(imageView);
    }


    /*public static void setImageWithGlide(Context ctx, final ImageView imageView, String url, int placeHolder) {

        if (!Utilities.isEmptyOrNull(url)) {
            Glide.with(ctx).load(url).
                    apply(new RequestOptions().placeholder(placeHolder).error(placeHolder)).
                    listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            Log.d("onError", "onError");
                            return false;
                        }


                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            return false;
                        }
                    }).into(imageView);

        }


    }*/

    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, int pixels) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap
                .getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPx = pixels;

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }



}
