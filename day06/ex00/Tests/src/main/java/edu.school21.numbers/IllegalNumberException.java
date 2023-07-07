package edu.school21.numbers;

public class IllegalNumberException extends Exception {
    IllegalNumberException() {
        super("Number should be positive and greater than 1.");
    }
}
