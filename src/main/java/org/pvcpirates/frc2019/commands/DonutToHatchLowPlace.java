package org.pvcpirates.frc2019.commands;

import org.pvcpirates.frc2019.Status;
import org.pvcpirates.frc2019.robot.Hardware;
import org.pvcpirates.frc2019.util.RobotMap.Constants;
import org.pvcpirates.frc2019.util.RobotMap.MotionProfiling;
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
        double avgCnt = MotionProfiling.AREA_AVG_CNT;
        double leftArea= 0;
        double rightArea = 0;
        //average the areas of the left and right vision targets
        for(int i = 0; i < avgCnt; i++){
            //truncate them to 2 decimal points and also make them whole numbers for legibility reasons
            double truncateLeft =  ((int)(Hardware.getInstance().limelight.getLeftVisTargetArea()* 10000.0))/100.0;
            double truncateRight = ((int)(Hardware.getInstance().limelight.getRightVisTargetArea()*10000.0))/100.0;
            //add them to running total
            leftArea += truncateRight;
            rightArea += truncateLeft;
        }
        //this calculates the percent difference in area, not absolute, so we're more consistent if we don't line up exactly the given bubble radius away
        double percentDiff = (((leftArea/avgCnt)-(rightArea/avgCnt))/(((leftArea/avgCnt)+(rightArea/avgCnt))/2));
        //This equation converts the percent difference of the area of each vision target to an angle
        double angle = 73.4*percentDiff - 9.12;
        Waypoint[] points = new Waypoint[] {
			new Waypoint(0, 0, Pathfinder.d2r(90)),
			new Waypoint(0, MotionProfiling.BUBBLE_RADIUS/Constants.INCHES_IN_METERS, Pathfinder.d2r(90+angle)),
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
        followMP.finished();
    }
}