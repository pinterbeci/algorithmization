package main.java.hu.pinterbeci.algorithms;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class StringPermutation {

    /**
     * Permutations of a String
     * <li>
     *     <ul>Problem: Given a string, find all its permutations.</ul>
     *     <ul>Challenge: Use recursion to generate permutations. Ensure that the solution does not have duplicates.</ul>
     * </li>
     *
     * @param str Given string
     */
    public static Set<String> findAllPermutations(final String str) {
        if (Objects.isNull(str) || str.isEmpty()) {
            System.out.println("The given string is not corresponding!");
            return Set.of();
        }
        return retrievePermutations(str);
    }

    private static Set<String> retrievePermutations(final String str) {
        final char[] baseStrCharacterArray = eliminateDuplicateCharacters(str);
        return retrievePermutationsUsingCharSwappingMethod(baseStrCharacterArray);
    }

    private static Set<String> retrievePermutationsUsingCharSwappingMethod(final char[] charArrayFromStr) {
        final Set<String> permutations = new HashSet<>();
        for (int i = 0; i < charArrayFromStr.length; i++) {
            final char[] swappedCharArray = retrieveSwappedCharArray(charArrayFromStr, 0);
            permutations.add(new String(swappedCharArray));
        }
        return permutations;
    }

    private static char[] retrieveSwappedCharArray(char[] charArray, int index) {
        if (index + 1 == charArray.length) {
            return charArray;
        }
        swap(charArray, index);
        return retrieveSwappedCharArray(charArray, index + 1);
    }

    private static void swap(char[] charArray, int index) {
        final char temp = charArray[index];
        charArray[index] = charArray[index + 1];
        charArray[index + 1] = temp;
    }

    private static char[] eliminateDuplicateCharacters(final String str) {
        final Set<Character> characterSet = new HashSet<>();
        for (char c : str.toCharArray()) {
            characterSet.add(c);
        }
        final char[] strCharArrayWithoutDuplication = new char[characterSet.size()];
        int index = 0;
        for (char character : characterSet) {
            strCharArrayWithoutDuplication[index++] = character;
        }
        return strCharArrayWithoutDuplication;
    }
}


