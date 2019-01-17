package org.pvcpirates.frc2019.robot;

import org.pvcpirates.frc2019.state.State;

public class Robot {

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
        this.state = state;
    }
}
