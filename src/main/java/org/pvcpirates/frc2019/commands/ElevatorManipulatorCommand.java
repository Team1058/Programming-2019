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
        if (this.gamepad.getButton(ButtonPadEnum.GAMEPIECE_SWITCH) && !this.gamepad.getButton(ButtonPadEnum.ENABLE_MANUAL)){
            if(gamepad.getButton(ButtonPadEnum.PICKUP) && !this.gamepad.getButton(ButtonPadEnum.SUPER_CARGO)){
                elevator.moveToIntake();
                cargoManipulator.cargoIn();
                cargoManipulator.disableSecondaryRollers();
                isGrabbingCargo = true;
            }else if (this.gamepad.getButton(ButtonPadEnum.PICKUP) && this.gamepad.getButton(ButtonPadEnum.SUPER_CARGO)){
                elevator.moveToIntake();
                cargoManipulator.cargoIn();
                cargoManipulator.enableSecondaryRollers();
                isGrabbingCargo = true;
            }else if(!gamepad.getButton(ButtonPadEnum.PICKUP) && isGrabbingCargo){
                elevator.defaultState(); 
                isGrabbingCargo = false;
            }else if (gamepad.getButton(ButtonPadEnum.SCORE_HIGH)){
                //elevator.moveToCargoHigh();
                //cargoManipulator.cargoOut();
                isPlacingCargoHigh = true;
            }else if(!gamepad.getButton(ButtonPadEnum.SCORE_HIGH) && isPlacingCargoHigh){
                //cargoManipulator.cargoStop();
                //elevator.defaultState();
                isPlacingCargoHigh = false;

            }else if(gamepad.getButton(ButtonPadEnum.SCORE_MIDDLE)){
                elevator.moveToCargoMid();
                cargoManipulator.cargoOut();
                isPlacingCargoMid = true;
            }else if(!gamepad.getButton(ButtonPadEnum.SCORE_MIDDLE) && isPlacingCargoMid){
                cargoManipulator.cargoStop();
                elevator.defaultState();
                isPlacingCargoMid = false;

            }else if(gamepad.getButton(ButtonPadEnum.SCORE_LOW)){
                elevator.moveToCargoLow();
                cargoManipulator.cargoOut();
                isPlacingCargoLow = true;
            }else if(!gamepad.getButton(ButtonPadEnum.SCORE_LOW) && isPlacingCargoLow){
                cargoManipulator.cargoStop();
                elevator.defaultState();
                isPlacingCargoLow = false;
            }else {
                cargoManipulator.disableSecondaryRollers();
                isPlacingCargoHigh = false;
                isPlacingCargoMid = false;
                isPlacingCargoLow = false;
                isGrabbingCargo = false;
            }

            
        } else if (!gamepad.getButton(ButtonPadEnum.GAMEPIECE_SWITCH) && !this.gamepad.getButton(ButtonPadEnum.ENABLE_MANUAL)) {
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
                //retracts rollers incase of switch mid SUPER
                cargoManipulator.disableSecondaryRollers();
                isPlacingHatchHigh = false;
                isPlacingHatchMid = false;
                isPlacingHatchLow = false;
                isGrabbingHatch = false;
            }


        }

    }

    @Override
    public void finished(){
    }
}
