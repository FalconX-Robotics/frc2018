package org.usfirst.frc.team6662.robot.subsystems;

import org.usfirst.frc.team6662.robot.OI;
import org.usfirst.frc.team6662.robot.Robot;
import org.usfirst.frc.team6662.robot.RobotMap;
import org.usfirst.frc.team6662.robot.commands.TankDriveWithJoystick;

import com.ctre.phoenix.sensors.PigeonIMU;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.interfaces.Gyro;

public class Drivetrain extends Subsystem {
	public Drivetrain() {
		super("Drivetrain");
		compressor.setClosedLoopControl(true);
	}
	public static final boolean HIGH_GEAR = true;
	public static final boolean LOW_GEAR = false;
	
	private Spark frontLeft = new Spark(RobotMap.FRONT_LEFT_MOTOR);
	private Spark rearLeft = new Spark(RobotMap.REAR_LEFT_MOTOR);
	private SpeedControllerGroup leftSide = new SpeedControllerGroup(frontLeft, rearLeft);
	
	private Spark frontRight = new Spark(RobotMap.FRONT_RIGHT_MOTOR);
	private Spark rearRight = new Spark(RobotMap.REAR_RIGHT_MOTOR);
	private SpeedControllerGroup rightSide = new SpeedControllerGroup(frontRight, rearRight);
	
	private DifferentialDrive drivetrain = new DifferentialDrive(leftSide, rightSide);
	
	private Compressor compressor = new Compressor();
	private DoubleSolenoid shifter = new DoubleSolenoid(RobotMap.SHIFTER_FORWARD_PORT, 
			RobotMap.SHIFTER_REVERSE_PORT);
	
	private Encoder leftEncoder = new Encoder(RobotMap.ENCODER_INPUT_LEFT_A, RobotMap.ENCODER_INPUT_LEFT_B);
	private Encoder rightEncoder = new Encoder(RobotMap.ENCODER_INPUT_RIGHT_A, RobotMap.ENCODER_INPUT_RIGHT_B);
	  
	private boolean shiftState = LOW_GEAR;
	
	private Gyro gyroscope = new ADXRS450_Gyro();
	
	private PigeonIMU pigeon = new PigeonIMU(RobotMap.PIGEON_PORT);
	
	public PigeonIMU getPigeon() {
		return pigeon;
	}
	
	public double getAngle() {
		return gyroscope.getAngle();
	}
	
	//stuff for the robot position mapping (optional)
	private double positionxy [] = {0,0};
	private double changeinxy [] = {0,0};
	private double errorxy [] = {0,0};
	
	
	public Gyro getGyro() {
		return gyroscope;
	}
	
	public Encoder getLeftEncoder() {
		return leftEncoder;
	}
	
	public Encoder getRightEncoder() {
		return rightEncoder;
	}
	
	public void tankDrive(double leftSpeed, double rightSpeed) {
		drivetrain.tankDrive(leftSpeed, rightSpeed);
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

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new TankDriveWithJoystick(Robot.oi.getJoystick(), OI.LEFT_Y_AXIS, OI.RIGHT_Y_AXIS));
	}
}
