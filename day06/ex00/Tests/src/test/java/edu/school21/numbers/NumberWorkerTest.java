package edu.school21.numbers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

public class NumberWorkerTest {

    private NumberWorker numberWorker;

    @BeforeEach
    public void NumberWorkerBeforeEach() { numberWorker = new NumberWorker(); }

    @ParameterizedTest
    @ValueSource(ints = {2, 3, 5, 7, 17, 19, 37, 131, 137, 199})
    void isPrimeForPrimes(int number) {
        Assertions.assertTrue(numberWorker.isPrime(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {4, 6, 18, 24, 33, 55, 86, 100})
    void isPrimeForNotPrimes(int number) {
        Assertions.assertFalse(numberWorker.isPrime(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 1})
    void isPrimeForIncorrectNumbers(int number) {
        Assertions.assertThrows(IllegalNumberException.class, ()->{
            numberWorker.isPrime(number);
        });
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv", delimiter = ',')
    void digitsSumTest(int source, int expected) {
        Assertions.assertEquals(expected, numberWorker.digitsSum(source));
    }
}
