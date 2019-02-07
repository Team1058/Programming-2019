package org.pvcpirates.frc2019.state;

import org.pvcpirates.frc2019.commands.MotionProfileTEST;
import org.pvcpirates.frc2019.gamepads.DriverGamepad;
import org.pvcpirates.frc2019.gamepads.OperatorGamepad;
import org.pvcpirates.frc2019.robot.Hardware;
import org.pvcpirates.frc2019.robot.subsystems.Drivetrain;
import org.pvcpirates.frc2019.robot.subsystems.Limelight.Pipelines;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;



public class TeleopState extends State {
    private DriverGamepad driverGamepad;
    private OperatorGamepad operatorGamepad;
    private Hardware hardware;
    
    @Override

    public void init() {
        driverGamepad = new DriverGamepad(0);
        operatorGamepad = new OperatorGamepad(1);
        hardware = Hardware.getInstance();
        hardware.limelight.setPipeline(Pipelines.HATCH_LOW);
    }

    int avgCnt = 0;
    double leftArea;
    double rightArea;
    @Override
    public void exec() {
        // Code here will all get called periodically (every ms) in Auto

        //Notes: our donut of consistency for percent diff is 35" inner radius 53" outer radius
        driverGamepad.executeCommands();
        operatorGamepad.executeCommands();
        SmartDashboard.putBoolean("Target", hardware.limelight.hasTarget());
        SmartDashboard.putNumber("X Angle", hardware.limelight.getTargetXAngle());
        SmartDashboard.putNumber("Y Angle", hardware.limelight.getTargetYAngle());
        SmartDashboard.putNumber("X Pos", hardware.limelight.getXYPos()[0]);
        SmartDashboard.putNumber("Y Pos", hardware.limelight.getXYPos()[1]);
        SmartDashboard.putNumber("Diag", hardware.limelight.getDiagonalRobotToVisTarget());
        double truncateLeft =  ((int)(hardware.limelight.getLeftVisTargetArea()* 10000.0))/100.0;
        double truncateRight = ((int)(hardware.limelight.getRightVisTargetArea()*10000.0))/100.0;
        SmartDashboard.putNumber("LeftVisTargetArea", truncateLeft);
        SmartDashboard.putNumber("RightVisTargetArea", truncateRight);

        //TODO: make me an enum
        if (avgCnt < 50){
        
            leftArea += truncateRight;
            rightArea += truncateLeft;
            avgCnt++;
            
        }else{
            
            double percentDiff = (((leftArea/avgCnt)-(rightArea/avgCnt))/(((leftArea/avgCnt)+(rightArea/avgCnt))/2));
            SmartDashboard.putNumber("PercentDiff", percentDiff);
            //This equation was calculated at 40 inches
            double calcAngle = 73.4*percentDiff - 9.12;
            SmartDashboard.putNumber("Calculated Angle", calcAngle);
            avgCnt = 0;
            leftArea = 0;
            rightArea = 0;
        }
        //SmartDashboard.putNumber("RawDiff", hardware.limelight.getLeftVisTargetArea()-hardware.limelight.getRightVisTargetArea());
    }

    @Override
    public void stop() {
        // Code here will get called when teleop state is stopped
    }

}
