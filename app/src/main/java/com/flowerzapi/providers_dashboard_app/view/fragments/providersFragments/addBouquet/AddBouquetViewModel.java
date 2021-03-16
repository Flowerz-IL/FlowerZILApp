package com.flowerzapi.providers_dashboard_app.view.fragments.providersFragments.addBouquet;

import android.graphics.Bitmap;

import androidx.lifecycle.ViewModel;

import com.flowerzapi.providers_dashboard_app.model.MainRepository;

public class AddBouquetViewModel extends ViewModel {

    // Data
    MainRepository mainRepository;

    public AddBouquetViewModel() {
        mainRepository =  MainRepository.getInstance();
    }

    public void addBouquet(String title, String description, Bitmap image, MainRepository.CustomListener<Boolean> listener){
        mainRepository.addBouquet(title, description, image, listener);
    }
}
