package com.mentormateacademy.flashcardmobileclient.data.singletons;

public class DataHolder {

    public static DataHolder newInstance = null;

    private static long userId;

    private static int strategyId;
    private static String deckTitle;

    //
    private static long deckId;
    private static long cardId;

    //
    private static int correct;
    private static int wrong;

    //
    private static int cardSize;


    private DataHolder(){}

    public static DataHolder getData(){
        if(newInstance == null) {
            newInstance = new DataHolder();
        }
        return newInstance;
    }

    // Base Getters and Setters

    public long getUserId(){
        return userId;
    }

    public void setUserId(long userId){
        DataHolder.userId = userId;
    }

    public long getDeckId() {
        return deckId;
    }

    public void setDeckId(long deckId) {
        DataHolder.deckId = deckId;
    }

    public long getCardId() {
        return cardId;
    }

    public void setCardId(long cardId) {
        DataHolder.cardId = cardId;
    }

    public int getCorrect() {
        return correct;
    }

    public void setCorrect(int correct) {
        DataHolder.correct = correct;
    }

    public int getWrong() {
        return wrong;
    }

    public void setWrong(int wrong) {
        DataHolder.wrong = wrong;
    }

    public int getCardSize() {
        return cardSize;
    }

    public void setCardSize(int cardSize) {
        DataHolder.cardSize = cardSize;
    }

    public int getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(int strategyId) {
        DataHolder.strategyId = strategyId;
    }

    public String getDeckTitle() {
        return deckTitle;
    }

    public void setDeckTitle(String deckTitle) {
        DataHolder.deckTitle = deckTitle;
    }

    // Base Composite methods
    public boolean hasStatistic(){
        return (correct != 0 || wrong != 0);
    }

    public boolean hasCards(){
        return (cardSize > 0);
    }
}
