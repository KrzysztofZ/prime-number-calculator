package com.prime.calculator.sieve;

import com.prime.calculator.IPrimeChecker;

public class SievePrimeChecker implements IPrimeChecker {
    public static boolean[] isPrime;
    private int segmentStart;
    private int segmentEnd;
    private int n;

    public SievePrimeChecker(int segmentStart, int segmentEnd, int n) {
        this.segmentStart = segmentStart;
        this.segmentEnd = segmentEnd;
        this.n = n;
    }

    @Override
    public void check() {
        for (int factor = segmentStart; factor <= segmentEnd; factor++) {
            if (isPrime[factor]) {
                for (int j = factor*factor; j <= n; j=j+factor) {
                    isPrime[j] = false;
                }
            }
        }
    }
}
