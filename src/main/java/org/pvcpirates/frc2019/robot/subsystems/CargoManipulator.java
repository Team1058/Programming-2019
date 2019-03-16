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
    public final DoubleSolenoid cargoSolenoid = new DoubleSolenoid(RobotMap.CANTalonIds.PCM, RobotMap.PCMIDS.doubleCargoSolenoidForwardPCM, RobotMap.PCMIDS.doubleCargoSolenoidReversePCM);
    public final DigitalInput cargoPhotoSensor = new DigitalInput(DIO.CARGO_PHOTO_SENSOR);

    public void initialize(){

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
        cargoVictor.set(ControlMode.PercentOutput, -.2);
    }

    public void enableSecondaryRollers(){
        cargoSolenoid.set(DoubleSolenoid.Value.kForward);
    }

    public void disableSecondaryRollers(){
        cargoSolenoid.set(DoubleSolenoid.Value.kReverse);
    }

    public void cargoStop(){
        cargoVictor.set(ControlMode.PercentOutput, 0);
    }
}
