package org.pvcpirates.frc2019.state;

import org.pvcpirates.frc2019.commands.MotionProfileTEST;
import org.pvcpirates.frc2019.gamepads.DriverGamepad;
import org.pvcpirates.frc2019.gamepads.OperatorGamepad;
import org.pvcpirates.frc2019.robot.Hardware;
import org.pvcpirates.frc2019.robot.subsystems.Drivetrain;
import org.pvcpirates.frc2019.robot.subsystems.Limelight.Pipelines;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;



public class TeleopState extends State {
    private DriverGamepad driverGamepad;
    private OperatorGamepad operatorGamepad;
    private Hardware hardware;
    
    @Override
    public void init() {
        driverGamepad = new DriverGamepad(0);
        operatorGamepad = new OperatorGamepad(1);
        hardware = Hardware.getInstance();
        hardware.limelight.setPipeline(Pipelines.HATCH_LOW);
    }

    @Override
    public void exec() {
        // Code here will all get called periodically (every ms) in Auto
        driverGamepad.executeCommands();
        operatorGamepad.executeCommands();
        SmartDashboard.putBoolean("Target", hardware.limelight.hasTarget());
        SmartDashboard.putNumber("X Angle", hardware.limelight.getTargetXAngle());
        SmartDashboard.putNumber("Y Angle", hardware.limelight.getTargetYAngle());
        SmartDashboard.putNumber("X Pos", hardware.limelight.getXPos());
        SmartDashboard.putNumber("Y Pos", hardware.limelight.getYPos());
        SmartDashboard.putNumber("Diag", hardware.limelight.getDiagonalRobotToVisTarget());
    }

    @Override
    public void stop() {
        // Code here will get called when teleop state is stopped
    }

}
