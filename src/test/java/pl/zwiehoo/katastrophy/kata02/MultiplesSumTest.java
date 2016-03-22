package pl.zwiehoo.katastrophy.kata02;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MultiplesSumTest {

    public static final int EXPECTED_SUM = 266333;

    private MultiplesSum createMultiplesSum() {
        return new SimpleMultiplesSum();
    }

    @Test
    public void shouldReturnCorrectSumForMultiplesOf3And5Below1000() {
        // Given
        MultiplesSum multiplesSum = createMultiplesSum();

        // When
        long sum = multiplesSum.sumMultiples();

        // Then
        assertEquals(EXPECTED_SUM, sum);
    }
}