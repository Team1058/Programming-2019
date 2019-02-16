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

    public static double FLIPPER_F = .115;
    public static double FLIPPER_P = .15;
    public static double FLIPPER_I = 0;
    public static double FLIPPER_D = 0;

    public void initialize(){
        flipperTalonFollower.follow(flipperTalonMain);
        flipperTalonMain.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
        flipperTalonFollower.setInverted(false);
        flipperTalonMain.configFeedbackNotContinuous(true, RobotMap.Constants.ROBOT_TIMEOUT);
    }

    @Override
    public void defaultState() {
        defaultPosition();
    }

    public void setPIDValuesFromShuffleboard(){
        flipperTalonMain.config_kF(0, ShuffleBoardManager.fFlipperEntry.getDouble(FLIPPER_F));
        flipperTalonMain.config_kP(0, ShuffleBoardManager.pFlipperEntry.getDouble(FLIPPER_P));
        flipperTalonMain.config_kI(0, ShuffleBoardManager.iFlipperEntry.getDouble(FLIPPER_I));
        flipperTalonMain.config_kD(0, ShuffleBoardManager.dFlipperEntry.getDouble(FLIPPER_D));
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
        flipperTalonMain.set(ControlMode.Position, positionForFlipper*RobotMap.RobotSpecs.FLIPPER_GEAR_RATIO);
    }

    public void miniWheelRotate(int percentOfPercentOutput){
        flipperMiniWheelVictor.set(ControlMode.PercentOutput, percentOfPercentOutput);
    }
}
