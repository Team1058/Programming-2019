package org.pvcpirates.frc2019.util;

import edu.wpi.first.wpilibj.shuffleboard.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ShuffleBoardManager {
    public static ShuffleboardTab pidTab = Shuffleboard.getTab("PID Manager");
    public static ShuffleboardTab competitionTab = Shuffleboard.getTab("Competition");

    public ShuffleBoardManager(){
    }
}
