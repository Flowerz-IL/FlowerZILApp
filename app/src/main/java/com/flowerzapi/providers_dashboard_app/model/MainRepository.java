package com.flowerzapi.providers_dashboard_app.model;

import android.app.Activity;
import android.content.Context;
import android.net.sip.SipSession;

import androidx.room.Room;

import com.flowerzapi.providers_dashboard_app.model.externalDB.ExternalRepository;
import com.flowerzapi.providers_dashboard_app.model.localDB.AppLocalDB;
import com.flowerzapi.providers_dashboard_app.model.localDB.LocalRepository;
import com.flowerzapi.providers_dashboard_app.model.userModel.User;

import java.util.List;

public class MainRepository {

    // Data
    private static MainRepository instance;
    private final ExternalRepository externalRepository;
    private LocalRepository localRepository;

    // Constructor
    private MainRepository() {
        this.externalRepository = new ExternalRepository();
        this.localRepository = new LocalRepository();
    }

    // Get Instance
    public static synchronized MainRepository getInstance() {
        if(instance == null)
            instance = new MainRepository();

        return instance;
    }

    // Interfaces
    public interface CustomListener<T> { void onComplete(T res); }

    // Authenticate functions
    public void signIn(String email, String password, Activity currentActivity, CustomListener<Boolean> listener){
        externalRepository.signIn(email, password, currentActivity, listener);
    }
    public void signUp(String email, String password, String firstName, String lastName, String phoneNumber, String storeName, Activity currentActivity, CustomListener<Boolean> listener){
        externalRepository.signUp(email, password, currentActivity, isSuccessful -> {
            if(isSuccessful) {
                String newUserId = externalRepository.getCurrentUser().getUid();
                User user = new User(newUserId, email, firstName, lastName, storeName, phoneNumber);
                addUser(user, listener);
            } else listener.onComplete(false);
        });
    }
    public boolean isSignedIn(){
        return externalRepository.getCurrentUser() != null;
    }
    public void signOut() { externalRepository.signOut();}

    // User Model
    public void addUser(User user, CustomListener<Boolean> listener){
        externalRepository.addOrUpdateUser(user, listener);
    }

    public void updateUser(User user, CustomListener<Boolean> listener){
        externalRepository.addOrUpdateUser(user, listener);
    }

    public void getAllUsers(CustomListener<List<User>> listener){
        externalRepository.getAllUsers(listener);
    }

    public void getSpecificUser(String userId,  CustomListener<User> listener){
        externalRepository.getSpecificUser(userId, listener);
    }

    public void getCurrentUser(CustomListener<User> listener){
        getSpecificUser(externalRepository.getCurrentUser().getUid(), listener);
    }

    public void deleteSpecificUser(String userId, CustomListener<Boolean> listener){
        externalRepository.deleteSpecificUser(userId, listener);
    }

}
