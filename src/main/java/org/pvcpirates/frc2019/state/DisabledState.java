package org.pvcpirates.frc2019.state;

import org.pvcpirates.frc2019.robot.Hardware;
import org.pvcpirates.frc2019.robot.Robot;
import org.pvcpirates.frc2019.util.ShuffleBoardManager;

public class DisabledState extends State {
    
    
    @Override
    public void init() {
        if(Robot.DEBUG){
            Hardware.getInstance().initializeHardware();
        }
    }

    @Override
    public void exec() {
        if (ShuffleBoardManager.fourBarZero.getBoolean(false)){
            Hardware.getInstance().elevator.fourBarTalon.getSensorCollection().setQuadraturePosition(0,10);
            Hardware.getInstance().elevator.elevatorEncoder.setPosition(0);
        }
    }

    @Override
    public void stop() {
    }

}
