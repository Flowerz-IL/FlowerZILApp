package com.flowerzapi.providers_dashboard_app.view.fragments.authFragments.signIn;

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
import com.flowerzapi.providers_dashboard_app.util.HelperClass;
import com.flowerzapi.providers_dashboard_app.util.Validation;
import com.flowerzapi.providers_dashboard_app.view.activities.ProvidersActivity;

public class signInFragment extends Fragment {

    // Data
    SignInViewModel viewModel;
    ProgressBar loader;
    Button signInButton, signUpButton;
    EditText emailET, passwordET;

    // Overrides
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialise viewModel
        viewModel = new ViewModelProvider(this).get(SignInViewModel.class);

        // Initialise view items
        loader = view.findViewById(R.id.loader_sign_in);
        signInButton = view.findViewById(R.id.sign_in_bt);
        signUpButton = view.findViewById(R.id.sign_up_bt);
        emailET = view.findViewById(R.id.email_et_sign_in);
        passwordET = view.findViewById(R.id.password_et_sign_in);

        // Set buttons actions
        signUpButton.setOnClickListener(moveToSignUp);
        signInButton.setOnClickListener(signIn);

        loader.setVisibility(View.GONE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.signin_fragment, container, false);
    }

    // On click listeners
    View.OnClickListener moveToSignUp = view -> HelperClass.navigateToFragment(view, R.id.signin_to_signup);
    View.OnClickListener signIn = view -> {
        loader.setVisibility(View.VISIBLE);
        signUpButton.setVisibility(View.GONE);
        signInButton.setVisibility(View.GONE);
        String email = emailET.getText().toString();
        String password = passwordET.getText().toString();
        if(!validateInputData(email, password)) {
            loader.setVisibility(View.GONE);
            signUpButton.setVisibility(View.VISIBLE);
            signInButton.setVisibility(View.VISIBLE);
            return;
        }
        viewModel.signIn(email, password, getActivity(), isSuccessful -> {
            loader.setVisibility(View.GONE);
            signUpButton.setVisibility(View.VISIBLE);
            signInButton.setVisibility(View.VISIBLE);
            if(isSuccessful) HelperClass.navigateToActivity(getActivity(), ProvidersActivity.class);
            else HelperClass.alertMessage(getActivity(), "Filed To Login");
            initialiseET();
        });
    };

    // Helpers
    private boolean validateInputData(String email, String password) {
        boolean res = true;

        if(!Validation.validateEmail(email)){
            emailET.setError("please insert a valid email address");
            res = false;
        }

        if(!Validation.validateShortText(password)){
            passwordET.setError("please insert a valid password");
            res = false;
        }

        if(!res) HelperClass.alertMessage(getActivity(), "fix the errors before submitting");
        return res;
    }
    private void initialiseET(){
        emailET.setText("");
        passwordET.setText("");
    }
}