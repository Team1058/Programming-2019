package org.pvcpirates.frc2019.state;

import org.pvcpirates.frc2019.robot.Hardware;
import org.pvcpirates.frc2019.robot.Robot;

public class AutoState extends State {

	@Override
	public void init() {
		// This will all get called at the start of auto
		Hardware.getInstance().initializeHardware();
	}

	@Override
	public void exec() {
		// Code here will all get called periodically (every ms) in Auto
	}


	@Override
	public void stop() {
		// Code here will get called when auto state is stopped
		Robot.getInstance().hardware.drivetrain.leftDrive1.clearMotionProfileTrajectories();
		Robot.getInstance().hardware.drivetrain.rightDrive1.clearMotionProfileTrajectories();
        Robot.getInstance().hardware.drivetrain.leftDrive1.clearMotionProfileHasUnderrun();
        Robot.getInstance().hardware.drivetrain.rightDrive1.clearMotionProfileHasUnderrun();
	}
}
