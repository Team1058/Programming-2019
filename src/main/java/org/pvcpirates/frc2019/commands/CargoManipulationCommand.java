package org.pvcpirates.frc2019.commands;

import org.pvcpirates.frc2019.gamepads.BaseGamepad;
import org.pvcpirates.frc2019.gamepads.GamepadEnum;
import org.pvcpirates.frc2019.robot.Hardware;
import org.pvcpirates.frc2019.robot.subsystems.CargoManipulator;
import org.pvcpirates.frc2019.util.ShuffleBoardManager;

public class CargoManipulationCommand extends TeleopCommand {

    private CargoManipulator cargoManipulator = Hardware.getInstance().cargoManipulator;
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
      *  Right bumper is hold
      *  if nothing is pressed the motors will do nothing */
      if (this.gamepad.getButton(GamepadEnum.Y_BUTTON) || ShuffleBoardManager.cargoIntakeHigh.getBoolean(false)){
        cargoManipulator.cargoIn();
      }else if (this.gamepad.getButton(GamepadEnum.A_BUTTON) || ShuffleBoardManager.cargoIntakeRev.getBoolean(false)){
        cargoManipulator.cargoOut();
      }else if(this.gamepad.getButton(GamepadEnum.RIGHT_BUMPER) || ShuffleBoardManager.cargoIntakeLow.getBoolean(false)){ 
        cargoManipulator.cargoHold();
      }else{
        cargoManipulator.cargoStop();
      }

    }

    @Override
    public void finished(){
    }

}
