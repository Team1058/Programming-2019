package org.pvcpirates.frc2019.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;

import org.pvcpirates.frc2019.gamepads.BaseGamepad;
import org.pvcpirates.frc2019.gamepads.GamepadEnum;
import org.pvcpirates.frc2019.gamepads.OperatorGamepad;
import org.pvcpirates.frc2019.robot.Hardware;
import org.pvcpirates.frc2019.robot.subsystems.Flipper;
import org.pvcpirates.frc2019.util.ShuffleBoardManager;

public class FlipperCommand extends TeleopCommand {

    double tempPosition = 0;
    double tempPercentOutput = 0;

    private Flipper flipper = Hardware.getInstance().flipper;
    public FlipperCommand(BaseGamepad gp){
        super(gp);
    }

    @Override
    public void init(){
    } 
    public void exec(){
        flipper.setPIDValuesFromShuffleboard();
        String shuffleBoardSelection = ShuffleBoardManager.flipperPositionChooser.getSelected();

        System.out.println(flipper.flipperTalonMain.getSensorCollection().getAnalogInRaw());
        System.out.println("position: "+shuffleBoardSelection);

        if(shuffleBoardSelection.equals(ShuffleBoardManager.fpLvl0to2FrontString)){
            flipper.lvl0to2Front();
            flipper.miniWheelRotate(1);
        
        }else if(shuffleBoardSelection.equals(ShuffleBoardManager.fpLvl0to2BackString)){
            flipper.lvl0to2Back();
            flipper.miniWheelRotate(1);
        }else if(shuffleBoardSelection.equals(ShuffleBoardManager.fpLvl2to3FrontString)){
            flipper.lvl2to3Front();
            flipper.miniWheelRotate(1);
        }else if(shuffleBoardSelection.equals(ShuffleBoardManager.fpLvl2to3BackString)){
            flipper.lvl2to3Back();
            flipper.miniWheelRotate(1);
        }else if(shuffleBoardSelection.equals(ShuffleBoardManager.fpDefaultString)){
            flipper.defaultPosition();
            flipper.miniWheelRotate(0);
        }else{   
            flipper.defaultPosition();
            flipper.miniWheelRotate(0);
        }

        /*
    System.out.println(this.gamepad.getAxis(GamepadEnum.LEFT_STICK_Y));
    if(Math.abs(this.gamepad.getAxis(GamepadEnum.LEFT_STICK_Y)) > OperatorGamepad.driverStickDeadband){
        flipper.flipperTalonMain.set(ControlMode.PercentOutput,.5 * this.gamepad.getAxis(GamepadEnum.LEFT_STICK_Y));
    }
        if(this.gamepad.getButton(GamepadEnum.DPAD_DOWN)){
            flipper.miniWheelRotate(tempPercentOutput);
        }else {
            flipper.miniWheelRotate(tempPercentOutput);
        }
    */
    }

    @Override
    public void finished(){
    }

}
