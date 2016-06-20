package com.prime.calculator.division;

import com.prime.calculator.IPrimeChecker;

public class DivisionPrimeChecker implements IPrimeChecker {
    public static boolean[] isPrime;
    private int segmentStart;
    private int segmentEnd;

    public DivisionPrimeChecker(int segmentStart, int segmentEnd) {
        this.segmentStart = segmentStart;
        this.segmentEnd = segmentEnd;
    }

    @Override
    public void check() {
        for (int factor = segmentStart; factor <= segmentEnd; factor++) {
            int sqrtN = (int)Math.sqrt(factor);
            for (int j = 2; j <= sqrtN; j++) {
                if(factor % j == 0) {
                    isPrime[factor] = false;
                    break;
                }
            }
        }
    }
}
