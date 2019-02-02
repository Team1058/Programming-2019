package org.pvcpirates.frc2019.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;


public class CargoManipulator extends BaseSubsystem {
    int cargoVictorDeviceId = 0;
    public final VictorSPX cargoVictor = new VictorSPX(cargoVictorDeviceId);
    
    public void initialize(){

    }

    public void cargoIn(){
        cargoVictor.set(ControlMode.PercentOutput, 1);
    }

    public void cargoOut(){
        cargoVictor.set(ControlMode.PercentOutput, 1);
    }

    public void cargoStop(){
        cargoVictor.set(ControlMode.PercentOutput, 0);
    }
}
