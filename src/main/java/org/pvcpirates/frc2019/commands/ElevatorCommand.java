package org.pvcpirates.frc2019.commands;

import org.pvcpirates.frc2019.robot.Hardware;
import org.pvcpirates.frc2019.gamepads.BaseGamepad;
import org.pvcpirates.frc2019.gamepads.GamepadEnum;
import org.pvcpirates.frc2019.robot.subsystems.Elevator;
import org.pvcpirates.frc2019.util.ShuffleBoardManager;

public class ElevatorCommand extends TeleopCommand {

    private Elevator elevator = Hardware.getInstance().elevator;
    public ElevatorCommand(BaseGamepad gp){
        super(gp);
    }

    @Override
    public void init(){
    }

    @Override
    public void exec(){
        if(ShuffleBoardManager.elevatorPercentOutputEntry.getDouble(0)!=0){
            elevator.elevatorSparkMax.set(ShuffleBoardManager.elevatorPercentOutputEntry.getDouble(0));
        }
    }

    @Override
    public void finished(){
    }
}
