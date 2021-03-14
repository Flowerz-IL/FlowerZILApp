package com.flowerzapi.providers_dashboard_app.view.fragments.authFragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.flowerzapi.providers_dashboard_app.R;
import com.flowerzapi.providers_dashboard_app.util.HelperClass;
import com.flowerzapi.providers_dashboard_app.view.activities.MainActivity;


public class welcome_screen_fragment extends Fragment {

    // Data
    Button amAGuestButton, amAProviderButton;

    // Overrides
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialise view items
        amAGuestButton = view.findViewById(R.id.welcome_page_bt_guest);
        amAProviderButton = view.findViewById(R.id.welcome_page_bt_providers);

        // Set buttons actions
        amAProviderButton.setOnClickListener(moveToSignIn);
        amAGuestButton.setOnClickListener(moveToGuestActivity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.welcome_screen_fragment, container, false);
    }

    // On click listeners
    View.OnClickListener moveToSignIn = view -> HelperClass.navigateToFragment(view, R.id.welcome_to_sigin);
    View.OnClickListener moveToGuestActivity = view -> HelperClass.navigateToActivity(getActivity(), MainActivity.class);
}