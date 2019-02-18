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

        
        if(shuffleBoardSelection.equals(ShuffleBoardManager.fpLvl0to2FrontString)){
            flipper.lvl0to2Front();
        }else if(shuffleBoardSelection.equals(ShuffleBoardManager.fpLvl0to2BackString)){
            flipper.lvl0to2Back();
        }else if(shuffleBoardSelection.equals(ShuffleBoardManager.fpLvl2to3FrontString)){
            flipper.lvl2to3Front();
        }else if(shuffleBoardSelection.equals(ShuffleBoardManager.fpLvl2to3BackString)){
            flipper.lvl2to3Back();
        }else if(shuffleBoardSelection.equals(ShuffleBoardManager.fpDefaultString)){
            flipper.defaultPosition();
        }else{   
            flipper.defaultPosition();
        }

        if(ShuffleBoardManager.miniWheelControl.getDouble(0)!= 0){
            flipper.miniWheelRotate(ShuffleBoardManager.miniWheelControl.getDouble(0));
        }
    }

    @Override
    public void finished(){
    }

}
