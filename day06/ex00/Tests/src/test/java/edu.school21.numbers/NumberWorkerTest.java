package edu.school21.numbers;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;



class NumberWorkerTest {
    private final NumberWorker instance = new NumberWorker();

    @DisplayName("Checking non-prime numbers.")
    @ParameterizedTest
    @ValueSource(ints = {121, 4, 14, 6, 8, 121, 75578, 349435, 7221})
    void isPrimeForNotPrimes(int nonPrime) {
        try {
            Assertions.assertFalse(instance.isPrime(nonPrime));

        } catch (IllegalNumberException e) {
            System.out.println("Only numbers greater than 1 excepted.");
        }
    }

    @DisplayName("Checking prime numbers.")
    @ParameterizedTest
    @ValueSource(ints = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 11, 5393, 7919, 7499})
    void isPrimeForPrimes(int prime) {
        try {
            Assertions.assertTrue(instance.isPrime(prime));

        } catch (IllegalNumberException e) {
            System.out.println("Only numbers greater than 1 excepted.");
        }
    }

    @DisplayName("Checking error numbers, exception should be thrown.")
    @ParameterizedTest
    @ValueSource(ints = {0, -1, 1, -5, -1000000, -34233233})
    void isPrimeForIncorrectNumbers(int errNumber) {
        Assertions.assertThrows(IllegalNumberException.class, () -> instance.isPrime(errNumber));
    }

    @DisplayName("Checking correct sum of digits.")
    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv")
    void digitsSumCorrect(int number, int expected) {
        Assertions.assertEquals(expected, instance.digitsSum(number));
    }


    @DisplayName("Checking error sum of digits.")
    @ParameterizedTest
    @CsvFileSource(resources = "/data_fails.csv")
    void digitsSumError(int number, int expected) {
        Assertions.assertNotEquals(expected, instance.digitsSum(number));
    }
}
