package utilities;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import androidx.core.content.ContextCompat;

public class GPSTracker implements

        LocationListener {

    public static FusedLocationProviderClient fusedLocationClient;
    boolean isPlayServiceAvailable = false;
    public static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    public static Context mContext;
    public static Location location;
    public static  double latitude;
    public static double longitude;


    public GPSTracker(Context context) {
        this.mContext = context;
//        enableMyLocation();

    }




    public static void enableMyLocation(Context context) {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission to access the location is missing.
//            PermissionUtils.requestPermission(((DashboardActivity) getActivity()), LOCATION_PERMISSION_REQUEST_CODE,
//                    Manifest.permission.ACCESS_FINE_LOCATION, true);

        } else  {
            // Access to the location has been granted to the app.



            fusedLocationClient = LocationServices.getFusedLocationProviderClient(context);

            fusedLocationClient.getLastLocation()
                    .addOnSuccessListener( new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            // Got last known location. In some rare situations this can be null.
                            if (location != null) {

                                Double srcLat = location.getLatitude();
                                Double srcLng = location.getLongitude();

                                GPSTracker.location = location;

//                                 latitude = location.getLatitude();
//                                 longitude = location.getLongitude();

//                                setLattitude(location.getLatitude());
//                                setLongitude(location.getLongitude());

                                LogUtils.LOGD("GPSTRACKER","Lat "+srcLat);
                                LogUtils.LOGD("GPSTRACKER","Lng " +srcLng);


                            }
                        }
                    });


        }


    }

    public static double getCurrentLatitude(){
        if(GPSTracker.location != null){
            GPSTracker.latitude = location.getLatitude();
        }

        return GPSTracker.latitude;
    }

    public static double getCurrLongitude(){
        if(GPSTracker.location != null){
            GPSTracker.longitude = location.getLongitude();
        }

        return GPSTracker.longitude;
    }


//    public static void setLattitude(Double lattitude)
//    {
//
//        GPSTracker.latitude = lattitude;
//    }
//
//
//    public static void setLongitude(Double longitude)
//    {
//
//        GPSTracker.longitude = longitude;
//    }


    public static double distanceBetweenSourceAndVenue(Location source , Location destination){

        double totalDistance = source.distanceTo(destination);

        return  totalDistance;

    }

    public static double convertMetreToKm(double meter)
    {

        meter = meter / 1000;

        return  meter;

    }


    public static double convertMetersToMiles(double meter)
    {
        double miles = meter * 0.00062;

        return  miles;

    }


    @Override
    public void onLocationChanged(Location location) {

        if (location != null) {

            LogUtils.LOGD("onLocationChanged", "lat " + location.getLatitude());
            LogUtils.LOGD("onLocationChanged", "lng " + location.getLongitude());

        }

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }


}