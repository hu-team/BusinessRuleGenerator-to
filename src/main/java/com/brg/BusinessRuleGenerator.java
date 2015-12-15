

package com.brg;

/**
 * BusinessRuleGenerator main class
 */
public class BusinessRuleGenerator {
    public static final String   VERSION = "1.0.0";
    public static final int      BUILD = 1;

    public static void main(String [] args) {
        // Start the controller facade
        ServiceProvider.getInstance().getControllerService().getMainWindow().start(args);
    }

}