package org.pvcpirates.frc2019.commands;

import org.pvcpirates.frc2019.robot.Hardware;
import org.pvcpirates.frc2019.gamepads.BaseGamepad;
import org.pvcpirates.frc2019.gamepads.GamepadEnum;
import org.pvcpirates.frc2019.robot.subsystems.Elevator;
import org.pvcpirates.frc2019.util.ShuffleBoardManager;

public class ElevatorCommand extends TeleopCommand {

    private Elevator elevator = Hardware.getInstance().elevator;
    public ElevatorCommand(BaseGamepad gp){
        super(gp);
    }

    @Override
    public void init(){
    }

    @Override
    public void exec(){
        if(ShuffleBoardManager.elevatorPercentOutputEntry.getDouble(0)!=0){
            elevator.elevatorSparkMax.set(ShuffleBoardManager.elevatorPercentOutputEntry.getDouble(0));
        }
        //String shuffleBoardSelection = ShuffleBoardManager.elevatorPositionChooser.getSelected();
        /*
        if(shuffleBoardSelection.equals(ShuffleBoardManager.elevatorCargoLowString)){
            elevator.moveToCargoLow();
        }else if(shuffleBoardSelection.equals(ShuffleBoardManager.elevatorCargoMidString)){
            elevator.moveToCargoMid();
        }else if(shuffleBoardSelection.equals(ShuffleBoardManager.elevatorHatchLowString)){
            elevator.moveToHatchLow();
        }else if(shuffleBoardSelection.equals(ShuffleBoardManager.elevatorHatchMidString)){
            elevator.moveToHatchMid();
        }else if(shuffleBoardSelection.equals(ShuffleBoardManager.elevatorHatchHighString)){
            elevator.moveToHatchHigh();
        }else if(shuffleBoardSelection.equals(ShuffleBoardManager.elevatorDefaultString)){
            elevator.moveToDefault();
        }else if(shuffleBoardSelection.equals(ShuffleBoardManager.elevatorIntakeString)){
            elevator.moveToIntake();
        }else{
            elevator.moveToDefault();
        }
        */
        //Four bar is being pushed to the side until week 5 possibly....
        /*
        String shuffleBoardFourBarSelection = ShuffleBoardManager.fourBarPositionChooser.getSelected();

        if (shuffleBoardFourBarSelection.equals(ShuffleBoardManager.fourBarHighString)){
            elevator.moveFourBarToHigh();
        }else if (shuffleBoardFourBarSelection.equals(ShuffleBoardManager.fourBarMidString)){
            elevator.moveFourBarToMid();
        }else if (shuffleBoardFourBarSelection.equals(ShuffleBoardManager.fourBarLowString)){
            elevator.moveFourBarToLow();
        }else {
            elevator.moveFourBarToLow();
        }
        */
    }

    @Override
    public void finished(){
    }
}
