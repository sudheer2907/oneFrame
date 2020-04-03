package oneframe.cucumber.projectone.testrunner;

import org.apache.log4j.PropertyConfigurator;
import org.junit.runner.RunWith;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;
import oneframe.cucumber.reporting.GenerateReport;

@RunWith(Cucumber.class)

@CucumberOptions(monochrome = true, features = "src//test//resources//features//projectOne", glue = "oneframe.cucumber.projectone.stepdefinitions", plugin = {
        "pretty", "io.qameta.allure.cucumberjvm.AllureCucumberJvm", "html:target/test-report/cucumber",
        "json:target/test-report/cucumber.json" }, tags = { "@TestGetMethod" })
public class TestRunner {
    private TestNGCucumberRunner testNGCucumberRunner;
    private static String scenarioName = null;
    static RemoteWebDriver driver = null;

    /**
     * setUpClass.
     */

    @BeforeClass(alwaysRun = true)
    public void setUpClass() {
        PropertyConfigurator.configure("log4j.properties");
        System.setProperty("log4j.configurationFile", "log4j.properties");
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }

    @Test(groups = "cucumber", description = "Runs Cucumber Feature", dataProvider = "features")
    public void feature(CucumberFeatureWrapper cucumberFeature) {
        scenarioName = cucumberFeature.getCucumberFeature().getPath();
        System.out.println("**************Executing scenario *********" + scenarioName);
        testNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());

    }

    @DataProvider
    public Object[][] features() {
        return testNGCucumberRunner.provideFeatures();
    }

    /**
     * Teardownclass.
     */
    @AfterClass(alwaysRun = true)
    public void tearDownClass() {
        testNGCucumberRunner.finish();
        GenerateReport.generateReport("oneFrame", "target/test-report");
    }

    public static String getScenarioName() {
        return scenarioName;
    }
}
