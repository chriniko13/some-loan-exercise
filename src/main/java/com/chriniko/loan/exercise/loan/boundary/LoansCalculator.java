package com.chriniko.loan.exercise.loan.boundary;

import com.chriniko.loan.exercise.loan.control.LoansValidator;
import com.chriniko.loan.exercise.loan.entity.LoanCalculatorResult;
import com.chriniko.loan.exercise.loan.entity.LoanInfo;
import com.google.inject.Inject;
import lombok.extern.log4j.Log4j2;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Log4j2

public class LoansCalculator {

    private static final Comparator<LoanInfo> LOANS_TRAVERSE_STRATEGY = Comparator.comparing(LoanInfo::getRate);

    private static final int LOAN_LENGTH_IN_MONTHS = 36;

    private final LoansDatabase loansDatabase;
    private final LoansValidator loansValidator;


    @Inject
    public LoansCalculator(LoansDatabase loansDatabase, LoansValidator loansValidator) {
        this.loansDatabase = loansDatabase;
        this.loansValidator = loansValidator;

        log.debug("total available amount: {}", findTotalAvailableAmount());
    }

    public LoanCalculatorResult process(long loanAmount) {

        loansValidator.process(loanAmount);

        if (loanAmount > findTotalAvailableAmount()) {
            return null;
        }

        List<LoanInfo> loanInfos = getLoanInfos();

        double totalRepayment = getTotalRepayment(loanAmount, loanInfos);

        double rate = Math.abs((totalRepayment * 100 / loanAmount) - 100) / 100;

        long monthlyPay = loanAmount / LOAN_LENGTH_IN_MONTHS;
        double monthlyRepayment = monthlyPay * rate + monthlyPay;

        return new LoanCalculatorResult(loanAmount, rate, monthlyRepayment, totalRepayment);
    }

    private double getTotalRepayment(long loanAmount, List<LoanInfo> loanInfos) {
        long accumulator = 0L;
        double totalRepayment = 0.0D;

        for (int i = 0; i < loanInfos.size() && accumulator < loanAmount; i++) {

            LoanInfo loanInfo = loanInfos.get(i);

            long remaining = loanAmount - accumulator;

            long available = loanInfo.getAvailable();
            double rate = loanInfo.getRate();

            long diff = remaining - available;

            if (diff < 0) {
                long howManyToKeep = available - -diff;

                accumulator += howManyToKeep;
                totalRepayment += howManyToKeep + (howManyToKeep * rate);

            } else {
                accumulator += available;
                totalRepayment += available + (available * rate);
            }
        }
        return totalRepayment;
    }

    private List<LoanInfo> getLoanInfos() {
        List<LoanInfo> loanInfos = loansDatabase.findAll();
        loanInfos.sort(LOANS_TRAVERSE_STRATEGY);
        return loanInfos;
    }

    private long findTotalAvailableAmount() {
        return Optional
                .ofNullable(loansDatabase.findAll())
                .orElse(Collections.emptyList())
                .stream()
                .map(LoanInfo::getAvailable)
                .reduce(0L, Long::sum);
    }


}
