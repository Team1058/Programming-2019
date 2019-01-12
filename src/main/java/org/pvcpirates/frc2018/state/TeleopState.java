package org.pvcpirates.frc2019.state;

import org.pvcpirates.frc2019.gamepads.DriverGamepad;
import org.pvcpirates.frc2019.gamepads.OperatorGamepad;
import org.pvcpirates.frc2019.robot.Hardware;


public class TeleopState extends State {
    private DriverGamepad driverGamepad;
    private OperatorGamepad operatorGamepad;
    private Hardware hardware;
    
    @Override
    public void init() {
        driverGamepad = new DriverGamepad(0);
        operatorGamepad = new OperatorGamepad(1);
    }

    @Override
    public void exec() {
        // Code here will all get called periodically (every ms) in Auto
        driverGamepad.executeCommands();
        operatorGamepad.executeCommands();
    }

    @Override
    public void stop() {
        // Code here will get called when teleop state is stopped
    }

}
