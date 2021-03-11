package com.flowerzapi.providers_dashboard_app.util;

import android.util.Patterns;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

    public static boolean validateEmail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static boolean validatePhoneNumber(String phoneNumber) {
        return Patterns.PHONE.matcher(phoneNumber).matches();
    }

    public static boolean validateShortText(String text) {
        int len = text.length();
        return len >= 4 && len < 25;
    }

    public static boolean validateLongText(String text) {
        int len = text.length();
        return len >= 4 && len < 100;
    }
}
