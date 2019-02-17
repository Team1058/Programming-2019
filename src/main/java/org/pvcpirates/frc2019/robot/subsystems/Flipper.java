package org.pvcpirates.frc2019.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import org.pvcpirates.frc2019.robot.Robot;
import org.pvcpirates.frc2019.util.RobotMap;
import org.pvcpirates.frc2019.util.ShuffleBoardManager;

public class Flipper extends BaseSubsystem {

    //public final VictorSPX flipperMiniWheelVictor = new VictorSPX(RobotMap.CANTalonIds.FLIPPER_MINI_WHEEL);
    public final TalonSRX flipperTalonMain = new TalonSRX(RobotMap.CANTalonIds.FLIPPER_MAIN);
    public final TalonSRX flipperTalonFollower = new TalonSRX(RobotMap.CANTalonIds.FLIPPER_FOLLOWER);
    
    public static double defaultPosConstant = 600;
    public static double lvl2to3FrontConstant = 812;
    public static double lvl2to3BackConstant = 126;
    public static double lvl0to2FrontConstant = 783;
    public static double lvl0to2BackConstant = 234;

    public static double miniWheelBasePercentOutput = .25;

    public static double FLIPPER_F = 0;
    public static double FLIPPER_P = 40;
    public static double FLIPPER_I = 0;
    public static double FLIPPER_D = 0;

    public void initialize(){
        flipperTalonFollower.follow(flipperTalonMain);
        setPIDValuesFromShuffleboard();
        flipperTalonMain.configSelectedFeedbackSensor(FeedbackDevice.Analog, 0, 10);
        flipperTalonMain.setSensorPhase(false);
        flipperTalonMain.setInverted(false);
        flipperTalonFollower.setInverted(false);
        flipperTalonMain.configFeedbackNotContinuous(true, RobotMap.Constants.ROBOT_TIMEOUT);
        flipperTalonMain.configAllowableClosedloopError(0, 5, 10);
    }

    @Override
    public void defaultState() {
        defaultPosition();
    }

    public void setPIDValuesFromShuffleboard(){
        flipperTalonMain.config_kF(0, ShuffleBoardManager.flipper_FEntry.getDouble(FLIPPER_F));
        flipperTalonMain.config_kP(0, ShuffleBoardManager.flipper_PEntry.getDouble(FLIPPER_P));
        flipperTalonMain.config_kI(0, ShuffleBoardManager.flipper_IEntry.getDouble(FLIPPER_I));
        flipperTalonMain.config_kD(0, ShuffleBoardManager.flipper_DEntry.getDouble(FLIPPER_D));
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

    public void lvl0to2Front(){
        flipperRotate(ShuffleBoardManager.flipperlvl0To2FrontEntry.getDouble(lvl0to2FrontConstant));
    }

    public void lvl0to2Back(){
        flipperRotate(ShuffleBoardManager.flipperlvl0To2BackEntry.getDouble(lvl0to2BackConstant));
    }

    public void flipperRotate(double positionForFlipper){
        /* Talons need to have a control mode of position
        *  PID needs to be done including gear ratios
        *  DO NOT DO PERCENT OUTPUT*/
        flipperTalonMain.set(ControlMode.Position, positionForFlipper);
        System.out.println("Enc pos:" +positionForFlipper);
    }

    public void miniWheelRotate(double percentOfPercentOutput){
        //flipperMiniWheelVictor.set(ControlMode.PercentOutput, percentOfPercentOutput);
    }
}
