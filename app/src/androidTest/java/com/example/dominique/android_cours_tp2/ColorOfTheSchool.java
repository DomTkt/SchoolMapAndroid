package com.example.dominique.android_cours_tp2;


import android.support.test.runner.AndroidJUnit4;

import com.example.dominique.android_cours_tp2.model.School;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ColorOfTheSchool {
    School school = new School();
    @Test
    public void GivenAUserWhenIHave301ForTheNumberOfStudentThenReturnTrue(){
        school.setNbEleve(301);
        assertEquals(true,school.isRed());
    }

    @Test
    public void GivenAUserWhenIHave150ForTheNumberOfStudentThenReturnTrue(){
        school.setNbEleve(148);
        assertEquals(true,school.isGreen());
    }

    @Test
    public void GivenAUserWhenIHaveANumberBetween150And300ForTheNumberOfStudentThenReturnTrue(){
        school.setNbEleve(170);
        assertEquals(true,school.isOrange());
    }

}
