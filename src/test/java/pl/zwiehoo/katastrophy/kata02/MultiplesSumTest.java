package pl.zwiehoo.katastrophy.kata02;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class MultiplesSumTest {

    public static final int EXPECTED_SUM = 266333;

    private MultiplesSum createMultiplesSum() {
        // Return your implementation of kata solution
        return Mockito.mock(MultiplesSum.class);
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