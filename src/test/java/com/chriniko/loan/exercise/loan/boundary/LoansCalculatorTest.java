package com.chriniko.loan.exercise.loan.boundary;

import com.chriniko.loan.exercise.loan.control.LoansValidator;
import com.chriniko.loan.exercise.loan.entity.LoanCalculatorResult;
import com.chriniko.loan.exercise.loan.entity.LoanInfo;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class LoansCalculatorTest {


    @Test
    public void process_normal_case() {

        // given
        long loanAmount = 1500;

        LoansDatabase loansDatabase = Mockito.mock(LoansDatabase.class);
        LoansValidator loansValidator = Mockito.mock(LoansValidator.class);

        List<LoanInfo> loanInfos = Collections.singletonList(new LoanInfo("Nick", "0.069", "20000"));
        Mockito.when(loansDatabase.findAll()).thenReturn(loanInfos);

        LoansCalculator loansCalculator = new LoansCalculator(loansDatabase, loansValidator);

        // when
        LoanCalculatorResult result = loansCalculator.process(loanAmount);


        // then
        Mockito.verify(loansValidator).process(loanAmount);
        Mockito.verify(loansDatabase, Mockito.times(3)).findAll();

        assertNotNull(result);

        Assert.assertEquals(1500, result.getLoanAmount());
        Assert.assertEquals(0.06900000000000006, result.getRate(), 0);
        Assert.assertEquals(43.829, result.getMonthlyRepayment(), 0);
        Assert.assertEquals(1603.5, result.getTotalRepayment(), 0);

    }

    @Test
    public void process_no_offers_empty_case() {

        // given
        long loanAmount = 1500;

        LoansDatabase loansDatabase = Mockito.mock(LoansDatabase.class);
        LoansValidator loansValidator = Mockito.mock(LoansValidator.class);

        Mockito.when(loansDatabase.findAll()).thenReturn(Collections.emptyList());

        LoansCalculator loansCalculator = new LoansCalculator(loansDatabase, loansValidator);

        // when
        LoanCalculatorResult result = loansCalculator.process(loanAmount);


        // then
        Mockito.verify(loansValidator).process(loanAmount);

        assertNull(result);
    }

    @Test
    public void process_no_offers_null_case() {

        // given
        long loanAmount = 1500;

        LoansDatabase loansDatabase = Mockito.mock(LoansDatabase.class);
        LoansValidator loansValidator = Mockito.mock(LoansValidator.class);

        Mockito.when(loansDatabase.findAll()).thenReturn(null);

        LoansCalculator loansCalculator = new LoansCalculator(loansDatabase, loansValidator);

        // when
        LoanCalculatorResult result = loansCalculator.process(loanAmount);


        // then
        Mockito.verify(loansValidator).process(loanAmount);

        assertNull(result);
    }
}