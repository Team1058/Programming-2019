package org.pvcpirates.frc2019.state;

import org.pvcpirates.frc2019.robot.Hardware;
import org.pvcpirates.frc2019.robot.subsystems.Flipper;

public class DisabledState extends State {
    
    
    @Override
    public void init() {
    }

    @Override
    public void exec() {
        
        System.out.println(Hardware.getInstance().flipper.flipperTalonMain.getSensorCollection().getAnalogInRaw());

    }

    @Override
    public void stop() {
    }

}
