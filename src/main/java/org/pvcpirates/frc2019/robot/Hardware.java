package org.pvcpirates.frc2019.robot;

import org.pvcpirates.frc2019.robot.subsystems.*;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI;

public class Hardware {
 
    private static Hardware ourInstance;

    public final Drivetrain drivetrain = new Drivetrain();
    public AHRS navx = new AHRS(SPI.Port.kMXP);
    public final Limelight limelight = new Limelight();
    private Hardware() {
        drivetrain.initialize();
        navx.reset();
        limelight.initialize();
    }	

	public static Hardware getInstance() {
		if (ourInstance == null) {
			ourInstance = new Hardware();
		}
		return ourInstance;
	}
}
