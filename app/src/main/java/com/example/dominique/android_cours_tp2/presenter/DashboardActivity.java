package com.example.dominique.android_cours_tp2.presenter;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.dominique.android_cours_tp2.R;
import com.example.dominique.android_cours_tp2.data.DataManagerSchool;
import com.example.dominique.android_cours_tp2.interfaces.IGetSchools;
import com.example.dominique.android_cours_tp2.service.EcoleAPI;
import com.example.dominique.android_cours_tp2.model.School;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String BUNDLE_SCHOOLS_LONGITUDE_DATA = "BUNDLE_SCHOOLS_LONGITUDE_DATA";
    private static final String BUNDLE_SCHOOLS_LATITUDE_DATA = "BUNDLE_SCHOOLS_LATITUDE_DATA";
    private static final String BUNDLE_SCHOOLS_ZOOM_DATA = "BUNDLE_SCHOOLS_ZOOM_DATA";
    private static final double LONGITUDE_LYON = 4.850000; // Longitude de la ville de lyon
    private static final double LATITUDE_LYON = 45.750000; // Latitude de la ville de Lyon
    private static final int ZOOM_DEFAULT = 10; // Permet d'afficher avec une zoom optimale la zone des écoles.
    private Button buttonGoToListSchools, buttonGoToMapSchools;
    private List<School> listSchools;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.dashboard_activity_action_bar_title);
        loadData();
        buttonGoToListSchools = findViewById(R.id.btn_go_to_list_school);
        buttonGoToMapSchools = findViewById(R.id.btn_go_to_map_schools);

        buttonGoToListSchools.setOnClickListener(this);
        buttonGoToMapSchools.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        //permet d'obtenir l'id de la view cliquer
        int id = v.getId();

        if(id == R.id.btn_go_to_list_school){
            goToListSchools();
        }else if(id == R.id.btn_go_to_map_schools){
            goToMapsSchools();
        }


    }

    //Permet d'aller sur l'activité de la liste des écoles
    public void goToListSchools(){
        if(listSchools == null)
        {
            //Si le webservice n'a pas été chargé correctement on affiche un message d'erreur.
            Toast.makeText(this, R.string.dashboard_activity_text_fail_webservice, Toast.LENGTH_SHORT).show();
        }else {
            //Sinon on accéde à la liste des écoles en envoyant les informations des écoles dans un bundle qu'on récupéreras sur l'activité ListSchool
            Intent intent = new Intent(DashboardActivity.this, ListSchoolActivity.class);
            startActivity(intent);
        }
    }

    //Permet d'aller sur la map avec les informations qu'on souhaite avant l'affichage de la map.
    public void goToMapsSchools(){
        if(listSchools == null)
        {
            Toast.makeText(this, R.string.dashboard_activity_text_fail_webservice, Toast.LENGTH_SHORT).show();
        }else {
            Intent intent = new Intent(DashboardActivity.this, MapSchoolActivity.class);
            Bundle bundle = new Bundle();
            bundle.putDouble(BUNDLE_SCHOOLS_LONGITUDE_DATA, LONGITUDE_LYON);
            bundle.putDouble(BUNDLE_SCHOOLS_LATITUDE_DATA, LATITUDE_LYON);
            bundle.putInt(BUNDLE_SCHOOLS_ZOOM_DATA, ZOOM_DEFAULT);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }

    //Chargée les données du webservice au chargement de l'application.
    public void loadData(){
        DataManagerSchool.getInstance().getSchool(new IGetSchools() {
            @Override
            public void onSuccess(List<School> schools) {
                listSchools = schools;
            }

            @Override
            public void onError(Throwable t) {
                Toast.makeText(DashboardActivity.this, R.string.dashboard_activity_text_failure_webservice, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
