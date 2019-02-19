package org.pvcpirates.frc2019.util;

import edu.wpi.first.wpilibj.shuffleboard.*;
import edu.wpi.first.wpilibj.smartdashboard.*;
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
    public static NetworkTableEntry flipperlvl0To2BackEntry;
    public static NetworkTableEntry flipperlvl0To2FrontEntry;
    public static NetworkTableEntry flipperlvl2To3BackEntry;
    public static NetworkTableEntry flipperlvl2To3FrontEntry;

    public static NetworkTableEntry flipperPercentOutputEntry;

    public static NetworkTableEntry elevatorIntakeSetpointEntry;
    public static NetworkTableEntry elevatorDefaultSetpointEntry;
    public static NetworkTableEntry elevatorHatchLowSetpointEntry;
    public static NetworkTableEntry elevatorHatchMidSetpointEntry;
    public static NetworkTableEntry elevatorHatchHighSetpointEntry;
    public static NetworkTableEntry elevatorCargoLowSetpointEntry;
    public static NetworkTableEntry elevatorCargoMidSetpointEntry;
    public static NetworkTableEntry elevatorPercentOutputEntry;
    
    public static NetworkTableEntry fourBarHigh;
    public static NetworkTableEntry fourBarMid;
    public static NetworkTableEntry fourBarLow;
    public static NetworkTableEntry miniWheelControl;
    // PID Tab Entries
    public static NetworkTableEntry fDriveEntry;
    public static NetworkTableEntry pDriveEntry;
    public static NetworkTableEntry iDriveEntry;
    public static NetworkTableEntry dDriveEntry;
    public static NetworkTableEntry loopDriveEntry;
    
    public static NetworkTableEntry fElevatorEntry;
    public static NetworkTableEntry pElevatorEntry;
    public static NetworkTableEntry iElevatorEntry;
    public static NetworkTableEntry dElevatorEntry;
    public static NetworkTableEntry loopElevatorEntry;
    public static NetworkTableEntry maxVelocElevatorEntry;
    public static NetworkTableEntry minVelocElevatorEntry;
    public static NetworkTableEntry maxAccelElevatorEntry;

    public static NetworkTableEntry fFlipperEntry;
    public static NetworkTableEntry pFlipperEntry;
    public static NetworkTableEntry iFlipperEntry;
    public static NetworkTableEntry dFlipperEntry;
    public static NetworkTableEntry pStowedFlipperEntry;

    public static NetworkTableEntry fFourBarEntry;
    public static NetworkTableEntry pFourBarEntry;
    public static NetworkTableEntry iFourBarEntry;
    public static NetworkTableEntry dFourBarEntry;
    // Comp Tab Entries
    public static NetworkTableEntry visionTargetBool;
    public static SendableChooser<String> flipperPositionChooser;
    public static NetworkTableEntry hatchClawPrepGEntry;
    public static NetworkTableEntry hatchClawGrabEntry;

    public static String fpLvl0to2FrontString = "Level 1 Front";
    public static String fpLvl0to2BackString = "Level 1 Back";
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
        visionTargetBool = competitionTab.addPersistent("visionTarget",false).getEntry();
        hatchClawGrabEntry = competitionTab.addPersistent("hatchClawGrab",false).withWidget(BuiltInWidgets.kToggleButton).getEntry();
        hatchClawPrepGEntry = competitionTab.addPersistent("hatchClawPrepGrab",false).withWidget(BuiltInWidgets.kToggleButton).getEntry();
        initializeFlipperComboBox();
    }

    private static void initializeFlipperComboBox(){
        flipperPositionChooser = new SendableChooser<String>();
        flipperPositionChooser.addOption(fpLvl0to2FrontString, fpLvl0to2FrontString);
        flipperPositionChooser.addOption(fpLvl0to2BackString, fpLvl0to2BackString);
        flipperPositionChooser.addOption(fpLvl2to3FrontString,fpLvl2to3FrontString);
        flipperPositionChooser.addOption(fpLvl2to3BackString, fpLvl2to3BackString);
        flipperPositionChooser.addOption(fpDefaultString,fpDefaultString);
        flipperPositionChooser.setDefaultOption(fpDefaultString, fpDefaultString);
        competitionTab.getLayout("Flipper", BuiltInLayouts.kGrid).add("Flipper Positions",flipperPositionChooser).withWidget(BuiltInWidgets.kSplitButtonChooser);
    }

    private static void initializeMaintainanceTab(){
        leftDriveSpeedEntry = maintainanceTab.addPersistent("leftDriveSpeed", 0).getEntry();
        rightDriveSpeedEntry = maintainanceTab.addPersistent("rightDriveSpeed",0).getEntry();
        leftJoyYaxisEntry = maintainanceTab.addPersistent("leftJoyYAxis", 0).getEntry();
        rightJoyYaxisEntry = maintainanceTab.addPersistent("rightJoyXAxis",0).getEntry();
        visionDiagEntry = maintainanceTab.addPersistent("visionDiag",0).getEntry();

        flipperDefaultPositionEntry = maintainanceTab.addPersistent("flipperDefaultPosition", Flipper.defaultPosConstant).getEntry();
        flipperlvl0To2BackEntry = maintainanceTab.addPersistent("flipperlvl0To2Back", Flipper.lvl0to2BackConstant).getEntry();
        flipperlvl0To2FrontEntry = maintainanceTab.addPersistent("flipperlvl0To2Front", Flipper.lvl0to2FrontConstant).getEntry();
        flipperlvl2To3BackEntry = maintainanceTab.addPersistent("flipperlvl2To3Back", Flipper.lvl2to3BackConstant).getEntry();
        flipperlvl2To3FrontEntry = maintainanceTab.addPersistent("flipperlvl2To3Front", Flipper.lvl2to3FrontConstant).getEntry();
        elevatorIntakeSetpointEntry = maintainanceTab.addPersistent("elevatorIntakeSetpoint",Elevator.intakeSetpoint).getEntry();
        elevatorDefaultSetpointEntry = maintainanceTab.addPersistent("elevatorDefaultSetpoint",Elevator.defaultSetpoint).getEntry();
        elevatorHatchLowSetpointEntry = maintainanceTab.addPersistent("elevatorHatchLowSetpoint",Elevator.hatchLowSetpoint).getEntry();
        elevatorHatchMidSetpointEntry = maintainanceTab.addPersistent("elevatorHatchMidSetpoint",Elevator.hatchMidSetpoint).getEntry();
        elevatorHatchHighSetpointEntry = maintainanceTab.addPersistent("elevatorHatchHighSetpoint",Elevator.hatchHighSetpoint).getEntry();
        elevatorCargoLowSetpointEntry = maintainanceTab.addPersistent("elevatorCargoLow",Elevator.cargoLowSetpoint).getEntry();
        elevatorCargoMidSetpointEntry = maintainanceTab.addPersistent("elevatorCargoMid",Elevator.cargoMidSetpoint).getEntry();

        fourBarLow = maintainanceTab.addPersistent("fourBarLow",Elevator.fourBarLowSetpoint).getEntry();
        fourBarMid = maintainanceTab.addPersistent("fourBarMid",Elevator.fourBarMidSetpoint).getEntry();
        fourBarHigh = maintainanceTab.addPersistent("fourBarHigh",Elevator.fourBarHighSetpoint).getEntry();
        miniWheelControl = maintainanceTab.addPersistent("miniWheelPercentOutput",0).withWidget(BuiltInWidgets.kNumberSlider).getEntry();
        flipperPercentOutputEntry = maintainanceTab.addPersistent("flipperPercentOutput",0).withWidget(BuiltInWidgets.kNumberSlider).getEntry();
        elevatorPercentOutputEntry = maintainanceTab.addPersistent("elevatorPercentOutput", 0).withWidget(BuiltInWidgets.kNumberSlider).getEntry();
    }

    private static void initializePIDTab(){

        fFlipperEntry = pidTab.addPersistent("flipper_F",Flipper.FLIPPER_F).withWidget(BuiltInWidgets.kTextView).getEntry();
        pFlipperEntry = pidTab.addPersistent("flipper_P",Flipper.FLIPPER_P).withWidget(BuiltInWidgets.kTextView).getEntry();
        iFlipperEntry = pidTab.addPersistent("flipper_I",Flipper.FLIPPER_I).withWidget(BuiltInWidgets.kTextView).getEntry();
        dFlipperEntry = pidTab.addPersistent("flipper_D",Flipper.FLIPPER_D).withWidget(BuiltInWidgets.kTextView).getEntry();
        pStowedFlipperEntry = pidTab.addPersistent("flipper_P_stowed",Flipper.FLIPPER_P_STOWED).withWidget(BuiltInWidgets.kTextView).getEntry();

        fDriveEntry = pidTab.addPersistent("drive_F", Drivetrain.DRIVE_F).getEntry();
        pDriveEntry = pidTab.addPersistent("drive_P", Drivetrain.DRIVE_P).getEntry();
        iDriveEntry = pidTab.addPersistent("drive_I", Drivetrain.DRIVE_I).getEntry();
        dDriveEntry = pidTab.addPersistent("drive_D", Drivetrain.DRIVE_D).getEntry();
        loopDriveEntry = pidTab.addPersistent("drive_LoopOutput", Drivetrain.DRIVE_PEAK_OUTPUT).getEntry();
        
        fElevatorEntry = pidTab.addPersistent("elevator_F",Elevator.ELEVATOR_F).getEntry();
        pElevatorEntry = pidTab.addPersistent("elevator_P",Elevator.ELEVATOR_P).getEntry();
        iElevatorEntry = pidTab.addPersistent("elevator_I",Elevator.ELEVATOR_I).getEntry();
        dElevatorEntry = pidTab.addPersistent("elevator_D",Elevator.ELEVATOR_D).getEntry();
        loopElevatorEntry = pidTab.addPersistent("elevator_closed_loop_ramp",Elevator.ELEVATOR_LOOP_RAMP_RATE).getEntry();
        maxAccelElevatorEntry = pidTab.addPersistent("elevator_max_accel",Elevator.ELEVATOR_MAX_ACCEL).getEntry();
        minVelocElevatorEntry = pidTab.addPersistent("elevator_min_veloc",Elevator.ELEVATOR_MIN_VELOC).getEntry();
        maxVelocElevatorEntry = pidTab.addPersistent("elevator_max_veloc",Elevator.ELEVATOR_MAX_VELOC).getEntry();

        fFourBarEntry = pidTab.addPersistent("fFourBarEntry",Elevator.FOUR_BAR_F).getEntry();
        pFourBarEntry = pidTab.addPersistent("pFourBarEntry",Elevator.FOUR_BAR_P).getEntry();
        iFourBarEntry = pidTab.addPersistent("iFourBarEntry",Elevator.FOUR_BAR_I).getEntry();
        dFourBarEntry = pidTab.addPersistent("dFourBarEntry",Elevator.FOUR_BAR_D).getEntry();
    }
}