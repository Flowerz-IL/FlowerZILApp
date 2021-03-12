package com.flowerzapi.providers_dashboard_app.view.fragments.providersFragments.providerItems;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.flowerzapi.providers_dashboard_app.R;


public class providers_items_fragment extends Fragment {

    private ProvidersItemsViewModel providersItemsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        providersItemsViewModel =
                new ViewModelProvider(this).get(ProvidersItemsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_providers_items, container, false);
        final TextView textView = root.findViewById(R.id.text_dashboard);
        providersItemsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}