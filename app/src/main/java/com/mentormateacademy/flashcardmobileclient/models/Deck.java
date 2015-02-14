package com.mentormateacademy.flashcardmobileclient.models;

public class Deck {

    private long _id;
    private String title;

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
}
