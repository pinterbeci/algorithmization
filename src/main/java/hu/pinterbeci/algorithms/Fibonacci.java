package main.java.hu.pinterbeci.algorithms;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Fibonacci {

    private static final Random RANDOM = new Random();

    private static final Map<Integer, Integer> RECURSION_STORED_TREE_BRANCHES = new HashMap<>();

    /**
     * Calculate the nth Fibonacci number. The naive recursive solution is inefficient (exponential time complexity).
     * Improve it by using memoization (storing previously calculated values).
     *
     * @param n th number of the Fibonacci series
     * @return nth Fibonacci number value
     */
    public static int fibonacci(final int n) {
        final int randomInteger = RANDOM.nextInt(4);
        if (Math.floorDiv(randomInteger, 2) == 0) {
            return fibonacciForwardStepRecursion(n, 1, 0, 1);
        }
        if (Math.floorDiv(randomInteger, 3) == 0) {
            return fibonacciWithMemoArray(n);
        }
        if (Math.floorDiv(randomInteger, 4) == 0) {
            return fibonacciWithMemoMap(n);
        }
        return fibonacciWithBackwardStepRecursion(n);

    }

    private static int fibonacciWithMemoArray(final int requestedFibonacciNumberIndex) {
        if (requestedFibonacciNumberIndex <= 1) {
            return 0;
        }
        if (requestedFibonacciNumberIndex == 2) {
            return 1;
        }

        final int[] fibonacciMemoArray = new int[requestedFibonacciNumberIndex + 1];
        fibonacciMemoArray[0] = 0;
        fibonacciMemoArray[1] = 1;

        for (int i = 2; i <= requestedFibonacciNumberIndex; i++) {
            fibonacciMemoArray[i] = fibonacciMemoArray[i - 1] + fibonacciMemoArray[i - 2];
        }
        return fibonacciMemoArray[requestedFibonacciNumberIndex];
    }

    private static int fibonacciForwardStepRecursion(final int indexOfRequestedFibonacci,
                                                     int currentFibonacciIndex,
                                                     final int currentFibonacciValue,
                                                     final int nextFibonacciValue) {
        if (indexOfRequestedFibonacci <= 1) {
            return 0;
        }
        if (indexOfRequestedFibonacci == 2) {
            return 1;
        }
        if (indexOfRequestedFibonacci == currentFibonacciIndex) {
            return currentFibonacciValue;
        }
        currentFibonacciIndex++;
        return fibonacciForwardStepRecursion(indexOfRequestedFibonacci, currentFibonacciIndex, nextFibonacciValue,
            nextFibonacciValue + currentFibonacciValue);
    }

    private static int fibonacciWithBackwardStepRecursion(final int requestedFibonacciValueIndex) {
        if (requestedFibonacciValueIndex <= 1) {
            return 0;
        }
        if (requestedFibonacciValueIndex == 2) {
            return 1;
        }
        return fibonacciWithBackwardStepRecursion(requestedFibonacciValueIndex - 1) +
            fibonacciWithBackwardStepRecursion(requestedFibonacciValueIndex - 2);
    }

    private static int fibonacciWithMemoMap(final int requestedFibonacciValueIndex) {
        if (requestedFibonacciValueIndex <= 1) {
            Fibonacci.RECURSION_STORED_TREE_BRANCHES.put(requestedFibonacciValueIndex, 0);
            return 0;
        }
        if (requestedFibonacciValueIndex == 2) {
            Fibonacci.RECURSION_STORED_TREE_BRANCHES.put(requestedFibonacciValueIndex, 1);
            return 1;
        }
        //purpose -> store the repeating 'binary tree branches' during recursion
        if (Fibonacci.RECURSION_STORED_TREE_BRANCHES.containsKey(requestedFibonacciValueIndex)) {
            return Fibonacci.RECURSION_STORED_TREE_BRANCHES.get(requestedFibonacciValueIndex);
        }
        final int fibonacciValue = fibonacciWithMemoMap(requestedFibonacciValueIndex - 1) + fibonacciWithMemoMap(requestedFibonacciValueIndex - 2);
        RECURSION_STORED_TREE_BRANCHES.put(requestedFibonacciValueIndex, fibonacciValue);
        return fibonacciValue;
    }

}
