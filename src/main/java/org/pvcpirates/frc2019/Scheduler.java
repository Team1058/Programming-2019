package org.pvcpirates.frc2019;

import org.pvcpirates.frc2019.robot.Robot;
import org.pvcpirates.frc2019.state.AutoState;
import org.pvcpirates.frc2019.state.TeleopState;
import org.pvcpirates.frc2019.util.ShuffleBoardManager;

import edu.wpi.first.wpilibj.IterativeRobot;

public class Scheduler extends IterativeRobot {

	public static final Robot robot = Robot.getInstance();

	@Override
	public void robotInit() {
<<<<<<< HEAD
=======
   	ShuffleBoardManager.initializeShuffleBoard();
>>>>>>> 67ff6b02674dcc613d999ed80aed094ade92b5de
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
