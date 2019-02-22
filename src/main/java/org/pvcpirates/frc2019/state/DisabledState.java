package org.pvcpirates.frc2019.state;

import org.pvcpirates.frc2019.robot.Hardware;

public class DisabledState extends State {
    
    
    @Override
    public void init() {
    }

    @Override
    public void exec() {
        //System.out.println(Hardware.getInstance().flipper.flipperTalonMain.getSensorCollection().getAnalogInRaw());
        System.out.println("Curr elevator pos:"+Hardware.getInstance().elevator.elevatorEncoder.getPosition());
    }

    @Override
    public void stop() {
    }

}
