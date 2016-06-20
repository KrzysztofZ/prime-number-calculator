package com.prime.calculator.naive;

import com.prime.calculator.IPrimeChecker;
import com.prime.calculator.division.DivisionPrimeChecker;
import org.junit.Test;

import java.util.stream.IntStream;

import static org.junit.Assert.assertArrayEquals;

public class NaivePrimeCheckerTest {

    @Test
    public void testCheckPrimes() throws Exception {
        int n = 5;
        NaivePrimeChecker.isPrime = new boolean[n +1];
        IntStream.rangeClosed(0, n).forEach(i -> NaivePrimeChecker.isPrime[i] = true);

        IPrimeChecker primeChecker = new NaivePrimeChecker(3,n);
        primeChecker.check();

        assertArrayEquals(new boolean[]{true,true,true,true,false,true}, NaivePrimeChecker.isPrime);
    }
}
