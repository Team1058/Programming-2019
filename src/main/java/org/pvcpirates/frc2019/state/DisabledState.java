package org.pvcpirates.frc2019.state;

import org.pvcpirates.frc2019.robot.Hardware;
import org.pvcpirates.frc2019.robot.Robot;
import org.pvcpirates.frc2019.robot.subsystems.Limelight.Camtran;
import org.pvcpirates.frc2019.util.ShuffleBoardManager;
import org.pvcpirates.frc2019.util.RobotMap.MotionProfiling;

public class DisabledState extends State {
    
    
    @Override
    public void init() {
        if(Robot.DEBUG){
            Hardware.getInstance().initializeHardware();
        }
    }

    @Override
    public void exec() {
        System.out.println("Tilt + "+Hardware.getInstance().limelight.get3DPosition()[Camtran.TILT.value]);
        //System.out.println("Yaw + "+Hardware.getInstance().limelight.get3DPosition()[Camtran.YAW.value]);
        //System.out.println("ROLL + "+Hardware.getInstance().limelight.get3DPosition()[Camtran.ROLL.value]);
        System.out.println("diag"+Hardware.getInstance().limelight.getDiagonalRobotToVisTargetModified(MotionProfiling.BUMPER_TO_CAMERA_DISTANCE));
        if (ShuffleBoardManager.fourBarZero.getBoolean(false)){
            Hardware.getInstance().elevator.fourBarTalon.getSensorCollection().setQuadraturePosition(0,10);
            Hardware.getInstance().elevator.elevatorEncoder.setPosition(0);
        }
    }

    @Override
    public void stop() {
    }

}
