package org.pvcpirates.frc2019.robot.subsystems;

import org.pvcpirates.frc2019.util.RobotMap;
import org.pvcpirates.frc2019.commands.Delay;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Timer;

public class HatchManipulator extends BaseSubsystem {

    int doubleClawSolenoidForwardPCM = 3;
    int doubleClawSolenoidReversePCM = 4;
    int doubleSliderSolenoidForwardPCM = 2;
    int doubleSliderSolenoidReversePCM = 5;

    public final Compressor compressor = new Compressor(RobotMap.CANTalonIds.PCM);
    public final DoubleSolenoid clawSolenoid = new DoubleSolenoid(5,doubleClawSolenoidForwardPCM,doubleClawSolenoidReversePCM);
    public final DoubleSolenoid sliderSolenoid = new DoubleSolenoid(5,doubleSliderSolenoidForwardPCM,doubleSliderSolenoidReversePCM);
    public void initialize(){
        compressor.setClosedLoopControl(true);
    }

    public void defaultPosition(){
        hatchClawRetract();
        hatchSliderIn();
    }

    public void prepGrab(){
        hatchSliderOut();
    }

    public void grabHatch(){
        hatchClawExpand();
        Timer.delay(0.1);
        hatchSliderIn();
    }

    public void holdHatch(){
        hatchClawExpand();
        hatchSliderIn();
    }

    public void placeHatch(){
        hatchSliderOut();
        Timer.delay(.225);
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
