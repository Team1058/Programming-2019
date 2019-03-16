package org.pvcpirates.frc2019.robot.subsystems;

import org.pvcpirates.frc2019.util.RobotMap;
import org.pvcpirates.frc2019.commands.Delay;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Timer;

public class HatchManipulator extends BaseSubsystem {

    public final Compressor compressor = new Compressor(RobotMap.CANTalonIds.PCM);
    public final DoubleSolenoid clawSolenoid = new DoubleSolenoid(5,RobotMap.PCMIDS.doubleClawSolenoidForwardPCM, RobotMap.PCMIDS.doubleClawSolenoidReversePCM);
    public final DoubleSolenoid sliderSolenoid = new DoubleSolenoid(5,RobotMap.PCMIDS.doubleSliderSolenoidForwardPCM,RobotMap.PCMIDS.doubleSliderSolenoidReversePCM);
    public void initialize(){
        compressor.setClosedLoopControl(true);
    }

    @Override
    public void defaultState() {
        defaultPosition();
    }

    @Override
    void setConstantsFromShuffleboard() {
        
    }
    public void defaultPosition(){
        hatchClawRetract();
        hatchSliderIn();
    }

    public void prepGrab(){
        hatchClawRetract();
        hatchSliderOut();
    }


    public void prepPlace(){
        hatchSliderOut();
    }

    //TODO this needs a state of whether when it's retracted it's holding a hatch or not

    public void grabHatch(){
        hatchClawExpand();
        Timer.delay(0.25);
        hatchSliderIn();
    }

    public void placeHatch(){
        hatchClawRetract();
        Timer.delay(.1);
        hatchSliderIn();
    }

    public void hatchClawRetract(){
        if (clawSolenoid.get() != DoubleSolenoid.Value.kForward){
            clawSolenoid.set(DoubleSolenoid.Value.kForward);
        }
    }

    public void hatchClawExpand() {
        if (clawSolenoid.get() != DoubleSolenoid.Value.kReverse){
            clawSolenoid.set(DoubleSolenoid.Value.kReverse);
        }
    }

    public void hatchSliderIn(){
        if (sliderSolenoid.get() != DoubleSolenoid.Value.kReverse){
            sliderSolenoid.set(DoubleSolenoid.Value.kReverse);
        }
    }

    public void hatchSliderOut(){
        if (sliderSolenoid.get() != DoubleSolenoid.Value.kForward){
            sliderSolenoid.set(DoubleSolenoid.Value.kForward);
        }
    }

}
