package com.example.dominique.android_cours_tp2.presenter;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.dominique.android_cours_tp2.R;
import com.example.dominique.android_cours_tp2.data.DataManagerSchool;
import com.example.dominique.android_cours_tp2.interfaces.IGetSchools;
import com.example.dominique.android_cours_tp2.model.School;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class MapSchoolActivity extends FragmentActivity implements OnMapReadyCallback {

    private static final String BUNDLE_SCHOOLS_LONGITUDE_DATA = "BUNDLE_SCHOOLS_LONGITUDE_DATA";
    private static final String BUNDLE_SCHOOLS_LATITUDE_DATA = "BUNDLE_SCHOOLS_LATITUDE_DATA";
    private static final String BUNDLE_SCHOOLS_ZOOM_DATA = "BUNDLE_SCHOOLS_ZOOM_DATA";
    private GoogleMap mMap;
    private List<School> listSchools;
    private double latitudeCamera, longitudeCamera;
    private int zoom;
    private BitmapDescriptor bitmapDescriptorGreen,bitmapDescriptorOrange,bitmapDescriptorRed,bitmapDescriptorColorDefault;
    private double latitudeSchool, longitudeSchool;
    private LatLng pointEcole, posCamera;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_school);
        Bundle bundle = getIntent().getExtras();
        latitudeCamera = bundle.getDouble(BUNDLE_SCHOOLS_LATITUDE_DATA);
        longitudeCamera = bundle.getDouble(BUNDLE_SCHOOLS_LONGITUDE_DATA);
        zoom = bundle.getInt(BUNDLE_SCHOOLS_ZOOM_DATA);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        loadData(googleMap);
    }

    //Permet de remplir les données dans la map de la liste d'école.
    public void setData(GoogleMap googleMap){
        mMap = googleMap;
        //Enregistre la position de la caméra avant d'afficher la map selon la latitude et longitude fournie.
        posCamera = new LatLng(latitudeCamera,longitudeCamera);
        //Place la caméra selon la position fourni.
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(posCamera,zoom));

        initMarker();

        //Parcours la liste des écoles et place les markers.
        for(int i = 0; i < listSchools.size() ; i ++){
            setMarker(listSchools.get(i));

        }
    }

    //Permet d'initialiser les couleurs pour les différents marker qu'on a besoin.
    public void initMarker(){
        bitmapDescriptorGreen
                = BitmapDescriptorFactory.defaultMarker(
                BitmapDescriptorFactory.HUE_GREEN);

        bitmapDescriptorOrange
                = BitmapDescriptorFactory.defaultMarker(
                BitmapDescriptorFactory.HUE_ORANGE);

        bitmapDescriptorRed
                = BitmapDescriptorFactory.defaultMarker(
                BitmapDescriptorFactory.HUE_RED);

        bitmapDescriptorColorDefault
                = BitmapDescriptorFactory.defaultMarker(
                BitmapDescriptorFactory.HUE_CYAN);
    }

    /**
     * Cette fonction prend en paramètre une école
     * et ajoute une couleur au marker selon le nombre d'élève et ajoute le marker sur la carte.
     * @param school
     */
    public void setMarker(School school){

        latitudeSchool = Double.parseDouble(school.getLatitude());
        longitudeSchool = Double.parseDouble(school.getLongitude());
        pointEcole = new LatLng(latitudeSchool, longitudeSchool);

        MarkerOptions option = new MarkerOptions()
                .position(pointEcole)
                .title(school.getNom());

        if(school.isGreen()){
            option.icon(bitmapDescriptorGreen);
        }else if(school.isOrange()){
            option.icon(bitmapDescriptorOrange);
        }else if(school.isRed()){
            option.icon(bitmapDescriptorRed);
        }else{
            option.icon(bitmapDescriptorColorDefault);
        }

        mMap.addMarker(option);

    }

    public void loadData(final GoogleMap googleMap){
        DataManagerSchool.getInstance().getSchool(new IGetSchools() {
            @Override
            public void onSuccess(List<School> schools) {
                listSchools = schools;
                setData(googleMap);
            }

            @Override
            public void onError(Throwable t) {
                Toast.makeText(MapSchoolActivity.this, R.string.dashboard_activity_text_failure_webservice, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
