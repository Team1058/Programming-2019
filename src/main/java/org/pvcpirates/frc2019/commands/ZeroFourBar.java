package org.pvcpirates.frc2019.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;

import org.pvcpirates.frc2019.robot.Hardware;
import org.pvcpirates.frc2019.util.RobotMap.Constants;

//we are going to need to zero the fourbar
public class ZeroFourBar extends Command{
    Hardware hardware;
    public ZeroFourBar(){
        hardware = Hardware.getInstance();
    }
    @Override
    public void init() {
        //hardware.elevator.fourBarTalon.set(ControlMode.PercentOutput, -0.1);
    }
    @Override
    public void exec() {
       // if (hardware.elevator.fourBarTalon.getSensorCollection().isRevLimitSwitchClosed()){
        //    hardware.elevator.fourBarTalon.set(ControlMode.PercentOutput, 0);
          //  hardware.elevator.fourBarTalon.getSensorCollection().setQuadraturePosition(0, Constants.ROBOT_TIMEOUT);
        //}

    }
    @Override
    public void finished() {
        //hardware.elevator.fourBarTalon.set(ControlMode.PercentOutput, 0);
    }
}