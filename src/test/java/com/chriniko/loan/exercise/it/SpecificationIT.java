package com.chriniko.loan.exercise.it;

import com.chriniko.loan.exercise.loan.boundary.LoansCalculator;
import com.chriniko.loan.exercise.loan.entity.LoanCalculatorResult;
import com.chriniko.loan.exercise.loan.entity.LoansValidationException;
import com.google.inject.Inject;
import org.junit.Assert;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SpecificationIT extends SpecificationBase {


    @Inject
    LoansCalculator loansCalculator;

    @Test
    public void normal_case_1() {

        // when
        LoanCalculatorResult result = loansCalculator.process(1000);

        // then
        Assert.assertNotNull(result);

        Assert.assertEquals(1000, result.getLoanAmount());
        Assert.assertEquals(0.07004000000000005, result.getRate(), 0);
        Assert.assertEquals(28.891080000000002, result.getMonthlyRepayment(), 0);
        Assert.assertEquals(1070.04, result.getTotalRepayment(), 0);

        String resultInfo = result.getInfo();

        Pattern p = Pattern.compile("^[+-]?(?:\\d+\\.?\\d*|\\d*\\.\\d+)$");
        Matcher matcher = p.matcher(resultInfo);

        String[] expectedValues = {"1000", "7.0", "28.89", "1070.04"};
        int idx = 0;
        while (matcher.find()) {
            Assert.assertEquals(expectedValues[idx++], matcher.group());
        }

    }

    @Test
    public void normal_case_2() {

        // when
        LoanCalculatorResult result = loansCalculator.process(2200);

        // then
        Assert.assertNotNull(result);

        Assert.assertEquals(2200, result.getLoanAmount());
        Assert.assertEquals(0.07397272727272707, result.getRate(), 0);
        Assert.assertEquals(65.51233636363635, result.getMonthlyRepayment(), 0);
        Assert.assertEquals(2362.74, result.getTotalRepayment(), 0);

        String resultInfo = result.getInfo();

        Pattern p = Pattern.compile("^[+-]?(?:\\d+\\.?\\d*|\\d*\\.\\d+)$");
        Matcher matcher = p.matcher(resultInfo);

        String[] expectedValues = {"1000", "7.0", "28.89", "1070.04"};
        int idx = 0;
        while (matcher.find()) {
            Assert.assertEquals(expectedValues[idx++], matcher.group());
        }

    }

    @Test
    public void not_available_amount_case() {

        // when
        LoanCalculatorResult result = loansCalculator.process(10000);

        // then
        Assert.assertNull(result);
    }


    @Test
    public void invalid_loan_amount_case_1() {
        try {
            // when
            loansCalculator.process(900);
            Assert.fail();

        } catch (LoansValidationException e) { // then
            Assert.assertEquals("not valid loan amount", e.getMessage());
        }
    }

    @Test
    public void invalid_loan_amount_case_2() {
        try {
            // when
            loansCalculator.process(16000);
            Assert.fail();

        } catch (LoansValidationException e) { // then
            Assert.assertEquals("not valid loan amount", e.getMessage());
        }
    }

    @Test
    public void invalid_loan_amount_case_3() {
        try {
            // when
            loansCalculator.process(1324);
            Assert.fail();

        } catch (LoansValidationException e) { // then
            Assert.assertEquals("not valid loan amount", e.getMessage());
        }
    }
}
