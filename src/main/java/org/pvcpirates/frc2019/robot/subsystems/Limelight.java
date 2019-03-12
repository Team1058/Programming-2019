package org.pvcpirates.frc2019.robot.subsystems;

import org.pvcpirates.frc2019.util.RobotMap.Constants;
import org.pvcpirates.frc2019.util.RobotMap.RobotSpecs;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Limelight extends BaseSubsystem{
    public NetworkTable limelight;
    public Pipelines currPipeline;

    // Default value in the camtran array, it takes the place of tilt, as we know that tilt is in degrees
    // and will never be greater than 360
    public static final double DEFAULT_CAM_TRAN = 400;

    @Override
    public void initialize() {
        limelight = NetworkTableInstance.getDefault().getTable("limelight");
        setPipeline(Pipelines.HATCH_LOW);
    }

    @Override
    public void defaultState() {
        setPipeline(Pipelines.HATCH_LOW);
    }

    // Returns true if the limelight sees something
    public boolean hasTarget(){
        return limelight.getEntry("tv").getNumber(0).intValue() == 1;
    }
    // This is in inches
    public double[] getXYPos(){
        // Multiply by the diagonal and the sin of the angle to get the x, basic trig 
        double valueX = Math.sin(Math.toRadians(getTargetXAngle())) * getDiagonalRobotToVisTarget();
        // Multiply by the diagaonal and the cos of the angle to get the y, basic trig 
        double valueY = Math.cos(Math.toRadians(getTargetXAngle())) * getDiagonalRobotToVisTarget();
        return new double[]{-valueX,valueY} ;
    }
    
    // The point of this method is so we could stop a distance x before the vision target, instead of driving righ up to it
    // Inches,Inches; reduction is in inches
    public double[] getXYPosModified(double reduction){
        // Multiply by the diagonal and the sin of the angle to get the x, basic trig 
        double valueX = Math.sin(Math.toRadians(getTargetXAngle())) * getDiagonalRobotToVisTargetModified(reduction);
        // Multiply by the diagaonal and the cos of the angle to get the y, basic trig 
        double valueY = Math.cos(Math.toRadians(getTargetXAngle())) * getDiagonalRobotToVisTargetModified(reduction);
        return new double[]{-valueX,valueY} ;
    }

    // Find the distance from the robots center front point to the vision target as if it were drawn as a line on the ground
    public double getDiagonalRobotToVisTarget(){
        double heightDiffCameraVisTarget = RobotSpecs.CAMERA_LENS_HEIGHT - Constants.TOP_OF_ROCKET_HATCH_VIS_TARGET_HEIGHT;
        // take the height difference between the camera and vis target then use that to find the distance from the robot to the vision target as the crow flies
        double diagonal = heightDiffCameraVisTarget/ Math.tan(Math.toRadians(getTargetYAngle()));
        return diagonal;
    }

    // The point of this method is so we could stop a distance x before the vision target, instead of driving righ up to it
    // Inches; reduction is in inches
    public double getDiagonalRobotToVisTargetModified(double reduction){
        double heightDiffCameraVisTarget = RobotSpecs.CAMERA_LENS_HEIGHT - Constants.TOP_OF_ROCKET_HATCH_VIS_TARGET_HEIGHT;
        // take the height difference between the camera and vis target then use that to find the distance from the robot to the vision target as the crow flies
        double diagonal = heightDiffCameraVisTarget/ Math.tan(Math.toRadians(getTargetYAngle()));
        return diagonal-reduction;
    }

    // big note these are DEGREES FROM CENTER, NOT NORMAL COORDS
    // Camera is flipped on its side so we're really getting y here
    // The angle offset is how much the limelight is tilted, and so we subtract that so really its like it's parallel
    public double getTargetYAngle(){
        return -limelight.getEntry("tx").getNumber(0).doubleValue();
    }

  

    // The camera is flipped on its side so it says we're getting y but that's really X also add a negative due to the orientation
    public double getTargetXAngle(){
        return limelight.getEntry("ty").getNumber(0).doubleValue();
    }

    // This is calculated by the findpnp function in opencv on the limelight, resulting in the values seen below (in the enum)
    public double[] get3DPosition(){
        return limelight.getEntry("camtran").getDoubleArray(new double[]{0,0,0,DEFAULT_CAM_TRAN,0,0});
    }

    // gets the area of the vision target in % area of the screen
    public double getLeftVisTargetArea(){
        return limelight.getEntry("ta1").getNumber(0).doubleValue();
    }

    // gets the area of the vision target in % area of the screen
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

    // Enums of what position in th 6 # array of camtran numbers are
    // X and y is the relative x,y pos in inches, yaw, tilt, and roll is of the camera in degrees 
    public enum Camtran{
        X(0),VERT_DROP_Y(1),Y(2),YAW(3),TILT(4),ROLL(5);
        public int value;
        private Camtran(int value){
            this.value = value;
        }
    }
}