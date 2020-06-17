package edu.fr5881cw.finalproject;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static final int LOCATION_REQUEST_CODE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    //Another section we can potentially cut

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

        UiSettings mapSettings;
        mapSettings = mMap.getUiSettings();
        //enable zoom controls
        mapSettings.setZoomControlsEnabled(true);
        //allow user to pinch, dble-tap, 2-fingertap, etc.
        mapSettings.setZoomGesturesEnabled(true);
        //allow dragging map w/ single finger
        mapSettings.setScrollGesturesEnabled(true);
        //tilt the angle of projection of map w/ 2 fingers
        mapSettings.setTiltGesturesEnabled(true);
        //can rotate map w/ 2 fingers in circular motion
        mapSettings.setRotateGesturesEnabled(true);

        //Add a marker in Moorhead and move the camera
        LatLng moorhead = new LatLng(46,-96);
        mMap.addMarker(new MarkerOptions().position(moorhead).title("Marker in Moorhead"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(moorhead));

        //Add a marker in NorthDakota
        LatLng NorthDakota = new LatLng(47.650589,-100.437012);
        mMap.addMarker(new MarkerOptions().position(moorhead).title("Marker in Moorhead"));

        //adding polygon
        mMap.addPolygon(
                new PolygonOptions()
                        .add(new LatLng(46.099724, -96.078331),
                                new LatLng(46.199724, -96.178331),
                                new LatLng(46.099724, -96.378331),
                                new LatLng(46.009724, -96.278331),
                                new LatLng(46.009724, -96.178331))
                        .strokeWidth(50f)
                        .strokeColor(Color.BLUE)
                        .fillColor(Color.argb(70,10,50,150))
        );

        mMap.addPolygon(
                new PolygonOptions()
                        .add(new LatLng(47.559724, -100.178331),
                                new LatLng(47.709724, -100.278331),
                                new LatLng(47.559724, -100.478331),
                                new LatLng(47.559724, -100.378331),
                                new LatLng(47.559724, -100.278331))
                        .strokeWidth(50f)
                        .strokeColor(Color.GREEN)
                        .fillColor(Color.argb(70,10,50,150))
        );



        CameraPosition cameraPos = new CameraPosition.Builder()
                .target(new LatLng(37.532600, 127.024612))
                .zoom(50)
                .bearing(70)
                .tilt(25)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPos));


        if (mMap != null) {
            int permission = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
            if (permission == PackageManager.PERMISSION_GRANTED){
                mMap.setMyLocationEnabled(true);
            }
            else {
                requestPermission(
                        Manifest.permission.ACCESS_FINE_LOCATION, LOCATION_REQUEST_CODE);
            }
            mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            //mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        }
    }

    protected void requestPermission(String permissionType, int requestCode){
        ActivityCompat.requestPermissions(this,
                new String[]{permissionType},requestCode);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[ ] grantResults) {
        switch (requestCode) {
            case LOCATION_REQUEST_CODE: {
                if (grantResults.length == 0 || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Can't show location - permission required",
                            Toast.LENGTH_SHORT).show();
                } else {
                    SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                            .findFragmentById(R.id.map);
                    mapFragment.getMapAsync(this);
                }
            }
        }
    }
}
