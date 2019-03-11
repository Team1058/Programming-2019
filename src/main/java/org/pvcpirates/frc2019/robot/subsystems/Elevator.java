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

    public static final double ELEVATOR_F = 0;
    public static final double ELEVATOR_P = 0;
    public static final double ELEVATOR_I = 0;
    public static final double ELEVATOR_D = 0;
    public static final double ELEVATOR_LOOP_RAMP_RATE = 0;
    public static final double ELEVATOR_MAX_ACCEL = 0;
    public static final double ELEVATOR_MAX_VELOC = 0;
    public static final double ELEVATOR_MIN_VELOC = 0;

    public static final double FOUR_BAR_P = 0;
    public static final double FOUR_BAR_I = 0;
    public static final double FOUR_BAR_D = 0;
    public static final double FOUR_BAR_F = 0;

    public static final boolean ENABLE_SMART_MOTION = false;    

    public void initialize(){
        // 16:22 gear ratio for encoder to fourbar
        fourBarTalon.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
        fourBarTalon.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyClosed);
        fourBarTalon.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyClosed);
        
        forwardLimitSwitch.enableLimitSwitch(true);
        reverseLimitSwitch.enableLimitSwitch(true);
        setPIDFromShuffleboard();
        if(Robot.DEBUG){
            setConstantsFromShuffleBoard();
        }
    }

    @Override
    public void defaultState() {
        moveToDefault();
    }

    private void setConstantsFromShuffleBoard(){
        intakeSetpoint = ShuffleBoardManager.elevatorIntakeSetpointEntry.getDouble(intakeSetpoint);
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
    }

    // Intake, default, 6 s
    // please do fourbar stuff for all of these

    public void moveToIntake(){
        setSetpoint(intakeSetpoint, ENABLE_SMART_MOTION);
        fourBarSetSetpoint(ShuffleBoardManager.fourBarLow.getDouble(fourBarLowSetpoint));
    }

    public void moveToDefault(){
        setSetpoint(ShuffleBoardManager.elevatorDefaultSetpointEntry.getDouble(defaultSetpoint), ENABLE_SMART_MOTION);
        fourBarSetSetpoint(ShuffleBoardManager.fourBarMid.getDouble(fourBarMidSetpoint));
    }

    public void moveToHatchLow(){
        setSetpoint(ShuffleBoardManager.elevatorHatchLowSetpointEntry.getDouble(hatchLowSetpoint), ENABLE_SMART_MOTION);
        fourBarSetSetpoint(ShuffleBoardManager.fourBarMid.getDouble(fourBarMidSetpoint));
    }

    public void moveToHatchMid(){
        setSetpoint(ShuffleBoardManager.elevatorHatchMidSetpointEntry.getDouble(hatchMidSetpoint), ENABLE_SMART_MOTION);
        fourBarSetSetpoint(ShuffleBoardManager.fourBarMid.getDouble(fourBarMidSetpoint));
    }

    public void moveToHatchHigh(){
        setSetpoint(ShuffleBoardManager.elevatorHatchHighSetpointEntry.getDouble(hatchHighSetpoint), ENABLE_SMART_MOTION);
        fourBarSetSetpoint(ShuffleBoardManager.fourBarHigh.getDouble(fourBarHighSetpoint));
    }

    public void moveToCargoLow(){
        setSetpoint(ShuffleBoardManager.elevatorCargoLowSetpointEntry.getDouble(cargoLowSetpoint), ENABLE_SMART_MOTION);
        fourBarSetSetpoint(ShuffleBoardManager.fourBarMid.getDouble(fourBarMidSetpoint));
    }

    public void moveToCargoMid(){
        setSetpoint(ShuffleBoardManager.elevatorCargoMidSetpointEntry.getDouble(cargoMidSetpoint), ENABLE_SMART_MOTION);
        fourBarSetSetpoint(ShuffleBoardManager.fourBarMid.getDouble(fourBarMidSetpoint));
    }

    // SmartMotion is motion profiling generated by the spark, requires additional tuning
    private void setSetpoint(double setpoint, boolean smartMotion){
        if (!smartMotion){
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
