package org.pvcpirates.frc2019.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.NeutralMode;
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
    public static double defaultSetpoint = -10;
    public static double hatchLowSetpoint = -20;
    public static double hatchMidSetpoint = -30;
    public static double hatchHighSetpoint = -40;
    public static double cargoLowSetpoint = -50;
    public static double cargoMidSetpoint = -60;

    private static double activeSetpoint = 0;
    public static double fourBarHighSetpoint = -1651;
    public static double fourBarMidSetpoint = -300;
    public static double fourBarLowSetpoint = 0;

    public static final double ELEVATOR_F = 0;
    public static final double ELEVATOR_P = 0.5;
    public static final double ELEVATOR_I = 0;
    public static final double ELEVATOR_D = 0;
    public static final double ELEVATOR_LOOP_RAMP_RATE = 0;
    public static final double ELEVATOR_MAX_ACCEL = 0;
    public static final double ELEVATOR_MAX_VELOC = 0;
    public static final double ELEVATOR_MIN_VELOC = 0;

    public static final double FOUR_BAR_P = 4;
    public static final double FOUR_BAR_I = 0;
    public static final double FOUR_BAR_D = 0;
    public static final double FOUR_BAR_F = 0;

    public static final boolean ENABLE_SMART_MOTION = false;    

    public void initialize(){
        //16:22 gear ratio for encoder to fourbar
        fourBarTalon.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
        //fourBarTalon.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen);
        //fourBarTalon.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen);
        fourBarTalon.setNeutralMode(NeutralMode.Brake);
        fourBarTalon.setSensorPhase(true);
        fourBarTalon.setInverted(true);
        //elevatorSparkMax.get
        forwardLimitSwitch.enableLimitSwitch(true);
        reverseLimitSwitch.enableLimitSwitch(true);
        setPIDFromShuffleboard();
    }
    @Override
    public void defaultState() {
        moveToDefault();
    }

    public void setPIDFromShuffleboard(){
        elevatorPIDController.setP(ShuffleBoardManager.pElevatorEntry.getDouble(ELEVATOR_P));
        elevatorPIDController.setI(ShuffleBoardManager.iElevatorEntry.getDouble(ELEVATOR_I));
        elevatorPIDController.setD(ShuffleBoardManager.dElevatorEntry.getDouble(ELEVATOR_D));
        elevatorPIDController.setFF(ShuffleBoardManager.fElevatorEntry.getDouble(ELEVATOR_F));
        elevatorSparkMax.setClosedLoopRampRate(ShuffleBoardManager.loopElevatorEntry.getDouble(ELEVATOR_LOOP_RAMP_RATE));
        elevatorPIDController.setSmartMotionMaxAccel(ShuffleBoardManager.maxAccelElevatorEntry.getDouble(ELEVATOR_MAX_ACCEL),0);
        elevatorPIDController.setSmartMotionMaxVelocity(ShuffleBoardManager.maxVelocElevatorEntry.getDouble(ELEVATOR_MAX_VELOC),0);
        elevatorPIDController.setSmartMotionMinOutputVelocity(ShuffleBoardManager.minVelocElevatorEntry.getDouble(ELEVATOR_MIN_VELOC),0);

        fourBarTalon.config_kP(0, ShuffleBoardManager.pFourBarEntry.getDouble(FOUR_BAR_P));
        fourBarTalon.config_kI(0, ShuffleBoardManager.iFourBarEntry.getDouble(FOUR_BAR_I));
        fourBarTalon.config_kD(0, ShuffleBoardManager.dFourBarEntry.getDouble(FOUR_BAR_D));
        fourBarTalon.config_kF(0, ShuffleBoardManager.fFourBarEntry.getDouble(FOUR_BAR_F));
        fourBarTalon.configPeakOutputForward(.75);
        fourBarTalon.configPeakOutputReverse(-.75);
    }

    // Intake, default, 6 s
    // please do fourbar stuff for all of these

    public void moveToIntake(){
        setSetpoint(intakeSetpoint, ENABLE_SMART_MOTION);
        //moveFourBarToLow();
    }

    public void moveToDefault(){
        setSetpoint(defaultSetpoint, ENABLE_SMART_MOTION);
        ///moveFourBarToMid();
    }

    public void moveToHatchLow(){
        setSetpoint(hatchLowSetpoint, ENABLE_SMART_MOTION);
        //moveFourBarToMid();
    }

    public void moveToHatchMid(){
        setSetpoint(hatchMidSetpoint, ENABLE_SMART_MOTION);
        //moveFourBarToMid();
    }

    public void moveToHatchHigh(){
        setSetpoint(hatchHighSetpoint, ENABLE_SMART_MOTION);
        //moveFourBarToHigh();
    }

    public void moveToCargoLow(){
        setSetpoint(cargoLowSetpoint, ENABLE_SMART_MOTION);
        //moveFourBarToMid();
    }

    public void moveToCargoMid(){
        setSetpoint(cargoMidSetpoint, ENABLE_SMART_MOTION);
        //moveFourBarToMid();
    }

    public void moveFourBarToHigh(){
        System.out.println("High is being called");
        fourBarSetSetpoint(fourBarHighSetpoint);
    }

    public void moveFourBarToMid(){
        System.out.println("Mid is being called");
        fourBarSetSetpoint(fourBarMidSetpoint);
    }

    public void moveFourBarToLow(){
        System.out.println("Low is being called");
        fourBarSetSetpoint(fourBarLowSetpoint);
    }

    // SmartMotion is motion profiling generated by the spark, requires additional tuning
    public void setSetpoint(double setpoint, boolean smartMotion){
        System.out.println("Target setpoint:"+setpoint);
        System.out.println("Actual setpoint:"+getSetpoint());
        
        if (activeSetpoint != setpoint){
            System.out.println("Set setpoint!");
            if (!smartMotion){
                elevatorPIDController.setReference(setpoint, ControlType.kPosition);
            }else{
                elevatorPIDController.setReference(setpoint, ControlType.kSmartMotion);
            }
            activeSetpoint = setpoint;
        }
        
    }

    public double getSetpoint(){
        return elevatorEncoder.getPosition();
    }

    private void fourBarSetSetpoint(double setpoint){
        // Pivot will be 0 to 180
        System.out.println("Target encoder pos"+setpoint);
        fourBarTalon.set(ControlMode.Position, setpoint);
    }
}
