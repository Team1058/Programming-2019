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

public class FollowMotionProfile extends Command{
    MotionProfileProcessor motionProfileProcessor;
    Drivetrain drivetrain = Robot.getInstance().hardware.drivetrain;
    Waypoint[] points;
    public FollowMotionProfile(Waypoint[] points){
        this.points = points;
    }
    
    @Override
    public void init() {
		//See test commands for documentation
		Config config = new Config(FitMethod.HERMITE_QUINTIC, Config.SAMPLES_FAST, MotionProfiling.POINT_DURATION, MotionProfiling.MAX_VELOCITY, MotionProfiling.MAX_ACCELERATION, MotionProfiling.MAX_JERK);
		Trajectory trajectory = Pathfinder.generate(points, config);
		TankModifier path = new TankModifier(trajectory);
		path.modify(RobotSpecs.WHEELBASE_WIDTH_METERS);
		motionProfileProcessor = new MotionProfileProcessor(path);
		motionProfileProcessor.reset();
		setStatus(Status.EXEC);
    }

    @Override
    public void exec() {
		for (int i = 0; i < 8; i++)
        	motionProfileProcessor.pushPoint(false);
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