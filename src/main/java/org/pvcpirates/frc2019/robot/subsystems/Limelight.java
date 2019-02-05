package org.pvcpirates.frc2019.robot.subsystems;

import org.pvcpirates.frc2019.util.RobotMap.Constants;
import org.pvcpirates.frc2019.util.RobotMap.RobotSpecs;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Limelight extends BaseSubsystem{
    NetworkTable limelight;
    public Pipelines currPipeline;
    @Override
    public void initialize() {
        limelight = NetworkTableInstance.getDefault().getTable("limelight");
    }
    public boolean hasTarget(){
        return limelight.getEntry("tv").getNumber(0).intValue() == 1;
    }
    //This is in inches
    public double[] getXYPos(){
        //Multiply by the diagonal and the sin of the angle to get the x, basic trig 
        double valueX = Math.sin(Math.toRadians(getTargetXAngle())) * getDiagonalRobotToVisTarget();
        //Multiply by the diagaonal and the cos of the angle to get the y, basic trig 
        double valueY = Math.cos(Math.toRadians(getTargetXAngle())) * getDiagonalRobotToVisTarget();
        return new double[]{-valueX,valueY} ;
    }
    
    //The point of this method is so we could stop a distance x before the vision target, instead of driving righ up to it
    //Inches,Inches; reduction is in inches
    public double[] getXYPosModified(double reduction){
        //Multiply by the diagonal and the sin of the angle to get the x, basic trig 
        double valueX = Math.sin(Math.toRadians(getTargetXAngle())) * getDiagonalRobotToVisTargetModified(reduction);
        //Multiply by the diagaonal and the cos of the angle to get the y, basic trig 
        double valueY = Math.cos(Math.toRadians(getTargetXAngle())) * getDiagonalRobotToVisTargetModified(reduction);
        return new double[]{-valueX,valueY} ;
    }

    public double getDiagonalRobotToVisTarget(){
        double heightDiffCameraVisTarget = RobotSpecs.CAMERA_LENS_HEIGHT - Constants.TOP_OF_ROCKET_HATCH_VIS_TARGET_HEIGHT;
        //take the height difference between the camera and vis target then use that to find the distance from the robot to the vision target as the crow flies
        double diagonal = heightDiffCameraVisTarget/ Math.tan(Math.toRadians(getTargetYAngle()));
        return diagonal;
    }

    //The point of this method is so we could stop a distance x before the vision target, instead of driving righ up to it
    //Inches; reduction is in inches
    public double getDiagonalRobotToVisTargetModified(double reduction){
        double heightDiffCameraVisTarget = RobotSpecs.CAMERA_LENS_HEIGHT - Constants.TOP_OF_ROCKET_HATCH_VIS_TARGET_HEIGHT;
        //take the height difference between the camera and vis target then use that to find the distance from the robot to the vision target as the crow flies
        double diagonal = heightDiffCameraVisTarget/ Math.tan(Math.toRadians(getTargetYAngle()));
        return diagonal-reduction;
    }

    //big note these are DEGREES FROM CENTER, NOT NORMAL COORDS
    //Camera is flipped on its side so we're really getting y here
    public double getTargetYAngle(){
        return -limelight.getEntry("tx").getNumber(0).doubleValue();
    }

    //The camera is flipped on its side so it says we're getting y but that's really X also add a negative due to the orientation
    public double getTargetXAngle(){
        return limelight.getEntry("ty").getNumber(0).doubleValue();
    }

    public double getLeftVisTargetAreaPipeline(){
        Pipelines prevPipline = currPipeline;
        setPipeline(Pipelines.HATCH_LOW_LEFT);
        double val = limelight.getEntry("ta0").getNumber(0).doubleValue();
        setPipeline(prevPipline);
        return val;
    }

    public double getLeftVisTargetArea(){
        return limelight.getEntry("ta1").getNumber(0).doubleValue();
    }

    public double getRightVisTargetArea(){
        return limelight.getEntry("ta0").getNumber(0).doubleValue();
    }

    public void setPipeline(Pipelines pipeline){
        limelight.getEntry("pipeline").setNumber(pipeline.value);
        currPipeline = pipeline;
    }

    public enum Pipelines{
        CARGO(1), HATCH_LOW(0), HATCHES(3), HATCH_LOW_LEFT(2);
        public int value;
        private Pipelines(int value){
            this.value = value;
        }
    }
}