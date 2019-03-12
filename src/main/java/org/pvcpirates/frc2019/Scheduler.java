package org.pvcpirates.frc2019;

import org.pvcpirates.frc2019.robot.Robot;
import org.pvcpirates.frc2019.state.AutoState;
import org.pvcpirates.frc2019.state.DisabledState;
import org.pvcpirates.frc2019.state.TeleopState;

import edu.wpi.first.wpilibj.TimedRobot;

public class Scheduler extends TimedRobot {

	public static final Robot robot = Robot.getInstance();

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
	  robot.setState(new AutoState());
	  robot.state.init();
	}

	@Override
	public void autonomousPeriodic() {
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
