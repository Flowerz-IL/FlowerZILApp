package com.flowerzapi.providers_dashboard_app.modelView.authModelView;

import android.app.Activity;

import androidx.lifecycle.ViewModel;

import com.flowerzapi.providers_dashboard_app.model.MainRepository;

public class SignInViewModel extends ViewModel {

    // Data
    MainRepository mainRepository = MainRepository.getInstance();

    // Sign-in
    public void signIn(String email, String password, Activity currentActivity, MainRepository.CustomListener<Boolean> listener) {
        mainRepository.signIn(email, password, currentActivity, listener);
    }
}
