package org.pvcpirates.frc2019.robot;

import org.pvcpirates.frc2019.robot.subsystems.*;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;

public class Hardware {
 
    private static Hardware ourInstance;

    public Drivetrain drivetrain = new Drivetrain();
    public AHRS navx = new AHRS(SPI.Port.kMXP);

    private Hardware() {
        initializeHardware();
    }

    public void initializeHardware(){
        drivetrain.initialize();
        navx.reset();
        //CargoManinpulator.initialize();
        //HatchManipulator.initialize();
        //Elevator.initialize();
    }

	public static Hardware getInstance() {
		if (ourInstance == null) {
			ourInstance = new Hardware();
		}
		return ourInstance;
	}
}