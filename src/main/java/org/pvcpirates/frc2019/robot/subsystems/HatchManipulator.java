package org.pvcpirates.frc2019.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;

public class HatchManipulator extends BaseSubsystem {

    int doubleClawSolenoidForwardPCM = 0;
    int doubleClawSolenoidReversePCM = 0;
    int doubleSliderSolenoidForwardPCM = 0;
    int doubleSliderSolenoidReversePCM = 0;

    public final Compressor compressor = new Compressor();
    public final DoubleSolenoid clawSolenoid = new DoubleSolenoid(doubleClawSolenoidForwardPCM,doubleClawSolenoidReversePCM);
    public final DoubleSolenoid sliderSolenoid = new DoubleSolenoid(doubleSliderSolenoidForwardPCM,doubleSliderSolenoidReversePCM);
    
    public void initialize(){

    }

    public void hatchClawRetract(){
        if (clawSolenoid.get() != DoubleSolenoid.Value.kReverse){
            sliderSolenoid.set(DoubleSolenoid.Value.kReverse);
        }
    }

    public void hatchClawExpand() {
        if (clawSolenoid.get() != DoubleSolenoid.Value.kForward){
            sliderSolenoid.set(DoubleSolenoid.Value.kForward);
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
