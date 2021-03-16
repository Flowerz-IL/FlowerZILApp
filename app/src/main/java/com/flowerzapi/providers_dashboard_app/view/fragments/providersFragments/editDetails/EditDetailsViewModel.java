package com.flowerzapi.providers_dashboard_app.view.fragments.providersFragments.editDetails;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.flowerzapi.providers_dashboard_app.model.MainRepository;
import com.flowerzapi.providers_dashboard_app.model.userModel.User;

public class EditDetailsViewModel extends ViewModel {

    // Data
    MainRepository mainRepository;
    private MutableLiveData<User> user;

    // Constructor
    public EditDetailsViewModel() {
        mainRepository = MainRepository.getInstance();
        user = mainRepository.getCurrentUser();
    }

    // Getters
    public LiveData<User> getUser() {
        return user;
    }
    public User getUserData() { return user.getValue(); }

    // edit user
    public void editUser(String email, String firstName, String lastName, String phoneNumber, String storeName, MainRepository.CustomListener<Boolean> listener) {
        User current = user.getValue();
        assert current != null;
        current.setEmail(email);
        current.setFirstName(firstName);
        current.setLastName(lastName);
        current.setPhoneNumber(phoneNumber);
        current.setStoreName(storeName);
        mainRepository.updateUser(current, listener);
    }
}