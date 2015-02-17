package com.mentormateacademy.flashcardmobileclient.data.loaders.cursorProviders;

import android.content.Context;
import android.database.Cursor;

import com.mentormateacademy.flashcardmobileclient.data.loaders.BaseCursorLoader;
import com.mentormateacademy.flashcardmobileclient.database.helper.DatabaseRepository;
import com.mentormateacademy.flashcardmobileclient.database.repositories.CardRepository;

public class CardCursorProvider extends BaseCursorLoader {

    private CardRepository cardRepository;

    private long deckId;

    public CardCursorProvider(Context context, long deck_id) {
        super(context);

        // get card cursor provider
        this.cardRepository = DatabaseRepository.getRepository(context).getCardRepository();

        this.deckId = deck_id;
    }

    @Override
    public Cursor loadInBackground() {
        return this.cardRepository.readAllBaseOnDeckId(this.deckId);
    }
}
