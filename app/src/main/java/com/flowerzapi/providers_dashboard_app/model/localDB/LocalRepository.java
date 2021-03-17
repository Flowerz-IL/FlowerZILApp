package com.flowerzapi.providers_dashboard_app.model.localDB;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.flowerzapi.providers_dashboard_app.model.models.flowerBouquetModel.FlowerBouquet;

import java.util.List;

public class LocalRepository {

    // Flower bouquets model
    public LiveData<List<FlowerBouquet>> getAllBouquets(){
        return AppLocalDB.getInstance().flowerBouquetDao().getAllBouquets();
    }
    public void addBouquet(FlowerBouquet bouquet){
        AppLocalDB.getInstance().flowerBouquetDao().insertBouquets(bouquet);
    }
}
