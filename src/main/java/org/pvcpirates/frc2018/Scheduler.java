package org.pvcpirates.frc2019;

import org.pvcpirates.frc2019.commands.Command;
import org.pvcpirates.frc2019.commands.DriveFor;
import org.pvcpirates.frc2019.robot.Hardware;
import org.pvcpirates.frc2019.robot.Robot;
import org.pvcpirates.frc2019.state.AutoState;
import org.pvcpirates.frc2019.state.TeleopState;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Scheduler extends IterativeRobot {

	public static final Robot robot = Robot.getInstance();
	public static SendableChooser<Command> autoChooser = new SendableChooser<>();
	Command c;

	@Override
	public void robotInit() {

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