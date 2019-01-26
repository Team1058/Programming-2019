package org.pvcpirates.frc2019.robot.subsystems;


import com.ctre.phoenix.motorcontrol.ControlMode;

import org.pvcpirates.frc2019.robot.Hardware;
import org.pvcpirates.frc2019.util.RobotMap;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import org.pvcpirates.frc2019.util.ShuffleBoardManager;

public class Drivetrain extends BaseSubsystem {
    private ShuffleBoardManager m_teamSB_SuffleBoardManeger = new ShuffleBoardManager();
    // This Is For The PID Numbers;
    public static double LEFT_PEAK_OUTPUT = .95;
    public static double RIGHT_PEAK_OUTPUT = .95;
    public static double LEFTDRIVE_F = .104;
    public static double LEFTDRIVE_P = 0.01;
    public static double LEFTDRIVE_I = 0;
    public static double LEFTDRIVE_D = 0;
    // RIGHT DRIVETRAIN;
    public static double RIGHTDRIVE_F = .115;
    public static double RIGHTDRIVE_P = 0.1;
    public static double RIGHTDRIVE_I = 0;
    public static double RIGHTDRIVE_D = 0;
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

        rightDrive1.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, RobotMap.Constants.ROBOT_TIMEOUT);
        rightDrive1.setSensorPhase(false);
        rightDrive2.setSensorPhase(false);


        rightDrive1.setInverted(true);
        rightDrive2.setInverted(true);
        leftDrive1.setInverted(true);
        leftDrive2.setInverted(true);

        leftDrive1.setNeutralMode(NeutralMode.Brake);
        leftDrive2.setNeutralMode(NeutralMode.Brake);
        rightDrive1.setNeutralMode(NeutralMode.Brake);
        rightDrive2.setNeutralMode(NeutralMode.Brake);

        leftDrive1.getSensorCollection().setQuadraturePosition(0, RobotMap.Constants.ROBOT_TIMEOUT);
        rightDrive1.getSensorCollection().setQuadraturePosition(0, RobotMap.Constants.ROBOT_TIMEOUT);

        leftDrive2.follow(leftDrive1);
        rightDrive2.follow(rightDrive1);
    }
    
    public void initializeDrivetrainPIDGetShuffle(){
     // LeftDrive PID Values;
      ShuffleBoardManager.pidTab.add("leftdrive_F", LEFTDRIVE_F);
      ShuffleBoardManager.pidTab.add("leftdrive_P",LEFTDRIVE_P);
      ShuffleBoardManager.pidTab.add("leftdrive_I", LEFTDRIVE_I);
      ShuffleBoardManager.pidTab.add("leftdrive_D", LEFTDRIVE_D);
     // RightDrive PID Values
      ShuffleBoardManager.pidTab.add("rightdrive_F", RIGHTDRIVE_F);
      ShuffleBoardManager.pidTab.add("rightdrive_P", RIGHTDRIVE_P);
      ShuffleBoardManager.pidTab.add("rightdrive_I",RIGHTDRIVE_I);
      ShuffleBoardManager.pidTab.add("rightdrive_D", RIGHTDRIVE_D);

      ShuffleBoardManager.pidTab.add("leftdrive_loopoutput", LEFT_PEAK_OUTPUT);
      ShuffleBoardManager.pidTab.add("rightdrive_loopoutput", RIGHT_PEAK_OUTPUT);  
    }

    public void initializeSetDrivePIDValues(){
        initializeDrivetrainPIDGetShuffle();
        leftDrive1.configClosedLoopPeakOutput(0, LEFT_PEAK_OUTPUT);
        rightDrive1.configClosedLoopPeakOutput(0, RIGHT_PEAK_OUTPUT);

        leftDrive1.config_kF(0, SmartDashboard.getNumber("leftdrive_F", LEFTDRIVE_F));
        leftDrive1.config_kP(0, SmartDashboard.getNumber("leftdrive_P",LEFTDRIVE_P));
        leftDrive1.config_kI(0, SmartDashboard.getNumber("leftdrive_I", LEFTDRIVE_I));
        leftDrive1.config_kD(0, SmartDashboard.getNumber("leftdrive_D", LEFTDRIVE_D));
        rightDrive1.config_kF(0, SmartDashboard.getNumber("rightdrive_F", RIGHTDRIVE_F));
        rightDrive1.config_kP(0, SmartDashboard.getNumber("rightdrive_P", RIGHTDRIVE_P));
        rightDrive1.config_kI(0, SmartDashboard.getNumber("rightdrive_I",RIGHTDRIVE_I));
        rightDrive1.config_kD(0, SmartDashboard.getNumber("rightdrive_D", RIGHTDRIVE_D));
        
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

}
