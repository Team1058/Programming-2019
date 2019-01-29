package org.pvcpirates.frc2019.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;

import org.pvcpirates.frc2019.Status;
import org.pvcpirates.frc2019.robot.Robot;
import org.pvcpirates.frc2019.robot.subsystems.Drivetrain;
import org.pvcpirates.frc2019.util.MotionProfileProcessor;
import org.pvcpirates.frc2019.util.RobotMap.MotionProfiling;
import org.pvcpirates.frc2019.util.RobotMap.RobotSpecs;

import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.Trajectory.Config;
import jaci.pathfinder.Trajectory.FitMethod;
import jaci.pathfinder.modifiers.TankModifier;

public class MotionProfileTEST extends Command{
    MotionProfileProcessor motionProfileProcessor;
    Drivetrain drivetrain = Robot.getInstance().hardware.drivetrain;

    public MotionProfileTEST(){

    }
    //Running init will take time, as it's generating a path and loading the points into the talons
    //This is why this is a TEST    
    @Override
    public void init() {
        //SET WAYPOINTS
		/*
		WAYPOINT RULES
		1. Waypoints are x,y, rotation of robot in meters, meters, and radians respectively measured from the center of the robot
		2. X direction is always where the robot is facing when the path starts
		   not necessarily positive x, if you try to move up 1 meter in the y direction only the robot code will FAIL
		3. The angles are relative to previous angles aka if we start at a 90 degree angle
		   then the next point is 180 degrees we will only turn 90, not magically turn 90 at the start then continue
		4. NO GOING BACKWARDS this is due to the fact that if you start at 0,0 then try to go to -1,0 the path generator
		   will just assume you're already facing in the negative direction. This problem could be solved by inverting
		   the motors but let's not worry about that for now
		5. Be mindful of the robot's turning radius, just because you can make a path doesn't mean the robot can turn on a dime
		6. You can't turn in place, sorry
		7. Please plot out the path and make sure it makes sense 
		8. Take into account that these points are where you want the CENTER of the robot to be, so make sure to take into account
		   the distance from the center of the robot to the edge of the bumpers so you don't run into stuff
		*/
		Waypoint[] points = new Waypoint[] {
			new Waypoint(1, -1, Pathfinder.d2r(90)),
			new Waypoint(0, 0, Pathfinder.d2r(180)),
			new Waypoint(-1, 1, Pathfinder.d2r(90)),
		};

		/*
		The config
		It's essentially where you set your line parameters and how fast you want your robot to move (NOT THE WHEELBASE WIDTH, 
		see tank modifier below)
		Parameters:
			FitMethod: If you understand Hermite splines then you can change this, if not just use quintic it's smoother
			Samples: How many points do you want to generate on the line, more will (theoretically) increase accuracy, but will
					 take significantally longer to generate
			dt/Time per point: How long each point is expected to be used for, this is in SECONDS, a good time is 10ms or .01 seconds
							   IF YOU ARE EXPERIENCING JITERRING IT COULD BE BECAUSE THIS IS _NOT_ THE SAME AS THE TALON POINT'S TIME DURATION
			Max Velocity: This is in METERS per second, basically the top speed of the robot, get this from either testing or whoever 
						  made the drivebase the faster this is the less accuracy, and please don't set this to the max velocity of the robot, 
						  it's the max velocity you want the robot to travel when the motion profile is running
			Max Acceleration: How fast you want the robot to change its velocity, again get this from either testing or from whoever made the
							  drivebase. Again as you increase the this the robot will lose accuracy, and again do not set this as the max POSSIBLE
							  acceleration of the robot, but something reasonable that's not the max (unless you have a super slow robot)
			Max Jerk: This is how fast you want the robot to accelerate. If you want a better explanation learn some calculus or look it up on
					  wikipedia, or ask a mentor. To find it essentially record the robot's acceleration from 0 to its max velocity then find
					  the slope of that using something like excel. This won't make that big of a difference unless its 0 or small, then your robot might
					  not drive. Again consult your local drivebase expert to figure out what to put here if you're lazy
		*/
		Config config = new Config(FitMethod.HERMITE_QUINTIC, Config.SAMPLES_FAST, MotionProfiling.POINT_DURATION, MotionProfiling.MAX_VELOCITY, MotionProfiling.MAX_ACCELERATION, MotionProfiling.MAX_JERK);
		/*
		  This contains all the points for the path of the CENTER of the robot, this is good for plotting and figuring out how the robot will move, but is
		  not that usuable for the talons.
		*/
		Trajectory trajectory = Pathfinder.generate(points, config);
		

		//This is another path but this will create a path that can be modified so it has a left and right path that are actually usuable by the talons
		TankModifier path = new TankModifier(trajectory);

		//Modify is necessary to seperate the path into both sides. The parameter is the WIDTH of the wheelbase in METERS
		path.modify(RobotSpecs.WHEELBASE_WIDTH_METERS);
		
		//Create a motion profile passing in the two sides of the robot and the tank modifier path
        motionProfileProcessor = new MotionProfileProcessor(path);
        //Push all points at once instead of on the fly
        motionProfileProcessor.pushAllPoints();
		setStatus(Status.EXEC);
    }

    @Override
    public void exec() {
        
        drivetrain.rightDrive1.set(ControlMode.MotionProfile, motionProfileProcessor.getSetValue().value);
        drivetrain.leftDrive1.set(ControlMode.MotionProfile, motionProfileProcessor.getSetValue().value);
        if(drivetrain.leftDrive1.isMotionProfileFinished()){
            setStatus(Status.STOP);
            motionProfileProcessor.reset();
            System.out.println("Finished profile");
        }
    }
    
    @Override
    public void finished() {
        motionProfileProcessor.reset();
    }
    
}