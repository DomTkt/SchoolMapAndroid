package com.example.dominique.android_cours_tp2.presenter;

import android.location.Geocoder;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.dominique.android_cours_tp2.adapter.AdapterListSchool;
import com.example.dominique.android_cours_tp2.R;
import com.example.dominique.android_cours_tp2.data.DataManagerSchool;
import com.example.dominique.android_cours_tp2.interfaces.IGetSchools;
import com.example.dominique.android_cours_tp2.model.School;

import java.util.List;
import java.util.Locale;

//Permet d'afficher à l'écran la liste des écoles
public class ListSchoolActivity extends AppCompatActivity {

    private RecyclerView schoolRecyclerView;
    private List<School> listSchools;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_school);

        //Permet de changer le titre de l'action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.list_school_activity_action_bar_title);

        loadData();
        schoolRecyclerView = findViewById(R.id.recycler_view_school);

        //définit l'ordonnancement des cellules
        schoolRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //L'adapter "AdapterListSchool" sert à remplir notre recyclerview


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_refresh, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                loadData();
                break;
        }

        return true;
    }

    public void loadData(){
        DataManagerSchool.getInstance().getSchool(new IGetSchools() {
            @Override
            public void onSuccess(List<School> schools) {
                listSchools = schools;
                schoolRecyclerView.setAdapter(new AdapterListSchool(listSchools));
                Toast.makeText(ListSchoolActivity.this, R.string.common_webservice_success_text, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable t) {
                Toast.makeText(ListSchoolActivity.this, R.string.dashboard_activity_text_failure_webservice, Toast.LENGTH_SHORT).show();
            }
        });
    }
}