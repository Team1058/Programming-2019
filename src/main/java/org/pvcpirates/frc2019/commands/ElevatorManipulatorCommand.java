package org.pvcpirates.frc2019.commands;

import org.pvcpirates.frc2019.gamepads.BaseGamepad;
import org.pvcpirates.frc2019.gamepads.ButtonPadEnum;
import org.pvcpirates.frc2019.gamepads.OperatorButtonPad;
import org.pvcpirates.frc2019.robot.Hardware;
import org.pvcpirates.frc2019.robot.subsystems.Elevator;
import org.pvcpirates.frc2019.robot.subsystems.CargoManipulator;
import org.pvcpirates.frc2019.robot.subsystems.HatchManipulator;

import edu.wpi.first.wpilibj.Timer;

public class ElevatorManipulatorCommand extends TeleopCommand {
   
    boolean isPlacingHatchHigh = false;
    boolean isPlacingHatchMid = false;
    boolean isPlacingHatchLow = false;
    boolean isGrabbingHatch = false;

    boolean isPlacingCargoHigh = false;
    boolean isPlacingCargoMid = false;
    boolean isPlacingCargoLow = false;
    boolean isGrabbingCargo = false;

    private Elevator elevator = Hardware.getInstance().elevator;
    private HatchManipulator hatchManipulator = Hardware.getInstance().hatchManipulator;
    private CargoManipulator cargoManipulator = Hardware.getInstance().cargoManipulator;
    public ElevatorManipulatorCommand(BaseGamepad gp){
        super(gp);
    }

    @Override
    public void init(){
    }

    @Override
    public void exec(){
        if (gamepad.getButton(ButtonPadEnum.GAMEPIECE_SWITCH)){
            
            if(gamepad.getButton(ButtonPadEnum.PICKUP)){
                elevator.moveToIntake();
                hatchManipulator.prepGrab();
                isGrabbingHatch = true;
            }else if(!gamepad.getButton(ButtonPadEnum.PICKUP) && isGrabbingHatch){
                hatchManipulator.grabHatch();
                elevator.defaultState();
                isGrabbingHatch = false;
                
            }else if (gamepad.getButton(ButtonPadEnum.SCORE_HIGH)){
                elevator.moveToHatchHigh();
                hatchManipulator.prepPlace();
                isPlacingHatchHigh = true;
            }else if(!gamepad.getButton(ButtonPadEnum.SCORE_HIGH) && isPlacingHatchHigh){
                hatchManipulator.placeHatch();
                elevator.defaultState();
                isPlacingHatchHigh = false;

            }else if(gamepad.getButton(ButtonPadEnum.SCORE_MIDDLE)){
                elevator.moveToHatchMid();
                hatchManipulator.prepPlace();
                isPlacingHatchMid = true;
            }else if(!gamepad.getButton(ButtonPadEnum.SCORE_MIDDLE) && isPlacingHatchMid){
                hatchManipulator.placeHatch();
                elevator.defaultState();
                isPlacingHatchMid = false;

            }else if(gamepad.getButton(ButtonPadEnum.SCORE_LOW)){
                elevator.moveToHatchLow();
                hatchManipulator.prepPlace();
                isPlacingHatchLow = true;
            }else if(!gamepad.getButton(ButtonPadEnum.SCORE_LOW) && isPlacingHatchLow){
                hatchManipulator.placeHatch();
                elevator.defaultState();
                isPlacingHatchLow = false;
            }else {
                isPlacingHatchHigh = false;
                isPlacingHatchMid = false;
                isPlacingHatchLow = false;
                isGrabbingHatch = false;
            }
        } else {


        }

    }

    @Override
    public void finished(){
    }
}
