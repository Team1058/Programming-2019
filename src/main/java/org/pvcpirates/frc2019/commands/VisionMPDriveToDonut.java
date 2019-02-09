package org.pvcpirates.frc2019.commands;

import org.pvcpirates.frc2019.Status;
import org.pvcpirates.frc2019.robot.Hardware;
import org.pvcpirates.frc2019.util.RobotMap.Constants;
import org.pvcpirates.frc2019.util.RobotMap.MotionProfiling;

import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Waypoint;

public class VisionMPDriveToDonut extends Command{

    public VisionMPDriveToDonut(){
        setStatus(Status.INIT);
    }

    FollowMotionProfile followMP;
    @Override
    public void init() {
        //how far to stop before the vision target in inches
		
		//get x and convert to meters
		double x = Hardware.getInstance().limelight.getXYPosModified(MotionProfiling.BUBBLE_RADIUS)[0]/Constants.INCHES_IN_METERS;
		double y = Hardware.getInstance().limelight.getXYPosModified(MotionProfiling.BUBBLE_RADIUS)[1]/Constants.INCHES_IN_METERS;
		//This is the angle we want to turn so the vistarget is centered on the line perpendicular to the robot's face
		double turnAngle = Hardware.getInstance().limelight.getTargetXAngle();        
		Waypoint[] points = new Waypoint[] {
			new Waypoint(0, 0, Pathfinder.d2r(90)),
			new Waypoint(x, y, Pathfinder.d2r(90+turnAngle)),
        };

		followMP = new FollowMotionProfile(points);
		followMP.init();
        setStatus(Status.EXEC);
    }

    @Override
    public void exec() {
        followMP.exec();
        if(followMP.status == Status.STOP){
            setStatus(Status.STOP);
        }
    }
    @Override
    public void finished() {
        followMP.finished();
    }
}