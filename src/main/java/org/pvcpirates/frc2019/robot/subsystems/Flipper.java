package org.pvcpirates.frc2019.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

public class Flipper extends BaseSubsystem {

    //public final VictorSPX flipperVictor = new VictorSPX();
    //public final TalonSRX flipperTalon1 = new TalonSRX();
    //public final TalonSRX flipperTalon2 = new TalonSRX();

    public void initialize(){

    }

    public void flipperRotate(int positionForFlipper){
        /* Talons need to have a control mode of position
        *  PID needs to be done including gear ratios
        *  DO NOT DO PERCENT OUTPUT*/
        //flipperTalon1.set();
        //flipperTalon2.set();
    }

    public void miniWheelRotate(int percentOfPercentOutput){
        //flipperVictor.set(ControlMode.PercentOutput, percentOfPercentOutput);
    }
}
