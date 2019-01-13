package com.sut.uniqueCheck.service;

public interface DataService {
    /**
     * Calculate uniqueness text and add this to Data Base
     *
     * @param text  new text
     * @param count count of words for selection
     */
    void add(String text, int count);
}
