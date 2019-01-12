package org.pvcpirates.frc2018.state;

import org.pvcpirates.frc2018.commands.PivotArm;
import org.pvcpirates.frc2018.gamepads.DriverGamepad;
import org.pvcpirates.frc2018.gamepads.OperatorGamepad;

import org.pvcpirates.frc2018.gamepads.VJoyKeyboard;
import org.pvcpirates.frc2018.robot.Hardware;
import org.pvcpirates.frc2018.robot.subsystems.Arm;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;

public class TeleopState extends State {
    private DriverGamepad driverGamepad;
    private OperatorGamepad operatorGamepad;
    private VJoyKeyboard vJoyKeyboard;
    private Hardware hardware;
    
    @Override
    public void init() {
        driverGamepad = new DriverGamepad(0);
        operatorGamepad = new OperatorGamepad(1);
        vJoyKeyboard = new VJoyKeyboard(2);
    }

    @Override
    public void exec() {
        driverGamepad.executeCommands();
        operatorGamepad.executeCommands();
        vJoyKeyboard.executeCommands();
    }

    @Override
    public void stop() {
    }

}
