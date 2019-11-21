package org.pvcpirates.frc2019.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;

import org.pvcpirates.frc2019.gamepads.BaseGamepad;
import org.pvcpirates.frc2019.gamepads.DriverGamepad;
import org.pvcpirates.frc2019.gamepads.GamepadEnum;
import org.pvcpirates.frc2019.robot.Hardware;
import org.pvcpirates.frc2019.robot.subsystems.Drivetrain;
import org.pvcpirates.frc2019.robot.subsystems.Flipper;
import org.pvcpirates.frc2019.util.*;
import org.pvcpirates.frc2019.Status;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;

public class TeleopDriveCommand extends TeleopCommand {
  final double green = .75;
  final double red = .61;
  final double yellow = .67;
  final double blue = .87;
  final double deadBand = DriverGamepad.driverStickDeadband;

  QuickAutoAssist qaa; 
  boolean qaaActive = false;
    public TeleopDriveCommand(BaseGamepad gp) {
      super(gp);
    }

    @Override
    public void exec(){

      rumbleIfSeeTarget();
      Hardware.getInstance().limelight.driverMode(true);
        if (!gamepad.getButton(GamepadEnum.X_BUTTON) && (Math.abs(this.gamepad.getAxis(GamepadEnum.LEFT_STICK_Y)) > deadBand||
            Math.abs(this.gamepad.getAxis(GamepadEnum.RIGHT_STICK_X)) > deadBand)){
            qaaActive = false;
                
            double percentOfTotalSpeed = 1;

            String flipperShuffleBoard = ShuffleBoardManager.flipperPositionChooser.getSelected();

            if (!flipperShuffleBoard.equals(ShuffleBoardManager.fpDefaultString)){
              percentOfTotalSpeed = .3;
            }else if (this.gamepad.getButton(GamepadEnum.LEFT_BUMPER) == true){
              percentOfTotalSpeed = .5;
            }else if (this.gamepad.getButton(GamepadEnum.RIGHT_BUMPER) == true){
              percentOfTotalSpeed = .25;
            }

            // we're seting the doubles for the blinkin colors <3
            

            // variables for joystick axis
            double leftJoyYAxis = -this.gamepad.getAxis(GamepadEnum.LEFT_STICK_Y);
            double rightJoyXAxis = -this.gamepad.getAxis(GamepadEnum.RIGHT_STICK_X);

            // prints out right joystick data
            // System.out.println("Left: "+ leftJoyYAxis);
            System.out.println("Right" + rightJoyXAxis);

            // blinks red for backwards, green for forwards, yellow for stop
            if (leftJoyYAxis < -deadBand) {
              hardware.blinkin.set(red);
            } else if (leftJoyYAxis > deadBand) {
              hardware.blinkin.set(green);
            } else {
              hardware.blinkin.set(blue);
            }




            /* 10 is maximum speed, multiplies by the subtracted/sum of both joysticks to get correct speed
            *  So it doesn't do either turn or drive straight, multiplied by how much of the speed gotton before
            *  should be used */
            double leftDriveSpeed = Drivetrain.FeetPerSecondToTalonVelocity(10 * (leftJoyYAxis - rightJoyXAxis) * percentOfTotalSpeed);
            double rightDriveSpeed = Drivetrain.FeetPerSecondToTalonVelocity(10 * (leftJoyYAxis + rightJoyXAxis) * percentOfTotalSpeed);
            hardware.drivetrain.setDrive(ControlMode.Velocity, leftDriveSpeed, rightDriveSpeed);
            if(hardware.flipper.flipperTalonMain.getSensorCollection().getAnalogIn() < Flipper.defaultPosConstant-100 || hardware.flipper.flipperTalonMain.getSensorCollection().getAnalogIn() > Flipper.defaultPosConstant+100){
              hardware.flipper.miniWheelRotate(leftJoyYAxis);
            }else{
              hardware.flipper.miniWheelRotate(0);
            }
            
             // Update shuffleboard to reflect real time input values
            updateDriveBaseShufleBoardEntries(leftJoyYAxis,rightJoyXAxis,leftDriveSpeed,rightDriveSpeed);
        }else if(!gamepad.getButton(GamepadEnum.X_BUTTON)){
            // 0,0 because if nothing is pressed nothing should be moving
            hardware.drivetrain.setDrive(ControlMode.PercentOutput, 0, 0);
            hardware.flipper.miniWheelRotate(0);
            qaaActive = false;
            Hardware.getInstance().limelight.driverMode(true);
            hardware.blinkin.set(yellow);
        }else if (this.gamepad.getButton(GamepadEnum.X_BUTTON)){
          //System.out.println("X pressed"+qaaActive);
          Hardware.getInstance().limelight.driverMode(false);
          if (!qaaActive){
              qaa = new QuickAutoAssist(this.gamepad);
              qaaActive = true;
              //System.out.println("create object");
          }
          if (qaa.getStatus() == Status.INIT){
              qaa.init();
              //System.out.println("init");
          }else if(qaa.getStatus() == Status.EXEC){
              qaa.exec();
              //System.out.println("Exec");
          }
      }
    }

    private void updateDriveBaseShufleBoardEntries(double lJoy, double rJoy, double lSpeed, double rSpeed){
      ShuffleBoardManager.leftJoyYaxisEntry.setDouble(lJoy);
      ShuffleBoardManager.rightJoyYaxisEntry.setDouble(rJoy);
      ShuffleBoardManager.leftDriveSpeedEntry.setDouble(lSpeed);
      ShuffleBoardManager.rightDriveSpeedEntry.setDouble(rSpeed);
    }

    private void rumbleIfSeeTarget(){

      if (hardware.limelight.hasTarget() == true){
        gamepad.setRumble(RumbleType.kLeftRumble, .5);
        gamepad.setRumble(RumbleType.kRightRumble, .5);
      }else {
          gamepad.setRumble(RumbleType.kLeftRumble, 0);
          gamepad.setRumble(RumbleType.kRightRumble, 0);
      }
    }

    
}