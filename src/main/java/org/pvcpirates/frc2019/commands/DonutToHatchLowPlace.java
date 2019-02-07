package org.pvcpirates.frc2019.commands;

import org.pvcpirates.frc2019.Status;
import org.pvcpirates.frc2019.robot.Hardware;
import org.pvcpirates.frc2019.util.RobotMap.Constants;
import org.pvcpirates.frc2019.util.RobotMap.RobotSpecs;

import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Waypoint;

public class DonutToHatchLowPlace extends Command{
    
    public DonutToHatchLowPlace(){
        setStatus(Status.INIT);
    }

    FollowMotionProfile followMP;
    @Override
    public void init() {
        double avgCnt = 50;
        double leftArea= 0;
        double rightArea = 0;
        for(int i = 0; i < avgCnt; i++){
            double truncateLeft =  ((int)(Hardware.getInstance().limelight.getLeftVisTargetArea()* 10000.0))/100.0;
            double truncateRight = ((int)(Hardware.getInstance().limelight.getRightVisTargetArea()*10000.0))/100.0;
            leftArea += truncateRight;
            rightArea += truncateLeft;
        }
        double percentDiff = (((leftArea/avgCnt)-(rightArea/avgCnt))/(((leftArea/avgCnt)+(rightArea/avgCnt))/2));
        double angle = 73.4*percentDiff - 9.12;
        Waypoint[] points = new Waypoint[] {
			new Waypoint(0, 0, Pathfinder.d2r(90)),
			new Waypoint(0, RobotSpecs.BUBBLE_RADIUS/Constants.INCHES_IN_METERS, Pathfinder.d2r(90+angle)),
        };
		followMP = new FollowMotionProfile(points);
        followMP.init();
        setStatus(Status.EXEC);
    }
    
    @Override
    public void exec() {
        followMP.exec();
        if (followMP.status == Status.STOP)
            setStatus(Status.STOP);
    }

    @Override
    public void finished() {
    }
}