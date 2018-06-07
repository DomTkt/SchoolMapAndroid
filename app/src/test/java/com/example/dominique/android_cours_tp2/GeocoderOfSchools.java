package com.example.dominique.android_cours_tp2;


import android.content.Context;

import com.example.dominique.android_cours_tp2.model.School;
import com.example.dominique.android_cours_tp2.utils.UtilsGeocoder;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static junit.framework.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(MockitoJUnitRunner.class)

//Ne peut pas mocker le context mais toutes les fonctions dans la classe (UtilsGeocoder) fonctionne et les test sont présents.
public class GeocoderOfSchools {

    private static final String FAKE_POSTAL_CODE = "69001";
    private static final String FAKE_CITY_NAME = "Lyon";
    private static final String FAKE_COUNTRY_NAME = "France";
    private static final String FAKE_STREET_NAME = "2-6 Rue Alsace Lorraine";
    private static final double FAKE_LATITUDE = 45.7720199;
    private static final double FAKE_LONGITUDE = 4.826889;
    @Mock
    School school = new School(String.valueOf(FAKE_LATITUDE),String.valueOf(FAKE_LONGITUDE));

    @Mock
    Context fakeContext;
    UtilsGeocoder utilsGeocoder;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        utilsGeocoder = new UtilsGeocoder(fakeContext);
    }

    @Test
    public void GivenAUserWhenIHave45Comma7720199LatitudeAnd4Comma826889ThenThePostalCodeShouldBe69001() throws Exception {
        String result = utilsGeocoder.getCityWithLocation(fakeContext,FAKE_LATITUDE,FAKE_LONGITUDE);
        assertEquals(FAKE_POSTAL_CODE, result);
    }

    @Test
    public void GivenAUserWhenIHave45Comma7720199LatitudeAnd4Comma826889ThenTheCityShouldBeLyon() throws Exception {
        String result = utilsGeocoder.getCityWithLocation(fakeContext,FAKE_LATITUDE,FAKE_LONGITUDE);
        assertEquals(FAKE_CITY_NAME, result);
    }

    @Test
    public void GivenAUserWhenIHave45Comma7720199LatitudeAnd4Comma826889ThenTheCountryShouldBeFrance() throws Exception {
        String result = utilsGeocoder.getCityWithLocation(fakeContext,FAKE_LATITUDE,FAKE_LONGITUDE);
        assertEquals(FAKE_COUNTRY_NAME, result);
    }

    @Test
    public void GivenAUserWhenIHave45Comma7720199LatitudeAnd4Comma826889ThenTheStreetShouldBeFrance() throws Exception {
        String result = utilsGeocoder.getCityWithLocation(fakeContext,FAKE_LATITUDE,FAKE_LONGITUDE);
        assertEquals(FAKE_STREET_NAME, result);
    }
}