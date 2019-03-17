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
    public static double defaultSetpoint =intakeSetpoint + 1;
    public static double hatchLowSetpoint =intakeSetpoint + .76;
    public static double hatchMidSetpoint =intakeSetpoint + 72;
    public static double hatchHighSetpoint =intakeSetpoint + 81;
    public static double cargoLowSetpoint =intakeSetpoint + 52.66;
    public static double cargoMidSetpoint =intakeSetpoint + 71.8;
    public static double cargoHPSetpoint =intakeSetpoint+ 48;

    private static double activeSetpoint = 0;
    public static double fourBarHighSetpoint = -1815;
    public static double fourBarMidSetpoint = -390;
    public static double fourBarLowSetpoint = 350;

    public static double ELEVATOR_F = 0;
    public static double ELEVATOR_P = 0.5;
    public static double ELEVATOR_I = 0;
    public static double ELEVATOR_D = 0;
    public static double ELEVATOR_LOOP_RAMP_RATE = 0;
    public static double ELEVATOR_MAX_ACCEL = 0;
    public static double ELEVATOR_MAX_VELOC = 0;
    public static double ELEVATOR_MIN_VELOC = 0;

    public static double FOUR_BAR_P = 4;
    public static double FOUR_BAR_I = 0;
    public static double FOUR_BAR_D = 0;
    public static double FOUR_BAR_F = 0;

    public static final boolean ENABLE_SMART_MOTION = false;    

    public void initialize(){
        fourBarTalon.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
        //fourBarTalon.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen);
        //fourBarTalon.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen);
        fourBarTalon.setNeutralMode(NeutralMode.Brake);
        fourBarTalon.setSensorPhase(true);
        fourBarTalon.setInverted(true);
        fourBarTalon.configPeakOutputForward(.25);
        fourBarTalon.configPeakOutputReverse(-.7);
        elevatorSparkMax.setInverted(true);
        elevatorPIDController.setOutputRange(-.5, 1);
        forwardLimitSwitch.enableLimitSwitch(false);
        reverseLimitSwitch.enableLimitSwitch(false);
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
        fourBarSetSetpoint(fourBarHighSetpoint);
    }

    public void moveToCargoLow(){
        setSetpoint(cargoLowSetpoint);
        fourBarSetSetpoint(fourBarMidSetpoint);
    }

    public void moveToCargoMid(){
        setSetpoint(cargoMidSetpoint);
        fourBarSetSetpoint(fourBarHighSetpoint);
    }
    public void moveToCargoHP(){
        setSetpoint(cargoHPSetpoint);
        fourBarSetSetpoint(fourBarHighSetpoint);
    }
    public void moveFourBarToHigh(){
        fourBarSetSetpoint(fourBarHighSetpoint);
    }

    public void moveFourBarToMid(){
        
        fourBarSetSetpoint(fourBarMidSetpoint);
    }

    public void moveFourBarToLow(){
        
        fourBarSetSetpoint(fourBarLowSetpoint);
    }
    
    // SmartMotion is motion profiling generated by the spark, requires additional tuning
    private void setSetpoint(double setpoint){
        System.out.println("Target setpoint:"+setpoint);
        System.out.println("Actual setpoint:"+elevatorEncoder.getPosition());
        if (activeSetpoint != setpoint){
            activeSetpoint = setpoint;
            if (!ENABLE_SMART_MOTION){
                elevatorPIDController.setReference(setpoint, ControlType.kPosition);
            }else{
                elevatorPIDController.setReference(setpoint, ControlType.kSmartMotion);
            }
        }
    }



    private void fourBarSetSetpoint(double setpoint){
        // Pivot will be 0 to 180
        fourBarTalon.set(ControlMode.Position, setpoint);
    }
}
