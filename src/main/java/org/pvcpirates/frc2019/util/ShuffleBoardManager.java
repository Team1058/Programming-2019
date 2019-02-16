package org.pvcpirates.frc2019.util;

import edu.wpi.first.wpilibj.shuffleboard.*;
import edu.wpi.first.wpilibj.smartdashboard.*;
import org.pvcpirates.frc2019.robot.subsystems.Drivetrain;
import org.pvcpirates.frc2019.robot.subsystems.Flipper;

import edu.wpi.first.networktables.NetworkTable;
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
    public static NetworkTableEntry flipper_F;
    public static NetworkTableEntry flipper_P;
    public static NetworkTableEntry flipper_I;
    public static NetworkTableEntry flipper_D;
    public static SendableChooser<Double> flipperPIDChooser;
    // PID Tab Entries
    public static NetworkTableEntry fDriveEntry;
    public static NetworkTableEntry pDriveEntry;
    public static NetworkTableEntry iDriveEntry;
    public static NetworkTableEntry dDriveEntry;
    public static NetworkTableEntry loopDriveEntry;
    // Comp Tab Entries
    public static NetworkTableEntry visionTargetBool;
    /*public static NetworkTableEntry flipperPosition1to2FrontBool;
    public static NetworkTableEntry flipperPosition1to2BackBool;
    public static NetworkTableEntry flipperPosition2to3FrontBool;
    public static NetworkTableEntry flipperPosition2to3BackBool;
    public static NetworkTableEntry flipperDefaultPositionBool;*/
    public static SendableChooser<String> flipperPositionChooser;
    public static NetworkTableEntry hatchClawPrepGEntry;
    public static NetworkTableEntry hatchClawGrabEntry;

    public static String fpLvl1to2FrontString = "Level 1 Front";
    public static String fpLvl1to2BackString = "Level 1 Back";
    public static String fpLvl2to3FrontString = "Level 2 Front";
    public static String fpLvl2to3BackString  = "Level 2 Back";
    public static String fpDefaultString  = "Stow Position";


    public ShuffleBoardManager(){
    }

    public static void initializeShuffleBoard(){
        initializeMaintainanceTab();
        initializePIDTab();
        initializeCompetitionTab();
    }

    private static void initializeCompetitionTab(){
        visionTargetBool = competitionTab.add("visionTarget",false).getEntry();
        hatchClawGrabEntry = competitionTab.add("hatchClawGrab",false).withWidget(BuiltInWidgets.kToggleButton).getEntry();
        hatchClawPrepGEntry = competitionTab.add("hatchClawPrepGrab",false).withWidget(BuiltInWidgets.kToggleButton).getEntry();
        initializeFlipperComboBox();
    }

    private static void initializeFlipperComboBox(){
        flipperPositionChooser = new SendableChooser<String>();
        flipperPositionChooser.addOption(fpLvl1to2FrontString, fpLvl1to2FrontString);
        flipperPositionChooser.addOption(fpLvl1to2BackString, fpLvl1to2BackString);
        flipperPositionChooser.addOption(fpLvl2to3FrontString,fpLvl2to3FrontString);
        flipperPositionChooser.addOption(fpLvl2to3BackString, fpLvl2to3BackString);
        flipperPositionChooser.addOption(fpDefaultString,fpDefaultString);
        flipperPositionChooser.setDefaultOption(fpDefaultString, fpDefaultString);
        competitionTab.getLayout("Flipper", BuiltInLayouts.kGrid).add("Flipper Positions",flipperPositionChooser).withWidget(BuiltInWidgets.kSplitButtonChooser);
    }

    private static void initializeMaintainanceTab(){
        leftDriveSpeedEntry = maintainanceTab.add("leftDriveSpeed", 0).getEntry();
        rightDriveSpeedEntry = maintainanceTab.add("rightDriveSpeed",0).getEntry();
        leftJoyYaxisEntry = maintainanceTab.add("leftJoyYAxis", 0).getEntry();
        rightJoyYaxisEntry = maintainanceTab.add("rightJoyXAxis",0).getEntry();
        visionDiagEntry = maintainanceTab.add("visionDiag",0).getEntry();
        flipperDefaultPositionEntry = maintainanceTab.add("flipperDefaultPositionEntry", 0).getEntry();
        flipperlvl1To2FrontEntry = maintainanceTab.add("flipperlvl1To2FrontEntry",0).getEntry();
        flipperlvl1To2BackEntry = maintainanceTab.add("flipperlvl1To2BackEntry",0).getEntry();
        flipperlvl2To3FrontEntry = maintainanceTab.add("flipperlvl2To3FrontEntry",0).getEntry();
        flipperlvl2To3BackEntry = maintainanceTab.add("flipperlvl2To3BackEntry",0).getEntry();
    }

    private static void initializePIDTab(){
        flipperPIDChooser = new SendableChooser<Double>();
        flipper_F = pidTab.add("flipper_F",0).withWidget(BuiltInWidgets.kTextView).getEntry();
        flipper_P = pidTab.add("flipper_P",0).withWidget(BuiltInWidgets.kTextView).getEntry();
        flipper_I = pidTab.add("flipper_I",0).withWidget(BuiltInWidgets.kTextView).getEntry();
        flipper_D = pidTab.add("flipper_D",0).withWidget(BuiltInWidgets.kTextView).getEntry();
        fDriveEntry = pidTab.add("drive_F", Drivetrain.DRIVE_F).getEntry();
        pDriveEntry = pidTab.add("drive_P", Drivetrain.DRIVE_P).getEntry();
        iDriveEntry = pidTab.add("drive_I", Drivetrain.DRIVE_I).getEntry();
        dDriveEntry = pidTab.add("drive_D", Drivetrain.DRIVE_D).getEntry();
        loopDriveEntry = pidTab.add("drive_LoopOutput", Drivetrain.DRIVE_PEAK_OUTPUT).getEntry();
    }
}