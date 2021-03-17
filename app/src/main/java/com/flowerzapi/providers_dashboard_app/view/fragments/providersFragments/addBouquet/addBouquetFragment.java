package com.flowerzapi.providers_dashboard_app.view.fragments.providersFragments.addBouquet;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.flowerzapi.providers_dashboard_app.R;
import com.flowerzapi.providers_dashboard_app.util.HelperClass;
import com.flowerzapi.providers_dashboard_app.util.Validation;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

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
        if(!validateInputData(title,description)) return;
        viewModel.addBouquet(title, description, image, isSuccessful -> {
            Loader.setVisibility(View.GONE);
            addBT.setVisibility(View.VISIBLE);
            cancelBT.setVisibility(View.VISIBLE);
            if(isSuccessful) HelperClass.navigateToFragment(view, -1);
            else HelperClass.alertMessage(getActivity(), "Something went wrong please try again later");
        });
    };
}