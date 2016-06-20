package com.prime.calculator.sieve;

import com.prime.calculator.IPrimeChecker;
import org.junit.Test;

import java.util.stream.IntStream;

import static org.junit.Assert.assertArrayEquals;

public class SievePrimeCheckerTest {

    @Test
    public void testCheckPrimes() throws Exception {
        int n = 6;
        SievePrimeChecker.isPrime = new boolean[n +1];
        IntStream.rangeClosed(0, n).forEach(i -> SievePrimeChecker.isPrime[i] = true);

        IPrimeChecker primeChecker = new SievePrimeChecker(2,2,n);
        primeChecker.check();

        assertArrayEquals(new boolean[]{true,true,true,true,false,true,false}, SievePrimeChecker.isPrime);
    }
}
