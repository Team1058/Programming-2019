package org.pvcpirates.frc2019.commands;

import org.pvcpirates.frc2019.teleop.TeleopCommand;
import org.pvcpirates.frc2019.gamepads.BaseGamepad;
import org.pvcpirates.frc2019.gamepads.GamepadEnum;
import org.pvcpirates.frc2019.robot.Hardware;
import org.pvcpirates.frc2019.robot.subsystems.Flipper;

public class FlipperCommand extends TeleopCommand {

    int tempPosition = 0;
    int tempPercentOutput = 0;

    private Flipper flipper = Hardware.getInstance().flipper;
    public FlipperCommand(BaseGamepad gp){
        super(gp);
    }

    public void init(){

    }   

    public void exec(){


        if(this.gamepad.getButton(GamepadEnum.DPAD_RIGHT)){
            flipper.flipperRotate(tempPosition);
        }else if(this.gamepad.getButton(GamepadEnum.DPAD_LEFT)){
            flipper.flipperRotate(tempPosition);
        }else {
            flipper.flipperRotate(tempPosition);
        }

        if(this.gamepad.getButton(GamepadEnum.DPAD_DOWN)){
            flipper.miniWheelRotate(tempPercentOutput);
        }else {
            flipper.miniWheelRotate(tempPercentOutput);
        }

    }

    public void finished(){

    }

}
