package com.prime.calculator.controller;

import com.prime.calculator.PrimeNumberCalculatorApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.util.NestedServletException;

import javax.annotation.Resource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {PrimeNumberCalculatorApplication.class})
public class PrimeNumberControllerTest {

    @Resource
    private WebApplicationContext webApplicationContext;

    protected MockMvc mvc;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testComputePrimesTrialDivision() throws Exception {
        final int n = 10;
        String expectedResponseResult = "[2,3,5,7]";
        mvc.perform(get("/prime-calc/" + n + "/division")).andExpect(status().isOk()).andExpect(content().string(expectedResponseResult));
    }

    @Test
    public void testComputePrimesOddDivision() throws Exception {
        final int n = 17;
        String expectedResponseResult = "[2,3,5,7,11,13,17]";
        mvc.perform(get("/prime-calc/" + n + "/division")).andExpect(status().isOk()).andExpect(content().string(expectedResponseResult));
    }

    @Test(expected = NestedServletException.class)
    public void testComputeNegativeDivision() throws Exception {
        final int n = -7;
        mvc.perform(get("/prime-calc/" + n + "/division")).andExpect(status().isInternalServerError());
    }

    @Test
    public void testComputePrimesSimpleNaive() throws Exception {
        final int n = 6;
        String expectedResponseResult = "[2,3,5]";
        mvc.perform(get("/prime-calc/" + n + "/naive")).andExpect(status().isOk()).andExpect(content().string(expectedResponseResult));
    }

    @Test
    public void testComputePrimesOddNaive() throws Exception {
        final int n = 11;
        String expectedResponseResult = "[2,3,5,7,11]";
        mvc.perform(get("/prime-calc/" + n + "/naive")).andExpect(status().isOk()).andExpect(content().string(expectedResponseResult));
    }

    @Test(expected = NestedServletException.class)
    public void testComputeNegativeNaive() throws Exception {
        final int n = -17;
        mvc.perform(get("/prime-calc/" + n + "/naive")).andExpect(status().isInternalServerError());
    }

    @Test
    public void testComputePrimesSieveOfEratosthenes() throws Exception {
        final int n = 5;
        String expectedResponseResult = "[2,3,5]";
        mvc.perform(get("/prime-calc/" + n + "/sieve")).andExpect(status().isOk()).andExpect(content().string(expectedResponseResult));
    }

    @Test
    public void testComputePrimesOddSieveOfEratosthenes() throws Exception {
        final int n = 9;
        String expectedResponseResult = "[2,3,5,7]";
        mvc.perform(get("/prime-calc/" + n + "/sieve")).andExpect(status().isOk()).andExpect(content().string(expectedResponseResult));
    }

    @Test(expected = NestedServletException.class)
    public void testComputeNegativeSieveOfEratosthenes() throws Exception {
        final int n = -237;
        mvc.perform(get("/prime-calc/" + n + "/sieve")).andExpect(status().isInternalServerError());
    }
}