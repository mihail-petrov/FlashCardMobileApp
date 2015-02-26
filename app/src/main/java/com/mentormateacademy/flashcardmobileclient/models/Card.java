package com.mentormateacademy.flashcardmobileclient.models;

import android.os.Bundle;

import com.mentormateacademy.flashcardmobileclient.configurations.ExtraFlagsConfiguration;

public class Card {

    private long _id;
    private long deckId;

    private String frontTitle;
    private String frontContent;

    private String backTitle;
    private String backContent;

    private String extraTitle;
    private String extraContent;

    public long getId() {
        return _id;
    }

    public void setId(long _id) {
        this._id = _id;
    }

    public long getDeckId() {
        return deckId;
    }

    public void setDeckId(long deckId) {
        this.deckId = deckId;
    }

    public String getFrontTitle() {
        return frontTitle;
    }

    public void setFrontTitle(String frontTitle) {
        this.frontTitle = frontTitle;
    }

    public String getFrontContent() {
        return frontContent;
    }

    public void setFrontContent(String frontContent) {
        this.frontContent = frontContent;
    }

    public String getBackTitle() {
        return backTitle;
    }

    public void setBackTitle(String backTitle) {
        this.backTitle = backTitle;
    }

    public String getBackContent() {
        return backContent;
    }

    public void setBackContent(String backContent) {
        this.backContent = backContent;
    }

    public String getExtraTitle() {
        return extraTitle;
    }

    public void setExtraTitle(String extraTitle) {
        this.extraTitle = extraTitle;
    }

    public String getExtraContent() {
        return extraContent;
    }

    public void setExtraContent(String extraContent) {
        this.extraContent = extraContent;
    }


    public Bundle getFrontData(){
        Bundle frontBundle = new Bundle();
        frontBundle.putString(ExtraFlagsConfiguration.FRONT_CARD_TITLE, getFrontTitle());
        frontBundle.putString(ExtraFlagsConfiguration.FRONT_CARD_CONTENT, getFrontContent());

        return frontBundle;
    }

    public Bundle getBackData(){
        Bundle frontBundle = new Bundle();
        frontBundle.putString(ExtraFlagsConfiguration.BACK_CARD_TITLE, getBackTitle());
        frontBundle.putString(ExtraFlagsConfiguration.BACK_CARD_CONTENT, getBackContent());

        return frontBundle;
    }

    public Bundle getExtraData(){
        Bundle frontBundle = new Bundle();
        frontBundle.putString(ExtraFlagsConfiguration.EXTRA_CARD_TITLE, getExtraTitle());
        frontBundle.putString(ExtraFlagsConfiguration.EXTRA_CARD_CONTENT, getExtraContent());

        return frontBundle;
    }




    public void setCardContent(Bundle arguments){

        if(arguments.getString(ExtraFlagsConfiguration.FRONT_CARD_TITLE) != null) {
            setFrontTitle(arguments.getString(ExtraFlagsConfiguration.FRONT_CARD_TITLE));
        }

        if(arguments.getString(ExtraFlagsConfiguration.FRONT_CARD_CONTENT) != null) {
            setFrontContent(arguments.getString(ExtraFlagsConfiguration.FRONT_CARD_CONTENT));
        }

        if(arguments.getString(ExtraFlagsConfiguration.BACK_CARD_TITLE) != null) {
            setBackTitle(arguments.getString(ExtraFlagsConfiguration.BACK_CARD_TITLE));
        }

        if(arguments.getString(ExtraFlagsConfiguration.BACK_CARD_CONTENT) != null) {
            setBackContent(arguments.getString(ExtraFlagsConfiguration.BACK_CARD_CONTENT));
        }

        if(arguments.getString(ExtraFlagsConfiguration.EXTRA_CARD_TITLE) != null) {
            setExtraTitle(arguments.getString(ExtraFlagsConfiguration.EXTRA_CARD_TITLE));
        }

        if(arguments.getString(ExtraFlagsConfiguration.EXTRA_CARD_CONTENT) != null) {
            setExtraContent(arguments.getString(ExtraFlagsConfiguration.EXTRA_CARD_CONTENT));
        }
    }
}
