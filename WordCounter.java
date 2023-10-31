package com.project;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

    public class WordCounter {

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Word Counter");

            String text = getTextFromUser(scanner);
            String[] words = splitTextIntoWords(text);

            int totalWordCount = countWords(words);

            int uniqueWordCount = countUniqueWords(words);

            System.out.println("Total word count: " + totalWordCount);
            System.out.println("Unique word count: " + uniqueWordCount);

            Map<String, Integer> wordFrequency = countWordFrequency(words);
            System.out.println("Word frequency:");
            for (Map.Entry<String, Integer> entry : wordFrequency.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue() + " times");
            }
        }

        private static String getTextFromUser(Scanner scanner) {
            System.out.println("Enter a text or provide a file path:");
            String input = scanner.nextLine();

            if (input.startsWith("file:")) {
                String filePath = input.substring(5);
                return readTextFromFile(filePath);
            } else {
                return input;
            }
        }

        private static String readTextFromFile(String filePath) {
            StringBuilder text = new StringBuilder();
            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = br.readLine()) != null) {
                    text.append(line).append(" ");
                }
            } catch (IOException e) {
                System.err.println("Error reading the file: " + e.getMessage());
            }
            return text.toString();
        }

        private static String[] splitTextIntoWords(String text) {
            return text.split("[\\s\\p{Punct}]+");
        }

        private static int countWords(String[] words) {
            return words.length;
        }

        private static int countUniqueWords(String[] words) {
            Map<String, Integer> wordCountMap = new HashMap<>();
            for (String word : words) {
                wordCountMap.put(word, wordCountMap.getOrDefault(word, 0) + 1);
            }
            return wordCountMap.size();
        }

        private static Map<String, Integer> countWordFrequency(String[] words) {
            Map<String, Integer> wordFrequency = new HashMap<>();
            for (String word : words) {
                wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
            }
            return wordFrequency;
        }
    }
