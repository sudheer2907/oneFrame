@TestUI
Feature: This is sample UI test.

  Scenario: 01 Test Single Input Fields
    Given I launch and login to the application
    And I click on tab Input Forms
    And I click on sub tab Simple Form Demo of Input Forms page
    And I enter text message as Sample into enterMessage textbox
    When I click on button show message
    Then I verify output message single input textbox is Sample

  Scenario: 02 Test Two input fields
    Given I launch and login to the application
    And I click on tab Input Forms
    And I click on sub tab Simple Form Demo of Input Forms page
    And I enter text message as 2 into enterA textbox
    And I enter text message as 3 into enterB textbox
    Then I verify output message double input textbox is 5

  Scenario: 03 Test Single Checkbox
    Given I launch and login to the application
    And I click on tab Input Forms
    And I click on sub tab Checkbox Demo of Input Forms page
    And I click on the checkbox Click on this check box
    Then I verify Success - Check box is checked message is displayed

  Scenario Outline: 04 Test single radio button
    Given I launch and login to the application
    And I click on tab Input Forms
    And I click on sub tab Radio Buttons Demo of Input Forms page
    And I select gender as <gender> radio button under section <sectionName>
    When I click on get checked value button
    Then I verify output message displayed as Radio button 'Male' is checked

    Examples: 
      | gender | sectionName       |
      | Male   | Radio Button Demo |
