package org.pvcpirates.frc2019.commands;

import org.pvcpirates.frc2019.Status;
import org.pvcpirates.frc2019.robot.Hardware;
import org.pvcpirates.frc2019.robot.subsystems.Limelight;
import org.pvcpirates.frc2019.robot.subsystems.Limelight.Camtran;
import org.pvcpirates.frc2019.util.RobotMap.Constants;
import org.pvcpirates.frc2019.util.RobotMap.MotionProfiling;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Waypoint;

public class DonutToHatchLowPlace extends Command{
    
    public DonutToHatchLowPlace(){
        setStatus(Status.INIT);
    }

    double angle;
    FollowMotionProfile followMP;
    @Override
    public void init() {
        double avgCnt = MotionProfiling.ANGLE_AVG_CNT;
        //double angle = 0;
        double angleTotal = 0;
        // average the areas of the left and right vision targets
        int stopCnt = 0;
        for(int i = 0; i < avgCnt; i++){
            double angleSample = Hardware.getInstance().limelight.get3DPosition()[Camtran.YAW.value];
            stopCnt++;
            if(angleSample == Limelight.DEFAULT_CAM_TRAN||angleSample == 0){
                i--;
                if (stopCnt > 200){
                    setStatus(Status.STOP);
                    break;
                }
            }else{
                angleTotal += angleSample;
            }
        }
        angle = angleTotal/avgCnt;
        angle *= 1;
        Waypoint[] points = new Waypoint[] {
			new Waypoint(0, 0, Pathfinder.d2r(90)),
			new Waypoint(0, MotionProfiling.BUBBLE_RADIUS/Constants.INCHES_IN_METERS, Pathfinder.d2r(90+angle)),
        };
        System.out.println("points: "+MotionProfiling.BUBBLE_RADIUS/Constants.INCHES_IN_METERS+"ANGLE: "+Pathfinder.d2r(90+angle));
		followMP = new FollowMotionProfile(points);
        followMP.init();
        setStatus(Status.EXEC);
    }
    
    @Override
    public void exec() {
        
        System.out.println("points: "+MotionProfiling.BUBBLE_RADIUS/Constants.INCHES_IN_METERS+"ANGLE: "+angle);
        if (followMP.status == Status.STOP)
            setStatus(Status.STOP);
        followMP.exec();
    }

    @Override
    public void finished() {
        followMP.finished();
    }
}