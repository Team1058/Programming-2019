package org.pvcpirates.frc2019.teleop;

import com.ctre.phoenix.motorcontrol.ControlMode;

import org.pvcpirates.frc2019.gamepads.BaseGamepad;
import org.pvcpirates.frc2019.gamepads.DriverGamepad;
import org.pvcpirates.frc2019.gamepads.GamepadEnum;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TeleopDriveCommand extends TeleopCommand {
    
    public TeleopDriveCommand(BaseGamepad gp) {
      super(gp);
    }

    @Override
    public void exec(){

        if (Math.abs(this.gamepad.getAxis(GamepadEnum.LEFT_STICK_Y)) > Math.abs(DriverGamepad.driverStickDeadband) ||
            Math.abs(this.gamepad.getAxis(GamepadEnum.RIGHT_STICK_X)) > Math.abs(DriverGamepad.driverStickDeadband)){
                
            double leftJoyYAxis = this.gamepad.getAxis(GamepadEnum.LEFT_STICK_Y);
            double rightJoyXAxis = -this.gamepad.getAxis(GamepadEnum.RIGHT_STICK_X);

            double leftDriveSpeed = -FeetPerSecondToTalonVelocity(10 * (leftJoyYAxis - rightJoyXAxis));
            double rightDriveSpeed = FeetPerSecondToTalonVelocity(10 * (leftJoyYAxis + rightJoyXAxis));

            SmartDashboard.putNumber("leftDriveSpeed", leftDriveSpeed);
            SmartDashboard.putNumber("rightDriveSpeed", rightDriveSpeed);
            SmartDashboard.putNumber("leftJoyYAxis", leftJoyYAxis);
            SmartDashboard.putNumber("rightJoyXAxis", rightJoyXAxis);

            //the first drive speed is negative so the left motor goes
            hardware.drivetrain.setDrive(ControlMode.Velocity, leftDriveSpeed, rightDriveSpeed);
        }else{
            hardware.drivetrain.setDrive(ControlMode.PercentOutput, 0, 0);
        }
    }


    public double TalonVelocityToFeetPerSecond(double talonVel){
        /* 11.25 = how many encoder rotations(not ticks) per 1 wheel rotation
         *  1024 = how many encoder ticks per one encoder rotation
         *  10 = convert from ticks/ms to ticks per second
         *  6 = wheel diameter, (2 * pi * radius)
         *  12 = convert from inches/second to feet/second
        */
        return ((((talonVel/11.25)/1024.0)*10.0)*6.0*Math.PI)/12;
      }
      public double FeetPerSecondToTalonVelocity(double feetPerSec){
        return ((feetPerSec*12)/6.0/Math.PI)*1024.0*11.25/10.0;
      }
}
