package com.flowerzapi.providers_dashboard_app.model;

import android.app.Activity;
import android.graphics.Bitmap;

import androidx.lifecycle.MutableLiveData;

import com.flowerzapi.providers_dashboard_app.model.externalDB.ExternalRepository;
import com.flowerzapi.providers_dashboard_app.model.models.flowerBouquetModel.FlowerBouquet;
import com.flowerzapi.providers_dashboard_app.model.localDB.LocalRepository;
import com.flowerzapi.providers_dashboard_app.model.models.userModel.User;

import java.util.List;
import java.util.Objects;

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
        externalRepository.signIn(email, password, currentActivity, isSuccessful -> {
            if(isSuccessful) getCurrentUser();
            listener.onComplete(isSuccessful);
        });
    }
    public void signUp(String email, String password, String firstName, String lastName, String phoneNumber, String storeName, Activity currentActivity, CustomListener<Boolean> listener){
        externalRepository.signUp(email, password, currentActivity, isSuccessful -> {
            if(isSuccessful) {
                String newUserId = externalRepository.getCurrentUser().getUid();
                User user = new User(newUserId, email, firstName, lastName, storeName, phoneNumber);
                addUser(user, success -> {
                    if(success) getCurrentUser();
                    listener.onComplete(success);
                });
            } else listener.onComplete(false);
        });
    }
    public boolean isSignedIn(){
        boolean res = externalRepository.getCurrentUser() != null;
        if(res) getCurrentUser();
        return res;
    }
    public void signOut() { externalRepository.signOut();}
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
    public String getCurrentUserId(){
        return isSignedIn() ? externalRepository.getCurrentUser().getUid() : null;
    }

    // Flower Model
    public void addBouquet(String title, String description, Bitmap image, CustomListener<Boolean> listener) {
        FlowerBouquet flowerBouquet = new FlowerBouquet();
        flowerBouquet.setUserId(Objects.requireNonNull(currentUser.getValue()).getUserId());
        flowerBouquet.setUserPhone(currentUser.getValue().getPhoneNumber());
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
    public void updateBouquet(String bouquetId, String title, String description, Bitmap image, CustomListener<Boolean> listener){
        FlowerBouquet flowerBouquet = new FlowerBouquet(bouquetId);
        flowerBouquet.setUserId(Objects.requireNonNull(currentUser.getValue()).getUserId());
        flowerBouquet.setUserPhone(currentUser.getValue().getPhoneNumber());
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
    public void deleteSpecificBouquet(String bouquetId, CustomListener<Boolean> listener){
        externalRepository.deleteSpecificBouquet(bouquetId, isSuccessful -> {
            if(isSuccessful) externalRepository.getAllBouquets(bouquets::setValue);
            listener.onComplete(isSuccessful);
        });
    }
    public MutableLiveData<List<FlowerBouquet>> getAllBouquets(){
        externalRepository.getAllBouquets(bouquets::setValue);
        return bouquets;
    }
}
