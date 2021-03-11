package com.flowerzapi.providers_dashboard_app.view.fragments.authFragments;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.flowerzapi.providers_dashboard_app.R;
import com.flowerzapi.providers_dashboard_app.modelView.authModelView.SignInViewModel;
import com.flowerzapi.providers_dashboard_app.util.HelperClass;
import com.flowerzapi.providers_dashboard_app.util.Validation;
import com.flowerzapi.providers_dashboard_app.view.activities.MainActivity;

import java.util.Objects;


public class signin_fragment extends Fragment {

    // Data
    SignInViewModel viewModel;
    Button signInButton, signUpButton;
    EditText emailET, passwordET;

    // Overrides
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialise viewModel
        viewModel = new ViewModelProvider(this).get(SignInViewModel.class);

        // Initialise view items
        signInButton = view.findViewById(R.id.sign_in_bt);
        signUpButton = view.findViewById(R.id.sign_up_bt);
        emailET = view.findViewById(R.id.email_et_sign_in);
        passwordET = view.findViewById(R.id.password_et_sign_in);

        // Set buttons actions
        signUpButton.setOnClickListener(moveToSignUp);
        signInButton.setOnClickListener(signIn);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.signin_fragment, container, false);
    }

    // On click listeners
    View.OnClickListener moveToSignUp = view -> HelperClass.navigateToFragment(view, R.id.signin_to_signup);
    View.OnClickListener signIn = view -> {
        String email = emailET.getText().toString();
        String password = passwordET.getText().toString();
        if(!validateInputData(email, password)) return;
        viewModel.signIn(email, password, getActivity(), isSuccessful -> {
            if(isSuccessful) HelperClass.navigateToActivity(getActivity(), MainActivity.class);
            else HelperClass.alertMessage(getActivity(), "Filed To Login");
            initialiseET();
        });
    };

    // Helpers
    private boolean validateInputData(String email, String password) {

        if(!Validation.validateEmail(email)){
            HelperClass.alertMessage(getActivity(), "please insert a valid email address");
            return false;
        }

        if(!Validation.validateShortText(password)){
            HelperClass.alertMessage(getActivity(), "please insert a valid password");
            return false;
        }

        return true;
    }
    private void initialiseET(){
        emailET.setText("");
        passwordET.setText("");
    }
}