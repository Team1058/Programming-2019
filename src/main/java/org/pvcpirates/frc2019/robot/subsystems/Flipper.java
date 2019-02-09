package org.pvcpirates.frc2019.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

public class Flipper extends BaseSubsystem {

    public final VictorSPX flipperMiniWheelVictor = new VictorSPX(7);
    public final TalonSRX flipperTalonMain = new TalonSRX(5);
    public final TalonSRX flipperTalonFollower = new TalonSRX(6);

    public void initialize(){

    }

    public void defaultPosition(){
        flipperRotate(0);
    }

    public void lvl2to3Front(){
        flipperRotate(-100);
    }

    public void lvl2to3Back(){
        flipperRotate(90);
    }

    public void lvl1to2Front(){
        flipperRotate(-90);
    }

    public void lvl1to2Back(){
        flipperRotate(80);
    }

    public void flipperRotate(int positionForFlipper){
        /* Talons need to have a control mode of position
        *  PID needs to be done including gear ratios
        *  DO NOT DO PERCENT OUTPUT*/
        flipperTalonMain.set(ControlMode.Position, positionForFlipper);
        flipperTalonFollower.set(ControlMode.Position, positionForFlipper);
    }

    public void miniWheelRotate(int percentOfPercentOutput){
        flipperMiniWheelVictor.set(ControlMode.PercentOutput, percentOfPercentOutput);
    }
}
