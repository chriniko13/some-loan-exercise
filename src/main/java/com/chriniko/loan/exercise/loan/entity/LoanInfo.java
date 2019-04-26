package com.chriniko.loan.exercise.loan.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode(of = {"lender"})
@ToString
@Getter
public class LoanInfo {

    private final String lender;

    private final double rate;
    private final long available;

    public LoanInfo(String lender, String rate, String available) {
        this.lender = lender;
        this.rate = Double.parseDouble(rate);
        this.available = Long.parseLong(available);
    }


}
