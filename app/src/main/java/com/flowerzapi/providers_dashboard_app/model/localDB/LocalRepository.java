package com.flowerzapi.providers_dashboard_app.model.localDB;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.flowerzapi.providers_dashboard_app.model.MainRepository;
import com.flowerzapi.providers_dashboard_app.model.models.flowerBouquetModel.FlowerBouquet;
import com.flowerzapi.providers_dashboard_app.model.models.userModel.User;
import com.flowerzapi.providers_dashboard_app.util.BackgroundTasksAgent;

import java.util.List;

public class LocalRepository {

    // User model
    public LiveData<List<User>> getAllUsers(){
        return AppLocalDB.getInstance().userDao().getAllUsers();
    }
    public void addUser(User user){
        BackgroundTasksAgent newAgent = new BackgroundTasksAgent();
        newAgent.executeAsync( () ->
                AppLocalDB.getInstance().userDao().insertUsers(user) , null);
    }
    public void deleteUserFromId(String userId, MainRepository.CustomListener<Boolean> listener){
        BackgroundTasksAgent newAgent = new BackgroundTasksAgent();
        newAgent.executeAsync( () ->
                AppLocalDB.getInstance().userDao().deleteUserFromId(userId) , listener);
    }

    // Flower bouquets model
    public LiveData<List<FlowerBouquet>> getAllBouquets(){
        return AppLocalDB.getInstance().flowerBouquetDao().getAllBouquets();
    }
    public void addBouquet(FlowerBouquet bouquet){
        BackgroundTasksAgent newAgent = new BackgroundTasksAgent();
        newAgent.executeAsync( () ->
                AppLocalDB.getInstance().flowerBouquetDao().insertBouquets(bouquet) , null);
    }
    public void deleteBouquetFromId(String bouquetId, MainRepository.CustomListener<Boolean> listener){
        BackgroundTasksAgent newAgent = new BackgroundTasksAgent();
        newAgent.executeAsync( () ->
                AppLocalDB.getInstance().flowerBouquetDao().deleteBouquetFromId(bouquetId) , listener);
    }
}
