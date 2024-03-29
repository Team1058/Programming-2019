package org.pvcpirates.frc2019.state;

import org.pvcpirates.frc2019.gamepads.DriverGamepad;
import org.pvcpirates.frc2019.gamepads.OperatorButtonPad;
import org.pvcpirates.frc2019.gamepads.OperatorGamepad;
import org.pvcpirates.frc2019.robot.Hardware;
import org.pvcpirates.frc2019.robot.subsystems.Limelight.Pipelines;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.revrobotics.ControlType;




public class TeleopState extends State {
    private DriverGamepad driverGamepad;
    private OperatorGamepad operatorGamepad;
    private OperatorButtonPad operatorButtonPad;
    private Hardware hardware;

    @Override
    public void init() {
        hardware = Hardware.getInstance();
        driverGamepad = new DriverGamepad(0);
        operatorGamepad = new OperatorGamepad(2);
        operatorButtonPad = new OperatorButtonPad(1);
    }

    @Override
    public void exec() {
        //System.out.println("ZeroR "+Hardware.getInstance().elevator.reverseLimitSwitch.get());
        //System.out.println("ZeroF "+Hardware.getInstance().elevator.forwardLimitSwitch.get());
        // Code here will all get called periodically (every ms) in Auto
        driverGamepad.executeCommands();
        operatorGamepad.executeCommands();
        operatorButtonPad.executeCommands();
    }

    @Override
    public void stop() {
        // Code here will get called when teleop state is stopped
    }

}
