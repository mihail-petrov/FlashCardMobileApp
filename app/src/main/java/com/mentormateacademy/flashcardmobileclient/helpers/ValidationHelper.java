package com.mentormateacademy.flashcardmobileclient.helpers;

public class ValidationHelper {

    public static boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
