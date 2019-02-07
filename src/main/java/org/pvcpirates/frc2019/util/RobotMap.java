package org.pvcpirates.frc2019.util;

public class RobotMap {
    public static final class CANTalonIds {
        public static final int LEFT_DRIVE_1 = 3;
        public static final int RIGHT_DRIVE_1 = 1;
        public static final int LEFT_DRIVE_2 = 4;
        public static final int RIGHT_DRIVE_2 = 2;
    }

    public static final class Constants {
        public static final int ROBOT_TIMEOUT = 10;
        public static final double INCHES_IN_METERS = 39.3701;
        //This is in inches, found in game manual, this will NEVER change (ever)
        public static final double TOP_OF_ROCKET_HATCH_VIS_TARGET_HEIGHT = 31.5;
    }
    public static final class RobotSpecs{
        //Distance from wheel to wheel
        public static final double WHEELBASE_WIDTH_INCHES = 22.5;
        public static final double WHEELBASE_WIDTH_METERS = 0.57;
        //Distance from edge to edge
        public static final double DRIVEBASE_WIDTH_INCHES = 28;
        public static final double DRIVEBASE_WIDTH_METERS = .711;
        //Inches
        public static final double WHEEL_RADIUS = 3;
        public static final double ENC_ROTATIONS_PER_WHEEL_ROTATION = 11.25;
        public static final double ENC_TICKS_PER_ENC_ROTATION = 1024;
        //Inches! will be 43.25 
        public static final double CAMERA_LENS_HEIGHT = 43.4;
        //Notes: our donut of consistency for percent diff is 35" inner radius 53" outer radius
        public static final double BUBBLE_RADIUS = 45;
    }
    
    public static final class MotionProfiling{
        //This is in seconds
        public static final double POINT_DURATION = .01;
        public static final int POINT_DURATION_MILLISECONDS = 10;
        //This is in _!METERS!_ per second
        public static final double MAX_VELOCITY = 1.6;
        //This is in _!METERS!_ per second squared
        public static final double MAX_ACCELERATION = 0.8;
        //This is in _!METERS!_ per second cubed
        public static final double MAX_JERK = 50;
        //How many points the talons need loaded into the buffer before we can start the motionprofile
        public static final int MIN_POINTS_NEEDED = 8;
        //This is in ms, how fast we want to run the buffer processing thread
        public static final int FRAME_PERIOD = 5;
        public static final double FRAME_PERIOD_SECONDS = 0.005;
    }

}