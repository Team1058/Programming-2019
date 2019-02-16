package org.pvcpirates.frc2019.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;

import org.pvcpirates.frc2019.gamepads.BaseGamepad;
import org.pvcpirates.frc2019.gamepads.GamepadEnum;
import org.pvcpirates.frc2019.robot.Hardware;
import org.pvcpirates.frc2019.robot.subsystems.Flipper;
import org.pvcpirates.frc2019.util.ShuffleBoardManager;

public class FlipperCommand extends TeleopCommand {

    double tempPosition = 0;
    int tempPercentOutput = 0;

    private Flipper flipper = Hardware.getInstance().flipper;
    public FlipperCommand(BaseGamepad gp){
        super(gp);
    }

    @Override
    public void init(){
    } 
    public void exec(){

        String shuffleBoardSelection = ShuffleBoardManager.flipperPositionChooser.getSelected();

        if(shuffleBoardSelection.equals(ShuffleBoardManager.fpLvl1to2FrontString)){
            flipper.lvl1to2Front();
        
        }else if(shuffleBoardSelection.equals(ShuffleBoardManager.fpLvl1to2BackString)){
            flipper.lvl1to2Back();
        
        }else if(shuffleBoardSelection.equals(ShuffleBoardManager.fpLvl2to3BackString)){
            flipper.lvl2to3Front();
        
        }else if(shuffleBoardSelection.equals(ShuffleBoardManager.fpLvl2to3BackString)){
            flipper.lvl2to3Back();
        
        }else if(shuffleBoardSelection.equals(ShuffleBoardManager.fpDefaultString)){
            flipper.defaultPosition();
        }else {
            flipper.defaultPosition();
        }

        if(this.gamepad.getButton(GamepadEnum.DPAD_DOWN)){
            flipper.miniWheelRotate(tempPercentOutput);
        }else {
            flipper.miniWheelRotate(tempPercentOutput);
        }

    }

    @Override
    public void finished(){
    }

}
