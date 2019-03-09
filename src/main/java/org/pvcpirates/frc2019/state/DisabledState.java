package org.pvcpirates.frc2019.state;

import org.pvcpirates.frc2019.robot.Hardware;
import org.pvcpirates.frc2019.util.ShuffleBoardManager;

public class DisabledState extends State {
    
    
    @Override
    public void init() {
        //System.out.println("Curr fourbar pos"+Hardware.getInstance().elevator.fourBarTalon.getSensorCollection().setQuadraturePosition(0, 10));
    }

    @Override
    public void exec() {
        //System.out.println(Hardware.getInstance().flipper.flipperTalonMain.getSensorCollection().getAnalogInRaw());
        //System.out.println("Curr elevator pos:"+Hardware.getInstance().elevator.elevatorEncoder.getPosition());
        //System.out.println("Curr fourbar pos"+Hardware.getInstance().elevator.fourBarTalon.getSensorCollection().getQuadraturePosition());
        if (ShuffleBoardManager.fourBarZero.getBoolean(false)){
            Hardware.getInstance().elevator.fourBarTalon.getSensorCollection().setQuadraturePosition(0, 10);
        }
    }

    @Override
    public void stop() {
    }

}
