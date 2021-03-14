package com.flowerzapi.providers_dashboard_app.view.fragments.providersFragments.changePassword;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.flowerzapi.providers_dashboard_app.R;
import com.flowerzapi.providers_dashboard_app.util.HelperClass;
import com.flowerzapi.providers_dashboard_app.util.Validation;
import com.flowerzapi.providers_dashboard_app.view.activities.AuthActivity;
import com.flowerzapi.providers_dashboard_app.view.fragments.providersFragments.providersDetails.ProvidersDetailsViewModel;

public class change_password_fragment extends Fragment {

    // Data
    ChangePasswordViewModel viewModel;
    EditText passwordET;
    Button saveBT, cancelBT;

    // Overrides
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialise viewModel
        viewModel = new ViewModelProvider(this).get(ChangePasswordViewModel.class);

        // Initialise view items
        passwordET = view.findViewById(R.id.change_password_et);
        saveBT = view.findViewById(R.id.save_bt_change_password);
        cancelBT = view.findViewById(R.id.cancel_bt_change_password);

        // Set buttons actions
        saveBT.setOnClickListener(changePassword);
        cancelBT.setOnClickListener(moveToEditItems);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.change_password_fragment, container, false);
    }

    // On click listeners
    View.OnClickListener changePassword = view -> {
        String password = passwordET.getText().toString();
        if(!Validation.validateShortText(password)){
            passwordET.setError("password - string length between 4 to 25");
            return;
        }
        viewModel.changePassword(password, isSuccessful -> {
            if(isSuccessful){
                HelperClass.alertMessage(getActivity(), "password changed successfully");
                HelperClass.navigateToFragment(view, -1);

            } else HelperClass.alertMessage(getActivity(), "something went wrong while change your password");
        });
    };
    View.OnClickListener moveToEditItems = view -> HelperClass.navigateToFragment(view, -1);
}