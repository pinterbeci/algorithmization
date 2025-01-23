package main.java.hu.pinterbeci.algorithms;

import java.util.Arrays;
import java.util.Objects;

public class MergeSort {

    public static int[] mergeSort(final int[] elementArray) {
        if (Objects.isNull(elementArray)) {
            return new int[0];
        }
        if (elementArray.length <= 1) {
            return elementArray;
        }
        if (elementArray.length == 2) {
            return sortArray(elementArray);
        }
        if (isSortedArray(elementArray)) {
            return elementArray;
        }
        final int halfSize = elementArray.length / 2;

        final int[] arrayHalf = Arrays.copyOfRange(elementArray, 0, halfSize);
        final int[] anotherHalf = Arrays.copyOfRange(elementArray, halfSize, elementArray.length);

        int[] ints = mergeSort(arrayHalf);
        int[] ints1 = mergeSort(anotherHalf);
        int[] ints2 = mergeArrays(ints, ints1);

        return mergeSort(ints2);
    }

    private static int[] sortArray(final int[] array) {
        final int largerElement = Math.max(array[0], array[1]);
        if (largerElement == array[0]) {
            int temp = array[0];
            array[0] = array[1];
            array[1] = temp;
        }
        return array;
    }


    private static boolean isSortedArray(final int[] elements) {
        for (int i = 0; i < elements.length - 1; i++) {
            if (elements[i] > elements[i + 1]) {
                return false;
            }
        }
        return true;
    }

    private static int[] mergeArrays(final int[] firstArray, final int[] secondArray) {
        final int[] mergedArray = new int[firstArray.length + secondArray.length];
        int index = 0;
        for (int element : firstArray) {
            mergedArray[index++] = element;
        }
        for (int element : secondArray) {
            mergedArray[index++] = element;
        }
        return mergedArray;
    }


}
