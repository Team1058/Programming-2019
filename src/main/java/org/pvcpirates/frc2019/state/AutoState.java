package org.pvcpirates.frc2019.state;

import com.ctre.phoenix.motion.MotionProfileStatus;
import com.ctre.phoenix.motion.SetValueMotionProfile;

import org.pvcpirates.frc2019.commands.MotionProfileTEST;
import org.pvcpirates.frc2019.commands.MotionProfileTESTPARALLEL;
import org.pvcpirates.frc2019.robot.Hardware;
import org.pvcpirates.frc2019.robot.Robot;

public class AutoState extends State {

	MotionProfileTESTPARALLEL test;
	long startTime;
	long endTime;
	@Override
	public void init() {
		// This will all get called at the start of auto
		Hardware.getInstance().navx.reset();
		System.out.println("Auto Init!");
		test = new MotionProfileTESTPARALLEL();
		startTime = System.currentTimeMillis();
		test.init();
		
		
	}


	MotionProfileStatus mpStatus = new MotionProfileStatus();
	@Override
	public void exec() {
		// Code here will all get called periodically (every ms) in Auto
		test.exec();
		Robot.getInstance().hardware.drivetrain.leftDrive1.getMotionProfileStatus(mpStatus);
		System.out.println(mpStatus.outputEnable.name());
		if (mpStatus.outputEnable == SetValueMotionProfile.Enable){
			endTime = System.currentTimeMillis();
			System.out.println("That took " + (endTime - startTime) + " milliseconds");
		}
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
