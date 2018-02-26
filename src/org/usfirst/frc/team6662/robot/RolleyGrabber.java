package org.usfirst.frc.team6662.robot.subsystems;

import org.usfirst.frc.team6662.robot.RobotMap;
import org.usfirst.frc.team6662.robot.commands.MoveRolleyGrabberWithXbox;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

public class RolleyGrabber extends Subsystem {
	public static final boolean LEFT_INVERTED = true;
	public static final boolean RIGHT_INVERTED = false;
	
	public static final double LEFT_NOMINAL_OUTPUT_FORWARD = 0;
	public static final double RIGHT_NOMINAL_OUTPUT_FORWARD = 0;
	
	public static final double LEFT_NOMINAL_OUTPUT_REVERSE = 0;
	public static final double RIGHT_NOMINAL_OUTPUT_REVERSE = 0;
	
	public static final double LEFT_PEAK_OUTPUT_FORWARD = 1;
	public static final double RIGHT_PEAK_OUTPUT_FORWARD = 1;
	
	public static final double LEFT_PEAK_OUTPUT_REVERSE = -1;
	public static final double RIGHT_PEAK_OUTPUT_REVERSE = -1;
	
	public static final int DEFAULT_TIMEOUT = 0;
	
	private WPI_TalonSRX leftMotor = new WPI_TalonSRX(RobotMap.ROLLEY_GRABBER_LEFT_MOTOR);
	private WPI_TalonSRX rightMotor = new WPI_TalonSRX(RobotMap.ROLLEY_GRABBER_RIGHT_MOTOR);
	
	public RolleyGrabber() {
		super("Rolley Grabber");
		
		// Configure motor controller neutral mode
		leftMotor.setNeutralMode(NeutralMode.Brake);
		rightMotor.setNeutralMode(NeutralMode.Brake);
		
		// Invert motor controller output
		leftMotor.setInverted(LEFT_INVERTED);
		rightMotor.setInverted(RIGHT_INVERTED);
		
		// Set nominal (minimum) outputs
		leftMotor.configNominalOutputForward(LEFT_NOMINAL_OUTPUT_FORWARD, DEFAULT_TIMEOUT);
		rightMotor.configNominalOutputForward(RIGHT_NOMINAL_OUTPUT_FORWARD, DEFAULT_TIMEOUT);
		
		leftMotor.configNominalOutputReverse(LEFT_NOMINAL_OUTPUT_REVERSE, DEFAULT_TIMEOUT);
		rightMotor.configNominalOutputReverse(RIGHT_NOMINAL_OUTPUT_REVERSE, DEFAULT_TIMEOUT);

		// Set peak (maximum) outputs
		leftMotor.configPeakOutputForward(LEFT_PEAK_OUTPUT_FORWARD, DEFAULT_TIMEOUT);
		rightMotor.configPeakOutputForward(RIGHT_PEAK_OUTPUT_FORWARD, DEFAULT_TIMEOUT);
		
		leftMotor.configPeakOutputReverse(LEFT_PEAK_OUTPUT_REVERSE, DEFAULT_TIMEOUT);
		rightMotor.configPeakOutputReverse(RIGHT_PEAK_OUTPUT_REVERSE, DEFAULT_TIMEOUT);
	}
	
	public void moveLeftMotor(double speed) {
		leftMotor.set(ControlMode.PercentOutput, speed);
	}
	
	public void moveRightMotor(double speed) {
		rightMotor.set(ControlMode.PercentOutput, speed);
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new MoveRolleyGrabberWithXbox());
	}
}
