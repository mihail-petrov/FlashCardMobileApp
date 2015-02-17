package com.mentormateacademy.flashcardmobileclient.data.loaders.cursorProviders;

import android.content.Context;
import android.database.Cursor;

import com.mentormateacademy.flashcardmobileclient.data.loaders.BaseCursorProvider;
import com.mentormateacademy.flashcardmobileclient.database.helper.DatabaseRepository;
import com.mentormateacademy.flashcardmobileclient.database.repositories.DeckRepository;

public final class DeckCursorProvider extends BaseCursorProvider {

    private DeckRepository deckRepository;

    public DeckCursorProvider(Context context) {
        super(context);

        // get cursor provider
        this.deckRepository = DatabaseRepository.getRepository(context).getDeckRepository();
    }

    @Override
    public Cursor loadInBackground() {
        return this.deckRepository.readAllCursor();
    }
}
