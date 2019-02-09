package org.pvcpirates.frc2019.robot.subsystems;


import com.ctre.phoenix.motion.MotionProfileStatus;
import com.ctre.phoenix.motion.SetValueMotionProfile;
import com.ctre.phoenix.motorcontrol.ControlMode;

import org.pvcpirates.frc2019.util.RobotMap;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import org.pvcpirates.frc2019.util.ShuffleBoardManager;
import org.pvcpirates.frc2019.util.RobotMap.RobotSpecs;

public class Drivetrain extends BaseSubsystem {

    // DRIVETRAIN PIDs;
    public static double DRIVE_PEAK_OUTPUT = .95;
    public static double DRIVE_F = .115;
    public static double DRIVE_P = .15;
    public static double DRIVE_I = 0;
    public static double DRIVE_D = 0;

    public final TalonSRX leftDrive1 = new TalonSRX(RobotMap.CANTalonIds.LEFT_DRIVE_1);
    public final TalonSRX rightDrive1 = new TalonSRX(RobotMap.CANTalonIds.RIGHT_DRIVE_1);
    public final TalonSRX leftDrive2 = new TalonSRX(RobotMap.CANTalonIds.LEFT_DRIVE_2);
    public final TalonSRX rightDrive2 = new TalonSRX(RobotMap.CANTalonIds.RIGHT_DRIVE_2);

    public void initialize() {
        initializeDriveMotors();
        initializeSetDrivePIDValues();
    }

    private void initializeDriveMotors(){
        leftDrive1.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, RobotMap.Constants.ROBOT_TIMEOUT);
        leftDrive1.setSensorPhase(false);
        leftDrive2.setSensorPhase(false);

        rightDrive1.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, RobotMap.Constants.ROBOT_TIMEOUT);
        rightDrive1.setSensorPhase(false);
        rightDrive2.setSensorPhase(false);

        rightDrive1.setInverted(true);
        rightDrive2.setInverted(true);
        leftDrive1.setInverted(false);
        leftDrive2.setInverted(false);

        leftDrive1.setNeutralMode(NeutralMode.Brake);
        leftDrive2.setNeutralMode(NeutralMode.Brake);
        rightDrive1.setNeutralMode(NeutralMode.Brake);
        rightDrive2.setNeutralMode(NeutralMode.Brake);

        leftDrive1.getSensorCollection().setQuadraturePosition(0, RobotMap.Constants.ROBOT_TIMEOUT);
        rightDrive1.getSensorCollection().setQuadraturePosition(0, RobotMap.Constants.ROBOT_TIMEOUT);
        
        leftDrive1.clearMotionProfileTrajectories();
		rightDrive1.clearMotionProfileTrajectories();
        leftDrive1.clearMotionProfileHasUnderrun();
        rightDrive1.clearMotionProfileHasUnderrun();
        leftDrive1.clearStickyFaults();
        rightDrive1.clearStickyFaults();

        leftDrive2.follow(leftDrive1);
        rightDrive2.follow(rightDrive1);
    }

    public void initializeSetDrivePIDValues(){
        leftDrive1.configClosedLoopPeakOutput(0, DRIVE_PEAK_OUTPUT);
        rightDrive1.configClosedLoopPeakOutput(0, DRIVE_PEAK_OUTPUT);

        leftDrive1.config_kF(0, ShuffleBoardManager.fDriveEntry.getDouble(DRIVE_F));
        leftDrive1.config_kP(0, ShuffleBoardManager.pDriveEntry.getDouble(DRIVE_P));
        leftDrive1.config_kI(0, ShuffleBoardManager.iDriveEntry.getDouble(DRIVE_I));
        leftDrive1.config_kD(0, ShuffleBoardManager.dDriveEntry.getDouble(DRIVE_D));

        rightDrive1.config_kF(0, ShuffleBoardManager.fDriveEntry.getDouble(DRIVE_F));
        rightDrive1.config_kP(0, ShuffleBoardManager.pDriveEntry.getDouble(DRIVE_P));
        rightDrive1.config_kI(0, ShuffleBoardManager.iDriveEntry.getDouble(DRIVE_I));
        rightDrive1.config_kD(0, ShuffleBoardManager.dDriveEntry.getDouble(DRIVE_D));
    }

    public void stopAll() {
        // Shut everything off
        leftDrive1.set(ControlMode.PercentOutput, 0);
        rightDrive1.set(ControlMode.PercentOutput, 0);
    }

    public void setDrive(ControlMode controlMode, double left, double right) {
        leftDrive1.set(controlMode, left);
        rightDrive1.set(controlMode, right);
    }

    public void setPIDF(double p, double i, double d, double f) {
        leftDrive1.config_kP(0, p, RobotMap.Constants.ROBOT_TIMEOUT);
        leftDrive1.config_kI(0, i, RobotMap.Constants.ROBOT_TIMEOUT);
        leftDrive1.config_kD(0, d, RobotMap.Constants.ROBOT_TIMEOUT);
        leftDrive1.config_kF(0, f, RobotMap.Constants.ROBOT_TIMEOUT);

        rightDrive1.config_kP(0, p, RobotMap.Constants.ROBOT_TIMEOUT);
        rightDrive1.config_kI(0, i, RobotMap.Constants.ROBOT_TIMEOUT);
        rightDrive1.config_kD(0, d, RobotMap.Constants.ROBOT_TIMEOUT);
        rightDrive1.config_kF(0, f, RobotMap.Constants.ROBOT_TIMEOUT);
    }

    
    public void stopMotionProfile(){
            //This will clear the motion profile from the talons memory so it doesn't try and run the next time we switch to motionprofile mode
            rightDrive1.clearMotionProfileTrajectories();
            leftDrive1.clearMotionProfileTrajectories();
            //Reset the encoders just to be sure
            rightDrive1.getSensorCollection().setQuadraturePosition(0, RobotMap.Constants.ROBOT_TIMEOUT);
            leftDrive1.getSensorCollection().setQuadraturePosition(0, RobotMap.Constants.ROBOT_TIMEOUT);
            //Disable the motion profile
            leftDrive1.set(ControlMode.MotionProfile,  SetValueMotionProfile.Disable.value);
            rightDrive1.set(ControlMode.MotionProfile,  SetValueMotionProfile.Disable.value);
    }

    public static double TalonVelocityToFeetPerSecond(double talonVel){
        /*
         * 2 is from  2 * pi * r
         *  10 = convert from ticks/ms to ticks per second
         *  12 = convert from inches/second to feet/second
        */
        return ((((talonVel/RobotSpecs.ENC_ROTATIONS_PER_WHEEL_ROTATION)/RobotSpecs.ENC_TICKS_PER_ENC_ROTATION)*10.0)*2*RobotSpecs.WHEEL_RADIUS*Math.PI)/12;
      }
      public static double FeetPerSecondToTalonVelocity(double feetPerSec){
        return ((feetPerSec*12)/(2.0*RobotSpecs.WHEEL_RADIUS)/Math.PI)*RobotSpecs.ENC_TICKS_PER_ENC_ROTATION*RobotSpecs.ENC_ROTATIONS_PER_WHEEL_ROTATION/10.0;
      }

}
