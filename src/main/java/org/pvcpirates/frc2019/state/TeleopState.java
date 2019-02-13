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
        Hardware.getInstance().initializeHardware();
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
<<<<<<< HEAD

        ShuffleBoardManager.visionTargetBool.setBoolean(hardware.limelight.hasTarget());
        ShuffleBoardManager.visionDiagEntry.setNumber(hardware.limelight.getDiagonalRobotToVisTarget());
        //SmartDashboard.putNumberArray("x", hardware.limelight.limelight.getEntry("camtran").getDoubleArray(new double[]{}));
        System.out.println(hardware.limelight.limelight.getEntry("camtran").getDoubleArray(new double[]{}).length);
        System.out.println(NetworkTableInstance.getDefault().getTable("limelight").getEntry("camtran").getNumberArray(new Number[]{}).length);
        //System.out.println(hardware.limelight.limelight.getSubTable("camtran").getEntry("x").getDouble());
=======
        SmartDashboard.putBoolean("Target", hardware.limelight.hasTarget());
        SmartDashboard.putNumber("Diag", hardware.limelight.getDiagonalRobotToVisTarget());

>>>>>>> 378a4bf2c66d74c54c6e7a0ac4461ade97bd9189
    }

    @Override
    public void stop() {
        // Code here will get called when teleop state is stopped
    }

}
