package org.pvcpirates.frc2019.commands;

import org.pvcpirates.frc2019.gamepads.BaseGamepad;
import org.pvcpirates.frc2019.gamepads.DriverGamepad;
import org.pvcpirates.frc2019.gamepads.GamepadEnum;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;

import org.pvcpirates.frc2019.robot.subsystems.*;
import org.pvcpirates.frc2019.util.ShuffleBoardManager;
import org.pvcpirates.frc2019.robot.*;

public class HatchManipulationCommand extends TeleopCommand {

    boolean isGrabbing = false;
    boolean isPlacing = false;
    private HatchManipulator hatchManipulator = Hardware.getInstance().hatchManipulator;
    public HatchManipulationCommand(BaseGamepad gp){
        super(gp);
    }

    @Override
    public void init(){

    }

    @Override
    public void exec(){
     
      /* while the b button is held the hatch slider is extended
      *  then when the button is released it extends the claws
      *  then retracts the slider back */
      
      if (this.gamepad.getButton(GamepadEnum.B_BUTTON) == true || ShuffleBoardManager.hatchClawPrepGEntry.getBoolean(false) == true){  
          hatchManipulator.prepGrab();
          isGrabbing = true;
      }else if(isGrabbing == true && (this.gamepad.getButton(GamepadEnum.B_BUTTON) == false || ShuffleBoardManager.hatchClawGrabEntry.getBoolean(false) == true)){
        ShuffleBoardManager.hatchClawPrepGEntry.setBoolean(false);  
        hatchManipulator.grabHatch();
      }
      
      /* while the X button is held the hatch slider is extended
      *  then when the button is released it retracts the claw
      *  then retracts the slider back*/
      
      if (this.gamepad.getButton(GamepadEnum.X_BUTTON) == true && isGrabbing == true){
        hatchManipulator.prepPlace();
        isPlacing = true;
        isGrabbing = false;
      }else if(this.gamepad.getButton(GamepadEnum.X_BUTTON) == false && isPlacing == true){
        hatchManipulator.placeHatch();
        isPlacing = false;
      }

       //if it isn't grabbing it goes to 'default' position
      if ((isGrabbing == false && isPlacing == false) || this.gamepad.getButton(GamepadEnum.BACK_BUTTON)){
        isGrabbing = false;   
        isPlacing = false;
        hatchManipulator.defaultPosition();
      }
    } 

    @Override
    public void finished(){

    }

}
