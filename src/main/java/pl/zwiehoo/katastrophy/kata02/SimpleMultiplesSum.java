package pl.zwiehoo.katastrophy.kata02;

import java.util.stream.IntStream;

class SimpleMultiplesSum implements MultiplesSum {

    private static final int MAX_NUMBER = 1000;

    @Override
    public long sumMultiples() {
        return IntStream.range(1, MAX_NUMBER).filter(x -> x % 3 == 0).sum() +
                IntStream.range(1, MAX_NUMBER).filter(x -> x % 5 == 0).sum();
    }
}
