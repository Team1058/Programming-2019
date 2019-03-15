package org.pvcpirates.frc2019;

import org.pvcpirates.frc2019.robot.Robot;
import org.pvcpirates.frc2019.state.DisabledState;
import org.pvcpirates.frc2019.state.TeleopState;

import edu.wpi.first.wpilibj.TimedRobot;

public class Scheduler extends TimedRobot {

	public static final Robot robot = Robot.getInstance();

	long start = 0;
	boolean fourBarOut = false;
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
	  robot.setState(new TeleopState());
		robot.state.init();
		
	}

	@Override
	public void autonomousPeriodic() {
		robot.state.exec();
		// This needs to happen so the four bars become detached from velcro tape
		// Matt I'm sorry if this triggers you but fight me
		if (!fourBarOut){
			if (start == 0){
				start = System.currentTimeMillis();
				robot.hardware.cargoManipulator.cargoOut();
			}else if (System.currentTimeMillis()-start >= 500 && System.currentTimeMillis() < 1000){
				robot.hardware.cargoManipulator.cargoIn();
			}else if(System.currentTimeMillis() - start >= 1000){
				robot.hardware.cargoManipulator.cargoStop();
				fourBarOut = true;
			}
		}
		
	  
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
