package org.pvcpirates.frc2019.state;

import org.pvcpirates.frc2019.robot.Hardware;
import org.pvcpirates.frc2019.util.ShuffleBoardManager;

public class DisabledState extends State {
    
    
    @Override
    public void init() {
    }

    @Override
    public void exec() {
        //System.out.println("Curr elevator pos:"+Hardware.getInstance().elevator.elevatorEncoder.getPosition());
        //System.out.println("Curr fourbar pos"+Hardware.getInstance().elevator.fourBarTalon.getSensorCollection().getQuadraturePosition());
        if (ShuffleBoardManager.fourBarZero.getBoolean(false)){
            Hardware.getInstance().elevator.fourBarTalon.getSensorCollection().setQuadraturePosition(0, 10);
        }
        if (ShuffleBoardManager.elevatorZero.getBoolean(false)){
            Hardware.getInstance().elevator.elevatorEncoder.setPosition(0);
        }
    }

    @Override
    public void stop() {
    }

}
