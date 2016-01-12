

package com.brg;

/**
 * BusinessRuleGenerator main class
 * @author BRG Team 5
 */
public class BusinessRuleGenerator {
    public static final String   VERSION    = "1.0.0";
    public static final int      BUILD      = 160;

    public static void main(String [] args) {
        // Start the controller facade
        ServiceProvider.getInstance().getControllerService().getMainWindow().start(args);
    }
}