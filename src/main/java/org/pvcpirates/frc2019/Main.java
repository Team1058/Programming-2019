package org.pvcpirates.frc2019;

import edu.wpi.first.wpilibj.RobotBase;

public final class Main {
    private Main() {
    }    
      public static void main(String... args) {
      RobotBase.startRobot(Scheduler::new);
    }
  }