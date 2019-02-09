package org.pvcpirates.frc2019.state;

import org.pvcpirates.frc2019.commands.MotionProfileTEST;
import org.pvcpirates.frc2019.gamepads.DriverGamepad;
import org.pvcpirates.frc2019.gamepads.OperatorGamepad;
import org.pvcpirates.frc2019.robot.Hardware;
import org.pvcpirates.frc2019.robot.subsystems.Drivetrain;
import org.pvcpirates.frc2019.robot.subsystems.Limelight.Pipelines;
import org.pvcpirates.frc2019.util.RobotMap.MotionProfiling;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;



public class TeleopState extends State {
    private DriverGamepad driverGamepad;
    private OperatorGamepad operatorGamepad;
    private Hardware hardware;

    @Override
    public void init() {
        Hardware.getInstance().initializeHardware();
        driverGamepad = new DriverGamepad(0);
        operatorGamepad = new OperatorGamepad(1);
        hardware = Hardware.getInstance();
        hardware.limelight.setPipeline(Pipelines.HATCH_LOW);
    }

    int avgCnt = 0;
    double leftArea;
    double rightArea;
    @Override
    public void exec() {
        // Code here will all get called periodically (every ms) in Auto
        driverGamepad.executeCommands();
        operatorGamepad.executeCommands();
        SmartDashboard.putBoolean("Target", hardware.limelight.hasTarget());
        SmartDashboard.putNumber("Diag", hardware.limelight.getDiagonalRobotToVisTarget());
    }

    @Override
    public void stop() {
        // Code here will get called when teleop state is stopped
    }

}
