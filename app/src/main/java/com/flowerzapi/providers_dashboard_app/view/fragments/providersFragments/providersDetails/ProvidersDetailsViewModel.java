package com.flowerzapi.providers_dashboard_app.view.fragments.providersFragments.providersDetails;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.flowerzapi.providers_dashboard_app.model.MainRepository;
import com.flowerzapi.providers_dashboard_app.model.userModel.User;

public class ProvidersDetailsViewModel extends ViewModel {

    // Data
    MainRepository mainRepository = MainRepository.getInstance();
    private MutableLiveData<User> user;

    // Constructor
    public ProvidersDetailsViewModel() {
        mainRepository = MainRepository.getInstance();
        user = new MutableLiveData<>();
        mainRepository.getCurrentUser(user -> {
            this.user.setValue(user);
        });
    }

    // Getters
    public LiveData<User> getUser() {
        return user;
    }

    // sign out
    public void signOut(){ mainRepository.signOut(); }

    // delete user
    public void deleteCurrentUser(MainRepository.CustomListener<Boolean> listener) {
        mainRepository.deleteCurrentUser(listener);
    }
}