package org.pvcpirates.frc2019.commands;
import org.pvcpirates.frc2019.robot.Hardware;
import org.pvcpirates.frc2019.robot.subsystems.Limelight.Camtran;

import edu.wpi.first.wpilibj.command.PIDCommand;

public class RotatePIDCommand extends PIDCommand{
    public double output = 10;
    public RotatePIDCommand(double kP, double kI, double kD) {
        super(kP, kI, kD);
    }

    @Override
    public void setSetpoint(double setpoint) {
      super.setSetpoint(setpoint);
      
    }
    @Override
    public void execute() {
        super.execute();
    }
  
    @Override
    protected double returnPIDInput() {
        return Hardware.getInstance().limelight.get3DPosition()[Camtran.YAW.value];
    }
    @Override
    protected void usePIDOutput(double output) {
        this.output = output;
    }
    @Override
    protected boolean isFinished() {
        return false; //(this.getPosition() <= this.getSetpoint() + 0.1) && (this.getPosition() >= this.getSetpoint() - 0.1);
    }
}