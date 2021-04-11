# oneFrame readme.md files contains all the instruction to make use of this framework. This framework can be used for the UI automation as well as for the API automation.

**#Framework Structure to be followed**
1. All the feature files should be available inside - src/test/resources/features
2. All the properties files should be kept into - src/test/resources/properties
3. All the test data files except input payload should be kept into src/test/resources/testData
4. Step definations should be kept into src/test/java/oneframe/cucumber/stepdefinitions
5. Test runner available into src/test/java/oneframe/cucumber/testrunner
6. All the payloads required for web service testing should be kept into /src/main/resources/payloads

**#About properties and some other supporting files**
* In this framework there will be multiple .properties files, e.g. config.properties, prodconfig.properties, qaconfig.properties
1. config.properties is a properties files where all the configurations related to test executions will be kept. You can keep all the common properties data into this config.properties file
2. if in config.properties files, if you pass Environment qa then all the execution will happen through qaconfig.properties file
3. if in config.properties files, if you pass Environment prod then all the execution will happen through prodconfig.properties file
* log4j.xml is a files which is used by log4j
* settings.xml files will be having all the repositories urls, this settings.xml is for pom.xml
* testng.xml files is required for execution, pass the test runner package inside this testng.xml

**#About some core feature of this framework**
1. WebDriverFactory is class where the common methods are written required for the UI executions.
* In this class only, your web driver will get initialised, call WebDriverFactory.getDriver() to get the instance of the initialised web driver,
_* Note: WebDriver will be initialised as per the values passed into qaconfig.properties or prodconfig.properties, if you pass browser as Chrome, then ChromeDriver will get initialised and calling WebDriverFactory.getDriver() will return instance of ChromeBrowser_

**2. Some of the important methods which are written in this framework are as below 
1. WebDriverFactory class**
* openApplication() - Launch application and navigate to the base url to start the execution.
* waitForPageToLoad(int timeout) - Wait for Page to Load.
* closeWindow() - close the open windows
* highlight(WebElement element) - Highlight web element while ececution.
* captureScreenShot(WebDriver driver, String screenshotname) - Take screenshots before test case failed.
* clickWebElement(WebElement element) - Click on the web element.
* waitForAnElementToBeVisible(WebElement element, int timeOut) - Wait for an element to be visible.
* mouseHoverOnElement(WebElement element) - Mouse hover over an element.
* waitForElementForVisibilityIgnoringException(WebElement elementLocator,int timeout, Class<? extends WebDriverException> klass) - Wait for Element to be visible on Screen before performing any action and Ignoring any Selenium Exception.
* switchToWindow(String windowHandle) - Switch to child or parent window.
* witchToFrameInDom(WebElement elementLocator) - Switch into frame.
* closeCurrentBrowserWindow() - Switch into frame.
***Note: To get complete idea about all the commnon method, please open WebDriverFactory class.**

**2. Utilities class**
* getEnvironmentProperties(String keyString) - Read config properties values either from qaconfig.properties or prodconfig.properties.
*Notes: To read properties from qaconfig.properties, pass Environment as qa into config.properties files, similarly for prodconfig.properties

***LogPrinter class**
* Use printLog(String message) to print any message on console


**#code-compliances is a folder which will having all the files related to code checks
#drivers is a folder where you can keep all the drivers of all the browsers**

**#Formatter URLS**
1. Google formatter https://github.com/google/styleguide
2. Google eclipse formatter https://github.com/google/styleguide/blob/gh-pages/intellij-java-google-style.xml
3. Google intellij Formatter https://github.com/google/styleguide/blob/gh-pages/eclipse-java-google-style.xml
4. Google checker https://raw.githubusercontent.com/checkstyle/checkstyle/checkstyle-8.16/src/main/resources/google_checks.xml
