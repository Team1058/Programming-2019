package org.pvcpirates.frc2019.commands;

import org.pvcpirates.frc2019.gamepads.BaseGamepad;
import org.pvcpirates.frc2019.gamepads.ButtonPadEnum;
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
     
      if (this.gamepad.getButton(ButtonPadEnum.ENABLE_MANUAL) && this.gamepad.getButton(ButtonPadEnum.ROLLERS_IN)){
        cargoManipulator.cargoIn();
      }else if (this.gamepad.getButton(ButtonPadEnum.ENABLE_MANUAL) && this.gamepad.getButton(ButtonPadEnum.ROLLERS_OUT)){
        cargoManipulator.cargoOut();
      }else if(!this.gamepad.getButton(ButtonPadEnum.ENABLE_MANUAL)){
        //cargoManipulator.cargoHold();
      }else if(!this.gamepad.getButton(ButtonPadEnum.PICKUP)){
        cargoManipulator.cargoStop();
      }

    }

    @Override
    public void finished(){
    }

}
