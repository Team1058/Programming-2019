package org.pvcpirates.frc2019.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;

import org.pvcpirates.frc2019.robot.Hardware;
import org.pvcpirates.frc2019.util.RobotMap;

public class Drivetrain extends BaseSubsystem {
    public final TalonSRX leftDrive1 = new TalonSRX(RobotMap.CANTalonIds.LEFT_DRIVE_1);
    public final TalonSRX rightDrive1 = new TalonSRX(RobotMap.CANTalonIds.RIGHT_DRIVE_1);
    public final TalonSRX leftDrive2 = new TalonSRX(RobotMap.CANTalonIds.LEFT_DRIVE_2);
    public final TalonSRX rightDrive2 = new TalonSRX(RobotMap.CANTalonIds.RIGHT_DRIVE_2);


    public void initialize() {
        leftDrive1.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, RobotMap.Constants.ROBOT_TIMEOUT);
        leftDrive1.setSensorPhase(false);

        rightDrive1.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, RobotMap.Constants.ROBOT_TIMEOUT);
        rightDrive1.setSensorPhase(false);
        rightDrive2.setSensorPhase(false);


        rightDrive1.setInverted(true);
        rightDrive2.setInverted(true);

        leftDrive1.setInverted(true);
        leftDrive2.setInverted(true);


        leftDrive1.getSensorCollection().setQuadraturePosition(0, ROBOT_TIMEOUT);
        rightDrive1.getSensorCollection().setQuadraturePosition(0, ROBOT_TIMEOUT);


        leftDrive2.follow(leftDrive1);
        rightDrive2.follow(rightDrive1);
    }

    public static void stopAll() {
        // Shut everything off
        hardware.leftDrive1.set(ControlMode.PercentOutput, 0);
        hardware.rightDrive1.set(ControlMode.PercentOutput, 0);
    }

    public static void setDrive(ControlMode controlMode, double left, double right) {
        hardware.leftDrive1.set(controlMode, left);
        hardware.rightDrive1.set(controlMode, right);
    }

    public static void setPIDF(double p, double i, double d, double f) {
        hardware.leftDrive1.config_kP(0, p, RobotMap.Constants.ROBOT_TIMEOUT);
        hardware.leftDrive1.config_kI(0, i, RobotMap.Constants.ROBOT_TIMEOUT);
        hardware.leftDrive1.config_kD(0, d, RobotMap.Constants.ROBOT_TIMEOUT);
        hardware.leftDrive1.config_kF(0, f, RobotMap.Constants.ROBOT_TIMEOUT);

        hardware.rightDrive1.config_kP(0, p, RobotMap.Constants.ROBOT_TIMEOUT);
        hardware.rightDrive1.config_kI(0, i, RobotMap.Constants.ROBOT_TIMEOUT);
        hardware.rightDrive1.config_kD(0, d, RobotMap.Constants.ROBOT_TIMEOUT);
        hardware.rightDrive1.config_kF(0, f, RobotMap.Constants.ROBOT_TIMEOUT);
    }


}
