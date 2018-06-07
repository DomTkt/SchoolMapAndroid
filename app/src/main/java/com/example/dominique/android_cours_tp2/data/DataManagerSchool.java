package com.example.dominique.android_cours_tp2.data;

import android.widget.Toast;

import com.example.dominique.android_cours_tp2.R;
import com.example.dominique.android_cours_tp2.interfaces.IGetSchools;
import com.example.dominique.android_cours_tp2.model.School;
import com.example.dominique.android_cours_tp2.presenter.DashboardActivity;
import com.example.dominique.android_cours_tp2.service.EcoleAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dominique on 08/02/2018.
 */

public class DataManagerSchool {

    private static DataManagerSchool instance = null;
    private EcoleAPI ecoleAPI;

    public DataManagerSchool() {

        ecoleAPI = new Retrofit.Builder()
                .baseUrl(EcoleAPI.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(EcoleAPI.class);

    }

    public static DataManagerSchool getInstance() {
        if (instance == null) {
            instance = new DataManagerSchool();
        }
        return instance;
    }

    public Call<List<School>> getSchool(final IGetSchools callback) {

        Call<List<School>> schools = ecoleAPI.listEcole();

        schools.enqueue(new Callback<List<School>>() {
            @Override
            public void onResponse(Call<List<School>> call, Response<List<School>> response) {
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<School>> call, Throwable t) {
                callback.onError(t);
                //Toast.makeText(DashboardActivity.this, R.string.dashboard_activity_text_failure_webservice, Toast.LENGTH_SHORT).show();
            }
        });

        return schools;
    }


}