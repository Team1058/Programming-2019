package org.pvcpirates.frc2019.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;

import org.pvcpirates.frc2019.gamepads.BaseGamepad;
import org.pvcpirates.frc2019.gamepads.DriverGamepad;
import org.pvcpirates.frc2019.gamepads.ButtonPadEnum;
import org.pvcpirates.frc2019.gamepads.GamepadEnum;
import org.pvcpirates.frc2019.gamepads.OperatorButtonPad;
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
        
        String shuffleBoardSelection = ShuffleBoardManager.flipperPositionChooser.getSelected();
        if (this.gamepad.getButton(ButtonPadEnum.ENABLE_MANUAL) && (Math.abs(this.gamepad.getAxis(ButtonPadEnum.FLIPPER_Y)) > Math.abs(DriverGamepad.driverStickDeadband))){
            flipper.flipperTalonMain.set(ControlMode.PercentOutput, this.gamepad.getAxis(ButtonPadEnum.FLIPPER_Y));
        }else if (ShuffleBoardManager.flipperPercentOutputEntry.getDouble(0) != 0){
            flipper.flipperTalonMain.set(ControlMode.PercentOutput, ShuffleBoardManager.flipperPercentOutputEntry.getDouble(0));
        }else if((!this.gamepad.getButton(ButtonPadEnum.CLIMB_SWITCH) && this.gamepad.getButton(ButtonPadEnum.CLIMB_FRONT)) || shuffleBoardSelection.equals(ShuffleBoardManager.fpLvl0to2FrontString)){
            //flipper.lvl0to2Front();
            System.out.println("L2 Front");
        }else if((!this.gamepad.getButton(ButtonPadEnum.CLIMB_SWITCH) && this.gamepad.getButton(ButtonPadEnum.CLIMB_REAR)) || shuffleBoardSelection.equals(ShuffleBoardManager.fpLvl0to2BackString)){
            //flipper.lvl0to2Back();
            System.out.println("L2 Rear");
        }else if((this.gamepad.getButton(ButtonPadEnum.CLIMB_SWITCH) && this.gamepad.getButton(ButtonPadEnum.CLIMB_FRONT)) || shuffleBoardSelection.equals(ShuffleBoardManager.fpLvl2to3FrontString)){
            //flipper.lvl2to3Front();
            System.out.println("L3 Front");
        }else if((this.gamepad.getButton(ButtonPadEnum.CLIMB_SWITCH) && this.gamepad.getButton(ButtonPadEnum.CLIMB_REAR)) || shuffleBoardSelection.equals(ShuffleBoardManager.fpLvl2to3BackString)){
            //flipper.lvl2to3Back();
            System.out.println("L3 Rear");
        }else if(shuffleBoardSelection.equals(ShuffleBoardManager.fpDefaultString)){
            //flipper.defaultPosition();
            System.out.println("Default Flipper Position");
        }else{   
            //flipper.defaultPosition();
            System.out.println("Default Flipper Position");
        }

        if(ShuffleBoardManager.miniWheelControl.getDouble(0)!= 0){
            flipper.miniWheelRotate(ShuffleBoardManager.miniWheelControl.getDouble(0));
        }

    }

    @Override
    public void finished(){
    }

}
