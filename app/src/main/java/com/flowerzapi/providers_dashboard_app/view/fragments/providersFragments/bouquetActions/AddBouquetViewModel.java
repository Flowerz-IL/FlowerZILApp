package com.flowerzapi.providers_dashboard_app.view.fragments.providersFragments.bouquetActions;

import android.graphics.Bitmap;

import androidx.lifecycle.ViewModel;

import com.flowerzapi.providers_dashboard_app.model.MainRepository;

public class AddBouquetViewModel extends ViewModel {

    // Data
    MainRepository mainRepository;

    // Constructor
    public AddBouquetViewModel() {
        mainRepository =  MainRepository.getInstance();
    }

    // Add bouquet
    public void addBouquet(String title, String description, Bitmap image, MainRepository.CustomListener<Boolean> listener){
        mainRepository.addBouquet(title, description, image, listener);
    }

    public void updateBouquet(String bouquetId, String title, String description, Bitmap image, MainRepository.CustomListener<Boolean> listener){
        mainRepository.updateBouquet(bouquetId, title, description, image, listener);
    }
}
