package org.pvcpirates.frc2019.commands;

import org.pvcpirates.frc2019.gamepads.BaseGamepad;
import org.pvcpirates.frc2019.gamepads.DriverGamepad;
import org.pvcpirates.frc2019.gamepads.GamepadEnum;
import org.pvcpirates.frc2019.robot.Hardware;
import org.pvcpirates.frc2019.robot.subsystems.BuddyClimb;
import org.pvcpirates.frc2019.util.ShuffleBoardManager;

public class BuddyClimbCommand extends TeleopCommand {
    BuddyClimb buddyClimb = Hardware.getInstance().buddyClimb;
    BuddyClimbCommand(BaseGamepad gp){
        super(gp);
    }
    @Override
    public void init() {
        
    }

    @Override
    public void exec() {
        if (gamepad.getButton(GamepadEnum.LEFT_BUMPER) && (Math.abs(this.gamepad.getAxis(GamepadEnum.LEFT_STICK_Y)) > Math.abs(DriverGamepad.driverStickDeadband))){
            buddyClimb.buddyClimbSparkMax.set(this.gamepad.getAxis(GamepadEnum.LEFT_STICK_Y));
        }
    }

    @Override
    public void finished() {
        
    }
}