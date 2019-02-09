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

import com.ctre.phoenix.motion.*;


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
        setStatus(Status.INIT);
        //Profile takes around 110ms to 400ms depending on length
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
		for (int i = 0; i < 4; i++){
            motionProfileProcessor.pushPoint(false);
        }
        drivetrain.rightDrive1.set(ControlMode.MotionProfile, motionProfileProcessor.getSetValue().value);
        drivetrain.leftDrive1.set(ControlMode.MotionProfile, motionProfileProcessor.getSetValue().value);
        //Get the status of the talons
        MotionProfileStatus status = new MotionProfileStatus();
        drivetrain.leftDrive1.getMotionProfileStatus(status);
        //stop the motion profile if the top and bottom buffer count are 0 aka we dont have any more points to run
        if(status.btmBufferCnt == 0 && status.topBufferCnt == 0 && motionProfileProcessor.getSetValue() == SetValueMotionProfile.Enable){
            setStatus(Status.STOP);
        }
    }
    
    @Override
    public void finished() {
		
        motionProfileProcessor.stop();
    }
    
}