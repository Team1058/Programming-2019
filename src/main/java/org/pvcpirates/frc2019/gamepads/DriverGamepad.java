package org.pvcpirates.frc2019.gamepads;

import org.pvcpirates.frc2019.teleop.TeleopDriveCommand;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;



public class DriverGamepad extends BaseGamepad {

    public static double driverStickDeadband = SmartDashboard.getNumber("Driver driverStickDeadband", -0.0088125) ;

    public DriverGamepad(int port) {
        super(port);
    }

    void mapCommandsToController() {
        this.teleopCommands.add(new TeleopDriveCommand(this));
    }

    

}
