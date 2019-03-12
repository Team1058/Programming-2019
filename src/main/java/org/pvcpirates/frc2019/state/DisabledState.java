package org.pvcpirates.frc2019.state;

import org.pvcpirates.frc2019.robot.Hardware;
import org.pvcpirates.frc2019.robot.Robot;

public class DisabledState extends State {
    
    
    @Override
    public void init() {
        if(Robot.DEBUG){
            Hardware.getInstance().initializeHardware();
        }
    }

    @Override
    public void exec() {
        
    }

    @Override
    public void stop() {
    }

}
