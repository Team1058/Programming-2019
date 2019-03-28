package org.pvcpirates.frc2019.util;

public class RobotMap {
    public static final class CANTalonIds {
        public static final int RIGHT_DRIVE_1 = 1;
        public static final int RIGHT_DRIVE_2 = 2;
        public static final int LEFT_DRIVE_1 = 3;
        public static final int LEFT_DRIVE_2 = 4;
        public static final int PCM = 5;
        public static final int FOUR_BAR = 6;
        public static final int ELEVATOR_SPARKMAX = 14;
        public static final int FLIPPER_MAIN = 8;
        public static final int FLIPPER_FOLLOWER = 9;
        public static final int FLIPPER_MINI_WHEEL = 10;
        public static final int CARGO_VICTOR = 11;
        public static final int BUDDY_CLIMB_SPARKMAX = 12;
        
    }

    public static final class PCMIDS{
        
    public static final int doubleClawSolenoidForwardPCM = 6;
    public static final int doubleClawSolenoidReversePCM = 5;

    public static final int doubleSliderSolenoidForwardPCM = 2;
    public static final int doubleSliderSolenoidReversePCM = 1;
    
    
    }


    public static final class Constants {
        public static final int ROBOT_TIMEOUT = 10;
        public static final double INCHES_IN_METERS = 39.3701;
        // This is in inches, found in game manual, this will NEVER change (ever)
        public static final double TOP_OF_ROCKET_HATCH_VIS_TARGET_HEIGHT = 31.5;
        
    }
    public static final class RobotSpecs{
        // Distance from wheel to wheel
        public static final double WHEELBASE_WIDTH_INCHES = 25.875;
        public static final double WHEELBASE_WIDTH_METERS = 0.657225;

        // Inches
        public static final double WHEEL_RADIUS = 3;
        public static final double ENC_ROTATIONS_PER_WHEEL_ROTATION = 11.25;
        public static final double ENC_TICKS_PER_ENC_ROTATION = 1024;
        // Inches! will be 43.25 
        public static final double CAMERA_LENS_HEIGHT = 43.4;
        public static final double FLIPPER_GEAR_RATIO = 1;
        
    }

    public static final class DIO{
        public static final int CARGO_PHOTO_SENSOR = 2;
    }
    
    public static final class MotionProfiling{
        // This is in seconds
        public static final double POINT_DURATION = .01;
        public static final int POINT_DURATION_MILLISECONDS = 10;
        // This is in _!METERS!_ per second
        public static final double MAX_VELOCITY = 1.6;
        // This is in _!METERS!_ per second squared
        public static final double MAX_ACCELERATION = 0.8;
        // This is in _!METERS!_ per second cubed
        public static final double MAX_JERK = 50;
        // How many points the talons need loaded into the buffer before we can start the motionprofile
        public static final int MIN_POINTS_NEEDED = 6;
        // This is in ms, how fast we want to run the buffer processing thread
        public static final int FRAME_PERIOD = 5;
        public static final double FRAME_PERIOD_SECONDS = 0.005;
        public static final double BUMPER_TO_CAMERA_DISTANCE = 27;
        // This is the distance we want to stop at from the vision targets to figure out the angle of them
        // Notes: our donut of consistency for percent diff is 35" inner radius 53" outer radius
        public static final double BUBBLE_RADIUS = 30;
        // How many area data points we want to average before calculating the angle
        public static final double ANGLE_AVG_CNT = 10;
    }

}