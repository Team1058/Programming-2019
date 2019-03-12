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
        hardware.elevator.elevatorSparkMax.set(-0.1);
    }
    @Override
    public void exec() {
        if (ShuffleBoardManager.fourBarZero.getBoolean(false)){
            Hardware.getInstance().elevator.fourBarTalon.getSensorCollection().setQuadraturePosition(0, 10);
        }
        if (ShuffleBoardManager.elevatorZero.getBoolean(false)){
            Hardware.getInstance().elevator.elevatorEncoder.setPosition(0);
        }
        
        if (hardware.elevator.reverseLimitSwitch.get()){
            hardware.elevator.elevatorSparkMax.set(0);
            
            hardware.elevator.elevatorEncoder.setPosition(0);
        }

    }
    @Override
    public void finished() {
        //hardware.elevator.fourBarTalon.set(ControlMode.PercentOutput, 0);
    }
}