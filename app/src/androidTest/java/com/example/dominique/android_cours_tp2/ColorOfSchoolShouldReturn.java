package com.example.dominique.android_cours_tp2;

import android.support.test.runner.AndroidJUnit4;

import com.example.dominique.android_cours_tp2.model.School;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class ColorOfSchoolShouldReturn {

    @Test
    public void TrueIfIHaveNumberHigherThan300(){
        School school = new School();
        school.setNbEleve(301);
        assertEquals(true,school.isRed());
    }

    @Test
    public void TrueIfIHaveNumberLowerThan148(){
        School school = new School();
        school.setNbEleve(148);
        assertEquals(true,school.isGreen());
    }

    @Test
    public void TrueIfIHaveNumberBetween150And300(){
        School school = new School();
        school.setNbEleve(170);
        assertEquals(true,school.isOrange());
    }

}