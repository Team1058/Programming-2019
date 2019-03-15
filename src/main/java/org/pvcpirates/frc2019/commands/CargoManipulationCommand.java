package org.pvcpirates.frc2019.commands;

import org.pvcpirates.frc2019.gamepads.BaseGamepad;
import org.pvcpirates.frc2019.gamepads.ButtonPadEnum;
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

      hold = !cargoManipulator.cargoPhotoSensor.get();
      
      if (this.gamepad.getButton(ButtonPadEnum.ENABLE_MANUAL) && this.gamepad.getButton(ButtonPadEnum.ROLLERS_IN) && !hold){
        cargoManipulator.cargoIn();
      }else if (this.gamepad.getButton(ButtonPadEnum.ENABLE_MANUAL) && this.gamepad.getButton(ButtonPadEnum.ROLLERS_OUT)){
        System.out.println("Rollers Out");
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
