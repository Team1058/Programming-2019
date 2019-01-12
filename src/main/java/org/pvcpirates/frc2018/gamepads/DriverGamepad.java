package org.pvcpirates.frc2019.gamepads;

import org.pvcpirates.frc2019.teleop.DriveOpenLoop;

public class DriverGamepad extends BaseGamepad {

    public DriverGamepad(int port) {
        super(port);
    }

    void mapCommandsToController() {
    	// Drive only with percent vbus
        teleopCommands.add(new DriveOpenLoop(this));
    }


}
