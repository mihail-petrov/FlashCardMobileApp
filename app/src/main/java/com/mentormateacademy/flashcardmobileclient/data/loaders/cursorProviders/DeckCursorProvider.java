package com.mentormateacademy.flashcardmobileclient.data.loaders.cursorProviders;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;

import com.mentormateacademy.flashcardmobileclient.configurations.DatabaseConfiguration;
import com.mentormateacademy.flashcardmobileclient.data.loaders.interfaces.BaseCursorProvider;
import com.mentormateacademy.flashcardmobileclient.database.helper.DatabaseRepository;
import com.mentormateacademy.flashcardmobileclient.database.repositories.DeckRepository;

public final class DeckCursorProvider extends BaseCursorProvider {

    private DeckRepository deckRepository;
    private long userId;

    public DeckCursorProvider(Context context, long id) {
        super(context);

        // get cursor provider
        this.deckRepository = DatabaseRepository.getRepository(context).getDeckRepository();
        this.userId = id;
    }

    @Override
    public Cursor loadInBackground() {

        Bundle bundle = new Bundle();
        bundle.putString(DatabaseConfiguration.TABLE_DECKS_USER_ID, String.valueOf(this.userId));

        return this.deckRepository.readByCursor(bundle);
        //return this.deckRepository.readAllCursor();
    }
}
