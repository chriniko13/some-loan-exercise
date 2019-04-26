package com.chriniko.loan.exercise.configuration;

import com.chriniko.loan.exercise.loan.boundary.LoansDatabase;
import com.chriniko.loan.exercise.loan.boundary.LoansCalculator;
import com.chriniko.loan.exercise.loan.control.LoansValidator;
import com.google.inject.AbstractModule;

public class AppModule extends AbstractModule {

    @Override
    protected void configure() {

        bind(LoansDatabase.class).asEagerSingleton();
        bind(LoansCalculator.class).asEagerSingleton();
        bind(LoansValidator.class).asEagerSingleton();
    }
}
