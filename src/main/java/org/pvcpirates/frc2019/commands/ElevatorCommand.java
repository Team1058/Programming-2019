package org.pvcpirates.frc2019.commands;

import org.pvcpirates.frc2019.teleop.TeleopCommand;
import org.pvcpirates.frc2019.robot.Hardware;
import org.pvcpirates.frc2019.gamepads.BaseGamepad;
import org.pvcpirates.frc2019.gamepads.GamepadEnum;
import org.pvcpirates.frc2019.robot.subsystems.Elevator;

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
    }

    @Override
    public void finished(){
    }
}
