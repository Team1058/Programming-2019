package org.pvcpirates.frc2019.robot;

import org.pvcpirates.frc2019.robot.subsystems.*;

import javax.swing.JToggleButton.ToggleButtonModel;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardContainer;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import org.pvcpirates.frc2019.util.ShuffleBoardManager;
public class Hardware {
    ShuffleboardTab tab = Shuffleboard.getTab("Battery_Voltage");
    public static ShuffleBoardManager sbm = new ShuffleBoardManager();
    private static Hardware ourInstance;
    private PowerDistributionPanel pdp = new PowerDistributionPanel(0);
    public AHRS navx = new AHRS(SPI.Port.kMXP);
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
    
    public void reportbatteryvoltage(){
        double voltage = pdp.getVoltage();
        if (voltage >= 12.0){
            tab.add("The Battery is ready for competition.", voltage);
        }
            
        if (voltage >= 11.0 && voltage < 12.0){
            tab.add("Warning! The battery is low but useable.", voltage);

        }
        if (voltage < 11.0){
            tab.add("DO NOT USE THIS BATTERY! ", voltage);
            
            
        }
    }

}
