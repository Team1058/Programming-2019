package org.pvcpirates.frc2019.robot;

import org.pvcpirates.frc2019.state.State;

public class Robot {

    public static final boolean DEBUG = false;
    private static Robot ourInstance;
    public final Hardware hardware = Hardware.getInstance();
    public State state;

    private Robot() {
    }

    public static Robot getInstance() {
        if (ourInstance == null) {
            ourInstance = new Robot();
        }
        return ourInstance;
    }

    public void setState(State state) {
        if (state != null)
            state.stop();
        this.state = state;
    }
}
