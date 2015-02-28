package com.mentormateacademy.flashcardmobileclient.helpers;

import android.os.Bundle;

import java.util.Iterator;
import java.util.Set;


public class BundleHelper {

    public static Bundle compressBundle(Bundle ...element){

        Bundle newBundle = new Bundle();

        for(Bundle arguments : element) {

            Set<String> bundleKeys  = arguments.keySet();
            Iterator<String> iterator = bundleKeys.iterator();

            while (iterator.hasNext()) {
                String getKey = iterator.next();
                String getValue = arguments.getString(getKey);

                newBundle.putString(getKey, getValue);
            }
        }

        return newBundle;
    }
}
