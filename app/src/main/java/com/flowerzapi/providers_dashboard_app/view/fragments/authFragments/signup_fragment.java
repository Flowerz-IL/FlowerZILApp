package com.flowerzapi.providers_dashboard_app.view.fragments.authFragments;

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
import com.flowerzapi.providers_dashboard_app.modelView.authModelView.SignInViewModel;
import com.flowerzapi.providers_dashboard_app.modelView.authModelView.SignUpViewModel;
import com.flowerzapi.providers_dashboard_app.util.HelperClass;
import com.flowerzapi.providers_dashboard_app.util.Validation;
import com.flowerzapi.providers_dashboard_app.view.activities.MainActivity;

public class signup_fragment extends Fragment {

    // Data
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
        signInButton = view.findViewById(R.id.sign_in_bt_sign_up);
        signUpButton = view.findViewById(R.id.sign_up_bt_sign_up);
        emailET = view.findViewById(R.id.email_et_sign_up);
        passwordET = view.findViewById(R.id.password_et_sign_up);
        firstNameET = view.findViewById(R.id.first_name_et_sign_up);
        lastNameET = view.findViewById(R.id.last_name_et_sign_up);
        phoneNumberET = view.findViewById(R.id.phone_et_sign_up);
        storeNameET = view.findViewById(R.id.store_name_et_sign_up);

        // Set buttons actions
        signInButton.setOnClickListener(moveToSignIn);
        signUpButton.setOnClickListener(signUp);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.signup_fragment, container, false);
    }

    // On click listeners
    View.OnClickListener moveToSignIn = view -> HelperClass.navigateToFragment(view, -1);
    View.OnClickListener signUp = view -> {
      String email = emailET.getText().toString();
      String password = passwordET.getText().toString();
      String firstName = firstNameET.getText().toString();
      String lastName = lastNameET.getText().toString();
      String phoneNumber = phoneNumberET.getText().toString();
      String storeName = storeNameET.getText().toString();
      if(!validateInputData(email, password, firstName, lastName, phoneNumber, storeName)) return;
      viewModel.signUp(email, password, firstName, lastName, phoneNumber, storeName, getActivity(), isSuccessful -> {
          if(isSuccessful) HelperClass.navigateToActivity(getActivity(), MainActivity.class);
          else HelperClass.alertMessage(getActivity(), "Filed To SignUp");
          initialiseET();
      });
    };

    // Helpers
    private boolean validateInputData(String email, String password, String firstName, String lastName, String phoneNumber, String storeName) {

        if(!Validation.validateEmail(email)){
            HelperClass.alertMessage(getActivity(), "please insert a valid email address");
            return false;
        }

        if(!Validation.validateShortText(password)){
            HelperClass.alertMessage(getActivity(), "please insert a valid password");
            return false;
        }

        if(!Validation.validateShortText(firstName)){
            HelperClass.alertMessage(getActivity(), "full name - string length between 4 to 25");
            return false;
        }

        if(!Validation.validateShortText(lastName)){
            HelperClass.alertMessage(getActivity(), "last name - string length between 4 to 25");
            return false;
        }

        if(!Validation.validatePhoneNumber(phoneNumber)){
            HelperClass.alertMessage(getActivity(), "please insert a valid mobile phone number");
            return false;
        }

        if(!Validation.validateShortText(storeName)){
            HelperClass.alertMessage(getActivity(), "store name - string length between 4 to 25");
            return false;
        }

        return true;
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