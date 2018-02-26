package org.usfirst.frc.team6662.robot.subsystems;

import org.usfirst.frc.team6662.robot.RobotMap;
import org.usfirst.frc.team6662.robot.commands.TankDriveWithXbox;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.sensors.PigeonIMU;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Drivetrain extends Subsystem {
	public static final int DEFAULT_PID_IDX = 0;
	public static final int DEFAULT_TIMEOUT = 0;
	
	public static final boolean PHASE_LEFT_SENSOR = true;
	public static final boolean PHASE_RIGHT_SENSOR = false;
	
	public static final boolean LEFT_INVERTED = true;
	public static final boolean RIGHT_INVERTED = true;
	
	public static final double LEFT_NOMINAL_OUTPUT_FORWARD = 0;
	public static final double RIGHT_NOMINAL_OUTPUT_FORWARD = 0;
	
	public static final double LEFT_NOMINAL_OUTPUT_REVERSE = 0;
	public static final double RIGHT_NOMINAL_OUTPUT_REVERSE = 0;
	
	public static final double LEFT_PEAK_OUTPUT_FORWARD = 1;
	public static final double RIGHT_PEAK_OUTPUT_FORWARD = 1;
	
	public static final double LEFT_PEAK_OUTPUT_REVERSE = -1;
	public static final double RIGHT_PEAK_OUTPUT_REVERSE = -1;
	
	public static final double LEFT_F = 0;
	public static final double LEFT_P = 1;
	public static final double LEFT_I = 0;
	public static final double LEFT_D = 0;
	
	public static final double RIGHT_F = 0;
	public static final double RIGHT_P = 1;
	public static final double RIGHT_I = 0;
	public static final double RIGHT_D = 0;
	
	public static final int ALLOWABLE_LOOP_ERROR = 0;
	
	public static final boolean COMPRESSOR_CLOSED_LOOP = true;
	
	public static final double WHEEL_DIAMETER = 6; // inches
	public static final double WHEEL_CIRCUMFERENCE = Math.PI * WHEEL_DIAMETER; // inches
	
	public static final double ENCODER_UNITS_PER_ROTATION = 4096;
	public static final int SHAFT_RATIO = 3;
	
	private WPI_TalonSRX frontLeft = new WPI_TalonSRX(RobotMap.FRONT_LEFT_MOTOR);
	private WPI_TalonSRX rearLeft = new WPI_TalonSRX(RobotMap.REAR_LEFT_MOTOR);
	private SpeedControllerGroup leftSide = new SpeedControllerGroup(frontLeft, rearLeft);
	
	private WPI_TalonSRX frontRight = new WPI_TalonSRX(RobotMap.FRONT_RIGHT_MOTOR);
	private WPI_TalonSRX rearRight = new WPI_TalonSRX(RobotMap.REAR_RIGHT_MOTOR);
	private SpeedControllerGroup rightSide = new SpeedControllerGroup(frontRight, rearRight);
	
	private DifferentialDrive drivetrain = new DifferentialDrive(leftSide, rightSide);
	
	private Compressor compressor = new Compressor();
	private DoubleSolenoid shifter = new DoubleSolenoid(RobotMap.SHIFTER_FORWARD, 
			RobotMap.SHIFTER_REVERSE);
	
	private PigeonIMU gyro = new PigeonIMU(rearRight);
	
	public enum Gear {
		LOW, HIGH
	}
	
	private Gear shiftState = Gear.LOW;
	
	private double[] yawRollPitch = new double[3];
	private short[] accelerationXYZ = new short[3];
	
	public Drivetrain() {
		super("Drivetrain");
		
		// Rear motor controllers follow front motor controllers
		rearLeft.follow(frontLeft);
		rearRight.follow(frontRight);
		
		// Configure sensors
		frontLeft.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 
				DEFAULT_PID_IDX, DEFAULT_TIMEOUT);
		frontRight.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 
				DEFAULT_PID_IDX, DEFAULT_TIMEOUT);
		
		// Set sensor phase
		frontLeft.setSensorPhase(PHASE_LEFT_SENSOR);
		frontRight.setSensorPhase(PHASE_RIGHT_SENSOR);
		
		// Configure motor controller neutral mode
		frontLeft.setNeutralMode(NeutralMode.Brake);
		rearLeft.setNeutralMode(NeutralMode.Brake);
		frontRight.setNeutralMode(NeutralMode.Brake);
		rearRight.setNeutralMode(NeutralMode.Brake);
		
		// Invert motor controller output
		frontLeft.setInverted(LEFT_INVERTED);
		rearLeft.setInverted(LEFT_INVERTED);
		frontRight.setInverted(RIGHT_INVERTED);
		rearRight.setInverted(RIGHT_INVERTED);
		
		// Set nominal (minimum) and peak (maximum) outputs
		frontLeft.configNominalOutputForward(LEFT_NOMINAL_OUTPUT_FORWARD, DEFAULT_TIMEOUT);
		frontLeft.configNominalOutputReverse(LEFT_NOMINAL_OUTPUT_REVERSE, DEFAULT_TIMEOUT);
		frontLeft.configPeakOutputForward(LEFT_PEAK_OUTPUT_FORWARD, DEFAULT_TIMEOUT);
		frontLeft.configPeakOutputReverse(LEFT_PEAK_OUTPUT_REVERSE, DEFAULT_TIMEOUT);
		
		frontRight.configNominalOutputForward(RIGHT_NOMINAL_OUTPUT_FORWARD, DEFAULT_TIMEOUT);
		frontRight.configNominalOutputReverse(RIGHT_NOMINAL_OUTPUT_REVERSE, DEFAULT_TIMEOUT);
		frontRight.configPeakOutputForward(RIGHT_PEAK_OUTPUT_FORWARD, DEFAULT_TIMEOUT);
		frontRight.configPeakOutputReverse(RIGHT_PEAK_OUTPUT_REVERSE, DEFAULT_TIMEOUT);
		
		// Configure closed-loop control
		frontLeft.config_kF(DEFAULT_PID_IDX, LEFT_F, DEFAULT_TIMEOUT);
		frontLeft.config_kP(DEFAULT_PID_IDX, LEFT_P, DEFAULT_TIMEOUT);
		frontLeft.config_kI(DEFAULT_PID_IDX, LEFT_I, DEFAULT_TIMEOUT);
		frontLeft.config_kD(DEFAULT_PID_IDX, LEFT_D, DEFAULT_TIMEOUT);
		frontLeft.configAllowableClosedloopError(DEFAULT_PID_IDX, ALLOWABLE_LOOP_ERROR, DEFAULT_TIMEOUT);
		
		frontRight.config_kF(DEFAULT_PID_IDX, RIGHT_F, DEFAULT_TIMEOUT);
		frontRight.config_kP(DEFAULT_PID_IDX, RIGHT_P, DEFAULT_TIMEOUT);
		frontRight.config_kI(DEFAULT_PID_IDX, RIGHT_I, DEFAULT_TIMEOUT);
		frontRight.config_kD(DEFAULT_PID_IDX, RIGHT_D, DEFAULT_TIMEOUT);
		frontRight.configAllowableClosedloopError(DEFAULT_PID_IDX, ALLOWABLE_LOOP_ERROR, DEFAULT_TIMEOUT);
		
		// Zero initial encoder position
		zeroPosition();
		
		// Zero initial gyro angle
		zeroYaw();
		
		// Set closed loop control for compressor
		compressor.setClosedLoopControl(COMPRESSOR_CLOSED_LOOP);
		
		// Initialize to low gear
		shiftGear(Gear.LOW);
		SmartDashboard.putString("Gear", getShiftStateName());
	}
	
	public void arcadeDrive(double speed, double rotation) {
		drivetrain.arcadeDrive(speed, rotation);
	}
	
	public void tankDrive(double leftSpeed, double rightSpeed) {
		drivetrain.tankDrive(leftSpeed, rightSpeed);
		
		// Un-comment the lines below to get encoder position readings
		
		//System.out.println("L:" + frontLeft.getSensorCollection().getQuadraturePosition());
		//System.out.println("R:" + frontRight.getSensorCollection().getQuadraturePosition());
	}
	
	public void driveDistance(double targetDistance) { // inches
		double targetRotations = targetDistance / WHEEL_CIRCUMFERENCE;
		double targetPosition = targetRotations * ENCODER_UNITS_PER_ROTATION / SHAFT_RATIO;

		frontLeft.set(ControlMode.Position, targetPosition);
		frontRight.set(ControlMode.Position, targetPosition);
	}
	
	public double getCurrentPosition() {
		double frontLeftPosition = frontLeft.getSelectedSensorPosition(DEFAULT_TIMEOUT);
		double frontRightPosition = frontRight.getSelectedSensorPosition(DEFAULT_TIMEOUT);
		double averagePosition = (frontLeftPosition + frontRightPosition) / 2;
		
		return averagePosition;
	}
	
	public void zeroPosition() {
		frontLeft.setSelectedSensorPosition(0, DEFAULT_PID_IDX, DEFAULT_TIMEOUT);
		frontRight.setSelectedSensorPosition(0, DEFAULT_PID_IDX, DEFAULT_TIMEOUT);
	}
	
	public void updateYawRollPitch() {
		gyro.getYawPitchRoll(yawRollPitch);
	}
	
	public void updateAccelerationXYZ() {
		gyro.getBiasedAccelerometer(accelerationXYZ);
	}
	
	public double getCurrentYaw() {
		updateYawRollPitch();
		
		return yawRollPitch[0];
	}
	
	public double getCurrentRoll() {
		updateYawRollPitch();
		
		return yawRollPitch[1];
	}
	
	public double getCurrentPitch() {
		updateYawRollPitch();
		
		return yawRollPitch[2];
	}
	
	public double getCurrentAccelerationX() {
		updateAccelerationXYZ();
		
		return accelerationXYZ[0];
	}
	
	public double getCurrentAccelerationY() {
		updateAccelerationXYZ();
		
		return accelerationXYZ[1];
	}
	
	public double getCurrentAccelerationZ() {
		updateAccelerationXYZ();
		
		return accelerationXYZ[2];
	}
	
	public void zeroYaw() {
		gyro.setYaw(0, DEFAULT_TIMEOUT);
	}
	
	public void shiftGear(Gear targetGear) {
		switch (targetGear) {
			case LOW:
				shifter.set(DoubleSolenoid.Value.kReverse);
				shiftState = Gear.LOW;
			case HIGH:
				shifter.set(DoubleSolenoid.Value.kForward);
				shiftState = Gear.HIGH;
		}
	}
	
	public String getShiftStateName() {
		return shiftState.toString();
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new TankDriveWithXbox());
	}
}
