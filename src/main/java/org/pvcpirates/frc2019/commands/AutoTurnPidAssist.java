package org.pvcpirates.frc2019.commands;

import org.pvcpirates.frc2019.gamepads.BaseGamepad;
import org.pvcpirates.frc2019.robot.Hardware;
import org.pvcpirates.frc2019.util.ShuffleBoardManager;

public class AutoTurnPidAssist extends TeleopCommand{
    public AutoTurnPidAssist(BaseGamepad gp){
        super(gp);
    }
    

    @Override
    public void exec() {
        double turnAngle = Hardware.getInstance().limelight.getTargetXAngle();
        double currAngle = Hardware.getInstance().navx.getAngle();
        
    }
}