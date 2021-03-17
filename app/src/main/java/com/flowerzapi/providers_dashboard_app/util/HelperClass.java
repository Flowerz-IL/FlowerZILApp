package com.flowerzapi.providers_dashboard_app.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import androidx.navigation.Navigation;

public class HelperClass {

    public static void navigateToFragment(View view, int actionId) {
        if(actionId == -1) Navigation.findNavController(view).popBackStack();
        else Navigation.findNavController(view).navigate(actionId);
    }

    public static void  navigateToActivity(Context currentActivity, Class<?> activityClass){
        Intent intent = new Intent(currentActivity, activityClass);
        currentActivity.startActivity(intent);
    }

    public static void alertMessage(Activity currentActivity, String msg){
        Toast.makeText(currentActivity, msg , Toast.LENGTH_SHORT).show();
    }

}
