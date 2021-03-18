package com.flowerzapi.providers_dashboard_app.view.fragments.providersFragments.editDetails;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.flowerzapi.providers_dashboard_app.R;
import com.flowerzapi.providers_dashboard_app.model.models.userModel.User;
import com.flowerzapi.providers_dashboard_app.util.HelperClass;
import com.flowerzapi.providers_dashboard_app.util.Validation;

public class editDetailsFragment extends Fragment {

    // Data
    EditDetailsViewModel viewModel;
    ProgressBar loader;
    EditText emailET, firstNameET, lastNameET, phoneNumberET, storeNameET;
    Button editBT, cancelBT;

    // Overrides
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialise viewModel
        viewModel = new ViewModelProvider(this).get(EditDetailsViewModel.class);

        // Initialise view items
        loader = view.findViewById(R.id.loader_edit_details);
        emailET = view.findViewById(R.id.email_et_edit_details);
        firstNameET = view.findViewById(R.id.first_name_et_edit_details);
        lastNameET = view.findViewById(R.id.last_name_et_edit_details);
        phoneNumberET = view.findViewById(R.id.phone_et_edit_details);
        storeNameET = view.findViewById(R.id.store_name_et_edit_details);
        editBT = view.findViewById(R.id.edit_bt_edit_details);
        cancelBT = view.findViewById(R.id.cancel_bt_edit_details);

        toggleVisibility(true);

        // Observers
        viewModel.getUser().observe(getViewLifecycleOwner(), user -> {
            initialiseForm(user);
            toggleVisibility(false);
        });

        // Set buttons actions
        editBT.setOnClickListener(editUserAndGoBack);
        cancelBT.setOnClickListener(goBackToUserDetails);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.edit_details_fragment, container, false);
    }

    // On click listeners
    View.OnClickListener goBackToUserDetails = view -> HelperClass.navigateToFragment(view, -1);
    View.OnClickListener editUserAndGoBack = view -> {
        String email = emailET.getText().toString();
        String firstName = firstNameET.getText().toString();
        String lastName = lastNameET.getText().toString();
        String phoneNumber = phoneNumberET.getText().toString();
        String storeName = storeNameET.getText().toString();
        if(!validateInputData(email, firstName, lastName, phoneNumber, storeName)) return;
        viewModel.editUser(email, firstName, lastName, phoneNumber, storeName, isSuccessful -> {
            if(isSuccessful) HelperClass.navigateToFragment(view, -1);
            else HelperClass.alertMessage(getActivity(), "Something went wrong please try again later");
        });
    };

    // Helpers
    public void toggleVisibility(Boolean loaderOn){
        int loaderVisibility = loaderOn ? View.VISIBLE : View.GONE;
        int inputsVisibility = loaderOn ? View.GONE : View.VISIBLE;
        loader.setVisibility(loaderVisibility);
        emailET.setVisibility(inputsVisibility);
        firstNameET.setVisibility(inputsVisibility);
        lastNameET.setVisibility(inputsVisibility);
        phoneNumberET.setVisibility(inputsVisibility);
        storeNameET.setVisibility(inputsVisibility);
        editBT.setVisibility(inputsVisibility);
        cancelBT.setVisibility(inputsVisibility);
    }
    public void initialiseForm(User user){
        emailET.setText(user.getEmail());
        firstNameET.setText(user.getFirstName());
        lastNameET.setText(user.getLastName());
        phoneNumberET.setText(user.getPhoneNumber());
        storeNameET.setText(user.getStoreName());
    }
    private boolean validateInputData(String email, String firstName, String lastName, String phoneNumber, String storeName) {
        boolean res = true;

        if(!Validation.validateEmail(email)){
            emailET.setError("please insert a valid email address");
            res = false;
        }

        if(!Validation.validateShortText(firstName)){
            firstNameET.setError("full name - string length between 4 to 25");
            res = false;
        }

        if(!Validation.validateShortText(lastName)){
            lastNameET.setError("last name - string length between 4 to 25");
            res = false;
        }

        if(!Validation.validatePhoneNumber(phoneNumber)){
            phoneNumberET.setError("please insert a valid mobile phone number");
            res = false;
        }

        if(!Validation.validateShortText(storeName)){
            storeNameET.setError("store name - string length between 4 to 25");
            res = false;
        }

        if(!res) HelperClass.alertMessage(getActivity(), "fix the errors before submitting");
        return res;
    }
}