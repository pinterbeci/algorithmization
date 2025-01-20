package main.java.hu.pinterbeci.algorithms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class StringPermutation {

    /**
     * Let's improve your code step by step to make it more memory-efficient, clean, and easier to understand. I'll also explain the reasoning behind
     * each change and point out the mistakes and inefficiencies in the original code.
     * Original Issues:
     * Redundant List Creation (usedParts):
     * You reset usedParts for each permutation but also create it inside the loop, which is unnecessary and wastes memory.
     * Inefficient Handling of Randomness:
     * Generating a new Random instance inside the loop is expensive and unnecessary.
     * Poor Duplicate Handling:
     * The logic to prevent duplicates relies on contains checks and re-randomization, which is inefficient and doesn't guarantee correct results.
     * Unnecessary Use of String[]:
     * Splitting the string into an array (baseStr.split("")) and using String[] is inefficient for character manipulation.
     * Inefficient String Concatenation:
     * Using StringBuilder repeatedly within nested loops is not the most efficient or cleanest way to construct strings here.
     * Non-Systematic Permutation Generation:
     * The approach relies on randomness to generate permutations, which doesn't ensure all unique permutations or proper memory usage.
     */
    public static List<String> findAllPermutationOfTheString(final String baseStr) {
        List<String> usedParts = new ArrayList<>();
        final String[] partOfTheBaseStr = baseStr.split("");
        final StringBuilder stringBuilder = new StringBuilder();
        final Set<String> permutations = new HashSet<>();

        Random random = new Random();

        for (int j = 0; j < partOfTheBaseStr.length; j++) {
            usedParts = new ArrayList<>();
            for (int i = 0; i < partOfTheBaseStr.length; i++) {
                String randomPartFromBaseStr = partOfTheBaseStr[random.nextInt(partOfTheBaseStr.length)];

                if (usedParts.contains(randomPartFromBaseStr)) {
                    random = new Random();
                    randomPartFromBaseStr = partOfTheBaseStr[random.nextInt(partOfTheBaseStr.length)];
                }
                stringBuilder.append(randomPartFromBaseStr);
                usedParts.add(randomPartFromBaseStr);
            }
            permutations.add(stringBuilder.toString());
            stringBuilder.setLength(0);
        }
        return permutations.stream().toList();
    }

    /**
     * Let's improve your code step by step to make it more memory-efficient, clean, and easier to understand. I'll also explain the reasoning behind
     * each change and point out the mistakes and inefficiencies in the original code.
     * Original Issues:
     * Redundant List Creation (usedParts):
     * You reset usedParts for each permutation but also create it inside the loop, which is unnecessary and wastes memory.
     * Inefficient Handling of Randomness:
     * Generating a new Random instance inside the loop is expensive and unnecessary.
     * Poor Duplicate Handling:
     * The logic to prevent duplicates relies on contains checks and re-randomization, which is inefficient and doesn't guarantee correct results.
     * Unnecessary Use of String[]:
     * Splitting the string into an array (baseStr.split("")) and using String[] is inefficient for character manipulation.
     * Inefficient String Concatenation:
     * Using StringBuilder repeatedly within nested loops is not the most efficient or cleanest way to construct strings here.
     * Non-Systematic Permutation Generation:
     * The approach relies on randomness to generate permutations, which doesn't ensure all unique permutations or proper memory usage.
     * Refactored Code:
     */


    public static List<String> findAllPermutations(final String baseStr) {
        if (baseStr == null || baseStr.isEmpty()) {
            return Collections.emptyList();
        }

        Set<String> permutations = new HashSet<>();
        char[] chars = baseStr.toCharArray();
        generatePermutations(chars, 0, permutations);

        return new ArrayList<>(permutations);
    }

    private static void generatePermutations(char[] chars, int currentIndex, Set<String> permutations) {
        if (currentIndex == chars.length - 1) {
            permutations.add(new String(chars));
            return;
        }

        Set<Character> used = new HashSet<>();
        for (int i = currentIndex; i < chars.length; i++) {
            if (!used.contains(chars[i])) {
                used.add(chars[i]);
                swap(chars, currentIndex, i);
                generatePermutations(chars, currentIndex + 1, permutations);
                swap(chars, currentIndex, i); // Backtrack
            }
        }
    }

    /**
     * Changes and Explanations:
     * Removed Redundant List (usedParts):
     *
     * In the original code, usedParts was created and reset unnecessarily. Instead, we use a Set<Character> (used) within each recursion to track which characters have been used at the current level. This avoids duplicate handling at each level without unnecessary list creation.
     * Recursive and Systematic Permutation Generation:
     *
     * The improved code uses a recursive approach to generate all permutations. This approach is systematic, ensures all possible unique permutations are covered, and doesn't rely on randomness.
     * Efficient Character Swapping:
     *
     * We use a simple swap method to swap characters in the array, avoiding additional memory overhead from repeatedly creating new strings or arrays.
     * Avoid New Random Instances:
     *
     * The improved method avoids creating Random instances entirely. Instead, it systematically explores all permutations, so randomness isn't needed.
     * Direct Use of char[] for Memory Efficiency:
     *
     * By converting the string to a char[] at the start, we avoid the overhead of repeatedly splitting the string or handling String[]. Arrays are more memory-efficient and faster for character manipulation.
     * Backtracking with Minimal Memory Usage:
     *
     * After swapping and recursive calls, we backtrack by swapping the characters back to their original positions. This ensures that we don't need extra space to maintain separate states.
     * Set for Unique Permutations:
     *
     * Using a Set<String> ensures we only store unique permutations without needing manual duplicate checks. This avoids duplicate memory storage and makes the code cleaner.
     * Mistakes in the Original Code:
     * Inefficient Random Handling:
     *
     * Randomness doesn't ensure all permutations are generated, leading to potential duplicates or missing permutations.
     * Inefficient Duplication Check:
     *
     * The usedParts.contains check is inefficient and could fail to prevent duplicates correctly in some cases.
     * Excessive Use of Data Structures:
     *
     * Creating a new ArrayList (usedParts) and Random instance inside loops wastes memory and CPU resources.
     * Poor Memory Management:
     *
     * Frequent creation of new objects (String[], StringBuilder, List<String>) in the loop increases garbage collection pressure and memory usage.
     * Final Benefits:
     * Memory Efficiency: By using a single char[] and systematic swaps, the memory usage is minimal.
     * Systematic Permutation Generation: This ensures all permutations are generated without randomness or manual duplication checks.
     * Clean Code: The recursive approach with clear separation of concerns (permutation generation, swapping, and backtracking) makes the code more maintainable and easier to understand.
     */

    private static void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }

    /**
     *
     * String randomPartFromBaseStr = partOfTheBaseStr[random.nextInt(partOfTheBaseStr.length)];
     *
     * if (usedParts.contains(randomPartFromBaseStr)) {
     *     random = new Random(); // Inefficient
     *     randomPartFromBaseStr = partOfTheBaseStr[random.nextInt(partOfTheBaseStr.length)];
     * }
     * stringBuilder.append(randomPartFromBaseStr);
     * usedParts.add(randomPartFromBaseStr);
     * Issues:
     * Inefficient Use of contains:
     *
     * The contains method on usedParts (a List<String>) checks each element sequentially, which is an O(n) operation for every iteration. This becomes costly, especially as the list grows in size with each loop iteration.
     * Ineffective Duplicate Handling:
     *
     * Even after detecting a duplicate and generating a new random character, there's no guarantee that the new character will not be another duplicate. This could lead to repeated attempts and inefficient loops.
     * Re-Randomization (random = new Random()):
     *
     * Creating a new Random instance inside the loop is unnecessary. The Random class can be reused and still provide sufficiently random numbers. Creating a new instance frequently increases overhead and doesn’t improve randomness significantly.
     * Inefficient Memory Usage:
     *
     * Each time a duplicate is detected, the loop essentially wastes effort and continues trying to generate unique characters, leading to inefficient memory and CPU usage.
     * How to Improve It:
     * To efficiently handle duplicates and avoid unnecessary randomness, we can use a more systematic approach:
     *
     * Use a Set<Character> for Tracking:
     *
     * Instead of checking duplicates using a list and contains, use a Set<Character>. Checking for existence in a set is O(1), which makes it much faster and more memory-efficient for duplicate detection.
     * Eliminate Random Selection for Permutation Generation:
     *
     * Rather than randomly selecting characters and handling duplicates through trial and error, generate permutations systematically using backtracking or iterative approaches. This avoids the need for randomness altogether and guarantees all unique permutations.
     * Reuse Random Instance (if randomness is still needed elsewhere):
     *
     * Create the Random instance once and reuse it throughout the process, rather than creating new instances repeatedly.
     * Improved Approach (Systematic Permutations):
     * As shown in the earlier refactored code, we can systematically generate permutations without randomness:
     */

    private static void generatePermutations(char[] chars, int currentIndex, Set<String> permutations) {
        if (currentIndex == chars.length - 1) {
            permutations.add(new String(chars));
            return;
        }

        Set<Character> used = new HashSet<>();
        for (int i = currentIndex; i < chars.length; i++) {
            if (!used.contains(chars[i])) {
                used.add(chars[i]);
                swap(chars, currentIndex, i);
                generatePermutations(chars, currentIndex + 1, permutations);
                swap(chars, currentIndex, i); // Backtrack
            }
        }
    }

    /**
     * Why This is Better:
     * Systematic and Guaranteed Unique:
     *
     * By systematically swapping and recursively generating permutations, all unique permutations are covered, and duplicates are naturally avoided without random retries.
     * Efficient Duplicate Handling:
     *
     * Using a Set<Character> at each recursion level ensures that no character is reused at the same level, which is efficient and clean.
     * Eliminates Redundant Checks:
     *
     * No need to repeatedly check for duplicates using contains, as the set naturally handles this efficiently.
     * Memory and CPU Efficiency:
     *
     * By avoiding random retries and using backtracking, the code avoids unnecessary memory usage and CPU cycles, making it both time and space-efficient.
     * @param args
     */


    public static void main(String[] args) {
        String baseStr = "tre";
        List<String> allPermutations = findAllPermutations(baseStr);
        System.out.println("All unique permutations: " + allPermutations);
    }
}


