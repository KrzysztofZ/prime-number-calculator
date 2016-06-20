package com.prime.calculator.naive;

import com.prime.calculator.IPrimeChecker;
import com.prime.calculator.division.IPrimeCalculatorService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

@Service
public class NaivePrimeCalculatorService implements IPrimeCalculatorService {

    @Override
    public List<Integer> calculate(int n) {
        validateInput(n);
        List<NaivePrimeChecker> chunkCheckers = initilize(n);

        buildCheckers(n, chunkCheckers);
        CountDownLatch latch = runComputations(chunkCheckers);
        waitForCalculations(latch);

        return convertResults(n);
    }

    private void validateInput(int n) {
        if(n<=1) {
            throw new IllegalArgumentException("n needs to be positive and greater than one.");
        }
        if(n > Integer.MAX_VALUE - 1) {
            throw new IllegalArgumentException("n cannot be greater than 2147483646");
        }
    }

    private List<NaivePrimeChecker> initilize(int n) {
        List<NaivePrimeChecker> chunkCheckers = new ArrayList<>();
        NaivePrimeChecker.isPrime = new boolean[n +1];
        IntStream.rangeClosed(0, n).forEach(i -> NaivePrimeChecker.isPrime[i] = true);
        return chunkCheckers;
    }

    private void buildCheckers(int n, List<NaivePrimeChecker> chunkCheckers) {
        int segmentSize = (int) n/5;
        for(int start = 3; start <=n; start+=segmentSize + 1) {
            int length = Math.min(segmentSize, n - start);
            chunkCheckers.add(new NaivePrimeChecker(start, start + length));
        }
    }

    private CountDownLatch runComputations(List<NaivePrimeChecker> chunkCheckers) {
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
        IntStream.rangeClosed(2,n).filter(i -> NaivePrimeChecker.isPrime[i]).forEach( i -> result.add(Integer.valueOf(i)));
        return result;
    }
}
