package com.mentormateacademy.flashcardmobileclient.models;

import android.os.Bundle;

import com.mentormateacademy.flashcardmobileclient.configurations.ExtraFlagsConfiguration;

public class Deck {

    private long _id;
    private String title;
    private int strategy_id;

    private int correctAnswers;
    private int wrongAnswers;

    private int cardSize;

    private long userId;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getId() {
        return _id;
    }

    public void setId(long _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStrategyId() {
        return strategy_id;
    }

    public void setStrategyId(int strategy_id) {
        this.strategy_id = strategy_id;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(int correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public int getWrongAnswers() {
        return wrongAnswers;
    }

    public void setWrongAnswers(int wrongAnswers) {
        this.wrongAnswers = wrongAnswers;
    }


    public int getCardSize() {
        return cardSize;
    }

    public void setCardSize(int cardSize) {
        this.cardSize = cardSize;
    }

    public Bundle getBundle() {

        Bundle deckBundle = new Bundle();

        deckBundle.putLong(ExtraFlagsConfiguration.DECK_ID, getId());
        deckBundle.putString(ExtraFlagsConfiguration.DECK_TITLE, getTitle());
        deckBundle.putInt(ExtraFlagsConfiguration.STRATEGY_ID, getStrategyId());
        deckBundle.putInt(ExtraFlagsConfiguration.CORRECT_ANSWERS, getCorrectAnswers());
        deckBundle.putInt(ExtraFlagsConfiguration.WRONG_ANSWERS, getWrongAnswers());
        deckBundle.putInt(ExtraFlagsConfiguration.DECK_CARD_COUNT, getCardSize());

        return deckBundle;
    }
}
