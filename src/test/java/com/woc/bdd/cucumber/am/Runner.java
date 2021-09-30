package com.woc.bdd.cucumber.am;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/featurefiles", glue = "com.woc.bdd.cucumber.am.test")
public class Runner {
}
