package org.pvcpirates.frc2019.commands;

import org.pvcpirates.frc2019.Status;
import org.pvcpirates.frc2019.robot.Hardware;
import org.pvcpirates.frc2019.util.RobotMap.Constants;
import org.pvcpirates.frc2019.util.RobotMap.MotionProfiling;
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
        // average the areas of the left and right vision targets
        for(int i = 0; i < avgCnt; i++){
            // truncate them to 2 decimal points and also make them whole numbers for legibility reasons
            
            // add them to running total
            leftArea += Hardware.getInstance().limelight.getRightVisTargetArea();
            rightArea += Hardware.getInstance().limelight.getLeftVisTargetArea();
            System.out.println("left"+leftArea);
            System.out.println("right"+rightArea);
        }
        double truncateLeft =  ((int)(leftArea * 10000.0))/100.0;
        double truncateRight = ((int)(rightArea*10000.0))/100.0;
        System.out.println("tleft "+truncateLeft);
        System.out.println("tright "+truncateLeft);
        // this calculates the percent difference in area, not absolute, so we're more consistent if we don't line up exactly the given bubble radius away
        double percentDiff = (((truncateLeft/avgCnt)-(truncateRight/avgCnt))/(((truncateLeft/avgCnt)+(truncateRight/avgCnt))/2));
        // This equation converts the percent difference of the area of each vision target to an angle
        double angle = 73.4*percentDiff - 9.12;
        Waypoint[] points = new Waypoint[] {
			new Waypoint(0, 0, Pathfinder.d2r(90)),
			new Waypoint(0, MotionProfiling.BUBBLE_RADIUS/Constants.INCHES_IN_METERS, Pathfinder.d2r(90+angle)),
        };
		followMP = new FollowMotionProfile(points);
        followMP.init();
        setStatus(Status.EXEC);
        System.out.println("Donut"+angle);
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