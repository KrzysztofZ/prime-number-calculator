package com.prime.calculator.controller;

import com.prime.calculator.division.IPrimeCalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/prime-calc")
public class PrimeNumberController {

    @Autowired
    private IPrimeCalculatorService divisionPrimeCalculatorService;

    @Autowired
    private IPrimeCalculatorService naivePrimeCalculatorService;

    @Autowired
    private IPrimeCalculatorService sievePrimeCalculatorService;

    @RequestMapping(value = "/{n}/division", method = RequestMethod.GET)
    public @ResponseBody List<Integer> getPrimeNumbersTrialDivision(@Valid @PathVariable Integer n) {
        return divisionPrimeCalculatorService.calculate(n);
    }

    @RequestMapping(value = "/{n}/naive", method = RequestMethod.GET)
    public @ResponseBody List<Integer> getPrimeNumbersNaive(@Valid @PathVariable Integer n) {
        return naivePrimeCalculatorService.calculate(n);
    }

    @RequestMapping(value = "/{n}/sieve", method = RequestMethod.GET)
    public @ResponseBody List<Integer> getPrimeNumbersSieve(@Valid @PathVariable Integer n) {
        return sievePrimeCalculatorService.calculate(n);
    }
}
