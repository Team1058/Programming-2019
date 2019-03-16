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
    boolean isPlacingCargoHP = false;
    boolean isPlacingCargoMid = false;
    boolean isPlacingCargoLow = false;
    boolean isGrabbingCargo = false;
    long start = 0;
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
        long timeDiff = System.currentTimeMillis()-start;
        if (this.gamepad.getButton(ButtonPadEnum.GAMEPIECE_SWITCH) && !this.gamepad.getButton(ButtonPadEnum.ENABLE_MANUAL)){
            if(gamepad.getButton(ButtonPadEnum.PICKUP)){
                elevator.moveToIntake();
                cargoManipulator.cargoIn();
                cargoManipulator.disableSecondaryRollers();
                isGrabbingCargo = true;
            }else if(!gamepad.getButton(ButtonPadEnum.PICKUP) && isGrabbingCargo){
                elevator.defaultState(); 
                isGrabbingCargo = false;
            }else if (gamepad.getButton(ButtonPadEnum.SCORE_MIDDLE)){
                //THIS IS actually cargo ship place
                elevator.moveToCargoHP();
                isPlacingCargoMid = true;
                start = System.currentTimeMillis();
            }else if(!gamepad.getButton(ButtonPadEnum.SCORE_MIDDLE) && isPlacingCargoMid){
                cargoManipulator.cargoOut();
                // TODO make me a constant
                if(timeDiff > 750){
                    cargoManipulator.cargoStop();
                    elevator.defaultState();
                    isPlacingCargoMid = false;
                    start = 0;
                }
            }else if(gamepad.getButton(ButtonPadEnum.SCORE_HIGH)){
                //THIS is actually mid rocket
                elevator.moveToCargoMid();
                isPlacingCargoHigh = true;
                start =System.currentTimeMillis();
            }else if(!gamepad.getButton(ButtonPadEnum.SCORE_HIGH) && isPlacingCargoHigh){
                cargoManipulator.cargoOut();
                if(timeDiff > 750){
                    cargoManipulator.cargoStop();
                    elevator.defaultState();
                    isPlacingCargoHigh = false;
                    start = 0;
                }
            }else if(gamepad.getButton(ButtonPadEnum.CARGO_HP_INTAKE)){
                //this is intake from human player station intake
                elevator.moveToCargoHP();
                cargoManipulator.cargoIn();
                isPlacingCargoHP = true;
                start = System.currentTimeMillis();
            }else if(!gamepad.getButton(ButtonPadEnum.CARGO_HP_INTAKE) && isPlacingCargoHP){
                if(timeDiff > 750){
                    cargoManipulator.cargoStop();
                    elevator.defaultState();
                    isPlacingCargoHP = false;
                    start = 0;
                }
            }else if(gamepad.getButton(ButtonPadEnum.SCORE_LOW)){
                elevator.moveToCargoLow();
                isPlacingCargoLow = true;
                start = System.currentTimeMillis();
            }else if(!gamepad.getButton(ButtonPadEnum.SCORE_LOW) && isPlacingCargoLow){
                cargoManipulator.cargoOut();
                if(timeDiff > 750){
                    elevator.defaultState();
                    cargoManipulator.cargoStop();
                    isPlacingCargoLow = false;
                    start = 0;
                }
            }else {
                cargoManipulator.cargoHold();
                cargoManipulator.disableSecondaryRollers();
                isPlacingCargoHigh = false;
                isPlacingCargoMid = false;
                isPlacingCargoLow = false;
                isPlacingCargoHP = false;
                isGrabbingCargo = false;
            }
        } else if (!gamepad.getButton(ButtonPadEnum.GAMEPIECE_SWITCH) && !this.gamepad.getButton(ButtonPadEnum.ENABLE_MANUAL)) {
            if(gamepad.getButton(ButtonPadEnum.PICKUP)){
                elevator.moveToHatchLow();
                hatchManipulator.prepGrab();
                isGrabbingHatch = true;
            }else if(!gamepad.getButton(ButtonPadEnum.PICKUP) && isGrabbingHatch){
                hatchManipulator.grabHatch();
                elevator.moveToHatchLow();                
                isGrabbingHatch = false;
            }else if (gamepad.getButton(ButtonPadEnum.SCORE_HIGH)){
                elevator.moveToHatchHigh();
                hatchManipulator.prepPlace();
                start = System.currentTimeMillis();
                isPlacingHatchHigh = true;
            }else if(!gamepad.getButton(ButtonPadEnum.SCORE_HIGH) && isPlacingHatchHigh){
                hatchManipulator.placeHatch();
                if (timeDiff > 750){
                    elevator.moveToHatchLow();
                    isPlacingHatchHigh = false;
                    start = 0;
                }
            }else if(gamepad.getButton(ButtonPadEnum.SCORE_MIDDLE)){
                elevator.moveToHatchMid();
                hatchManipulator.prepPlace();
                isPlacingHatchMid = true;
            }else if(!gamepad.getButton(ButtonPadEnum.SCORE_MIDDLE) && isPlacingHatchMid){
                hatchManipulator.placeHatch();
                elevator.moveToHatchLow();
                isPlacingHatchMid = false;
            }else if(gamepad.getButton(ButtonPadEnum.SCORE_LOW)){
                elevator.moveToHatchLow();
                hatchManipulator.prepPlace();
                isPlacingHatchLow = true;
            }else if(!gamepad.getButton(ButtonPadEnum.SCORE_LOW) && isPlacingHatchLow){
                hatchManipulator.placeHatch();
                elevator.moveToHatchLow();
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
