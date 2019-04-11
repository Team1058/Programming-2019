package org.pvcpirates.frc2019.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;

import org.pvcpirates.frc2019.Status;
import org.pvcpirates.frc2019.gamepads.BaseGamepad;
import org.pvcpirates.frc2019.gamepads.GamepadEnum;
import org.pvcpirates.frc2019.robot.Hardware;
import org.pvcpirates.frc2019.robot.subsystems.Limelight.Camtran;
import org.pvcpirates.frc2019.util.PlagiarismDriveHelper;
import org.pvcpirates.frc2019.util.RobotMap;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.PIDCommand;

public class QuickAutoAssist extends TeleopCommand{
    public double distanceMin = 27;
    public double[] output = new double[]{0,0};
    public PlagiarismDriveHelper helper;// = new PlagiarismDriveHelper();
    //RotatePIDCommand rotatePID;// = new RotatePIDCommand(.16, 0, 0);
    PIDController pid;
    double pidOutput;
    public QuickAutoAssist(BaseGamepad bg){
        super(bg);
        this.status = Status.INIT;
        //rotatePID = new RotatePIDCommand(.2, 0, 0);
    }

    @Override
    public void init() {
        helper = new PlagiarismDriveHelper();
        PIDSource pidSource = new PIDSource(){
        
            @Override
            public void setPIDSourceType(PIDSourceType pidSource) {
                
            }
        
            @Override
            public double pidGet() {
                return Hardware.getInstance().limelight.getTargetXAngle();
            }
        
            @Override
            public PIDSourceType getPIDSourceType() {
                return PIDSourceType.kDisplacement;
            }
        };
        
        pid = new PIDController(.08, 0, 0.1, pidSource, (double output) -> {pidOutput = output;});
        pid.enable();
        //rotatePID.setSetpoint(0);
        //rotatePID.start();
        this.setStatus(Status.EXEC);
    }
    @Override
    public void exec() {
        /*if(hardware.limelight.getDiagonalRobotToVisTarget()  < distanceMin && hardware.limelight.getDiagonalRobotToVisTarget() > 15){
            Hardware.getInstance().hatchManipulator.hatchSliderOut();
            Timer.delay(.5);
            Hardware.getInstance().hatchManipulator.grabHatch();
            //setStatus(Status.STOP);


        }*/


        if(status != Status.STOP){
            //System.out.println("PID status"+ rotatePID.isRunning()+" "+rotatePID.isCompleted());
            System.out.println("Pid output"+pidOutput);
            output = helper.cheesyDrive(-this.gamepad.getAxis(GamepadEnum.LEFT_STICK_Y)/2, pidOutput, false);
            Hardware.getInstance().drivetrain.leftDrive1.set(ControlMode.PercentOutput, output[0]);
            Hardware.getInstance().drivetrain.rightDrive1.set(ControlMode.PercentOutput, output[1]);
        }else{
            Hardware.getInstance().drivetrain.leftDrive1.set(ControlMode.PercentOutput, 0);
            Hardware.getInstance().drivetrain.rightDrive1.set(ControlMode.PercentOutput, 0);
        }
        
    }

}