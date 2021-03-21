package com.flowerzapi.providers_dashboard_app.view.fragments.providersFragments.presentBouquets;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.flowerzapi.providers_dashboard_app.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class bouquetListFragment extends Fragment {

    // Data
    protected SwipeRefreshLayout swipeRefresh;
    protected ProgressBar loader;
    protected bouquetListViewModel viewModel;
    protected RecyclerView bouquetList;
    protected FloatingActionButton addBouquetBT;
    protected BouquetsListAdapter bouquetsListAdapter;

    // Overrides
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialise viewModel
        viewModel = new ViewModelProvider(this).get(bouquetListViewModel.class);

        // Initialise view items
        swipeRefresh = view.findViewById(R.id.swipeRefresh);
        loader = view.findViewById(R.id.loader_provider_items);
        bouquetList = view.findViewById(R.id.bouquet_list_provider_items);
        bouquetsListAdapter = new BouquetsListAdapter(getActivity(), viewModel.getCurrentUserId());
        bouquetList.setAdapter(bouquetsListAdapter);
        bouquetList.setLayoutManager(new LinearLayoutManager(getActivity()));
        addBouquetBT = view.findViewById(R.id.add_bouquet_button);

        swipeRefresh.setOnRefreshListener(() -> viewModel.refreshList(isSuccessful -> swipeRefresh.setRefreshing(false)));
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.item_list_fragment, container, false);
    }
}
