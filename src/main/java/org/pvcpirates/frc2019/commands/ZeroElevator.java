package org.pvcpirates.frc2019.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;

import org.pvcpirates.frc2019.robot.Hardware;
import org.pvcpirates.frc2019.util.ShuffleBoardManager;

public class ZeroElevator extends Command{
    Hardware hardware;
    public ZeroElevator(){
        hardware = Hardware.getInstance();
    }
    @Override
    public void init() {
        //This is zero so gravity can pull it down because driving it down doesnt help always
        hardware.elevator.elevatorSparkMax.set(-0.0);
    }
    @Override
    public void exec() {
        if (hardware.elevator.reverseLimitSwitch.get()){
            hardware.elevator.elevatorSparkMax.set(0);
            
            hardware.elevator.elevatorEncoder.setPosition(0);
            setStatus(status.STOP);
        }

    }
    @Override
    public void finished() {
    }
}