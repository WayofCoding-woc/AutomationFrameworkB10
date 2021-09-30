package com.woc.bdd.cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/featurefiles", glue = "com.woc.bdd.cucumber.test")
public class BDDRunner {
}
