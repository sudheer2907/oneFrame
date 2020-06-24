package oneframe.cucumber.projectone.stepdefinitions.ui;

import com.oneframe.cucumber.oneframe.utils.WebDriverFactory;

import cucumber.api.java.en.Given;

public class TestLogin {

    @Given("^I launch and login to the application$")
    public void i_launch_and_login_to_the_application() {
        WebDriverFactory.launchApplication();
    }
}
