package com.flowerzapi.providers_dashboard_app.view.fragments.providersFragments.providersDetails;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.flowerzapi.providers_dashboard_app.model.MainRepository;
import com.flowerzapi.providers_dashboard_app.model.models.userModel.User;

public class ProvidersDetailsViewModel extends ViewModel {

    // Data
    MainRepository mainRepository;
    private MutableLiveData<User> user;

    // Constructor
    public ProvidersDetailsViewModel() {
        mainRepository = MainRepository.getInstance();
        user = mainRepository.getCurrentUser();
    }

    // Getters
    public LiveData<User> getUser() { return user; }
    public User getUserData() { return user.getValue(); }

    // sign out
    public void signOut(){ mainRepository.signOut(); }

    // delete user
    public void deleteCurrentUser(MainRepository.CustomListener<Boolean> listener) {
        mainRepository.deleteCurrentUser(listener);
    }
}