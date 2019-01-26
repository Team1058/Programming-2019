package org.pvcpirates.frc2019.teleop;

import com.ctre.phoenix.motorcontrol.ControlMode;

import org.pvcpirates.frc2019.gamepads.BaseGamepad;
import org.pvcpirates.frc2019.gamepads.DriverGamepad;
import org.pvcpirates.frc2019.gamepads.GamepadEnum;
import org.pvcpirates.frc2019.robot.subsystems.Drivetrain;
import org.pvcpirates.frc2019.util.*;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TeleopDriveCommand extends TeleopCommand {
    
    public TeleopDriveCommand(BaseGamepad gp) {
      super(gp);
    }

    @Override
    public void exec(){

        if (Math.abs(this.gamepad.getAxis(GamepadEnum.LEFT_STICK_Y)) > Math.abs(DriverGamepad.driverStickDeadband) ||
            Math.abs(this.gamepad.getAxis(GamepadEnum.RIGHT_STICK_X)) > Math.abs(DriverGamepad.driverStickDeadband)){
                
            double percentOfTotalSpeed = 1;
                    
            if (this.gamepad.getButton(GamepadEnum.LEFT_BUMPER) == true){
              percentOfTotalSpeed = .5;
            }else if (this.gamepad.getButton(GamepadEnum.RIGHT_BUMPER) == true){
              percentOfTotalSpeed = .25;
            }

            double leftJoyYAxis = -this.gamepad.getAxis(GamepadEnum.LEFT_STICK_Y);
            double rightJoyXAxis = -this.gamepad.getAxis(GamepadEnum.RIGHT_STICK_X);

            /* 10 is maximum speed, multiplies by the subtracted/sum of both joysticks to get correct speed
            *  So it doesn't do either turn or drive straight, multiplied by how much of the speed gotton before
            *  should be used */
            double leftDriveSpeed = Drivetrain.FeetPerSecondToTalonVelocity(10 * (leftJoyYAxis - rightJoyXAxis) * percentOfTotalSpeed);
            double rightDriveSpeed = Drivetrain.FeetPerSecondToTalonVelocity(10 * (leftJoyYAxis + rightJoyXAxis) * percentOfTotalSpeed);
            SmartDashboard.putNumber("leftDriveSpeed", leftDriveSpeed);
            SmartDashboard.putNumber("rightDriveSpeed", rightDriveSpeed);
            SmartDashboard.putNumber("leftJoyYAxis", leftJoyYAxis);
            SmartDashboard.putNumber("rightJoyXAxis", rightJoyXAxis);
            
            hardware.drivetrain.setDrive(ControlMode.Velocity, leftDriveSpeed, rightDriveSpeed);
        }else{
            // 0,0 because if nothing is pressed nothing should be moving
            hardware.drivetrain.setDrive(ControlMode.PercentOutput, 0, 0);
        }
    }


    
}