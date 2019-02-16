package org.pvcpirates.frc2019.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANEncoder;

import org.pvcpirates.frc2019.util.RobotMap;
import org.pvcpirates.frc2019.util.ShuffleBoardManager;

public class Elevator extends BaseSubsystem {

   // public final TalonSRX pivotTalon = new TalonSRX(RobotMap.CANTalonIds.ELEVATOR_PIVOT);
    //public final CANSparkMax elevatorSparkMax = new CANSparkMax(RobotMap.CANTalonIds.ELEVATOR_SPARKMAX,MotorType.kBrushless);
    //public final CANPIDController elevatorPIDController = new CANPIDController(elevatorSparkMax);
    //public final CANEncoder elevatorEncoder = new CANEncoder(elevatorSparkMax);

    public void initialize(){
    }

    public void setSetpoint(int setpoint){
      //  elevatorPIDController.setReference(setpoint, ControlType.kPosition);
    }

    public void getSetpoint(){
        //elevatorEncoder.getPosition();
    }

    public void manipulatorPivot(){
        // Pivot will be 0 to 180
    }
}
