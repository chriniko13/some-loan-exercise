### Job Technical Exercise

###### Assignee: Nikolaos Christidis (nick.christidis@yahoo.com)

#### Build
* Execute: `mvn clean install`


#### Run
* Execute: `java -jar target/loan-exerice-1.0-SNAPSHOT-jar-with-dependencies.jar LOAN_AMOUNT`


#### Run unit tests
* Execute: `mvn clean test`


#### Run integration tests
* Execute: `mvn integration-test -DskipUTs=true`


#### Coverage reports (unit & integration)
* Execute: `mvn clean verify`

* Unit Test coverage reports, go to: `target/site/jacoco-ut/index.html`

* Integration Test coverage reports, go to: `target/site/jacoco-it/index.html`


#### Code Quality via SonarQube (you should run docker-compose up first)
* Execute: `docker-compose up`

* First login to web portal: `http://localhost:9000/sessions/new`
    * Credentials
        * Username: `admin`
        * Password: `admin`
        
* Then execute: `mvn sonar:sonar` and browse to: `http://localhost:9000/projects` and select the correct one
  in order to see more information for code quality.
  
  
* (Optional) execute: `docker-compose down`



#### Description

There is a need for a rate calculation system allowing prospective borrowers to
obtain a quote from our pool of lenders for 36 month loans. This system will
take the form of a command-line application.

You will be provided with a file containing a list of all the offers being made
by the lenders within the system in CSV format, see the example market.csv file
provided alongside this specification.

You should strive to provide as low a rate to the borrower as is possible to
ensure that company's quotes are as competitive as they can be against our
competitors'. You should also provide the borrower with the details of the
monthly repayment amount and the total repayment amount.
Repayment amounts should be displayed to 2 decimal places and the rate of the
loan should be displayed to one decimal place.

Borrowers should be able to request a loan of any £100 increment between
£1000 and £15000 inclusive. If the market does not have sufficient offers from
lenders to satisfy the loan then the system should inform the borrower that it
is not possible to provide a quote at that time.

The application should take arguments in the form:

* `cmd> [application] [market_file] [loan_amount]`

Example:
* `cmd> quote.exe market.csv 1500`


The application should produce output in the form:

* `cmd> [application] [market_file] [loan_amount]`
```text
Requested amount: £XXXX
Rate: X.X%
Monthly repayment: £XXXX.XX
Total repayment: £XXXX.XXExample:
cmd> quote.exe market.csv 1000
Requested amount: £1000
Rate: 7.0%
Monthly repayment: £30.78
Total repayment: £1108.10
```
