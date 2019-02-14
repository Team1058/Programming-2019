package org.pvcpirates.frc2019.util;

import edu.wpi.first.wpilibj.shuffleboard.*;
import org.pvcpirates.frc2019.robot.subsystems.Drivetrain;
import edu.wpi.first.networktables.NetworkTableEntry;

public class ShuffleBoardManager {
    public static ShuffleboardTab pidTab = Shuffleboard.getTab("PID Manager");
    public static ShuffleboardTab competitionTab = Shuffleboard.getTab("Competition");
    public static ShuffleboardTab maintainanceTab = Shuffleboard.getTab("Maintainance");
    // Maintainance Tab Entries
    public static NetworkTableEntry leftDriveSpeedEntry;
    public static NetworkTableEntry rightDriveSpeedEntry;
    public static NetworkTableEntry leftJoyYaxisEntry;
    public static NetworkTableEntry rightJoyYaxisEntry;
    public static NetworkTableEntry visionDiagEntry;
    public static NetworkTableEntry flipperDefaultPositionEntry;
    public static NetworkTableEntry flipperlvl1To2BackEntry;
    public static NetworkTableEntry flipperlvl1To2FrontEntry;
    public static NetworkTableEntry flipperlvl2To3BackEntry;
    public static NetworkTableEntry flipperlvl2To3FrontEntry;
    // PID Tab Entries
    public static NetworkTableEntry fDriveEntry;
    public static NetworkTableEntry pDriveEntry;
    public static NetworkTableEntry iDriveEntry;
    public static NetworkTableEntry dDriveEntry;
    public static NetworkTableEntry loopDriveEntry;
    // Comp Tab Entries
    public static NetworkTableEntry visionTargetBool;



    public ShuffleBoardManager(){
    }

    public static void initializeShuffleBoard(){
        initializeMaintainanceTab();
        initializePIDTab();
        initializeCompetitionTab();
    }

    private static void initializeCompetitionTab(){
        visionTargetBool = competitionTab.add("visionTarget",false).getEntry();
    }

    private static void initializeMaintainanceTab(){
        leftDriveSpeedEntry = maintainanceTab.add("leftDriveSpeed", 0).getEntry();
        rightDriveSpeedEntry = maintainanceTab.add("rightDriveSpeed",0).getEntry();
        leftJoyYaxisEntry = maintainanceTab.add("leftJoyYAxis", 0).getEntry();
        rightJoyYaxisEntry = maintainanceTab.add("rightJoyXAxis",0).getEntry();
        visionDiagEntry = maintainanceTab.add("visionDiag",0).getEntry();
    }

    private static void initializePIDTab(){
        fDriveEntry = pidTab.add("drive_F", Drivetrain.DRIVE_F).getEntry();
        pDriveEntry = pidTab.add("drive_P", Drivetrain.DRIVE_P).getEntry();
        iDriveEntry = pidTab.add("drive_I", Drivetrain.DRIVE_I).getEntry();
        dDriveEntry = pidTab.add("drive_D", Drivetrain.DRIVE_D).getEntry();
        loopDriveEntry = pidTab.add("drive_LoopOutput", Drivetrain.DRIVE_PEAK_OUTPUT).getEntry();
    }
}