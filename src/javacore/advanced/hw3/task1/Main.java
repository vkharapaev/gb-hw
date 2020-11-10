package javacore.advanced.hw3.task1;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        String[] wordList = {"linux", "began", "in", "as", "personal", "project", "by", "finnish", "student",
                "linus", "torvalds", "to", "create", "a",
                "linus", "torvalds", "to", "create", "a",};

        Map<String, Integer> wordFreqMap = new HashMap<>();
        for (String word : wordList) {
            wordFreqMap.compute(word, (k, v) -> v != null ? ++v : 1);
        }

        System.out.println("Unique words:");
        for (Map.Entry<String, Integer> entry : wordFreqMap.entrySet()) {
            if (entry.getValue() == 1) {
                System.out.printf(" - %s\n", entry.getKey());
            }
        }

        System.out.println("Word frequency:");
        for (Map.Entry<String, Integer> entry : wordFreqMap.entrySet()) {
            System.out.printf(" - %d: %s \n", entry.getValue(), entry.getKey());
        }
    }

}
