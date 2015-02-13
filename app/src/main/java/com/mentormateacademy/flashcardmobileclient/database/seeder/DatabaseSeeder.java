package com.mentormateacademy.flashcardmobileclient.database.seeder;

import android.app.Application;
import android.content.Context;

import com.mentormateacademy.flashcardmobileclient.database.helper.DatabaseRepository;


public class DatabaseSeeder extends Application {

    private static final boolean SEED_DATABASE = false;
    private static final boolean DELETE_TABLES_BEFORE_SEED = false;
    private DatabaseRepository databaseRepository;
    private Context context;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public void seedDatabase() {

    }
}
