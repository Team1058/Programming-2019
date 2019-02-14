package org.pvcpirates.frc2019.state;

import org.pvcpirates.frc2019.commands.MotionProfileTEST;
import org.pvcpirates.frc2019.gamepads.DriverGamepad;
import org.pvcpirates.frc2019.gamepads.OperatorGamepad;
import org.pvcpirates.frc2019.robot.Hardware;
import org.pvcpirates.frc2019.robot.subsystems.Drivetrain;
import org.pvcpirates.frc2019.robot.subsystems.Limelight.Pipelines;
import org.pvcpirates.frc2019.util.ShuffleBoardManager;
import org.pvcpirates.frc2019.util.RobotMap.MotionProfiling;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;



public class TeleopState extends State {
    private DriverGamepad driverGamepad;
    private OperatorGamepad operatorGamepad;
    private Hardware hardware;

    @Override
    public void init() {
        hardware = Hardware.getInstance();
        hardware.initializeHardware();
        driverGamepad = new DriverGamepad(0);
        operatorGamepad = new OperatorGamepad(1);
        //TODO: Find a better location for this
        Hardware.getInstance().limelight.setPipeline(Pipelines.HATCH_LOW);
    }

    @Override
    public void exec() {
        // Code here will all get called periodically (every ms) in Auto
        driverGamepad.executeCommands();
        operatorGamepad.executeCommands();
    }

    @Override
    public void stop() {
        // Code here will get called when teleop state is stopped
    }

}
