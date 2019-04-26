package com.chriniko.loan.exercise.loan.boundary;

import com.chriniko.loan.exercise.loan.entity.LoansProcessingException;
import com.chriniko.loan.exercise.loan.entity.LoanInfo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class LoansDatabase {

    List<LoanInfo> findAll() {

        try (BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(
                        Objects.requireNonNull(LoansDatabase.class.getClassLoader().getResourceAsStream("data/loans.csv"))))
        ) {
            bufferedReader.readLine(); // Note: skip header.

            String line;
            List<LoanInfo> loanInfos = new LinkedList<>();

            while ((line = bufferedReader.readLine()) != null) {
                String[] splittedInfo = line.split(",");
                loanInfos.add(new LoanInfo(splittedInfo[0], splittedInfo[1], splittedInfo[2]));
            }

            return loanInfos;

        } catch (Exception error) {
            throw new LoansProcessingException("could not load loans, message: " + error.getMessage(), error);
        }
    }

}
