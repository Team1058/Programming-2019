package org.pvcpirates.frc2019.state;

import org.pvcpirates.frc2019.commands.MotionProfileTEST;
import org.pvcpirates.frc2019.gamepads.DriverGamepad;
import org.pvcpirates.frc2019.gamepads.OperatorGamepad;
import org.pvcpirates.frc2019.robot.Hardware;
import org.pvcpirates.frc2019.robot.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;



public class TeleopState extends State {
    private DriverGamepad driverGamepad;
    private OperatorGamepad operatorGamepad;
    private Hardware hardware;
    
    @Override
    public void init() {
        driverGamepad = new DriverGamepad(0);
        operatorGamepad = new OperatorGamepad(1);
<<<<<<< HEAD
        hardware = hardware.getInstance();
=======
        
>>>>>>> 79cfa1b15bbc3fa8fd706f993c8821fbc46c4fcf
    }

    @Override
    public void exec() {
        // Code here will all get called periodically (every ms) in Auto
        driverGamepad.executeCommands();
        operatorGamepad.executeCommands();
<<<<<<< HEAD
        SmartDashboard.putBoolean("Target", hardware.limelight.hasTarget());
        SmartDashboard.putNumberArray("XY", hardware.limelight.getTargetXY());
=======
        
>>>>>>> 79cfa1b15bbc3fa8fd706f993c8821fbc46c4fcf
        
    }

    @Override
    public void stop() {
        // Code here will get called when teleop state is stopped
    }

}
