package org.pvcpirates.frc2019.gamepads;

import org.pvcpirates.frc2019.commands.HatchManipulationCommand;
import org.pvcpirates.frc2019.commands.PirateDriveCommand;
import org.pvcpirates.frc2019.commands.TeleopAutoHatchAssist;
import org.pvcpirates.frc2019.commands.TeleopDriveCommand;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriverGamepad extends BaseGamepad {

    public static double driverStickDeadband = SmartDashboard.getNumber("Driver driverStickDeadband", -0.0078125) ;

    public DriverGamepad(int port) {
        super(port);
    }

    void mapCommandsToController() {
        //this.teleopCommands.add(new PlagiarismDriveCommand(this));

        this.teleopCommands.add(new TeleopDriveCommand(this));
        // TODO: Uncomment once we are comfortable with auto-assist
        //this.teleopCommands.add(new TeleopAutoHatchAssist(this));
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
