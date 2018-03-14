package com.techgaurav.mapsprac;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.regex.MatchResult;

public class MainActivity1 extends AppCompatActivity implements OnMapReadyCallback {

    TextView finalmessage_source, finalmessage_dest;

    private GoogleMap mMap;

    Button btn_caldist;

    //to
    private double tolatitude;
    private double tolongitude;

    //from
    private double fromlatitude;
    private double fromlongitude;

    LatLng latLng_source;
    LatLng latLng_dest;

    Marker marker;

    Marker firstAndroidmarker,secondmarker;

    Boolean swap1=false,swap2=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        finalmessage_source = findViewById(R.id.finalmessage_source);
        finalmessage_dest = findViewById(R.id.finalmessage_dest);
        btn_caldist = findViewById(R.id.btn_caldist);

        PlaceAutocompleteFragment place_source = (PlaceAutocompleteFragment) getFragmentManager().
                findFragmentById(R.id.autocomplete_places_source);

        PlaceAutocompleteFragment place_destination = (PlaceAutocompleteFragment) getFragmentManager().
                findFragmentById(R.id.autocomplete_places_destination);


        final SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mymap);
        mapFragment.getMapAsync(this);


        place_source.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                Toast.makeText(MainActivity1.this, "The source latitude is: " + place.getLatLng().latitude, Toast.LENGTH_SHORT).show();
                finalmessage_source.setText("The lat/long of source is " + place.getLatLng());
                fromlatitude = place.getLatLng().latitude;
                fromlongitude = place.getLatLng().longitude;

                latLng_dest = new LatLng(fromlatitude, fromlongitude);

                mapFragment.getMapAsync(new OnMapReadyCallback() {
                    @Override
                    public void onMapReady(GoogleMap googleMap) {

                        if (firstmarker == null) {
                            MarkerOptions markerOptions = new MarkerOptions();
                            markerOptions.position(latLng_dest);
                            firstmarker = googleMap.addMarker(markerOptions);
                            swap1=true;
                        }

                        if(true)
                        {
                            firstmarker.setPosition(latLng_dest);
                        }






/*                            googleMap.addMarker(new MarkerOptions()
                                .position(latLng_dest)
                                .title(latLng_source.toString())
                                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));*/
                    }
                });
            }

            @Override
            public void onError(Status status) {
                Toast.makeText(MainActivity1.this, "probably somethings wrong with source", Toast.LENGTH_SHORT).show();
            }
        });

        place_destination.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                Toast.makeText(MainActivity1.this, "The destination lat/long is: " + place.getLatLng(), Toast.LENGTH_SHORT).show();
                finalmessage_dest.setText("The lat/long of source is " + place.getLatLng());

                tolatitude = place.getLatLng().latitude;
                tolongitude = place.getLatLng().longitude;

                latLng_source = new LatLng(tolatitude, tolongitude);

                mapFragment.getMapAsync(new OnMapReadyCallback() {
                    @Override
                    public void onMapReady(GoogleMap googleMap) {

                        if (secondmarker == null) {
                            MarkerOptions markerOptions = new MarkerOptions();
                            markerOptions.position(latLng_source);
                            secondmarker = googleMap.addMarker(markerOptions);
                            swap2=true;
                        }

                        if(swap2)
                        {
                            secondmarker.setPosition(latLng_source);
                        }



/*

                        /*googleMap.addMarker(new MarkerOptions()
                                .position(latLng_source)
                                .title(latLng_source.toString())
                                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));*/
                    }
                });
            }

            @Override
            public void onError(Status status) {
                Toast.makeText(MainActivity1.this, "Probably something went wrong with destination", Toast.LENGTH_SHORT).show();
            }
        });

        caldistclicklistener();
    }

    private void caldistclicklistener() {

        btn_caldist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

      /*  LatLng fromloc = new LatLng(fromlatitude,fromlongitude);
        mMap.addMarker(new MarkerOptions().position(fromloc).title("From marker"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(fromloc));

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/
    }


}
