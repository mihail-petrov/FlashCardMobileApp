package com.mentormateacademy.flashcardmobileclient.database.helper;

public class ValidationHelper {

    public static boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
