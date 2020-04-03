package com.oneframe.cucumber.oneframeutils;

public class Utilities {
    private static String strEnvironment = null;

    /**
     * Get Environment name.
     *
     * @return - environment name.
     */
    public static String getStrEnvironment() {
        return strEnvironment;
    }

    /**
     * Set environment name.
     *
     * @param strEnvironment
     *            - Environment name.
     */
    public static void setStrEnvironment(String strEnvironment) {
        Utilities.strEnvironment = strEnvironment;
    }
}