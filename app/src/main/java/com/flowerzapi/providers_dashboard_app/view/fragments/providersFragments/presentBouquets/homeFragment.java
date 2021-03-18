package com.flowerzapi.providers_dashboard_app.view.fragments.providersFragments.presentBouquets;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


public class homeFragment extends bouquetListFragment {

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        addBouquetBT.setVisibility(View.GONE);

        // Observers
        viewModel.getBouquets().observe(getViewLifecycleOwner(), bouquets -> {
            bouquetsListAdapter.setData(bouquets);
            bouquetsListAdapter.notifyDataSetChanged();
            loader.setVisibility(View.GONE);
        });
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}