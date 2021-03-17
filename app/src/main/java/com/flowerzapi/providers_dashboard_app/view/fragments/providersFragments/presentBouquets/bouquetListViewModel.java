package com.flowerzapi.providers_dashboard_app.view.fragments.providersFragments.presentBouquets;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.flowerzapi.providers_dashboard_app.model.MainRepository;
import com.flowerzapi.providers_dashboard_app.model.models.flowerBouquetModel.FlowerBouquet;

import java.util.List;

public class bouquetListViewModel extends ViewModel {

    // Data
    MainRepository mainRepository;
    private LiveData<List<FlowerBouquet>> flowerBouquets;
    private String currentUserId;

    // Constructor
    public bouquetListViewModel() {
        mainRepository = MainRepository.getInstance();
        this.currentUserId = mainRepository.getCurrentUserId();
        flowerBouquets = mainRepository.getAllBouquets();
    }

    // Getter
    public String getCurrentUserId() { return currentUserId; }
    public LiveData<List<FlowerBouquet>> getBouquets() {
        return flowerBouquets;
    }
    public List<FlowerBouquet> getBouquetsData() { return flowerBouquets.getValue(); }
}