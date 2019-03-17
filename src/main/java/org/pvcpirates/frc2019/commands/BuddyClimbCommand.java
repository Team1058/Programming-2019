package org.pvcpirates.frc2019.commands;

import org.pvcpirates.frc2019.gamepads.BaseGamepad;
import org.pvcpirates.frc2019.gamepads.ButtonPadEnum;
import org.pvcpirates.frc2019.gamepads.DriverGamepad;
import org.pvcpirates.frc2019.gamepads.GamepadEnum;
import org.pvcpirates.frc2019.robot.Hardware;
import org.pvcpirates.frc2019.robot.subsystems.BuddyClimb;
import org.pvcpirates.frc2019.util.ShuffleBoardManager;

public class BuddyClimbCommand extends TeleopCommand {
    BuddyClimb buddyClimb = Hardware.getInstance().buddyClimb;
    public BuddyClimbCommand(BaseGamepad gp){
        super(gp);
    }
    @Override
    public void init() {
        
    }

    @Override
    public void exec() {
        if (this.gamepad.getButton(ButtonPadEnum.ENABLE_MANUAL) && (Math.abs(this.gamepad.getAxis(ButtonPadEnum.FLIPPER_X)) > Math.abs(DriverGamepad.driverStickDeadband))){
            buddyClimb.moveProngs(this.gamepad.getAxis(ButtonPadEnum.FLIPPER_X));
        }else if (Math.abs(ShuffleBoardManager.buddyClimbPercentOutputEntry.getDouble(0)) > ShuffleBoardManager.shuffleBoardDeadband){
            buddyClimb.moveProngs(ShuffleBoardManager.buddyClimbPercentOutputEntry.getDouble(0));
        }
        else {
            buddyClimb.defaultState();
        }
    }

    @Override
    public void finished() {
        
    }
}