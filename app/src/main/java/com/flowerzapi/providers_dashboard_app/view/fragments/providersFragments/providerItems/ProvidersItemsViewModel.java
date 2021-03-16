package com.flowerzapi.providers_dashboard_app.view.fragments.providersFragments.providerItems;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.flowerzapi.providers_dashboard_app.model.MainRepository;
import com.flowerzapi.providers_dashboard_app.model.flowerBouquetModel.FlowerBouquet;

import java.util.List;

public class ProvidersItemsViewModel extends ViewModel {

    // Data
    MainRepository mainRepository;
    private MutableLiveData<List<FlowerBouquet>> flowerBouquets;

    // Constructor
    public ProvidersItemsViewModel() {
        mainRepository = MainRepository.getInstance();
        flowerBouquets = mainRepository.getAllBouquets();
    }

    // Getter
    public LiveData<List<FlowerBouquet>> getBouquets() {
        return flowerBouquets;
    }
    public List<FlowerBouquet> getBouquetsData() { return flowerBouquets.getValue(); }
}