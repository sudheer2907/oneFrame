package com.oneframe.cucumber.projectone.pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.oneframe.cucumber.oneframeutils.TestFactory;

public class SimpleFormDemoPage {

    public SimpleFormDemoPage() {
        PageFactory.initElements(TestFactory.getDriver(), this);
    }

    @FindBy(xpath = "//a[@class='dropdown-toggle'][contains(text(),'Input Forms')]")
    private WebElement tabInputForms;

    @FindBy(xpath = "//ul[@class='dropdown-menu']//a[contains(text(),'Simple Form Demo')]")
    private WebElement subTabSimpleFormDemo;

    @FindBy(xpath = "//ul[@class='dropdown-menu']//a[contains(text(),'Checkbox Demo')]")
    private WebElement subTabCheckBoxDemo;

    @FindBy(xpath = "//ul[@class='dropdown-menu']//a[contains(text(),'Radio Buttons Demo')]")
    private WebElement subTabRadioButtonDemo;

    @FindBy(xpath = "//ul[@class='dropdown-menu']//a[contains(text(),'Select Dropdown List')]")
    private WebElement subTabSelectDropDownList;

    @FindBy(xpath = "//ul[@class='dropdown-menu']//a[contains(text(),'Input Form Submit')]")
    private WebElement subTabInputFormSubmit;

    @FindBy(xpath = "//ul[@class='dropdown-menu']//a[contains(text(),'Ajax Form Submit')]")
    private WebElement subTabAjaxFormSubmit;

    @FindBy(xpath = "//ul[@class='dropdown-menu']//a[contains(text(),'JQuery Select dropdown')]")
    private WebElement subTabJQuerySelectDropdown;

    public void clickonTab(String tabString) {
        TestFactory.clickWebElement(tabInputForms);
    }

    /**
     * Click on subtab.
     *
     * @param subTabString
     *            - sub tab name.
     * @author sudheer.singh
     */
    public void clickOnSubtab(String subTabString) {
        switch (subTabString) {
        case "Simple Form Demo":
            TestFactory.clickWebElement(subTabSimpleFormDemo);
            break;
        case "Checkbox Demo":
            TestFactory.clickWebElement(subTabCheckBoxDemo);
            break;
        case "Radio Buttons Demo":
            TestFactory.clickWebElement(subTabRadioButtonDemo);
            break;
        case "Select Dropdown List":
            TestFactory.clickWebElement(subTabSelectDropDownList);
            break;
        case "Input Form Submit":
            TestFactory.clickWebElement(subTabInputFormSubmit);
            break;
        case "Ajax Form Submit":
            TestFactory.clickWebElement(subTabAjaxFormSubmit);
            break;
        case "JQuery Select dropdown":
            TestFactory.clickWebElement(subTabJQuerySelectDropdown);
            break;
        default:
            Assert.fail("Wrong Sub tab name is provided.");
        }
    }
}
