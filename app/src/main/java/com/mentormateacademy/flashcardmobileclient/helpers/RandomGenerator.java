package com.mentormateacademy.flashcardmobileclient.helpers;

import java.util.Random;

public class RandomGenerator {

    private static final String alphabetCollection = "abcdefghijklmnopqrstuvwxyz";
    private Random randomGenerator;

    public RandomGenerator() {
        this.randomGenerator = new Random();
    }

    public String generateString(int maxLength, boolean addSpace) {
        return this.generateString(0, maxLength, addSpace);
    }

    /**
     * @param minLength
     * @param maxLength
     * @param addSpace
     * @return
     */
    public String generateString(int minLength, int maxLength, boolean addSpace) {

        StringBuilder stringAccumulator = new StringBuilder();

        for (int i = minLength; i < maxLength; i++) {

            int position = this.generateInteger(alphabetCollection.length());
            char element = alphabetCollection.charAt(position);

            if (addSpace) {
                if (i % 5 == 0) stringAccumulator.append(" ");
            }
            stringAccumulator.append(element);
        }

        return stringAccumulator.toString();
    }

    /**
     * @param maxLength
     * @return
     */
    public int generateInteger(int maxLength) {
        return this.randomGenerator.nextInt(maxLength);
    }

    /**
     * @param minLength
     * @param maxLength
     * @return
     */
    public int generateInteger(int minLength, int maxLength) {

        int randomNumberElement = this.randomGenerator.nextInt(maxLength);
        return (minLength + randomNumberElement);
    }
}
