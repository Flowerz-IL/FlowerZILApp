package com.flowerzapi.providers_dashboard_app.view.fragments.providersFragments.changePassword;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.flowerzapi.providers_dashboard_app.model.MainRepository;
import com.flowerzapi.providers_dashboard_app.model.userModel.User;

public class ChangePasswordViewModel extends ViewModel {

    // Data
    MainRepository mainRepository = MainRepository.getInstance();

    // Constructor
    public ChangePasswordViewModel() {
        mainRepository = MainRepository.getInstance();
    }

    // change password
    public void changePassword(String password, MainRepository.CustomListener<Boolean> listener){ mainRepository.changePassword(password, listener); }
}