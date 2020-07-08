package oneframe.cucumber.projectone.stepdefinitions.ui;

import com.oneframe.cucumber.projectone.pages.SimpleFormDemoPage;

import cucumber.api.java.en.And;

public class TestSimpleFormDemo {

    SimpleFormDemoPage SimpleFormDemoPageObj = new SimpleFormDemoPage();

    @And("^I click on tab (.*)$")
    public void i_click_on_tab(String tabNameString) {
        SimpleFormDemoPageObj.clickonTab(tabNameString);
    }

    @And("^I click on sub tab (.*)$")
    public void i_click_on_sub_tab(String subTabNameString) {
        SimpleFormDemoPageObj.clickOnSubtab(subTabNameString);
    }

}
