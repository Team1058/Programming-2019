package org.pvcpirates.frc2019.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import org.pvcpirates.frc2019.util.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;


public class CargoManipulator extends BaseSubsystem {

    public final VictorSPX cargoVictor = new VictorSPX(RobotMap.CANTalonIds.CARGO_VICTOR);
    public final DoubleSolenoid cargoSolenoid = new DoubleSolenoid(RobotMap.CANTalonIds.PCM, RobotMap.PCMIDS.doubleCargoSolenoidForwardPCM, RobotMap.PCMIDS.doubleCargoSolenoidReversePCM);

    public void initialize(){

    }

    @Override
    public void defaultState() {
        cargoStop();
    }

    public void cargoIn(){
        cargoVictor.set(ControlMode.PercentOutput, 1);
    }

    public void cargoOut(){
        cargoVictor.set(ControlMode.PercentOutput, -1);
    }

    //cargo solenoid: 
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
