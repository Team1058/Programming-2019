package org.pvcpirates.frc2019.robot;

import org.pvcpirates.frc2019.robot.subsystems.*;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;

public class Hardware {
 
    private static Hardware ourInstance;

    public AHRS navx = new AHRS(SPI.Port.kMXP);
    public final Limelight limelight = new Limelight();
    public Drivetrain drivetrain = new Drivetrain();
    public CargoManinpulator cargoManinpulator = new CargoManinpulator();
    public HatchManipulator hatchManipulator = new HatchManipulator();
    public Elevator elevator = new Elevator();
    public Flipper flipper = new Flipper();
    
    private Hardware() {
        initializeHardware();
    }

    public void initializeHardware(){
        navx.reset();
        limelight.initialize();
        drivetrain.initialize();
        cargoManinpulator.initialize();
        hatchManipulator.initialize();
        elevator.initialize();
        flipper.initialize();
       
    }

	public static Hardware getInstance() {
		if (ourInstance == null) {
			ourInstance = new Hardware();
		}
		return ourInstance;
	}
}