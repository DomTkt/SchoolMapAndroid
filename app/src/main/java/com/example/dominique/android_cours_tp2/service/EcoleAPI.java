package com.example.dominique.android_cours_tp2.service;

import com.example.dominique.android_cours_tp2.model.School;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


/**
 * Created by dominique on 02/02/2018.
 */

public interface EcoleAPI {

    //Adresse de l'API sans les routes
    public static final String BASE_URL = "http://ynov-tp1.getsandbox.com";
    public static final String PORT = "80";
    public static final String ENDPOINT = BASE_URL+":"+PORT;

    //Routes d'accès
    @GET("/getEcolePrimaire")
    Call<List<School>> listEcole();

}
