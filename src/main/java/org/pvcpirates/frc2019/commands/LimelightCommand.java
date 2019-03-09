package org.pvcpirates.frc2019.commands;

import org.pvcpirates.frc2019.gamepads.BaseGamepad;
import org.pvcpirates.frc2019.robot.Hardware;
import org.pvcpirates.frc2019.robot.subsystems.Limelight;
import org.pvcpirates.frc2019.robot.subsystems.Limelight.Pipelines;
import org.pvcpirates.frc2019.util.ShuffleBoardManager;

public class LimelightCommand extends TeleopCommand{

    Limelight limelight;
    public LimelightCommand(BaseGamepad bg){
        super(bg);
        limelight = Hardware.getInstance().limelight;
    }
    
    @Override
    public void init() {
        limelight.setPipeline(Pipelines.HATCH_LOW);
    }

    @Override
    public void exec() {        
        System.out.println("Limelight = "+ShuffleBoardManager.limelightToggleButton.getBoolean(false));
        limelight.driverCam(ShuffleBoardManager.limelightToggleButton.getBoolean(false));
        
    }

    @Override
    public void finished() {
    }



    
}