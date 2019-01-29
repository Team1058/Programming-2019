package org.pvcpirates.frc2019.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Limelight extends BaseSubsystem{
    NetworkTable limelight;
    @Override
    public void initialize() {
        limelight = NetworkTableInstance.getDefault().getTable("limelight");
    }
    public boolean hasTarget(){
        return limelight.getEntry("tv").getNumber(0).intValue() == 1;
    }
    public double[] getTargetXY(){
        return new double[] {limelight.getEntry("tx").getNumber(0).doubleValue(),limelight.getEntry("ty").getNumber(0).doubleValue()};
    }

}