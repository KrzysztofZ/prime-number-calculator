package com.prime.calculator.division;

import com.prime.calculator.IPrimeChecker;
import org.junit.Test;

import java.util.stream.IntStream;

import static org.junit.Assert.assertArrayEquals;

public class DivisionPrimeCheckerTest {

    @Test
    public void testCheckPrimes() throws Exception {
        int n = 5;
        DivisionPrimeChecker.isPrime = new boolean[n +1];
        IntStream.rangeClosed(0, n).forEach(i -> DivisionPrimeChecker.isPrime[i] = true);

        IPrimeChecker primeChecker = new DivisionPrimeChecker(3,n);
        primeChecker.check();

        assertArrayEquals(new boolean[]{true,true,true,true,false,true}, DivisionPrimeChecker.isPrime);
    }
}
