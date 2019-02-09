package org.pvcpirates.frc2019.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import org.pvcpirates.frc2019.util.ShuffleBoardManager;

public class Flipper extends BaseSubsystem {

    public final VictorSPX flipperMiniWheelVictor = new VictorSPX(7);
    public final TalonSRX flipperTalonMain = new TalonSRX(5);
    public final TalonSRX flipperTalonFollower = new TalonSRX(6);
    
    public static double defaultPosConstant = 0;
    public static double lvl2to3FrontConstant = -100;
    public static double lvl2to3BackConstant = 90;
    public static double lvl1to2FrontConstant = -90;
    public static double lvl1to2BackConstant = 80;

    public void initialize(){
        flipperTalonFollower.follow(flipperTalonMain);
        flipperTalonMain.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder /* , pidIdx , timeout ms */);
        flipperTalonFollower.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder /* , pidIdx , timeout ms */);
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

    public void flipperRotate(Double positionForFlipper){
        /* Talons need to have a control mode of position
        *  PID needs to be done including gear ratios
        *  DO NOT DO PERCENT OUTPUT*/
        flipperTalonMain.set(ControlMode.Position, positionForFlipper);
    }

    public void miniWheelRotate(Double percentOfPercentOutput){
        flipperMiniWheelVictor.set(ControlMode.PercentOutput, percentOfPercentOutput);
    }
}
