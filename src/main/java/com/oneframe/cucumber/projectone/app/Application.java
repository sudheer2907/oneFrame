package com.oneframe.cucumber.projectone.app;

/**
 * Application Names to be used.
 * <li>{@link #NOC}</li>
 * <li>{@link #CHERWELL}</li>
 *
 */
public enum Application {

    NOCBO("NOCBO"), CHERWELL("Cherwell");

    private Application(String appName) {
        this.applicationName = appName;
    }

    private String applicationName;

    public String getApplicationName() {
        return applicationName;
    }

}