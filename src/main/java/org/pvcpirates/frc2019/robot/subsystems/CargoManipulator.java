package org.pvcpirates.frc2019.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import org.pvcpirates.frc2019.util.RobotMap;
import org.pvcpirates.frc2019.util.RobotMap.Constants;
import org.pvcpirates.frc2019.util.RobotMap.DIO;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;


public class CargoManipulator extends BaseSubsystem {

    public final VictorSPX cargoVictor = new VictorSPX(RobotMap.CANTalonIds.CARGO_VICTOR);
    public final DigitalInput cargoPhotoSensor = new DigitalInput(DIO.CARGO_PHOTO_SENSOR);

    public void initialize(){
        cargoVictor.setInverted(true);
        cargoHold();
    }

    @Override
    public void defaultState() {
        cargoStop();
    }
    
    @Override
    void setConstantsFromShuffleboard() {
        
    }

    public void cargoIn(){
        cargoVictor.set(ControlMode.PercentOutput, -1);
    }

    public void cargoOut(){
        cargoVictor.set(ControlMode.PercentOutput, 1);
    }

    public void cargoHold(){
        cargoVictor.set(ControlMode.PercentOutput, -.3);
    }

    public void cargoStop(){
        cargoVictor.set(ControlMode.PercentOutput, -.3);
    }
}
