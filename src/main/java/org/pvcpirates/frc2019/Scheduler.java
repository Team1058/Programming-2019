package org.pvcpirates.frc2019;

import org.pvcpirates.frc2019.robot.Robot;
import org.pvcpirates.frc2019.state.DisabledState;
import org.pvcpirates.frc2019.state.TeleopState;

import edu.wpi.first.wpilibj.TimedRobot;

public class Scheduler extends TimedRobot {

	public static final Robot robot = Robot.getInstance();
	private double start = 0;
	private boolean startHatch = false;
	@Override
	public void robotInit() {
	  robot.setState(new TeleopState());
	  robot.state.init();
	}

	@Override
	public void robotPeriodic() {
		if(Robot.DEBUG){
			robot.hardware.printAllHardwareSensorValues();
		}
	}

	@Override
	public void autonomousInit() {
		robot.hardware.hatchManipulator.hatchSliderOut();
		robot.hardware.hatchManipulator.grabHatch();
		start = System.currentTimeMillis();
		startHatch = false;
	  	robot.setState(new TeleopState());
		robot.state.init();
		robot.hardware.elevator.fourBarTalon.getSensorCollection().setQuadraturePosition(0, 10);
		robot.hardware.elevator.elevatorEncoder.setPosition(0);
		
	}

	@Override
	public void autonomousPeriodic() {
		if(System.currentTimeMillis() - start > 100 && !startHatch){
			startHatch = true;
			robot.hardware.hatchManipulator.hatchSliderIn();
		}
		robot.state.exec();
	}

	@Override
	public void teleopInit() {
	  robot.setState(new TeleopState());
	  robot.state.init();
	}

	@Override
	public void teleopPeriodic() {
		robot.state.exec();
		
	}

	@Override
	public void disabledInit() {
		robot.setState(new DisabledState());
		robot.state.init();
	}

	@Override
	public void disabledPeriodic() {
		robot.state.exec();
	}

}
