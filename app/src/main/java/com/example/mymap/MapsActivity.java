package com.example.mymap;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    LocationManager location;



    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        location = (LocationManager) getSystemService(LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {


            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 420);

            return;
        }


        location.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {

                String title = "";
                double lat = location.getLatitude();
                double lon = location.getLongitude();


                try {
                    Geocoder geo = new Geocoder(MapsActivity.this);
                    List<Address> address = geo.getFromLocation(lat, lon, 1);
                    title = address.get(0).getCountryCode() + "," + address.get(0).getLocality();
                } catch (IOException e) {
                    e.printStackTrace();
                }


                LatLng mylocation = new LatLng(lat, lon);
                mMap.addMarker(new MarkerOptions().position(mylocation).title(title));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mylocation, 10.2f));


            }
        });

        location.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {

                String title = "";
                double lat = location.getLatitude();
                double lon = location.getLongitude();

                try {
                    Geocoder geo = new Geocoder(MapsActivity.this);
                    List<Address> address = geo.getFromLocation(lat, lon, 1);
                    title = address.get(0).getCountryCode() + "," + address.get(0).getLocality();
                } catch (IOException e) {
                    e.printStackTrace();
                }


                LatLng mylocation = new LatLng(lat, lon);
                mMap.addMarker(new MarkerOptions().position(mylocation).title(title));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mylocation, 15.2f));

            }
        });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 420 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            location.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new LocationListener() {
                @Override
                public void onLocationChanged(@NonNull Location location) {

                    String title = "";
                    double lat = location.getLatitude();
                    double lon = location.getLongitude();


                    try {
                        Geocoder geo = new Geocoder(MapsActivity.this);
                        List<Address> address = geo.getFromLocation(lat, lon, 1);
                        title = address.get(0).getCountryCode() + "," + address.get(0).getLocality();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                    LatLng mylocation = new LatLng(lat, lon);
                    mMap.addMarker(new MarkerOptions().position(mylocation).title(title));
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mylocation, 15.2f));


                }
            });
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
          mMap = googleMap;
//
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}


