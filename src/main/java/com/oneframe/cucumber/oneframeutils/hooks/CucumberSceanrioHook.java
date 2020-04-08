package com.oneframe.cucumber.oneframeutils.hooks;

import java.io.IOException;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

/**
 * {@link CucumberSceanrioHook} class will re-initailize the DatabaseBean before
 * and after each scenario to resolve the conflicts if occurred during the
 * scenarios execution.
 *
 */
public class CucumberSceanrioHook {

    @Before
    public void beforeSceanrio(Scenario scenario) throws IOException {
    }

    @After
    public void afterSceanrio(Scenario scenario) throws IOException {
    }
}