package org.pvcpirates.frc2019.commands;

import org.pvcpirates.frc2019.gamepads.BaseGamepad;
import org.pvcpirates.frc2019.gamepads.GamepadEnum;
import org.pvcpirates.frc2019.robot.Hardware;
import org.pvcpirates.frc2019.robot.subsystems.CargoManipulator;

public class CargoManipulationCommand extends TeleopCommand {

    private CargoManipulator cargoManipulator = Hardware.getInstance().cargoManipulator;
    private boolean hold = false;
    public CargoManipulationCommand(BaseGamepad gp){
        super(gp);
    }

    @Override
    public void init(){
    }

    @Override
    public void exec(){

      /* While the Y button is held it take in cargo unless
      *  A is pushed then it will spit it out
      *  if nothing is pressed the motors will do nothing */
      if (this.gamepad.getButton(GamepadEnum.Y_BUTTON) && !hold){
        cargoManipulator.cargoIn();
        hold = cargoManipulator.cargoPhotoSensor.get();
      }else if (this.gamepad.getButton(GamepadEnum.A_BUTTON)){
        cargoManipulator.cargoOut();
        hold = false;
      }else if(hold){
        cargoManipulator.cargoHold();
      }else{
        cargoManipulator.cargoStop();
      }

    }

    @Override
    public void finished(){
    }

}
