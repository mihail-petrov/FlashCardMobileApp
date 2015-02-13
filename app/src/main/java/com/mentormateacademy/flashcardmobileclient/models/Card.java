package com.mentormateacademy.flashcardmobileclient.models;

public class Card {

    private long _id;
    private String title;

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
