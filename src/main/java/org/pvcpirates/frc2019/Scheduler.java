package org.pvcpirates.frc2019;

import org.pvcpirates.frc2019.robot.*;
import org.pvcpirates.frc2019.state.AutoState;
import org.pvcpirates.frc2019.state.TeleopState;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.hal.PowerJNI;

public class Scheduler extends IterativeRobot {

	public static final Robot robot = Robot.getInstance();

	@Override
	public void robotInit() {
	  robot.setState(new TeleopState());
		robot.state.init();
	
	}

	@Override
	public void robotPeriodic() {
	  robot.state.exec();
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
	}

	@Override
	public void disabledPeriodic() {
	}

	@Override
	public void testInit() {
		Hardware.getInstance().reportbatteryvoltage();
	}

	@Override
	public void testPeriodic() {
	
	}
}
