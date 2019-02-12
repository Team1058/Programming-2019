package org.pvcpirates.frc2019.commands;

import org.pvcpirates.frc2019.commands.Command;
import org.pvcpirates.frc2019.gamepads.BaseGamepad;
import org.pvcpirates.frc2019.robot.Hardware;
import org.pvcpirates.frc2019.robot.Robot;


public abstract class TeleopCommand extends Command {

    public BaseGamepad gamepad;
    public Hardware hardware = Robot.getInstance().hardware;

    public TeleopCommand(BaseGamepad gp) {
        gamepad = gp;
    }


}
