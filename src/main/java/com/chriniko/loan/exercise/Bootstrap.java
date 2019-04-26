package com.chriniko.loan.exercise;

import com.chriniko.loan.exercise.configuration.AppModule;
import com.chriniko.loan.exercise.loan.boundary.LoansCalculator;
import com.chriniko.loan.exercise.loan.entity.LoanCalculatorResult;
import com.google.inject.Guice;
import com.google.inject.Injector;
import lombok.extern.log4j.Log4j2;

@Log4j2

public class Bootstrap {

    public static void main(String[] args) {

        if (args.length != 1) {
            log.error("Enter loan amount!");
            System.exit(1);
        }

        Injector injector = Guice.createInjector(new AppModule());
        LoansCalculator loansCalculator = injector.getInstance(LoansCalculator.class);

        long loanAmount = Long.parseLong(args[0]);
        LoanCalculatorResult result = loansCalculator.process(loanAmount);

        if (result != null) {
            log.info(result);
            log.info(result.getInfo());
        } else {
            log.warn("it is not possible to provide a quote at that time");
        }

    }


}
