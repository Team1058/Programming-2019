package org.pvcpirates.frc2019.gamepads;

import org.pvcpirates.frc2019.commands.CargoManipulationCommand;
import org.pvcpirates.frc2019.commands.ElevatorCommand;
import org.pvcpirates.frc2019.commands.FlipperCommand;
import org.pvcpirates.frc2019.commands.HatchManipulationCommand;
import org.pvcpirates.frc2019.commands.ZeroAll;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class OperatorGamepad extends BaseGamepad {

    public static double driverStickDeadband = SmartDashboard.getNumber("Driver driverStickDeadband", -0.0078125) ;

    public OperatorGamepad(int port) {
        super(port);
    }

    @Override
    void mapCommandsToController() {
        //this.teleopCommands.add(new HatchManipulationCommand(this));
        //this.teleopCommands.add(new CargoManipulationCommand(this));
        this.teleopCommands.add(new FlipperCommand(this));
        //this.teleopCommands.add(new ElevatorCommand(this));
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
