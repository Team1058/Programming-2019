package org.pvcpirates.frc2019.robot.subsystems;


public abstract class BaseSubsystem {
    abstract void initialize();
    abstract void defaultState();
    abstract void setConstantsFromShuffleboard();
}
