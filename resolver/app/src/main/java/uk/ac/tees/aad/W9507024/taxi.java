package uk.ac.tees.aad.W9507024;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

public class taxi extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap map;
    FusedLocationProviderClient location ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taxi);
        location= LocationServices.getFusedLocationProviderClient(this);

        SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.googlemap);
        supportMapFragment.getMapAsync(this);


    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

        map = googleMap;
        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 12);
        }
        location.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(@NonNull Location l) {
                if (l != null) {

                    map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(l.getLatitude(),l.getLongitude()),15));
                    map.addMarker(new MarkerOptions().position(new LatLng(l.getLatitude(),l.getLongitude())).title("Taxi reaching to Your Location...")).showInfoWindow();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            map.addMarker(new MarkerOptions().position(new LatLng(l.getLatitude(),l.getLongitude())).title("Your Location Sent to Taxi")).showInfoWindow();
                        }
                    },3000);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            map.addMarker(new MarkerOptions().position(new LatLng(l.getLatitude(),l.getLongitude())).title("Taxi reaching to Your Location...")).showInfoWindow();
                        }
                    },6000);

                }
            }
        });
    }
}