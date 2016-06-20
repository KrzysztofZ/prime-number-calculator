package com.prime.calculator.division;

import com.prime.calculator.IPrimeChecker;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

@Service
public class DivisionPrimeCalculatorService implements IPrimeCalculatorService {

    @Override
    public List<Integer> calculate(int n) {
        if(n<=1) {
            throw new IllegalArgumentException("n needs to be positive and greater than one");
        }
        List<DivisionPrimeChecker> chunkCheckers = initilize(n);

        buildCheckers(n, chunkCheckers);
        CountDownLatch latch = runComputations(chunkCheckers);
        waitForCalculations(latch);

        return convertResults(n);
    }

    private List<DivisionPrimeChecker> initilize(int n) {
        List<DivisionPrimeChecker> chunkCheckers = new ArrayList<>();
        DivisionPrimeChecker.isPrime = new boolean[n +1];
        IntStream.rangeClosed(0, n).forEach(i -> DivisionPrimeChecker.isPrime[i] = true);
        return chunkCheckers;
    }

    private void buildCheckers(int n, List<DivisionPrimeChecker> chunkCheckers) {
        int segmentSize = (int) n/5;
        for(int start = 3; start <=n  ; start+=segmentSize + 1) {
            int length = Math.min(segmentSize, n - start);
            chunkCheckers.add(new DivisionPrimeChecker(start, start + length));
        }
    }

    private CountDownLatch runComputations(List<DivisionPrimeChecker> chunkCheckers) {
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
        IntStream.rangeClosed(2,n).filter(i -> DivisionPrimeChecker.isPrime[i]).forEach( i -> result.add(i));
        return result;
    }
}
