package com.example.dominique.android_cours_tp2.interfaces;

import com.example.dominique.android_cours_tp2.model.School;

import java.util.List;

/**
 * Created by dominique on 08/02/2018.
 */

public interface IGetSchools {
    void onSuccess(List<School> schools);
    void onError(Throwable t);
}
