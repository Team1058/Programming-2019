package org.pvcpirates.frc2019.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANDigitalInput.LimitSwitch;
import com.revrobotics.CANDigitalInput.LimitSwitchPolarity;
import com.revrobotics.CANSparkMaxLowLevel.ConfigParameter;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANDigitalInput;
import com.revrobotics.CANEncoder;

import org.pvcpirates.frc2019.robot.Robot;
import org.pvcpirates.frc2019.util.RobotMap;
import org.pvcpirates.frc2019.util.ShuffleBoardManager;

public class Elevator extends BaseSubsystem {

    public final TalonSRX fourBarTalon = new TalonSRX(RobotMap.CANTalonIds.FOUR_BAR);
    public final CANSparkMax elevatorSparkMax = new CANSparkMax(RobotMap.CANTalonIds.ELEVATOR_SPARKMAX,MotorType.kBrushless);
    public final CANPIDController elevatorPIDController = elevatorSparkMax.getPIDController();
    public final CANEncoder elevatorEncoder = elevatorSparkMax.getEncoder();
    public final CANDigitalInput forwardLimitSwitch = elevatorSparkMax.getForwardLimitSwitch(LimitSwitchPolarity.kNormallyClosed);
    public final CANDigitalInput reverseLimitSwitch = elevatorSparkMax.getReverseLimitSwitch(LimitSwitchPolarity.kNormallyClosed);

    public static double intakeSetpoint = 0;
    public static double defaultSetpoint = 0;
    public static double hatchLowSetpoint = 0;
    public static double hatchMidSetpoint = 0;
    public static double hatchHighSetpoint = 0;
    public static double cargoLowSetpoint = 0;
    public static double cargoMidSetpoint = 0;

    public static double fourBarHighSetpoint = 0;
    public static double fourBarMidSetpoint = 0;
    public static double fourBarLowSetpoint = 0;

    public static double ELEVATOR_F = 0;
    public static double ELEVATOR_P = 0;
    public static double ELEVATOR_I = 0;
    public static double ELEVATOR_D = 0;
    public static double ELEVATOR_LOOP_RAMP_RATE = 0;
    public static double ELEVATOR_MAX_ACCEL = 0;
    public static double ELEVATOR_MAX_VELOC = 0;
    public static double ELEVATOR_MIN_VELOC = 0;

    public static double FOUR_BAR_P = 0;
    public static double FOUR_BAR_I = 0;
    public static double FOUR_BAR_D = 0;
    public static double FOUR_BAR_F = 0;

    public static final boolean ENABLE_SMART_MOTION = false;    

    public void initialize(){
        // 16:22 gear ratio for encoder to fourbar
        fourBarTalon.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
        fourBarTalon.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyClosed);
        fourBarTalon.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyClosed);
        
        forwardLimitSwitch.enableLimitSwitch(true);
        reverseLimitSwitch.enableLimitSwitch(true);
        if(Robot.DEBUG){
            setConstantsFromShuffleboard();
        }
        setPID();
    }

    @Override
    public void defaultState() {
        moveToDefault();
    }
    
    @Override
    void setConstantsFromShuffleboard(){
        ELEVATOR_P = ShuffleBoardManager.pElevatorEntry.getDouble(ELEVATOR_P);
        ELEVATOR_I = ShuffleBoardManager.iElevatorEntry.getDouble(ELEVATOR_I);
        ELEVATOR_D = ShuffleBoardManager.dElevatorEntry.getDouble(ELEVATOR_D);
        ELEVATOR_F = ShuffleBoardManager.dElevatorEntry.getDouble(ELEVATOR_F);
        ELEVATOR_LOOP_RAMP_RATE = ShuffleBoardManager.loopElevatorEntry.getDouble(ELEVATOR_LOOP_RAMP_RATE);
        ELEVATOR_MAX_ACCEL = ShuffleBoardManager.maxAccelElevatorEntry.getDouble(ELEVATOR_MAX_ACCEL);
        ELEVATOR_MAX_VELOC = ShuffleBoardManager.maxVelocElevatorEntry.getDouble(ELEVATOR_MAX_VELOC);
        ELEVATOR_MIN_VELOC= ShuffleBoardManager.minVelocElevatorEntry.getDouble(ELEVATOR_MIN_VELOC);
        FOUR_BAR_P = ShuffleBoardManager.pFourBarEntry.getDouble(FOUR_BAR_P);
        FOUR_BAR_I = ShuffleBoardManager.iFourBarEntry.getDouble(FOUR_BAR_I);
        FOUR_BAR_D= ShuffleBoardManager.dFourBarEntry.getDouble(FOUR_BAR_D);
        FOUR_BAR_F = ShuffleBoardManager.fFourBarEntry.getDouble(FOUR_BAR_F);
        defaultSetpoint = ShuffleBoardManager.elevatorDefaultSetpointEntry.getDouble(defaultSetpoint);
        hatchLowSetpoint = ShuffleBoardManager.elevatorHatchLowSetpointEntry.getDouble(hatchLowSetpoint);
        hatchMidSetpoint = ShuffleBoardManager.elevatorHatchMidSetpointEntry.getDouble(hatchMidSetpoint);
        hatchHighSetpoint = ShuffleBoardManager.elevatorHatchHighSetpointEntry.getDouble(hatchHighSetpoint);
        cargoLowSetpoint = ShuffleBoardManager.elevatorCargoLowSetpointEntry.getDouble(cargoLowSetpoint);
        cargoMidSetpoint = ShuffleBoardManager.elevatorCargoMidSetpointEntry.getDouble(cargoMidSetpoint);
        fourBarLowSetpoint = ShuffleBoardManager.fourBarLow.getDouble(fourBarLowSetpoint);
        fourBarMidSetpoint = ShuffleBoardManager.fourBarMid.getDouble(fourBarMidSetpoint);
        
    }

    public void setPID(){
        elevatorPIDController.setP(ELEVATOR_P);
        elevatorPIDController.setI(ELEVATOR_I);
        elevatorPIDController.setD(ELEVATOR_D);
        elevatorPIDController.setFF(ELEVATOR_F);
        elevatorSparkMax.setClosedLoopRampRate(ELEVATOR_LOOP_RAMP_RATE);
        elevatorPIDController.setSmartMotionMaxAccel(ELEVATOR_MAX_ACCEL,0);
        elevatorPIDController.setSmartMotionMaxVelocity(ELEVATOR_MAX_VELOC,0);
        elevatorPIDController.setSmartMotionMinOutputVelocity(ELEVATOR_MIN_VELOC,0);

        fourBarTalon.config_kP(0, FOUR_BAR_P);
        fourBarTalon.config_kI(0, FOUR_BAR_I);
        fourBarTalon.config_kD(0, FOUR_BAR_D);
        fourBarTalon.config_kF(0, FOUR_BAR_F);
    }


    public void moveToIntake(){
        setSetpoint(intakeSetpoint);
        fourBarSetSetpoint(fourBarLowSetpoint);
    }

    public void moveToDefault(){
        setSetpoint(defaultSetpoint);
        fourBarSetSetpoint(fourBarMidSetpoint);
    }

    public void moveToHatchLow(){
        setSetpoint(hatchLowSetpoint);
        fourBarSetSetpoint(fourBarMidSetpoint);
    }

    public void moveToHatchMid(){
        setSetpoint(hatchMidSetpoint);
        fourBarSetSetpoint(fourBarMidSetpoint);
    }

    public void moveToHatchHigh(){
        setSetpoint(hatchHighSetpoint);
        fourBarSetSetpoint(fourBarMidSetpoint);
    }

    public void moveToCargoLow(){
        setSetpoint(cargoLowSetpoint);
        fourBarSetSetpoint(fourBarMidSetpoint);
    }

    public void moveToCargoMid(){
        setSetpoint(cargoMidSetpoint);
        fourBarSetSetpoint(fourBarMidSetpoint);
    }

    public void moveFourBarToLow(){
        fourBarSetSetpoint(ShuffleBoardManager.fourBarLow.getDouble(fourBarLowSetpoint));
    }

    // SmartMotion is motion profiling generated by the spark, requires additional tuning
    private void setSetpoint(double setpoint){
        if (!ENABLE_SMART_MOTION){
            elevatorPIDController.setReference(setpoint, ControlType.kPosition);
        }else{
            elevatorPIDController.setReference(setpoint, ControlType.kSmartMotion);
        }
    }

    private void getSetpoint(){
    }

    private void fourBarSetSetpoint(double setpoint){
        // Pivot will be 0 to 180
        fourBarTalon.set(ControlMode.Position, setpoint);
    }
}
