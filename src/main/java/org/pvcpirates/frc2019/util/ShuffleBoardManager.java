package org.pvcpirates.frc2019.util;

import edu.wpi.first.wpilibj.shuffleboard.*;


import org.pvcpirates.frc2019.robot.subsystems.Drivetrain;
import org.pvcpirates.frc2019.robot.subsystems.Elevator;

import edu.wpi.first.networktables.NetworkTableEntry;

public class ShuffleBoardManager {
    public static ShuffleboardTab pidTab = Shuffleboard.getTab("PID Manager");
    public static ShuffleboardTab competitionTab = Shuffleboard.getTab("Competition");
    public static ShuffleboardTab maintainanceTab = Shuffleboard.getTab("Maintainance");
    public static ShuffleboardTab Test = Shuffleboard.getTab("Test");
    public static ShuffleboardTab OperatorTab = Shuffleboard.getTab("OperatorPantel");
    // Maintainance Tab Entries
    public static NetworkTableEntry leftDriveSpeedEntry;
    public static NetworkTableEntry rightDriveSpeedEntry;
    public static NetworkTableEntry leftJoyYaxisEntry;
    public static NetworkTableEntry rightJoyYaxisEntry;
    // PID Tab Entries
    public static NetworkTableEntry fDriveEntry;
    public static NetworkTableEntry pDriveEntry;
    public static NetworkTableEntry iDriveEntry;
    public static NetworkTableEntry dDriveEntry;
    public static NetworkTableEntry loopDriveEntry;


    public ShuffleBoardManager(){
    }

    public static void initializeShuffleBoard(){
        initializeMaintainanceTab();
        initializePIDTab();
    }

    private static void initializeMaintainanceTab(){
        leftDriveSpeedEntry = maintainanceTab.add("leftDriveSpeed", 0).getEntry();
        rightDriveSpeedEntry = maintainanceTab.add("rightDriveSpeed",0).getEntry();
        leftJoyYaxisEntry = maintainanceTab.add("leftJoyYAxis", 0).getEntry();
        rightJoyYaxisEntry = maintainanceTab.add("rightJoyXAxis",0).getEntry(); 
    }

    private static void initializePIDTab(){

        fDriveEntry = pidTab.add("drive_F", Drivetrain.DRIVE_F).withWidget(BuiltInWidgets.kPIDController).getEntry();
        pDriveEntry = pidTab.add("drive_P", Drivetrain.DRIVE_P).withWidget(BuiltInWidgets.kPIDController).getEntry();
        iDriveEntry = pidTab.add("drive_I", Drivetrain.DRIVE_I).withWidget(BuiltInWidgets.kPIDController).getEntry();
        dDriveEntry = pidTab.add("drive_D", Drivetrain.DRIVE_D).withWidget(BuiltInWidgets.kPIDController).getEntry();
        loopDriveEntry = pidTab.add("drive_LoopOutput", Drivetrain.DRIVE_PEAK_OUTPUT).withWidget(BuiltInWidgets.kPIDController).getEntry();
    }
    public static void initializeOperatorTab(){
    }
}