package com.example.dominique.android_cours_tp2.viewholder;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dominique.android_cours_tp2.R;
import com.example.dominique.android_cours_tp2.model.School;
import com.example.dominique.android_cours_tp2.presenter.MapSchoolActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by dominique on 02/02/2018.
 */

public class ListSchoolViewHolder extends RecyclerView.ViewHolder{

    private static final String BUNDLE_SCHOOLS_DATA = "BUNDLE_SCHOOLS_DATA";
    private static final String BUNDLE_SCHOOLS_LONGITUDE_DATA = "BUNDLE_SCHOOLS_LONGITUDE_DATA";
    private static final String BUNDLE_SCHOOLS_LATITUDE_DATA = "BUNDLE_SCHOOLS_LATITUDE_DATA";
    private static final String BUNDLE_SCHOOLS_ZOOM_DATA = "BUNDLE_SCHOOLS_ZOOM_DATA";
    private final int ZOOM = 15; //Permet d'afficher l'école en question avec un zoom optimal.

    private LinearLayout linearLayout;
    private Context context;

    private TextView textViewNomEcole, textViewAdresseEcole, textViewNbEleveEcole,textViewStatusEcole, textViewLatitudeEcole, textViewLongitudeEcole;
    private ImageButton imageButtonAccessLocation;
    private double longitudeSchool, latitudeSchool;
    private List<School> listSchool;

    //itemView est la vue correspondante à 1 cellule
    public ListSchoolViewHolder(View itemView, Context context, List<School> listSchool) {
        super(itemView);
        this.context = context;
        this.listSchool = listSchool;
        init();
    }

    //puis ajouter une fonction pour remplir la cellule en fonction d'un objet school
    public void bind(final School school) throws IOException {
        setData(school);
        setStyleCell(school);
        imageButtonAccessLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMapSchool(school);
            }
        });
    }

    //Initialise les widgets
    public void init(){
        linearLayout = itemView.findViewById(R.id.linear_layout_cell);
        textViewNomEcole = itemView.findViewById(R.id.nom_ecole);
        textViewAdresseEcole = itemView.findViewById(R.id.adresse_ecole);
        textViewNbEleveEcole = itemView.findViewById(R.id.nb_eleve);
        textViewStatusEcole = itemView.findViewById(R.id.status_ecole);
        textViewLatitudeEcole = itemView.findViewById(R.id.latitude_ecole);
        textViewLongitudeEcole = itemView.findViewById(R.id.longitude_ecole);
        imageButtonAccessLocation = itemView.findViewById(R.id.btn_acess_map_cell);
    }

    //Remplis nos champs avec les données de l'école.
    public void setData(School school) throws IOException {
        int nbEleve = school.getNbEleve();
        Double latitude = Double.parseDouble(school.getLatitude());
        Double longitude = Double.parseDouble(school.getLongitude());
//
//        String postalCode = getPostalCodeWithLocation(latitude,longitude);
//        String streetName = getStreetNameWithLocation(latitude,longitude);
        //String countryName = getCountryNameWithLocation(latitude,longitude);
        //String cityName = getCityWithLocation(latitude,longitude);


        textViewNomEcole.setText(school.getNom());
        textViewAdresseEcole.setText(school.getAddresse());
        textViewNbEleveEcole.setText(context.getString(R.string.list_school_view_holder_text_nb_eleves) + String.valueOf(nbEleve));
        textViewLatitudeEcole.setText(context.getString(R.string.list_school_view_holder_text_latitude) + school.getLatitude());
        textViewLongitudeEcole.setText(context.getString(R.string.list_school_view_holder_text_longitude) + school.getLongitude());
        //textViewLongitudeEcole.setText(countryName);
    }

    public String getPostalCodeWithLocation(double latitude, double longitude) throws IOException {
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
        String postalCode = addresses.get(0).getPostalCode();
        return postalCode;
    }

    public String getStreetNameWithLocation(double latitude, double longitude) throws IOException {
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
        String streetName = addresses.get(0).getAddressLine(0);
        return streetName;
    }

    public String getCountryNameWithLocation(double latitude, double longitude) throws IOException {
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
        String countryName = addresses.get(0).getCountryName();
        return countryName;
    }

    public String getCityWithLocation(double latitude, double longitude) throws IOException {
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
        String cityName = addresses.get(0).getLocality();
        return cityName;
    }

    @SuppressLint("ResourceType")

    public void setStyleCell(School school){

        if(school.isGreen()){
            linearLayout.setBackgroundColor(Color.parseColor(context.getString(R.color.greenCellSchool)));
        }else if(school.isOrange()){
            linearLayout.setBackgroundColor(Color.parseColor(context.getString(R.color.orangeCellSchool)));
        }else if(school.isRed()){
            linearLayout.setBackgroundColor(Color.parseColor(context.getString(R.color.redCellSchool)));
        }

        if(school.getStatus() == 1) {
            textViewStatusEcole.setText(R.string.list_school_view_holder_status_open);
            textViewStatusEcole.setTextColor(Color.parseColor(context.getString(R.color.greenColorStatus)));
        }else{
            textViewStatusEcole.setText(R.string.list_school_view_holder_status_close);
            textViewStatusEcole.setTextColor(Color.parseColor(context.getString(R.color.redColorStatus)));
        }

    }

    /**
     * Cette fonction permet de prendre en paramètre une école et de l'afficher sur la map selon sa position et son zoom
     * @param school
     */
    public void goToMapSchool(School school){
        Intent intent = new Intent(context, MapSchoolActivity.class);
        Bundle bundle = new Bundle();
        longitudeSchool = Double.parseDouble(school.getLongitude());
        latitudeSchool = Double.parseDouble(school.getLatitude());
        bundle.putParcelableArrayList(BUNDLE_SCHOOLS_DATA, (ArrayList<? extends Parcelable>) listSchool);
        bundle.putDouble(BUNDLE_SCHOOLS_LONGITUDE_DATA, longitudeSchool);
        bundle.putDouble(BUNDLE_SCHOOLS_LATITUDE_DATA, latitudeSchool);
        bundle.putInt(BUNDLE_SCHOOLS_ZOOM_DATA, ZOOM);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}