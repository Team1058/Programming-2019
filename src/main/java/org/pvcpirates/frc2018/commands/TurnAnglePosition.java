package org.pvcpirates.frc2019.commands;

import org.pvcpirates.frc2019.Status;
import org.pvcpirates.frc2019.robot.Hardware;
import org.pvcpirates.frc2019.robot.subsystems.Drivetrain;

public class TurnAnglePosition extends Command {

	double angle;
	Command drive;
	public TurnAnglePosition(double angle) {
		this.angle = angle;
	}
	@Override
	public void init() {
		double feet =((angle/360.0)*(20.96*Math.PI));
		
		drive = new DriveFor(feet,-feet);
		drive.init();
		//pid must be after reeee
		Drivetrain.setPIDF(.1, 0.0, 0, 0);
		Hardware.setPIDF(0.07, 0, 0, 0, Hardware.getInstance().leftDrive1);
	}
	@Override
	public void exec() {		
	}
	@Override
	public void finished() {
		drive.finished();
	}
	
	
}
