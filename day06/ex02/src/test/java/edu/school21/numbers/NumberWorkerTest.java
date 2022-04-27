package edu.school21.numbers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

public class NumberWorkerTest {
    private static NumberWorker numberWorker;

    @BeforeAll
    static void beforeAll() {
        numberWorker = new NumberWorker();
    }

    @ParameterizedTest
    @ValueSource(ints = {7, 113, 233})
    void isPrimeShouldReturnTrueForPrimeNumbers(int number) {
        Assertions.assertTrue(numberWorker.isPrime(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {10, 46, 222})
    void isPrimeShouldReturnFalseForNotPrimeNumbers(int number) {
        Assertions.assertFalse(numberWorker.isPrime(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {-3, 0, 1})
    void isPrimeShouldThrowIllegalNumberExceptionForNumbersLessThanTwo(int number) {
        Assertions.assertThrowsExactly(IllegalNumberException.class, () -> numberWorker.isPrime(number));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv", delimiter = ',')
    void digitsSumTest(int i, int j) {
        Assertions.assertEquals(numberWorker.digitsSum(i), j);
    }
}
