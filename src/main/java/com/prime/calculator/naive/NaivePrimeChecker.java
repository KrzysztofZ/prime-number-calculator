package com.prime.calculator.naive;

import com.prime.calculator.IPrimeChecker;

public class NaivePrimeChecker implements IPrimeChecker {
    public static boolean[] isPrime;
    private int segmentStart;
    private int segmentEnd;

    public NaivePrimeChecker(int segmentStart, int segmentEnd) {
        this.segmentStart = segmentStart;
        this.segmentEnd = segmentEnd;
    }

    @Override
    public void check() {
        for (int factor = segmentStart; factor <= segmentEnd; factor++) {
            for (int j = 2; j < factor; j++) {
                if(factor % j == 0) {
                    isPrime[factor] = false;
                    break;
                }
            }
        }
    }
}
