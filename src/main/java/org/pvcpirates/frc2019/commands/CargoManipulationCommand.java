package org.pvcpirates.frc2019.commands;

import org.pvcpirates.frc2019.teleop.TeleopCommand;
import org.pvcpirates.frc2019.gamepads.BaseGamepad;
import org.pvcpirates.frc2019.gamepads.GamepadEnum;
import org.pvcpirates.frc2019.teleop.TeleopCommand;
import org.pvcpirates.frc2019.robot.Hardware;
import org.pvcpirates.frc2019.robot.subsystems.CargoManipulator;

public class CargoManipulationCommand extends TeleopCommand {

    private CargoManipulator cargoManipulator = Hardware.getInstance().cargoManipulator;
    public CargoManipulationCommand(BaseGamepad gp){
        super(gp);
    }

    public void init(){

    }

    public void exec(){

      if (this.gamepad.getButton(GamepadEnum.Y_BUTTON)){
        cargoManipulator.cargoIn();
      }else if (this.gamepad.getButton(GamepadEnum.A_BUTTON)){
        cargoManipulator.cargoOut();
      }else {
        cargoManipulator.cargoStop();
      }

    }

    public void finished(){

    }

}
