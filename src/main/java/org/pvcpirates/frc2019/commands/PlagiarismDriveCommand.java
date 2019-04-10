package org.pvcpirates.frc2019.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;

import org.pvcpirates.frc2019.Status;
import org.pvcpirates.frc2019.gamepads.BaseGamepad;
import org.pvcpirates.frc2019.gamepads.DriverGamepad;
import org.pvcpirates.frc2019.gamepads.GamepadEnum;
import org.pvcpirates.frc2019.robot.Hardware;
import org.pvcpirates.frc2019.util.PlagiarismDriveHelper;

public class PlagiarismDriveCommand extends TeleopCommand{
    PlagiarismDriveHelper helper = new PlagiarismDriveHelper();
    public PlagiarismDriveCommand(BaseGamepad gp) {
        super(gp);
    }
    QuickAutoAssist qaa; //= new QuickAutoAssist(this.gamepad);
    boolean qaaActive = false;
    @Override
    public void exec(){
        if (this.gamepad.getButton(GamepadEnum.X_BUTTON)){
            System.out.println("X pressed");
            if (!qaaActive){
                qaa = new QuickAutoAssist(this.gamepad);
                qaaActive = true;
                System.out.println("create object");
            }
            if (qaa.getStatus() == Status.INIT){
                qaa.init();
                System.out.println("init");
            }else if(qaa.getStatus() == Status.EXEC){
                qaa.exec();
                System.out.println("Exec");
            }
        }else{
            qaaActive = false;
            double left = -this.gamepad.getAxis(GamepadEnum.LEFT_STICK_Y);
            double right = this.gamepad.getAxis(GamepadEnum.RIGHT_STICK_X);
            boolean quickTurn = false;
            if (this.gamepad.getButton(GamepadEnum.LEFT_BUMPER)){
                quickTurn = true;
                left /= 2;
                right /= 2;
            }
            double[] output = helper.cheesyDrive(left, right, quickTurn);
            Hardware.getInstance().drivetrain.leftDrive1.set(ControlMode.PercentOutput, output[0]);
            Hardware.getInstance().drivetrain.rightDrive1.set(ControlMode.PercentOutput, output[1]);
        }
        
    }
}
