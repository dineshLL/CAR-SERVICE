package com.example.dineshliyanage.easycarservice;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.widget.EditText;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener{

    private GoogleMap mMap;
    private Marker markerDasun;
    private Marker markerKamal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng driver1 = new LatLng(7.8731, 80.7718);
        LatLng driver2 = new LatLng(8.2, 80.8);

        markerDasun=mMap.addMarker(new MarkerOptions().position(driver1).title("Dasun(Driver)"));
        markerDasun.setTag(0);

        markerKamal=mMap.addMarker(new MarkerOptions().position(driver2).title("Kamal(Driver)"));
        markerKamal.setTag(0);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(driver1));
        mMap.setOnMarkerClickListener(this);
    }

    @Override
    public boolean onMarkerClick(Marker marker){
        Intent intent=new Intent(getBaseContext(),Driver.class);

        Bundle bundle = new Bundle();
        bundle.putString("NAME",marker.getTitle());
        intent.putExtras(bundle);
        startActivity(intent);
//        request_user_name();
        return  false;
    }


}
