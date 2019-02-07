
package org.pvcpirates.frc2019.util;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.*;

import org.pvcpirates.frc2019.robot.Hardware;
import org.pvcpirates.frc2019.robot.Robot;
import org.pvcpirates.frc2019.robot.subsystems.Drivetrain;
import org.pvcpirates.frc2019.util.RobotMap.Constants;
import org.pvcpirates.frc2019.util.RobotMap.MotionProfiling;
import org.pvcpirates.frc2019.util.RobotMap.RobotSpecs;

import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import jaci.pathfinder.Trajectory.Segment;
import jaci.pathfinder.modifiers.TankModifier;

import com.ctre.phoenix.motion.*;

public class MotionProfileProcessor {

	private TankModifier path;
	//Status whether to enable or disable the motion profile
	SetValueMotionProfile status;
	
	private int currPoint = 0;
	private TalonSRX talonR;
	private TalonSRX talonL;
	/*
	  Create a seperate thread to process the points on the talon
	  It is _VERY_ iportant that this is in a seperate thread so it doesn't get held up by anything
	  Make sure this runs twice as fast as your time length on your points so the talons don't run out of points
	*/
	class MotionProfileBufferProcessThread implements java.lang.Runnable {
		public void run() {  
			//PROCESS the motion profile
			
			talonR.processMotionProfileBuffer();
			talonL.processMotionProfileBuffer();
		}
	}
	//Create a notifier to run the thread
	Notifier motionProfileBufferProcessThread = new Notifier(new MotionProfileBufferProcessThread());
	
	public MotionProfileProcessor(TankModifier path) {
		this.talonR = Hardware.getInstance().drivetrain.rightDrive1;
		this.talonL = Hardware.getInstance().drivetrain.leftDrive1;
		this.path = path;
		//Default this should be disabled until the motion profile is loaded into the talons
		status = SetValueMotionProfile.Disable;

		currPoint = 0;
		/*
		 * since our MP is 10ms per point, set the control frame rate and the
		 * notifer to half that, these units are in ms
		 */
		talonR.changeMotionControlFramePeriod(MotionProfiling.FRAME_PERIOD);
		talonL.changeMotionControlFramePeriod(MotionProfiling.FRAME_PERIOD);
		//Set the threads repeat time to 5ms also, this is in
		motionProfileBufferProcessThread.startPeriodic(MotionProfiling.FRAME_PERIOD_SECONDS);
	}

	public void reset() {
		/*
		 * Let's clear the buffer just in case user decided to disable in the
		 * middle of an MP, and now we have the second half of a profile just
		 * sitting in memory.
		 */
		Hardware.getInstance().drivetrain.stopMotionProfile();
		currPoint = 0;
		
	}

	//Load all the motion profile points into the talons' memory
	public void pushAllPoints() {
		currPoint = 0;
		reset();

		/* set the base trajectory period to zero, use the individual trajectory period below */
		talonR.configMotionProfileTrajectoryPeriod(0, 30);
		talonL.configMotionProfileTrajectoryPeriod(0, 30);
		
		for (int i = 0; i < path.getSourceTrajectory().length(); i++){
			pushPoint(true);
		}
		status = SetValueMotionProfile.Enable;
		
	}

	public void pushPoint(boolean waitForAllPointsLoaded){
		//Get the right and left segments (these are the same thing as the points)
		if (currPoint < path.getSourceTrajectory().length()){
			TrajectoryPoint pointL = new TrajectoryPoint();
			TrajectoryPoint pointR = new TrajectoryPoint();

			Segment left = path.getLeftTrajectory().get(currPoint);
			Segment right = path.getRightTrajectory().get(currPoint);
			//each segment we use position and velocity, which are in meters and meters per second respectively
			//if you want you can get the x and y position for graphing purposes, or the acceleration and jerk for debug/ testing purposes
			//	all of these being in meters or meters per second squared or meters per second cubed

			//Convert from position on the ground to feet to revolutions to encoder ticks
			//.3048 is how many meters to feet, * 12 goes from feet to inches, then divide by the wheel's circumference, finally convert to encoder ticks then take into account the gearbox ratio
			pointR.position = (right.position*Constants.INCHES_IN_METERS/(2*RobotSpecs.WHEEL_RADIUS*Math.PI)) * RobotSpecs.ENC_TICKS_PER_ENC_ROTATION*RobotSpecs.ENC_ROTATIONS_PER_WHEEL_ROTATION; 
			//load the velocity into the point, convert from meters per second
			pointR.velocity = Drivetrain.FeetPerSecondToTalonVelocity(right.velocity/.3048); //Convert RPM to Units/100ms
			
			//Same as before but now the left
			pointL.position = (left.position*Constants.INCHES_IN_METERS/(2*RobotSpecs.WHEEL_RADIUS*Math.PI)) * RobotSpecs.ENC_TICKS_PER_ENC_ROTATION*RobotSpecs.ENC_ROTATIONS_PER_WHEEL_ROTATION;
			pointL.velocity = Drivetrain.FeetPerSecondToTalonVelocity(left.velocity/.3048);
			//System.out.println("Pushing points "+left.position);
			
			//Set what PID slot you want to use
			pointR.profileSlotSelect0 = 0;
			
			//Set how long you want the point to last for make sure THIS IS THE SAME AS WHAT YOU SET IN THE CONFIG
			pointR.timeDur = MotionProfiling.POINT_DURATION_MILLISECONDS;
			pointR.zeroPos = false;

			pointL.profileSlotSelect0 = 0;

			pointL.timeDur = MotionProfiling.POINT_DURATION_MILLISECONDS;
			pointL.zeroPos = false;

			//If this is the first point make sure you set it so
			if (currPoint == 0){
				pointR.zeroPos = true; 
				pointL.zeroPos = true;
			}
			//If this is the last point make sure you set it so
			pointR.isLastPoint = false;
			pointL.isLastPoint = false;
			if ((currPoint + 1) == path.getSourceTrajectory().length()){
				pointR.isLastPoint = true;
				pointL.isLastPoint = true;
			}
			//Load the motion profile points into the talons
			//All points will be loaded before the motion profile starts, but this is not necessary, if you want to cut down on time then you can load the points and run the profile concurrently 
			talonR.pushMotionProfileTrajectory(pointR);
			talonL.pushMotionProfileTrajectory(pointL);
		}

		if (!waitForAllPointsLoaded){
			MotionProfileStatus mpStatus = new MotionProfileStatus();
			talonL.getMotionProfileStatus(mpStatus);
			if (currPoint > MotionProfiling.MIN_POINTS_NEEDED && mpStatus.btmBufferCnt > MotionProfiling.MIN_POINTS_NEEDED){
				status = SetValueMotionProfile.Enable;
			}
		}
		currPoint++;
	}
	
	//This returns the motion profile status
	public SetValueMotionProfile getSetValue() {	
		return status;
	}
	
}
