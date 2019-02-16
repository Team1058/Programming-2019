package org.pvcpirates.frc2019.state;

import com.ctre.phoenix.motion.MotionProfileStatus;
import com.ctre.phoenix.motion.SetValueMotionProfile;
import com.ctre.phoenix.motorcontrol.ControlMode;

import org.pvcpirates.frc2019.commands.AutoAssistHatchLow;
import org.pvcpirates.frc2019.commands.DonutToHatchLowPlace;
import org.pvcpirates.frc2019.commands.FollowMotionProfile;
import org.pvcpirates.frc2019.commands.MotionProfileTEST;
import org.pvcpirates.frc2019.commands.MotionProfileTESTPARALLEL;
import org.pvcpirates.frc2019.commands.VisionMPDriveToDonut;
import org.pvcpirates.frc2019.robot.Hardware;
import org.pvcpirates.frc2019.robot.Robot;
import org.pvcpirates.frc2019.robot.subsystems.Flipper;
import org.pvcpirates.frc2019.util.RobotMap.Constants;
import org.pvcpirates.frc2019.util.RobotMap.RobotSpecs;
import org.pvcpirates.frc2019.robot.subsystems.Flipper;

import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Waypoint;

public class AutoState extends State {

	@Override
	public void init() {
		// This will all get called at the start of auto
		Hardware.getInstance().flipper.flipperTalonMain.set(ControlMode.PercentOutput, 0);
	}

	@Override
	public void exec() {
		// Code here will all get called periodically (every ms) in Auto

	}


	@Override
	public void stop() {
		// Code here will get called when auto state is stopped
		Hardware.getInstance().flipper.flipperTalonMain.set(ControlMode.PercentOutput, 0);
	}
}
