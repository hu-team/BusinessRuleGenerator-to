

package com.brg;

/**
 * BusinessRuleGenerator main class
 * @author BRG Team 5
 */
public class BusinessRuleGenerator {
    public static final String   VERSION    = "1.0.0-beta1";
    public static final int      BUILD      = 283;
    public static final boolean  DEBUG      = true;

    public static void main(String [] args) {
        // Start the controller facade
        ServiceProvider.getInstance().startFirstWave(args);
    }
}
