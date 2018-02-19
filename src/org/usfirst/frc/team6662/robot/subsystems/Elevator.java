package org.usfirst.frc.team6662.robot.subsystems;

import org.usfirst.frc.team6662.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Elevator extends Subsystem {

	private WPI_TalonSRX elevatorMotor = new WPI_TalonSRX(RobotMap.ELEVATOR_MOTOR_ID);
	
	private double MAX_SPEED =  79.0;  // in./sec,  output of gearbox
	private double SPROCKET_DIAMETER = 1.9; //inches
	private double peakOutputForward = 1.;
	private double peakOutputReverse = -1.;
	
	private Elevator elevator = new Elevator();
	public Elevator getElevator() {
		return elevator;
	}
	
	private static final int PID_LoopNum = 0;
	/*
	private static final double PID_F_SETTING = 0;
	private static final double PID_P_SETTING = 50.0;
	private static final double PID_I_SETTING = 0.005;
	private static final double PID_D_SETTING = 0;
	*/
	private static final int timeoutMS = 10;

	//Max Morehead님, 읽고있으면, 꼭 알려줘고싶었어요: 너무 감사함니다!
	public void moveAtJoystickPosition(double position) {
		moveAtSpeed(position * MAX_SPEED);
	}
	public void moveAtSpeed(double targetSpeed) {
	    double talonSpeed = convertToTalonSpeed(targetSpeed);
		elevatorMotor.set(ControlMode.Velocity, talonSpeed);
	}
	private double convertToTalonSpeed(double targetSpeed) {
	    double sprocketCircumference = SPROCKET_DIAMETER * Math.PI;
	    //how many times the sprocket rotates with the given amount of time and speed (inch/sec.)
	    double sprocketRotationsPerSecond = targetSpeed /  sprocketCircumference;
	    /* unit conversion, converts sprocket's rps into units compatible with talon:
	     *  (rotation/second) => (encoder clicks/100ms)
	     */
	    double talonSpeed = 4096. / 10. * sprocketRotationsPerSecond;
	    return talonSpeed;
	}
	public void moveUp(double position){
		elevator.moveAtJoystickPosition(Math.abs(position));
	}
	
	public void moveDown(double position){
		elevator.moveAtJoystickPosition(position *-1.0);
	}
	
    public void initDefaultCommand() {
    		
    }
    public Elevator() {
    		elevatorMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, PID_LoopNum, timeoutMS);
		elevatorMotor.setSensorPhase(true);
		/* The PID(F) value can easily be set up using the Web Configuration
		elevatorMotor.configAllowableClosedloopError(0,PID_LoopNum, timeoutMS);
		elevatorMotor.config_kF(PID_LoopNum, PID_F_SETTING, timeoutMS);
		elevatorMotor.config_kP(PID_LoopNum, PID_P_SETTING, timeoutMS);
		elevatorMotor.config_kI(PID_LoopNum, PID_I_SETTING, timeoutMS);
		elevatorMotor.config_kD(PID_LoopNum, PID_D_SETTING, timeoutMS);
		*/
		elevatorMotor.configNominalOutputForward(0, timeoutMS);
		elevatorMotor.configNominalOutputReverse(0, timeoutMS);
		elevatorMotor.configPeakOutputForward(peakOutputForward, timeoutMS);
		elevatorMotor.configPeakOutputReverse(peakOutputReverse, timeoutMS);
    }
}
