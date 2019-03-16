package org.pvcpirates.frc2019.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import org.pvcpirates.frc2019.util.RobotMap;

public class BuddyClimb extends BaseSubsystem{
    public final TalonSRX buddyClimbSRX = new TalonSRX(RobotMap.CANTalonIds.BUDDY_CLIMB_SPARKMAX);
    
    @Override
    public void initialize() {
        
    }
    @Override
    void setConstantsFromShuffleboard() {
        
    }
    public void moveProngs(double percentOutput){
        if(percentOutput > 0){
            buddyClimbSRX.set(ControlMode.PercentOutput,percentOutput);
        } else if(percentOutput < 0){
            buddyClimbSRX.set(ControlMode.PercentOutput,percentOutput * .5);
        } else {
            buddyClimbSRX.set(ControlMode.PercentOutput, 0);
        }
    }

    @Override
    public void defaultState() {
        buddyClimbSRX.set(ControlMode.PercentOutput, 0);
    }


}