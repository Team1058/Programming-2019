package org.pvcpirates.frc2019.util;

import edu.wpi.first.wpilibj.shuffleboard.*;
import org.pvcpirates.frc2019.robot.subsystems.Drivetrain;
import org.pvcpirates.frc2019.robot.subsystems.Elevator;
import org.pvcpirates.frc2019.robot.subsystems.Flipper;

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

    public static NetworkTableEntry elevatorIntakeSetpointEntry;
    public static NetworkTableEntry elevatorDefaultSetpointEntry;
    public static NetworkTableEntry elevatorHatchLowSetpointEntry;
    public static NetworkTableEntry elevatorHatchMidSetpointEntry;
    public static NetworkTableEntry elevatorHatchHighSetpointEntry;
    public static NetworkTableEntry elevatorCargoLowSetpointEntry;
    public static NetworkTableEntry elevatorCargoMidSetpointEntry;

    public static NetworkTableEntry fourBarHigh;
    public static NetworkTableEntry fourBarMid;
    public static NetworkTableEntry fourBarLow;
    // PID Tab Entries
    public static NetworkTableEntry fDriveEntry;
    public static NetworkTableEntry pDriveEntry;
    public static NetworkTableEntry iDriveEntry;
    public static NetworkTableEntry dDriveEntry;
    public static NetworkTableEntry loopDriveEntry;

    public static NetworkTableEntry fFlipperEntry;
    public static NetworkTableEntry pFlipperEntry;
    public static NetworkTableEntry iFlipperEntry;
    public static NetworkTableEntry dFlipperEntry;
    
    public static NetworkTableEntry fElevatorEntry;
    public static NetworkTableEntry pElevatorEntry;
    public static NetworkTableEntry iElevatorEntry;
    public static NetworkTableEntry dElevatorEntry;
    public static NetworkTableEntry loopElevatorEntry;
    public static NetworkTableEntry maxVelocElevatorEntry;
    public static NetworkTableEntry minVelocElevatorEntry;
    public static NetworkTableEntry maxAccelElevatorEntry;

    public static NetworkTableEntry fFourBarEntry;
    public static NetworkTableEntry pFourBarEntry;
    public static NetworkTableEntry iFourBarEntry;
    public static NetworkTableEntry dFourBarEntry;
    // Comp Tab Entries
    public static NetworkTableEntry visionTargetBool;
    public static NetworkTableEntry cargoIntakeHigh;
    public static NetworkTableEntry cargoIntakeLow;
    public static NetworkTableEntry cargoIntakeRev;



    public ShuffleBoardManager(){
    }

    public static void initializeShuffleBoard(){
        initializeMaintainanceTab();
        initializePIDTab();
        initializeCompetitionTab();
    }

    private static void initializeCompetitionTab(){
        visionTargetBool = competitionTab.add("visionTarget",false).getEntry();
        cargoIntakeHigh = competitionTab.add("cargoIntakeHigh",false).withWidget(BuiltInWidgets.kToggleButton).getEntry();
        cargoIntakeLow = competitionTab.add("cargoIntakeLow",false).withWidget(BuiltInWidgets.kToggleButton).getEntry();
        cargoIntakeRev = competitionTab.add("cargoIntakeRev",false).withWidget(BuiltInWidgets.kToggleButton).getEntry();
    }

    private static void initializeMaintainanceTab(){
        leftDriveSpeedEntry = maintainanceTab.add("leftDriveSpeed", 0).getEntry();
        rightDriveSpeedEntry = maintainanceTab.add("rightDriveSpeed",0).getEntry();
        leftJoyYaxisEntry = maintainanceTab.add("leftJoyYAxis", 0).getEntry();
        rightJoyYaxisEntry = maintainanceTab.add("rightJoyXAxis",0).getEntry();
        visionDiagEntry = maintainanceTab.add("visionDiag",0).getEntry();

        flipperDefaultPositionEntry = maintainanceTab.add("flipperDefaultPosition", Flipper.defaultPosConstant).getEntry();
        flipperlvl1To2BackEntry = maintainanceTab.add("flipperlvl1To2Back", Flipper.lvl1to2BackConstant).getEntry();
        flipperlvl1To2FrontEntry = maintainanceTab.add("flipperlvl1To2Front", Flipper.lvl1to2FrontConstant).getEntry();
        flipperlvl2To3BackEntry = maintainanceTab.add("flipperlvl2To3Back", Flipper.lvl2to3BackConstant).getEntry();
        flipperlvl2To3FrontEntry = maintainanceTab.add("flipperlvl2To3Front", Flipper.lvl2to3FrontConstant).getEntry();

        elevatorIntakeSetpointEntry = maintainanceTab.add("elevatorIntakeSetpoint",Elevator.intakeSetpoint).getEntry();
        elevatorDefaultSetpointEntry = maintainanceTab.add("elevatorDefaultSetpoint",Elevator.defaultSetpoint).getEntry();
        elevatorHatchLowSetpointEntry = maintainanceTab.add("elevatorHatchLowSetpoint",Elevator.hatchLowSetpoint).getEntry();
        elevatorHatchMidSetpointEntry = maintainanceTab.add("elevatorHatchMidSetpoint",Elevator.hatchMidSetpoint).getEntry();
        elevatorHatchHighSetpointEntry = maintainanceTab.add("elevatorHatchHighSetpoint",Elevator.hatchHighSetpoint).getEntry();
        elevatorCargoLowSetpointEntry = maintainanceTab.add("elevatorCargoLow",Elevator.cargoLowSetpoint).getEntry();
        elevatorCargoMidSetpointEntry = maintainanceTab.add("elevatorCargoMid",Elevator.cargoMidSetpoint).getEntry();

        fourBarLow = maintainanceTab.add("fourBarLow",Elevator.fourBarLowSetpoint).getEntry();
        fourBarMid = maintainanceTab.add("fourBarMid",Elevator.fourBarMidSetpoint).getEntry();
        fourBarHigh = maintainanceTab.add("fourBarHigh",Elevator.fourBarHighSetpoint).getEntry();
    }

    private static void initializePIDTab(){
        fDriveEntry = pidTab.add("drive_F", Drivetrain.DRIVE_F).getEntry();
        pDriveEntry = pidTab.add("drive_P", Drivetrain.DRIVE_P).getEntry();
        iDriveEntry = pidTab.add("drive_I", Drivetrain.DRIVE_I).getEntry();
        dDriveEntry = pidTab.add("drive_D", Drivetrain.DRIVE_D).getEntry();
        loopDriveEntry = pidTab.add("drive_LoopOutput", Drivetrain.DRIVE_PEAK_OUTPUT).getEntry();

        fFlipperEntry = pidTab.add("flipper_F", Flipper.FLIPPER_F).getEntry();
        pFlipperEntry = pidTab.add("flipper_P", Flipper.FLIPPER_P).getEntry();
        iFlipperEntry = pidTab.add("flipper_I", Flipper.FLIPPER_I).getEntry();
        dFlipperEntry = pidTab.add("flipper_D", Flipper.FLIPPER_D).getEntry();
        
        fElevatorEntry = pidTab.add("elevator_F",Elevator.ELEVATOR_F).getEntry();
        pElevatorEntry = pidTab.add("elevator_P",Elevator.ELEVATOR_P).getEntry();
        iElevatorEntry = pidTab.add("elevator_I",Elevator.ELEVATOR_I).getEntry();
        dElevatorEntry = pidTab.add("elevator_D",Elevator.ELEVATOR_D).getEntry();
        loopElevatorEntry = pidTab.add("elevator_closed_loop_ramp",Elevator.ELEVATOR_LOOP_RAMP_RATE).getEntry();
        maxAccelElevatorEntry = pidTab.add("elevator_max_accel",Elevator.ELEVATOR_MAX_ACCEL).getEntry();
        minVelocElevatorEntry = pidTab.add("elevator_min_veloc",Elevator.ELEVATOR_MIN_VELOC).getEntry();
        maxVelocElevatorEntry = pidTab.add("elevator_max_veloc",Elevator.ELEVATOR_MAX_VELOC).getEntry();

        fFourBarEntry = pidTab.add("fFourBarEntry",Elevator.FOUR_BAR_F).getEntry();
        pFourBarEntry = pidTab.add("pFourBarEntry",Elevator.FOUR_BAR_P).getEntry();
        iFourBarEntry = pidTab.add("iFourBarEntry",Elevator.FOUR_BAR_I).getEntry();
        dFourBarEntry = pidTab.add("dFourBarEntry",Elevator.FOUR_BAR_D).getEntry();
    }
}