package com.flowerzapi.providers_dashboard_app.view.fragments.authFragments.signUp;

import android.app.Activity;

import androidx.lifecycle.ViewModel;

import com.flowerzapi.providers_dashboard_app.model.MainRepository;

public class SignUpViewModel extends ViewModel {
    // Data
    MainRepository mainRepository = MainRepository.getInstance();

    // Sign-up
    public void signUp(String email, String password, String firstName, String lastName, String phoneNumber, String storeName, Activity currentActivity, MainRepository.CustomListener<Boolean> listener) {
        mainRepository.signUp(email, password, firstName, lastName, phoneNumber, storeName, currentActivity, listener);
    }
}
