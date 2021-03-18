package com.flowerzapi.providers_dashboard_app.view.fragments.providersFragments.presentBouquets;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.flowerzapi.providers_dashboard_app.R;
import com.flowerzapi.providers_dashboard_app.model.models.flowerBouquetModel.FlowerBouquet;
import com.flowerzapi.providers_dashboard_app.util.HelperClass;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;


public class providerItemsFragment extends bouquetListFragment {

    // Overrides
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bouquetsListAdapter.setAllowEdit(true);

        // Observers
        viewModel.getBouquets().observe(getViewLifecycleOwner(), bouquets -> {
            List<FlowerBouquet> data = new ArrayList<>();
            for( FlowerBouquet bouquet : bouquets)
                if (bouquet.getUserId().equals(viewModel.getCurrentUserId()))
                    data.add(bouquet);
            bouquetsListAdapter.setData(data);
            bouquetsListAdapter.notifyDataSetChanged();
            loader.setVisibility(View.GONE);
        });

        // Set buttons actions
        addBouquetBT.setOnClickListener(moveToAddBouquet);
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    // On click listeners
    View.OnClickListener moveToAddBouquet = view -> HelperClass.navigateToFragment(view, R.id.provider_items_to_add_bouquet);
}