package org.pvcpirates.frc2019.robot.subsystems;

import org.pvcpirates.frc2019.util.RobotMap.Constants;
import org.pvcpirates.frc2019.util.RobotMap.RobotSpecs;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Limelight extends BaseSubsystem{
    NetworkTable limelight;
    @Override
    public void initialize() {
        limelight = NetworkTableInstance.getDefault().getTable("limelight");
    }
    public boolean hasTarget(){
        return limelight.getEntry("tv").getNumber(0).intValue() == 1;
    }
    //This is in inches
    public double getXPos(){
        //Multiply by the diagonal and the sin of the angle to get the x, basic trig 
        double value = Math.sin(Math.toRadians(getTargetXAngle())) * getDiagonalRobotToVisTarget();
        return value;
    }
    //This is in inches
    public double getYPos(){
        //Multiply by the diagaonal and the cos of the angle to get the y, basic trig 
        double value = Math.cos(Math.toRadians(getTargetXAngle())) * getDiagonalRobotToVisTarget();
        //Negative value because having a positive y is easier to deal with
        return value;
    }

    public double getDiagonalRobotToVisTarget(){
        double heightDiffCameraVisTarget = RobotSpecs.CAMERA_LENS_HEIGHT - Constants.TOP_OF_ROCKET_HATCH_VIS_TARGET_HEIGHT;
        //take the height difference between the camera and vis target then use that to find the distance from the robot to the vision target as the crow flies
        double diagonal = heightDiffCameraVisTarget/ Math.tan(Math.toRadians(getTargetYAngle()));
        return -diagonal;
    }

    //big note these are DEGREES FROM CENTER, NOT NORMAL COORDS
    //Camera is flipped on its side so we're really getting y here
    public double getTargetYAngle(){
        return limelight.getEntry("tx").getNumber(0).doubleValue();
    }

    //The camera is flipped on its side so it says we're getting y but that's really X also add a negative due to the orientation
    public double getTargetXAngle(){
        return -limelight.getEntry("ty").getNumber(0).doubleValue();
    }

    public void setPipeline(Pipelines pipeline){
        limelight.getEntry("pipeline").setNumber(pipeline.value);
    }

    public enum Pipelines{
        CARGO(1), HATCH_LOW(0), HATCHES(2);
        public int value;
        private Pipelines(int value){
            this.value = value;
        }
    }
}