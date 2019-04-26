package com.chriniko.loan.exercise.loan.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.text.DecimalFormat;

@Getter
@RequiredArgsConstructor
@ToString
public class LoanCalculatorResult {

    private static DecimalFormat df1;
    private static DecimalFormat df2;

    static {
        df1 = new DecimalFormat("#.#");
        df1.setMinimumFractionDigits(1);

        df2 = new DecimalFormat("#.##");
        df2.setMinimumFractionDigits(2);
    }

    private final long loanAmount;
    private final double rate;
    private final double monthlyRepayment;
    private final double totalRepayment;


    public String getInfo() {

        return "\n" +
                "Requested amount: " + loanAmount + "\n" +
                "Rate: " + df1.format(rate * 100) + " %" + "\n" +
                "Monthly repayment: " + df2.format(monthlyRepayment) + "\n" +
                "Total repayment: " + df2.format(totalRepayment) + "\n";
    }
}
