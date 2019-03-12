package org.pvcpirates.frc2019.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import org.pvcpirates.frc2019.commands.ElevatorCommand;
import org.pvcpirates.frc2019.robot.Hardware;
import org.pvcpirates.frc2019.robot.Robot;
import org.pvcpirates.frc2019.util.RobotMap;
import org.pvcpirates.frc2019.util.ShuffleBoardManager;

public class Flipper extends BaseSubsystem {

    public final VictorSPX flipperMiniWheelVictor = new VictorSPX(RobotMap.CANTalonIds.FLIPPER_MINI_WHEEL);
    public final TalonSRX flipperTalonMain = new TalonSRX(RobotMap.CANTalonIds.FLIPPER_MAIN);
    public final TalonSRX flipperTalonFollower = new TalonSRX(RobotMap.CANTalonIds.FLIPPER_FOLLOWER);
    
    public static double defaultPosConstant = 570;
    public static double lvl2to3FrontConstant = defaultPosConstant + 212;
    public static double lvl2to3BackConstant = defaultPosConstant - 474;
    public static double lvl0to2FrontConstant = defaultPosConstant + 183;
    public static double lvl0to2BackConstant = defaultPosConstant - 366;

    public static double miniWheelBasePercentOutput = .25;

    public static double FLIPPER_F = 0;
    public static double FLIPPER_P = 30;
    public static double FLIPPER_I = 0;
    public static double FLIPPER_D = 0;

    public static double FLIPPER_P_STOWED = 6;
    public void initialize(){
        if(Robot.DEBUG){
            setConstantsFromShuffleboard();
        }
        flipperTalonFollower.follow(flipperTalonMain);
        setPIDValues();
        flipperTalonMain.configSelectedFeedbackSensor(FeedbackDevice.Analog, 0, 10);
        flipperTalonMain.setSensorPhase(false);
        flipperTalonMain.setInverted(false);
        flipperTalonFollower.setInverted(false);
        flipperTalonMain.configFeedbackNotContinuous(true, RobotMap.Constants.ROBOT_TIMEOUT);
        flipperTalonMain.configAllowableClosedloopError(0, 5, 10);
        flipperMiniWheelVictor.setInverted(true);
    }

    @Override
    public void defaultState() {
        defaultPosition();
    }

    @Override
    void setConstantsFromShuffleboard() {
        FLIPPER_F = ShuffleBoardManager.fFlipperEntry.getDouble(FLIPPER_F);
        FLIPPER_P = ShuffleBoardManager.pFlipperEntry.getDouble(FLIPPER_P);
        FLIPPER_I = ShuffleBoardManager.iFlipperEntry.getDouble(FLIPPER_I);
        FLIPPER_D = ShuffleBoardManager.dFlipperEntry.getDouble(FLIPPER_D);
        FLIPPER_P_STOWED = ShuffleBoardManager.pStowedFlipperEntry.getDouble(FLIPPER_P_STOWED);
        defaultPosConstant = ShuffleBoardManager.flipperDefaultPositionEntry.getDouble(defaultPosConstant);
        lvl2to3FrontConstant = ShuffleBoardManager.flipperlvl2To3FrontEntry.getDouble(lvl2to3FrontConstant);
        lvl2to3BackConstant = ShuffleBoardManager.flipperlvl2To3BackEntry.getDouble(lvl2to3BackConstant);
        lvl0to2FrontConstant = ShuffleBoardManager.flipperlvl0To2FrontEntry.getDouble(lvl0to2FrontConstant);
        lvl0to2BackConstant = ShuffleBoardManager.flipperlvl0To2BackEntry.getDouble(lvl0to2BackConstant);
    }
    public void setPIDValues(){
        flipperTalonMain.config_kF(0, FLIPPER_F);
        flipperTalonMain.config_kP(0, FLIPPER_P);
        flipperTalonMain.config_kI(0, FLIPPER_I);
        flipperTalonMain.config_kD(0, FLIPPER_D);
        flipperTalonMain.config_kP(1, FLIPPER_P_STOWED);
    }

    public void defaultPosition(){
        flipperTalonMain.selectProfileSlot(1,0);
        flipperRotate(defaultPosConstant);
    }

    public void lvl2to3Front(){
        flipperTalonMain.selectProfileSlot(0,0);
        flipperRotate(lvl2to3FrontConstant); 
    }

    public void lvl2to3Back(){
        flipperTalonMain.selectProfileSlot(0,0);
        flipperRotate(lvl2to3BackConstant);
    }

    public void lvl0to2Front(){
        flipperTalonMain.selectProfileSlot(0,0);
        flipperRotate(lvl0to2FrontConstant);
    }

    public void lvl0to2Back(){
        flipperTalonMain.selectProfileSlot(0,0);
        flipperRotate(lvl0to2BackConstant);
    }

    public void flipperRotate(double positionForFlipper){
       // if the elevator isn't in the ideal position for climbing (default) then it sets the position to a ideal position
       if (positionForFlipper != defaultPosConstant){
           Hardware.getInstance().elevator.moveToDefault();
       }
        /* Talons need to have a control mode of position
        *  PID needs to be done including gear ratios
        *  DO NOT DO PERCENT OUTPUT
        */
        flipperTalonMain.set(ControlMode.Position, positionForFlipper);
    }

    public void miniWheelRotate(double percentOfPercentOutput){
        flipperMiniWheelVictor.set(ControlMode.PercentOutput, percentOfPercentOutput);
    }
}
