package org.pvcpirates.frc2019.gamepads;

import org.pvcpirates.frc2019.commands.HatchManipulationCommand;
import org.pvcpirates.frc2019.commands.TeleopAutoHatchAssist;
import org.pvcpirates.frc2019.commands.TeleopDriveCommand;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriverGamepad extends BaseGamepad {

    public static double driverStickDeadband = SmartDashboard.getNumber("Driver driverStickDeadband", -0.0078125) ;

    public DriverGamepad(int port) {
        super(port);
    }

    void mapCommandsToController() {
        this.teleopCommands.add(new TeleopDriveCommand(this));
        this.teleopCommands.add(new TeleopAutoHatchAssist(this));

        //Hatch manipulator for testing
        this.teleopCommands.add(new HatchManipulationCommand(this));
    }

    @Override
    public double getAxis(GamepadEnum axisEnum) {
       double rawAxis = super.getAxis(axisEnum);
    
        if(Math.abs(rawAxis) <= Math.abs(driverStickDeadband)){
            return 0;
        }else {
            return rawAxis;
        }
    }

}
