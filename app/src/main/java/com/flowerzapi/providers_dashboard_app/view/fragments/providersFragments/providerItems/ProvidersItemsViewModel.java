package com.flowerzapi.providers_dashboard_app.view.fragments.providersFragments.providerItems;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ProvidersItemsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ProvidersItemsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}