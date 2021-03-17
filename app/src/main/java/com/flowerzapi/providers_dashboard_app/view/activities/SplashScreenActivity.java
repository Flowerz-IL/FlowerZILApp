package com.flowerzapi.providers_dashboard_app.view.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.flowerzapi.providers_dashboard_app.R;
import com.flowerzapi.providers_dashboard_app.model.MainRepository;
import com.flowerzapi.providers_dashboard_app.util.HelperClass;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        MainRepository mainRepository = MainRepository.getInstance();

        if(mainRepository.isSignedIn()) HelperClass.navigateToActivity(this, ProvidersActivity.class);
        else HelperClass.navigateToActivity(this, AuthActivity.class);
    }
}