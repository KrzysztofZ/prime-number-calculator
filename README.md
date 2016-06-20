# prime-number-calculator
# README prime-number-calculator

This README documents what steps are necessary to get your application up and running.

* Quick summary

### How do I get set up? ###
This is a standalone Spring Boot App. No additional configuration is required for current release.

####Dependencies
* JDK 1.8

#How to run tests
## Unit and Integration Tests
mvn test will run all unit tests and integration tests.

* Startup instructions
java -jar ./target/prime-number-calculator-1.0-SNAPSHOT.jar

* calling rest service instructions
Following endpoints are available after application has been started:

```
localhost:8080/prime-calc/{n}/division
localhost:8080/prime-calc/{n}/naive
localhost:8080/prime-calc/{n}/sieve
```

{n} is non-negative integer

The following 3 algorithms have been implemented:
1) Trial division
2) Brute force(naive)
3) Sieve of Eratosthenes