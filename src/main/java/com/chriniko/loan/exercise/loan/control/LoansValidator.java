package com.chriniko.loan.exercise.loan.control;

import com.chriniko.loan.exercise.loan.entity.LoansValidationException;

public class LoansValidator {

    private static final int VALID_START_LOAN_AMOUNT = 1000;
    private static final int VALID_END_LOAN_AMOUNT = 15000;

    private static final int VALID_STEP_LOAN_AMOUNT = 100;

    public void process(long loanAmount) {

        if (loanAmount < VALID_START_LOAN_AMOUNT
                || loanAmount > VALID_END_LOAN_AMOUNT
                || loanAmount % VALID_STEP_LOAN_AMOUNT != 0) {
            throw new LoansValidationException("not valid loan amount");
        }

    }

}
