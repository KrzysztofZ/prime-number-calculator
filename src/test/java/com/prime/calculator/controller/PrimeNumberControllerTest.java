package com.prime.calculator.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration
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

    }

    @Test
    public void testComputePrimesOddDivision() throws Exception {

    }

    @Test
    public void testComputeNegativeDivision() throws Exception {

    }

    @Test
    public void testComputePrimesTrialNaive() throws Exception {

    }

    @Test
    public void testComputePrimesOddNaive() throws Exception {

    }

    @Test
    public void testComputeNegativeNaive() throws Exception {

    }

    @Test
    public void testComputePrimesSieveOfEratosthenes() throws Exception {

    }

    @Test
    public void testComputePrimesOddSieveOfEratosthenes() throws Exception {

    }

    @Test
    public void testComputeNegativeSieveOfEratosthenes() throws Exception {

    }
}