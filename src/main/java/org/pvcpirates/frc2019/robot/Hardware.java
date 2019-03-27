package org.pvcpirates.frc2019.robot;

import org.pvcpirates.frc2019.robot.subsystems.*;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.SPI;

public class Hardware {
 
    private static Hardware ourInstance;

    public AHRS navx = new AHRS(SPI.Port.kMXP);
    public final Limelight limelight = new Limelight();
    public Drivetrain drivetrain = new Drivetrain();
    public CargoManipulator cargoManipulator = new CargoManipulator();
    public HatchManipulator hatchManipulator = new HatchManipulator();
    public Elevator elevator = new Elevator();
    public Flipper flipper = new Flipper();


    private Hardware() {
        
        initializeHardware();
    }

    public void initializeHardware(){
        //UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
    	//camera.setFPS(20);
    	//camera.setResolution(128, 96);
        navx.reset();
        limelight.initialize();
        drivetrain.initialize();
        cargoManipulator.initialize();
        hatchManipulator.initialize();
        elevator.initialize();
        flipper.initialize();       
    }

    public void defaultAll(){
        limelight.defaultState();
        drivetrain.defaultState();
        cargoManipulator.defaultState();
        hatchManipulator.defaultState();
        elevator.defaultState();
        flipper.defaultState();
    }

    public void printAllHardwareSensorValues(){
        System.out.println("Drivetrain R:"+drivetrain.rightDrive1.getSensorCollection().getQuadraturePosition()+" L:"+drivetrain.rightDrive1.getSensorCollection().getQuadraturePosition());
        System.out.println("Cargo Manipulator photosensor: "+cargoManipulator.cargoPhotoSensor.get());
        System.out.println("Elevator encoder: "+elevator.elevatorEncoder.getPosition());
        System.out.println("Four Bar encoder: "+elevator.fourBarTalon.getSensorCollection().getQuadraturePosition());
        System.out.println("Flipper: "+flipper.flipperTalonMain.getSensorCollection().getAnalogIn());
        System.out.println("Elevator Pos: "+Hardware.getInstance().elevator.elevatorEncoder.getPosition());
    }

	public static Hardware getInstance() {
		if (ourInstance == null) {
			ourInstance = new Hardware();
		}
		return ourInstance;
	}
}