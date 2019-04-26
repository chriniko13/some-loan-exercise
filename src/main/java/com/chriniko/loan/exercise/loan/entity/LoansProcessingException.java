package com.chriniko.loan.exercise.loan.entity;

public class LoansProcessingException extends RuntimeException {

    public LoansProcessingException(String message, Throwable error) {
        super(message, error);
    }
}
