package com.example.dominique.android_cours_tp2.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.common.api.Scope;

/**
 * Created by dominique on 02/02/2018.
 */
// On implémente l'interface Parcelable pour pouvoir passer des Entité School entre les activitées avec les Bundles.
public class School implements Parcelable{

    private String nom;
    private String addresse;
    private int nbEleve;
    private int status;
    private String latitude;
    private String longitude;

    protected School(Parcel in) {
        nom = in.readString();
        addresse = in.readString();
        nbEleve = in.readInt();
        status = in.readInt();
        latitude = in.readString();
        longitude = in.readString();
    }

    public School(){

    }

    public School(String latitude, String longitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }


    public void setNbEleve(int nbEleve) {
        this.nbEleve = nbEleve;
    }

    public static final Creator<School> CREATOR = new Creator<School>() {
        @Override
        public School createFromParcel(Parcel in) {
            return new School(in);
        }

        @Override
        public School[] newArray(int size) {
            return new School[size];
        }
    };

    public String getNom() {
        return nom;
    }

    public String getAddresse() {
        return addresse;
    }

    public int getNbEleve() {
        return nbEleve;
    }

    public int getStatus() {
        return status;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nom);
        dest.writeString(addresse);
        dest.writeInt(nbEleve);
        dest.writeInt(status);
        dest.writeString(latitude);
        dest.writeString(longitude);
    }

    public boolean isRed(){
        return nbEleve > 300;
    }

    public boolean isGreen(){
        return nbEleve < 150;
    }

    public boolean isOrange(){
        return nbEleve > 150 && nbEleve < 300;
    }
}
