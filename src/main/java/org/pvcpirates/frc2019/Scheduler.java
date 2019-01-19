package org.pvcpirates.frc2019;

import org.pvcpirates.frc2019.robot.Hardware;
import org.pvcpirates.frc2019.robot.Robot;
import org.pvcpirates.frc2019.state.AutoState;
import org.pvcpirates.frc2019.state.TeleopState;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.IterativeRobotBase;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.RobotBase;


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

}