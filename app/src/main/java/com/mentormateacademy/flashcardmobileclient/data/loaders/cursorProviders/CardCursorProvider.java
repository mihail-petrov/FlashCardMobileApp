package com.mentormateacademy.flashcardmobileclient.data.loaders.cursorProviders;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;

import com.mentormateacademy.flashcardmobileclient.data.loaders.BaseCursorProvider;
import com.mentormateacademy.flashcardmobileclient.database.helper.DatabaseRepository;
import com.mentormateacademy.flashcardmobileclient.database.repositories.CardRepository;

public class CardCursorProvider extends BaseCursorProvider {

    private CardRepository cardRepository;
    private long deckId;

    public CardCursorProvider(Context context, long deckId) {
        super(context);

        this.cardRepository = DatabaseRepository.getRepository(context).getCardRepository();
        this.deckId         = deckId;
    }

    @Override
    public Cursor loadInBackground() {

        Log.d("DATA_DATA_DATA", String.valueOf(this.deckId));

        Bundle bundle = new Bundle();
        bundle.putString("deck_id", String.valueOf(this.deckId));
        return this.cardRepository.readByCursor(bundle);

//        return  this.cardRepository.readAllCursor();
    }
}
