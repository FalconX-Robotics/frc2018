package org.usfirst.frc.team6662.robot.subsystems;

import org.usfirst.frc.team6662.robot.OI;
import org.usfirst.frc.team6662.robot.Robot;
import org.usfirst.frc.team6662.robot.RobotMap;
import org.usfirst.frc.team6662.robot.commands.TankDriveWithJoystick;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
//import edu.wpi.first.wpilibj.Encoder;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Gyro;

public class Drivetrain extends Subsystem {
	
	public static final boolean HIGH_GEAR = true;
	public static final boolean LOW_GEAR = false;
	private static final double Pval = 0;
	private static final double Ival = 0;
	private static final double Dval = 0;
	private static final double Kval = 0;
	
	public static final int LEFT_PEAK_OUTPUT_FORWARD = 1;
	public static final int RIGHT_PEAK_OUTPUT_FORWARD = 1;
	public static final int LEFT_PEAK_OUTPUT_REVERSE = 1;
	public static final int RIGHT_PEAK_OUTPUT_REVERSE = 1;
	
	public static final int DEFAULT_TIMEOUT = 1; //안녕하세요! 잘있으시죠? 방갑습니다!
	
	private WPI_TalonSRX frontLeft = new WPI_TalonSRX(RobotMap.FRONT_LEFT_MOTOR);
	private WPI_TalonSRX rearLeft = new WPI_TalonSRX(RobotMap.REAR_LEFT_MOTOR);
	
	private WPI_TalonSRX frontRight = new WPI_TalonSRX(RobotMap.FRONT_RIGHT_MOTOR);
	private WPI_TalonSRX rearRight = new WPI_TalonSRX(RobotMap.REAR_RIGHT_MOTOR);

	private Compressor compressor = new Compressor();
	private DoubleSolenoid shifter = new DoubleSolenoid(RobotMap.SHIFTER_FORWARD_PORT, 
			RobotMap.SHIFTER_REVERSE_PORT);
	/*
	private Encoder leftEncoder = new Encoder(RobotMap.ENCODER_INPUT_LEFT_A, RobotMap.ENCODER_INPUT_LEFT_B);
	private Encoder rightEncoder = new Encoder(RobotMap.ENCODER_INPUT_RIGHT_A, RobotMap.ENCODER_INPUT_RIGHT_B);
	*/
	//us means microseconds? µ = micro
	
	private boolean shiftState = LOW_GEAR;
	
	private DifferentialDrive drivetrain = new DifferentialDrive(frontLeft, frontRight);
	
	private Gyro gyroscope = new ADXRS450_Gyro();
	
	public double getAngle() {
		return gyroscope.getAngle();
	}
	
	public Gyro getGyro() {
		return gyroscope;
	}
	public DifferentialDrive getDrivetrain() {
		return drivetrain;
	}
	/*
	public Encoder getLeftEncoder() {
		return leftEncoder;
	}
	
	public Encoder getRightEncoder() {
		return rightEncoder;
	}
	*/
	public void driveForward(double velocity) {
		Robot.drivetrain.frontLeft.set(velocity);
		Robot.drivetrain.frontRight.set(velocity);
		
	}
	
	public boolean isDoneDriving() {//is done driving in autonomous driving
		return frontLeft.getOutputCurrent() == 0 && frontRight.getOutputCurrent() == 0;
	}
	public void toggleGear() {
		if (shiftState == LOW_GEAR) {
			shiftToHighGear();
		}
		else {
			shiftToLowGear();
		}
	}
	
	public void shiftToHighGear() {
		shifter.set(DoubleSolenoid.Value.kForward);
		shiftState = HIGH_GEAR;
	}
	
	public void shiftToLowGear() {
		shifter.set(DoubleSolenoid.Value.kReverse);
		shiftState = LOW_GEAR;
	}

	public Drivetrain() {
		super("Drivetrain");
		compressor.setClosedLoopControl(true);
		
		rearLeft.follow(frontLeft);
		rearRight.follow(frontRight);
		
		frontLeft.setInverted(true);
		frontLeft.setNeutralMode(NeutralMode.Brake);
		frontRight.setNeutralMode(NeutralMode.Brake);
		
		frontLeft.configClosedloopRamp(1, 1);
		frontRight.configClosedloopRamp(1, 1);
		
		frontLeft.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 0);
		frontRight.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 0);
		
		int pulseWidthPosLeft = frontLeft.getSensorCollection().getPulseWidthPosition();
		int pulseWidthPosRight = frontRight.getSensorCollection().getPulseWidthPosition();
		int pulseWidthUsLeft = frontLeft.getSensorCollection().getPulseWidthRiseToFallUs();
		int pulseWidthUsRight = frontRight.getSensorCollection().getPulseWidthRiseToFallUs();
		int periodUsLeft = frontLeft.getSensorCollection().getPulseWidthRiseToRiseUs();
		int periodUsRight = frontRight.getSensorCollection().getPulseWidthRiseToRiseUs();
		
		int constP = 0;
		int constI = 0;
		int constD = 0;
		int constK = 0;
		int timeoutMs = 10;
		
		frontLeft.configPeakOutputForward(LEFT_PEAK_OUTPUT_FORWARD, DEFAULT_TIMEOUT);
		frontRight.configPeakOutputForward(RIGHT_PEAK_OUTPUT_FORWARD, DEFAULT_TIMEOUT);
		frontLeft.configPeakOutputReverse(LEFT_PEAK_OUTPUT_REVERSE, DEFAULT_TIMEOUT);
		frontRight.configPeakOutputReverse(RIGHT_PEAK_OUTPUT_REVERSE, DEFAULT_TIMEOUT);
		
		frontLeft.config_kP(constP, Pval, timeoutMs);
		frontLeft.config_kI(constI, Ival, timeoutMs);
		frontLeft.config_kD(constD, Dval, timeoutMs);
		frontLeft.config_kF(constK, Kval, timeoutMs);
		
		frontRight.config_kP(constP, Pval, timeoutMs);
		frontRight.config_kI(constI, Ival, timeoutMs);
		frontRight.config_kD(constD, Dval, timeoutMs);
		frontRight.config_kF(constK, Kval, timeoutMs);
		
	}
	public void tankDrive(double leftSpeed, double rightSpeed) {
		Robot.drivetrain.tankDrive(leftSpeed, rightSpeed);
	}
	
	public void resetPosition() {
		
	}
	public double getCurrentPosition() {
		//average out measurements from encoders.
		return 0.0;
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new TankDriveWithJoystick(Robot.oi.getJoystick(), OI.LEFT_Y_AXIS, OI.RIGHT_Y_AXIS));
	}

	
}
