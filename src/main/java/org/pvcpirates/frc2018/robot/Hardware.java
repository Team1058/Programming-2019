package org.pvcpirates.frc2019.robot;

import static org.pvcpirates.frc2019.util.RobotMap.Constants.ROBOT_TIMEOUT;

import org.pvcpirates.frc2019.util.RobotMap;

import com.ctre.phoenix.ParamEnum;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.interfaces.Accelerometer.Range;


public class Hardware {
    /*
     * Creates and managers hardware settings excluding PID
     * Changes between practice robot and competition robot
     * 
     */

    private static Hardware ourInstance;

    public final TalonSRX leftDrive1 = new TalonSRX(RobotMap.CANTalonIds.LEFT_DRIVE_1);
    public final TalonSRX rightDrive1 = new TalonSRX(RobotMap.CANTalonIds.RIGHT_DRIVE_1);
    public final TalonSRX leftDrive2 = new TalonSRX(RobotMap.CANTalonIds.LEFT_DRIVE_2);
    public final TalonSRX rightDrive2 = new TalonSRX(RobotMap.CANTalonIds.RIGHT_DRIVE_2);

    public final Compressor compressor = new Compressor(0);
    public AHRS navx = new AHRS(SPI.Port.kMXP);


    private Hardware() {
        compressor.setClosedLoopControl(true);
        navx.reset();

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

    public static void setPIDF(double p, double i, double d, double f, TalonSRX talonSRX) {
        talonSRX.config_kP(0, p, ROBOT_TIMEOUT);
        talonSRX.config_kI(0, i, ROBOT_TIMEOUT);
        talonSRX.config_kD(0, d, ROBOT_TIMEOUT);
        talonSRX.config_kD(0, f, ROBOT_TIMEOUT);
    }
	

	public static Hardware getInstance() {
		if (ourInstance == null) {
			ourInstance = new Hardware();
		}
		return ourInstance;
	}
}
