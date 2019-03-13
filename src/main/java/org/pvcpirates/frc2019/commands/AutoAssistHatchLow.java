package org.pvcpirates.frc2019.commands;

import org.pvcpirates.frc2019.util.ShuffleBoardManager;

public class AutoAssistHatchLow extends Command{
    public AutoAssistHatchLow(){
        commands.add(new VisionMPDriveToDonut());
        commands.add(new DonutToHatchLowPlace());
    }
    
    @Override
    public void init() {
        super.init();
        
    }

    @Override
    public void exec() {
        super.exec();
    }

    @Override
    public void finished() {
        super.finished();
        
    }



    
}