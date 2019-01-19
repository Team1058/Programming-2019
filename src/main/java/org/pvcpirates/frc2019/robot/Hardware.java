package org.pvcpirates.frc2019.robot;

import org.pvcpirates.frc2019.util.RobotMap;
import org.pvcpirates.frc2019.robot.subsystems.*;

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

    public final Drivetrain drivetrain = new Drivetrain();
    public AHRS navx = new AHRS(SPI.Port.kMXP);


    private Hardware() {
        drivetrain.initialize();
        navx.reset();
    }	

	public static Hardware getInstance() {
		if (ourInstance == null) {
			ourInstance = new Hardware();
		}
		return ourInstance;
	}
}
