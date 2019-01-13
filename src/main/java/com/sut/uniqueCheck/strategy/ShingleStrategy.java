package com.sut.uniqueCheck.strategy;

import java.util.ArrayList;

public class ShingleStrategy {

    private static final String STOP_SYMBOLS[] = {".", ",", "!", "?", ":", ";", "-", "\\", "/", "*", "(", ")", "+", "@",
            "#", "$", "%", "^", "&", "=", "'", "\"", "[", "]", "{", "}", "|", "«", "»"};
    private static final String STOP_WORDS_RU[] = {"это", "как", "так", "и", "в", "над", "к", "до", "не", "на", "но", "за",
            "то", "с", "ли", "а", "во", "от", "со", "для", "о", "же", "ну", "вы",
            "бы", "что", "кто", "он", "она"};


    /**
     * Compare two string
     *
     * @param newText new text
     * @param count   count of words for selection in algorithm
     * @param oldText text from Data Base
     * @return the uniqueness text
     */
    public double getUniq(String newText, int count, String oldText) {
        ArrayList<Integer> textShingles1New = genShingle(newText, count);
        ArrayList<Integer> textShinglesOld = genShingle(oldText, count);
        if (textShingles1New == null || textShinglesOld == null) return 0.0;

        int textShingles1Number = textShingles1New.size();
        int textShingles2Number = textShinglesOld.size();

        double similarShinglesNumber = 0;

        for (int i = 0; i < textShingles1Number; i++) {
            for (int j = 0; j < textShingles2Number; j++) {
                if (textShingles1New.get(i).equals(textShinglesOld.get(j))) similarShinglesNumber++;
            }
        }

        double uniq = 100 - ((similarShinglesNumber / ((textShingles1Number + textShingles2Number) / 2.0)) * 100);
        if (uniq < 0) {
            uniq = 0;
        } else if (uniq > 100) {
            uniq = 100;
        }
        return Math.round(uniq);
    }

    /**
     * Transform text to Shingle and get their checksum
     *
     * @param strNew        string for creating Shingle
     * @param shingleLength length of choosing Shingle
     * @return ArrayList of Shingles
     */
    private ArrayList<Integer> genShingle(String strNew, int shingleLength) {
        ArrayList<Integer> shingles = new ArrayList<Integer>();
        String str = canonize(strNew.toLowerCase());
        String words[] = str.split(" ");
        int shinglesNumber = words.length - shingleLength;

        //Create all shingles
        for (int i = 0; i <= shinglesNumber; i++) {
            String shingle = "";

            //Create one shingle
            for (int j = 0; j < shingleLength; j++) {
                shingle = shingle + words[i + j] + " ";
            }

            shingles.add(shingle.hashCode());
        }

        return shingles;
    }

    /**
     * Not full canonize of string. Delete unneeded words and symbols don't using in comparing <br>
     *
     * @param str string for canonize
     * @return canonized string
     */
    private String canonize(String str) {
        for (String stopSymbol : STOP_SYMBOLS) {
            str = str.replace(stopSymbol, "");
        }

        for (String stopWord : STOP_WORDS_RU) {
            str = str.replace(" " + stopWord + " ", " ");
        }

        return str;
    }
}

