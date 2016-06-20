package com.prime.calculator.sieve;

import com.prime.calculator.IPrimeChecker;
import com.prime.calculator.division.IPrimeCalculatorService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

public class SievePrimeCalculatorService implements IPrimeCalculatorService {
    @Override
    public List<Integer> calculate(int n) {
        if(n<=0) {
            throw new IllegalArgumentException("n needs to be positive");
        }
        List<SievePrimeChecker> chunkCheckers = initilize(n);

        buildCheckers(n, chunkCheckers);
        CountDownLatch latch = runComputations(chunkCheckers);
        waitForCalculations(latch);

        return convertResults(n);
    }

    private List<SievePrimeChecker> initilize(int n) {
        List<SievePrimeChecker> chunkCheckers = new ArrayList<>();
        SievePrimeChecker.isPrime = new boolean[n +1];
        IntStream.rangeClosed(0, n).forEach(i -> SievePrimeChecker.isPrime[i] = true);
        return chunkCheckers;
    }

    private void buildCheckers(int n, List<SievePrimeChecker> chunkCheckers) {
        int sqrtN = (int)Math.sqrt(n);
        int segmentSize = sqrtN/5;
        for(int start = 2; start <=sqrtN  ; start+=segmentSize + 1) {
            int length = Math.min(segmentSize, sqrtN - start);
            chunkCheckers.add(new SievePrimeChecker(start, start + length,n));
        }
    }

    private CountDownLatch runComputations(List<SievePrimeChecker> chunkCheckers) {
        CountDownLatch latch = new CountDownLatch(chunkCheckers.size());
        for (IPrimeChecker primeChecker : chunkCheckers) {
            CompletableFuture.runAsync(() -> primeChecker.check())
                    .thenAccept(result -> {
                        latch.countDown();
                    });
        }
        return latch;
    }

    private void waitForCalculations(CountDownLatch latch) {
        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException("Interrupted during calculations", e);
        }
    }

    private List<Integer> convertResults(int n) {
        List<Integer> result = new ArrayList<>();
        IntStream.rangeClosed(2,n).filter(i -> SievePrimeChecker.isPrime[i]).forEach( i -> result.add(Integer.valueOf(i)));
        return result;
    }
}
