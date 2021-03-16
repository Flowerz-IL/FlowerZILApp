package com.flowerzapi.providers_dashboard_app.view.fragments.providersFragments.providerItems;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.flowerzapi.providers_dashboard_app.R;
import com.flowerzapi.providers_dashboard_app.util.HelperClass;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class providers_items_fragment extends Fragment {

    // Data
    private ProvidersItemsViewModel viewModel;
    FloatingActionButton addBouquetBT;

    // Overrides
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialise viewModel
        viewModel = new ViewModelProvider(this).get(ProvidersItemsViewModel.class);

        // Initialise view items
        addBouquetBT = view.findViewById(R.id.add_bouquet_button);

        // Observers
        viewModel.getBouquets().observe(getViewLifecycleOwner(), bouquets -> {
            // TODO: update recycle view.
        });

        // Set buttons actions
        addBouquetBT.setOnClickListener(moveToAddBouquet);
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_providers_items, container, false);
    }

    // On click listeners
    View.OnClickListener moveToAddBouquet = view -> HelperClass.navigateToFragment(view, R.id.provider_items_to_add_bouquet);

}