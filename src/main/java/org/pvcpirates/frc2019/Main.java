package org.pvcpirates.frc2019;

import org.pvcpirates.frc2019.util.ShuffleBoardManager;

import edu.wpi.first.wpilibj.RobotBase;

public final class Main {
    private Main() {
    }    
    public static void main(String... args) {
      ShuffleBoardManager.initializeShuffleBoard();
      RobotBase.startRobot(Scheduler::new);
    }
  }