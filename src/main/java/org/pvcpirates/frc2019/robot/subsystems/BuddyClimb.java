package org.pvcpirates.frc2019.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import org.pvcpirates.frc2019.util.RobotMap;

public class BuddyClimb extends BaseSubsystem{
    public final CANSparkMax buddyClimbSparkMax = new CANSparkMax(RobotMap.CANTalonIds.BUDDY_CLIMB_SPARKMAX,MotorType.kBrushless);
    //public final CANPIDController buddyClimbController = buddyClimbSparkMax.getPIDController();
    //public final CANEncoder buddyClimbEncoder = buddyClimbSparkMax.getEncoder();
    
    @Override
    void initialize() {
        
    }
    @Override
    void setConstantsFromShuffleboard() {
        
    }
    @Override
    void defaultState() {
        
    }


}