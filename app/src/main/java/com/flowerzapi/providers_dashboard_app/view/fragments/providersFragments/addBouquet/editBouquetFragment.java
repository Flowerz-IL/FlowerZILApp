package com.flowerzapi.providers_dashboard_app.view.fragments.providersFragments.addBouquet;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.flowerzapi.providers_dashboard_app.R;
import com.flowerzapi.providers_dashboard_app.util.HelperClass;
import com.flowerzapi.providers_dashboard_app.util.Validation;
import com.squareup.picasso.Picasso;


public class editBouquetFragment extends bouquetActionsFragment {

    // Data
    String currentBouquetId;

    // Overrides
    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Get arguments
        assert getArguments() != null;
        this.currentBouquetId = editBouquetFragmentArgs.fromBundle(getArguments()).getBouquetId();
        String imageUrl = editBouquetFragmentArgs.fromBundle(getArguments()).getBouqetImageUrl();
        String title = editBouquetFragmentArgs.fromBundle(getArguments()).getBouqetTitle();
        String description = editBouquetFragmentArgs.fromBundle(getArguments()).getBouquetDescription();

        // update data
        textView.setText("Edit Bouquet");
        Picasso.get()
                .load(imageUrl)
                .placeholder(R.drawable.defaultbouquet)
                .error(R.drawable.defaultbouquet)
                .into(bouquetIV);
        titleET.setText(title);
        descriptionET.setText(description);
        addBT.setText("Edit");
        addBT.setOnClickListener(updateBouquet);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    // OnClickListeners
    View.OnClickListener updateBouquet = view -> {
        Loader.setVisibility(View.VISIBLE);
        addBT.setVisibility(View.GONE);
        cancelBT.setVisibility(View.GONE);
        String title = titleET.getText().toString();
        String description = descriptionET.getText().toString();
        Bitmap image = ((BitmapDrawable) bouquetIV.getDrawable()).getBitmap();
        if(!validateInputData(title,description)) return;
        viewModel.updateBouquet(currentBouquetId, title, description, image, isSuccessful -> {
            Loader.setVisibility(View.GONE);
            addBT.setVisibility(View.VISIBLE);
            cancelBT.setVisibility(View.VISIBLE);
            if(isSuccessful) HelperClass.navigateToFragment(view, -1);
            else HelperClass.alertMessage(getActivity(), "Something went wrong please try again later");
        });
    };

}