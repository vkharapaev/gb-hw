package javacore.advanced.hw3.task1;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        String[] wordList = {"linux", "began", "in", "as", "personal", "project", "by", "finnish", "student",
                "linus", "torvalds", "to", "create", "a",
                "linus", "torvalds", "to", "create", "a",};

        Map<String, Integer> wordFreqMap = new HashMap<>();
        for (String word : wordList) {
            Integer count = wordFreqMap.getOrDefault(word, 0);
            wordFreqMap.put(word, ++count);
        }

        System.out.println("Unique words:");
        wordFreqMap.forEach((key, value) -> {
            if (value == 1) {
                System.out.printf(" - %s\n", key);
            }
        });

        System.out.println("Word frequency:");
        wordFreqMap.forEach((key, value) -> System.out.printf(" - %d: %s \n", value, key));
    }

}
