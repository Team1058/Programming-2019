package org.pvcpirates.frc2019.state;

import org.pvcpirates.frc2019.commands.ZeroElevator;
import org.pvcpirates.frc2019.robot.Hardware;
import org.pvcpirates.frc2019.robot.Robot;

public class AutoState extends State {

	ZeroElevator cmd = new ZeroElevator();
	@Override
	public void init() {
		// This will all get called at the start of auto
		Hardware.getInstance().defaultAll();
	}

	@Override
	public void exec() {
		cmd.exec();
		// Code here will all get called periodically (every ms) in Auto
		
	}


	@Override
	public void stop() {
		// Code here will get called when auto state is stopped
	}
}
