package org.pvcpirates.frc2019.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import org.pvcpirates.frc2019.util.RobotMap;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import org.pvcpirates.frc2019.util.ShuffleBoardManager;
import org.pvcpirates.frc2019.util.RobotMap.CANTalonIds;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import org.pvcpirates.frc2019.util.ShuffleBoardManager;
public class Drivetrain extends BaseSubsystem {

    
    public static ShuffleBoardManager sbm = new ShuffleBoardManager();
    ShuffleboardTab tab = Shuffleboard.getTab("TalonPIDValues");

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
    public void talonchecker(){
           double leftDrive1Volts = leftDrive1.getBusVoltage();
           double leftDrive2Volts = leftDrive2.getBusVoltage();
           double rightDrive1Volts = rightDrive1.getBusVoltage();
           double rightDrive2Volts = rightDrive2.getBusVoltage();
           ShuffleBoardManager.Test.add("Left Drive Talon1 Voltage", leftDrive1Volts);
           ShuffleBoardManager.Test.add("Left Drive Talon2 Voltage", leftDrive2Volts);
           ShuffleBoardManager.Test.add("Right Drive1 Voltage ", rightDrive1Volts);
           ShuffleBoardManager.Test.add("Right Drive2 Voltage",rightDrive2Volts);
           
    }
}
