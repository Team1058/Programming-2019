package org.pvcpirates.frc2019.gamepads;

import org.pvcpirates.frc2019.commands.FlipperCommand;
import org.pvcpirates.frc2019.commands.ElevatorCommand;
import org.pvcpirates.frc2019.commands.ElevatorManipulatorCommand;
import org.pvcpirates.frc2019.commands.HatchManipulationCommand;
import org.pvcpirates.frc2019.commands.CargoManipulationCommand;

public class OperatorButtonPad extends BaseGamepad {

    double buttonPadStickDeadband;

    public OperatorButtonPad(int port){
        super(port);
    }

    void mapCommandsToController() {
        this.teleopCommands.add(new FlipperCommand(this));
        this.teleopCommands.add(new HatchManipulationCommand(this));
        this.teleopCommands.add(new CargoManipulationCommand(this));
        this.teleopCommands.add(new ElevatorCommand(this));
        this.teleopCommands.add(new ElevatorManipulatorCommand(this));
    }

    @Override
    public double getAxis(GamepadEnum axisEnum) {
       double rawAxis = super.getAxis(axisEnum);
    
        if(Math.abs(rawAxis) <= Math.abs(buttonPadStickDeadband)){
            return 0;
        }else {
            return rawAxis;
        }
    }

}
