package com.flowerzapi.providers_dashboard_app.view.fragments.providersFragments.providersDetails;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.flowerzapi.providers_dashboard_app.R;
import com.flowerzapi.providers_dashboard_app.util.HelperClass;
import com.flowerzapi.providers_dashboard_app.view.activities.AuthActivity;

public class providers_details_fragment extends Fragment {

    private ProvidersDetailsViewModel viewModel;
    ProgressBar loader;
    Button editDetailsBT, changePasswordBT, providersItemsBT, deleteProviderBT, logOutBT;
    TextView providerStoreTV, providerFullName;

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialise viewModel
        viewModel = new ViewModelProvider(this).get(ProvidersDetailsViewModel.class);

        // Initialise view items
        loader = view.findViewById(R.id.loader_user_details);
        deleteProviderBT = view.findViewById(R.id.delete_account_bt);
        logOutBT = view.findViewById(R.id.log_out_bt);
        providerStoreTV = view.findViewById(R.id.provider_store_name_tv);
        providerFullName = view.findViewById(R.id.provider_full_name_tv);
        editDetailsBT = view.findViewById(R.id.edit_details_bt);
        changePasswordBT = view.findViewById(R.id.change_password_bt);
        providersItemsBT = view.findViewById(R.id.providers_items_bt);

        // Observers
        viewModel.getUser().observe(getViewLifecycleOwner(), user -> {
            providerFullName.setText(user.getFirstName() + " " +  user.getLastName());
            providerStoreTV.setText(user.getStoreName());
            loader.setVisibility(View.GONE);
        });

        // Set buttons actions
        editDetailsBT.setOnClickListener(moveToEditItems);
        providersItemsBT.setOnClickListener(moveToProviderItems);
        changePasswordBT.setOnClickListener(moveToChangePassword);
        logOutBT.setOnClickListener(signOut);

    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_providers_details, container, false);
    }

    // On click listeners
    View.OnClickListener signOut = view -> {
        viewModel.signOut();
        HelperClass.navigateToActivity(getActivity(), AuthActivity.class);
    };
    View.OnClickListener moveToProviderItems = view -> HelperClass.navigateToFragment(view, R.id.user_details_to_provider_items);
    View.OnClickListener moveToEditItems = view -> HelperClass.navigateToFragment(view, R.id.user_details_to_edit_details);
    View.OnClickListener moveToChangePassword = view -> HelperClass.navigateToFragment(view, R.id.edit_details_to_change_pass);
}