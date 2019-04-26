package com.chriniko.loan.exercise.it;


import com.chriniko.loan.exercise.configuration.AppModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.Before;

public class SpecificationBase {

    protected Injector injector = Guice.createInjector(new AppModule());

    @Before
    public void setup() {
        injector.injectMembers(this);
    }
}
