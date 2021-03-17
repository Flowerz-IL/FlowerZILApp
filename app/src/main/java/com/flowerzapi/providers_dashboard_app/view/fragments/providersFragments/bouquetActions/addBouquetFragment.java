package com.flowerzapi.providers_dashboard_app.view.fragments.providersFragments.bouquetActions;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.flowerzapi.providers_dashboard_app.util.HelperClass;

public class addBouquetFragment extends bouquetActionsFragment {

    // Override
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textView.setText("Add Bouquet");
        addBT.setOnClickListener(addBouquet);
        Loader.setVisibility(View.GONE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    // On click listeners
    View.OnClickListener addBouquet = view -> {
        Loader.setVisibility(View.VISIBLE);
        addBT.setVisibility(View.GONE);
        cancelBT.setVisibility(View.GONE);
        String title = titleET.getText().toString();
        String description = descriptionET.getText().toString();
        Bitmap image = ((BitmapDrawable) bouquetIV.getDrawable()).getBitmap();
        if(!validateInputData(title,description)) {
            Loader.setVisibility(View.GONE);
            addBT.setVisibility(View.VISIBLE);
            cancelBT.setVisibility(View.VISIBLE);
            return;
        }
        viewModel.addBouquet(title, description, image, isSuccessful -> {
            Loader.setVisibility(View.GONE);
            addBT.setVisibility(View.VISIBLE);
            cancelBT.setVisibility(View.VISIBLE);
            if(isSuccessful) HelperClass.navigateToFragment(view, -1);
            else HelperClass.alertMessage(getActivity(), "Something went wrong please try again later");
        });
    };
}