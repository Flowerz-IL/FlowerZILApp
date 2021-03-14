package com.flowerzapi.providers_dashboard_app.view.fragments.authFragments.signUp;

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
import android.widget.ProgressBar;

import com.flowerzapi.providers_dashboard_app.R;
import com.flowerzapi.providers_dashboard_app.util.HelperClass;
import com.flowerzapi.providers_dashboard_app.util.Validation;
import com.flowerzapi.providers_dashboard_app.view.activities.ProvidersActivity;

public class signup_fragment extends Fragment {

    // Data
    ProgressBar loader;
    SignUpViewModel viewModel;
    Button signInButton, signUpButton;
    EditText emailET, passwordET, firstNameET, lastNameET, phoneNumberET, storeNameET;

    // Overrides
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialise viewModel
        viewModel = new ViewModelProvider(this).get(SignUpViewModel.class);

        // Initialise view items
        loader = view.findViewById(R.id.loader_sign_up);
        signInButton = view.findViewById(R.id.cancel_bt_edit_details);
        signUpButton = view.findViewById(R.id.edit_bt_edit_details);
        emailET = view.findViewById(R.id.email_et_edit_details);
        passwordET = view.findViewById(R.id.password_et_sign_up);
        firstNameET = view.findViewById(R.id.first_name_et_edit_details);
        lastNameET = view.findViewById(R.id.last_name_et_edit_details);
        phoneNumberET = view.findViewById(R.id.phone_et_edit_details);
        storeNameET = view.findViewById(R.id.store_name_et_edit_details);

        // Set buttons actions
        signInButton.setOnClickListener(moveToSignIn);
        signUpButton.setOnClickListener(signUp);

        loader.setVisibility(View.GONE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.signup_fragment, container, false);
    }

    // On click listeners
    View.OnClickListener moveToSignIn = view -> HelperClass.navigateToFragment(view, -1);
    View.OnClickListener signUp = view -> {
        loader.setVisibility(View.VISIBLE);
        signUpButton.setVisibility(View.GONE);
        signInButton.setVisibility(View.GONE);
        String email = emailET.getText().toString();
        String password = passwordET.getText().toString();
        String firstName = firstNameET.getText().toString();
        String lastName = lastNameET.getText().toString();
        String phoneNumber = phoneNumberET.getText().toString();
        String storeName = storeNameET.getText().toString();
        if(!validateInputData(email, password, firstName, lastName, phoneNumber, storeName)) return;
        viewModel.signUp(email, password, firstName, lastName, phoneNumber, storeName, getActivity(), isSuccessful -> {
            loader.setVisibility(View.GONE);
            signUpButton.setVisibility(View.VISIBLE);
            signInButton.setVisibility(View.VISIBLE);
            if(isSuccessful) HelperClass.navigateToActivity(getActivity(), ProvidersActivity.class);
            else HelperClass.alertMessage(getActivity(), "Filed To SignUp");
            initialiseET();
        });
    };

    // Helpers
    private boolean validateInputData(String email, String password, String firstName, String lastName, String phoneNumber, String storeName) {
        boolean res = true;

        if(!Validation.validateEmail(email)){
            emailET.setError("please insert a valid email address");
            res = false;
        }

        if(!Validation.validateShortText(password)){
            passwordET.setError("please insert a valid password");
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
    private void initialiseET(){
        emailET.setText("");
        passwordET.setText("");
        firstNameET.setText("");
        lastNameET.setText("");
        phoneNumberET.setText("");
        storeNameET.setText("");
    }
}