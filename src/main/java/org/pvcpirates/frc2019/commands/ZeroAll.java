package org.pvcpirates.frc2019.commands;

public class ZeroAll extends Command{
    public ZeroAll(){
        commands.add(new ZeroElevator());
        commands.add(new ZeroFourBar());
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