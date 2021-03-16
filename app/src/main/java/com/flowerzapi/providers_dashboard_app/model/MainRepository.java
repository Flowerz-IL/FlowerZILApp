package com.flowerzapi.providers_dashboard_app.model;

import android.app.Activity;
import android.graphics.Bitmap;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.flowerzapi.providers_dashboard_app.model.externalDB.ExternalRepository;
import com.flowerzapi.providers_dashboard_app.model.flowerBouquetModel.FlowerBouquet;
import com.flowerzapi.providers_dashboard_app.model.localDB.LocalRepository;
import com.flowerzapi.providers_dashboard_app.model.userModel.User;

import java.util.List;

public class MainRepository {

    // Data
    private static MainRepository instance;
    private final ExternalRepository externalRepository;
    private LocalRepository localRepository;

    private MutableLiveData<User> currentUser;
    private MutableLiveData<List<User>> users;
    private MutableLiveData<List<FlowerBouquet>> bouquets;

    // Constructor
    private MainRepository() {
        this.externalRepository = new ExternalRepository();
        this.localRepository = new LocalRepository();
        currentUser = new MutableLiveData<>();
        users = new MutableLiveData<>();
        bouquets = new MutableLiveData<>();
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
    public void signOut() { externalRepository.signOut(); currentUser.setValue(null);}
    public void changePassword(String password, MainRepository.CustomListener<Boolean> listener) {
        externalRepository.changePassword(password, listener);
    }
    public void deleteCurrentUser(CustomListener<Boolean> listener) {
       externalRepository.deleteCurrentUser(userId -> {
           if(userId.equals("")) listener.onComplete(false);
           else deleteSpecificUser(userId, listener);
       });
    }

    // User Model
    public void addUser(User user, CustomListener<Boolean> listener){
        externalRepository.addOrUpdateUser(user, isSuccessful -> {
            if(isSuccessful) externalRepository.getAllUsers(users::setValue);
            listener.onComplete(isSuccessful);
        });
    }
    public void updateUser(User user, CustomListener<Boolean> listener){
        externalRepository.addOrUpdateUser(user, isSuccessful -> {
            if(isSuccessful) externalRepository.getAllUsers(users::setValue);
            listener.onComplete(isSuccessful);
        });
    }
    public void getSpecificUser(String userId,  CustomListener<User> listener){
        externalRepository.getSpecificUser(userId, listener);
    }
    public void deleteSpecificUser(String userId, CustomListener<Boolean> listener){
        externalRepository.deleteSpecificUser(userId, isSuccessful -> {
            if(isSuccessful) externalRepository.getAllUsers(users::setValue);
            listener.onComplete(isSuccessful);
        });
    }
    public MutableLiveData<List<User>> getAllUsers(){
        externalRepository.getAllUsers(users::setValue);
        return users;
    }
    public MutableLiveData<User> getCurrentUser(){
        getSpecificUser(externalRepository.getCurrentUser().getUid(), currentUser::setValue);
        return currentUser;
    }

    // Flower Model
    public void addBouquet(String title, String description, Bitmap image, CustomListener<Boolean> listener) {
        FlowerBouquet flowerBouquet = new FlowerBouquet();
        flowerBouquet.setUserId(externalRepository.getCurrentUser().getUid());
        flowerBouquet.setBouquetTitle(title);
        flowerBouquet.setBouquetDescription(description);
        externalRepository.uploadImage(image, flowerBouquet.getBouquetId(), url -> {
            if(url == null) {listener.onComplete(false); return;}
            flowerBouquet.setBouquetImageUrl(url);
            externalRepository.addOrUpdateBouquet(flowerBouquet, isSuccessful -> {
                if(isSuccessful) externalRepository.getAllBouquets(bouquets::setValue);
                listener.onComplete(isSuccessful);
            });
        });
    }
    public MutableLiveData<List<FlowerBouquet>> getAllBouquets(){
        externalRepository.getAllBouquets(bouquets::setValue);
        return bouquets;
    }
}
