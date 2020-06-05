package oneframe.cucumber.projectone.stepdefinitions.ui;

import com.oneframe.cucumber.oneframeutils.TestFactory;

import cucumber.api.java.en.Given;

public class TestLogin {

    @Given("^I lauch and login to the application$")
    public void i_launch_and_login_to_the_application() {
        TestFactory.launchApplication();
    }
}
