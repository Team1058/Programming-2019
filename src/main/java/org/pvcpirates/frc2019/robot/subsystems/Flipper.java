package org.pvcpirates.frc2019.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import org.pvcpirates.frc2019.robot.Robot;
import org.pvcpirates.frc2019.util.RobotMap;
import org.pvcpirates.frc2019.util.ShuffleBoardManager;

public class Flipper extends BaseSubsystem {

  public final VictorSPX flipperMiniWheelVictor = new VictorSPX(RobotMap.CANTalonIds.FLIPPER_MINI_WHEEL);
  public final TalonSRX flipperTalonMain = new TalonSRX(RobotMap.CANTalonIds.FLIPPER_MAIN);
  public final TalonSRX flipperTalonFollower = new TalonSRX(RobotMap.CANTalonIds.FLIPPER_FOLLOWER);

  public static double defaultPosConstant = 0;
  public static double lvl2to3FrontConstant = -100;
  public static double lvl2to3BackConstant = 90;
  public static double lvl1to2FrontConstant = -90;
  public static double lvl1to2BackConstant = 80;

  public void initialize(){
    flipperTalonFollower.follow(flipperTalonMain);
    flipperTalonMain.configSelectedFeedbackSensor(FeedbackDevice.Analog , 0 , RobotMap.Constants.ROBOT_TIMEOUT);
    flipperTalonMain.setSelectedSensorPosition(0);

    initializePIDValues();
  }

  public void initializePIDValues(){
    flipperTalonMain.config_kF(0, ShuffleBoardManager.flipper_F.getDouble(0));
    flipperTalonMain.config_kP(0, ShuffleBoardManager.flipper_P.getDouble(0));
    flipperTalonMain.config_kI(0, ShuffleBoardManager.flipper_I.getDouble(0));
    flipperTalonMain.config_kD(0, ShuffleBoardManager.flipper_D.getDouble(0));
  }

  public void defaultPosition(){
    flipperRotate(ShuffleBoardManager.flipperDefaultPositionEntry.getDouble(defaultPosConstant));
  }

  public void lvl2to3Front(){
    flipperRotate(ShuffleBoardManager.flipperlvl2To3FrontEntry.getDouble(lvl2to3FrontConstant));
  }

  public void lvl2to3Back(){
    flipperRotate(ShuffleBoardManager.flipperlvl2To3BackEntry.getDouble(lvl2to3BackConstant));
  }

  public void lvl1to2Front(){
    flipperRotate(ShuffleBoardManager.flipperlvl1To2FrontEntry.getDouble(lvl1to2FrontConstant));
  }

  public void lvl1to2Back(){
    flipperRotate(ShuffleBoardManager.flipperlvl1To2BackEntry.getDouble(lvl1to2BackConstant));
  }

  public void flipperRotate(double positionForFlipper){
    /* Talons need to have a control mode of position
    *  PID needs to be done including gear ratios
    *  DO NOT DO PERCENT OUTPUT*/
    flipperTalonMain.set(ControlMode.Position, positionForFlipper);
  }

  public void miniWheelRotate(int percentOfPercentOutput){
    flipperMiniWheelVictor.set(ControlMode.PercentOutput, percentOfPercentOutput);
  }
}
