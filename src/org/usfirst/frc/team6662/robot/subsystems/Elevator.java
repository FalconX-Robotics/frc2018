package org.usfirst.frc.team6662.robot.subsystems;

import org.usfirst.frc.team6662.robot.RobotMap;
import org.usfirst.frc.team6662.robot.commands.MoveElevatorWithXbox;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Elevator extends Subsystem {
	public static final int DEFAULT_PID_IDX = 0;
	public static final int DEFAULT_TIMEOUT = 0;
	
	public static final boolean PHASE_SENSOR = true;
	
	public static final boolean MOTOR_INVERTED = true;
	
	public static final double MOTOR_NOMINAL_OUTPUT_FORWARD = 0;
	public static final double MOTOR_NOMINAL_OUTPUT_REVERSE = 0;
	
	public static final double MOTOR_PEAK_OUTPUT_FORWARD = 1;
	public static final double MOTOR_PEAK_OUTPUT_REVERSE = -1;
	
	public static final double F = 0;
	public static final double P = 1;
	public static final double I = 0;
	public static final double D = 0;
	
	public static final int PID_MAIN_LOOP = 0;
	
	public static final int ALLOWABLE_LOOP_ERROR = 0;
	
	public static final double SPROCKET_DIAMETER = 1.751;
	public static final double SPROCKET_CIRCUMFERENCE = Math.PI * SPROCKET_DIAMETER;
	public static final double ENCODER_UNITS_PER_ROTATION = 4096;
	public static final int SHAFT_RATIO = 7;
	
	public static final double UP_SPEED_LIMIT = 0.8;
	public static final double DOWN_SPEED_LIMIT = -0.6;
	
	private WPI_TalonSRX motor = new WPI_TalonSRX(RobotMap.ELEVATOR_MOTOR);
	
	public Elevator() {
		super("Elevator");
		
		// Configure sensors
		motor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 
				DEFAULT_PID_IDX, DEFAULT_TIMEOUT);
		
		// Set sensor phase
		motor.setSensorPhase(PHASE_SENSOR);
		
		// Configure motor controller neutral mode
		motor.setNeutralMode(NeutralMode.Brake);
		
		// Invert motor controller output
		motor.setInverted(MOTOR_INVERTED);
		
		// Set nominal (minimum) and peak (maximum) outputs
		motor.configNominalOutputForward(MOTOR_NOMINAL_OUTPUT_FORWARD, DEFAULT_TIMEOUT);
		motor.configNominalOutputReverse(MOTOR_NOMINAL_OUTPUT_REVERSE, DEFAULT_TIMEOUT);
		motor.configPeakOutputForward(MOTOR_PEAK_OUTPUT_FORWARD, DEFAULT_TIMEOUT);
		motor.configPeakOutputReverse(MOTOR_PEAK_OUTPUT_REVERSE, DEFAULT_TIMEOUT);
		
		// Configure closed-loop control
		/*motor.config_kF(DEFAULT_PID_IDX, F, DEFAULT_TIMEOUT);
		motor.config_kP(DEFAULT_PID_IDX, P, DEFAULT_TIMEOUT);
		motor.config_kI(DEFAULT_PID_IDX, I, DEFAULT_TIMEOUT);
		motor.config_kD(DEFAULT_PID_IDX, D, DEFAULT_TIMEOUT);*/
		motor.configAllowableClosedloopError(DEFAULT_PID_IDX, ALLOWABLE_LOOP_ERROR, DEFAULT_TIMEOUT);
		
		// Zero initial encoder position
		zeroPosition();
	}
	
	public double getError() {
		return motor.getClosedLoopError(PID_MAIN_LOOP);
	}
	
	public void move(double speed) {
		if (speed > 0) {
			speed = Math.min(speed, UP_SPEED_LIMIT);
		}
		else {
			speed = Math.max(speed, DOWN_SPEED_LIMIT);
		}
		
		motor.set(ControlMode.PercentOutput, speed);
	}
	
	public void goToPosition(double targetDistance) {
		double targetRotations = targetDistance / SPROCKET_CIRCUMFERENCE;
		double targetPosition = targetRotations * ENCODER_UNITS_PER_ROTATION / SHAFT_RATIO;
		
		motor.set(ControlMode.Position, targetPosition);
	}
	
	public double getCurrentPosition() {
		return motor.getSelectedSensorPosition(DEFAULT_TIMEOUT);
	}
	
	public void zeroPosition() {
		motor.setSelectedSensorPosition(0, DEFAULT_PID_IDX, DEFAULT_TIMEOUT);
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new MoveElevatorWithXbox());
	}
}
