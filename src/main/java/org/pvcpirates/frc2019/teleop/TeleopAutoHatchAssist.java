
package org.pvcpirates.frc2019.teleop;

import com.ctre.phoenix.motorcontrol.ControlMode;

import org.pvcpirates.frc2019.Status;
import org.pvcpirates.frc2019.commands.AutoAssistHatchLow;
import org.pvcpirates.frc2019.gamepads.BaseGamepad;
import org.pvcpirates.frc2019.gamepads.DriverGamepad;
import org.pvcpirates.frc2019.gamepads.GamepadEnum;
import org.pvcpirates.frc2019.robot.Hardware;
import org.pvcpirates.frc2019.robot.subsystems.Drivetrain;
import org.pvcpirates.frc2019.util.*;


public class TeleopAutoHatchAssist extends TeleopCommand {
 
    AutoAssistHatchLow assist;
    public TeleopAutoHatchAssist(BaseGamepad gp) {
      super(gp);
      assist = new AutoAssistHatchLow();
    }
    boolean done = false;
    @Override
    public void exec(){
        if(gamepad.getButton(GamepadEnum.X_BUTTON)){
            if(assist.getStatus() == Status.INIT){
                System.out.println("assist teleop init");
                assist.init();
            }else if(assist.getStatus() == Status.EXEC){
                System.out.println("assist teleop exec");
                assist.exec();
            }else if (assist.getStatus() == Status.STOP && !done){
                System.out.println("assist teleop finished");
                assist.finished();
                done = true;
            }
        }else{

            //If someone lets go of the x button but the profile is NOT done then just stop the motion profile
            if (assist.getStatus() == Status.EXEC){
                assist.finished(); 
                assist.setStatus(Status.STOP);
                assist = new AutoAssistHatchLow();
                Hardware.getInstance().drivetrain.stopMotionProfile();
                System.out.println("assist teleop stop command");
                done = false;
            }
            //If it is done then finished has already been called and so just make a new command
            if(done == true){
                System.out.println("assist teleop new command");
                assist = new AutoAssistHatchLow();
                done = false;
                Hardware.getInstance().drivetrain.stopMotionProfile();
            }
            
        }

    }
}