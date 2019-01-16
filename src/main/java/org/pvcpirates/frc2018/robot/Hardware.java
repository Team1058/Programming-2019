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

    public final BaseSubsystem drivetrain = new Drivetrain();
    public AHRS navx = new AHRS(SPI.Port.kMXP);


    private Hardware() {
        drivetrain.initialize();
        navx.reset();
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
