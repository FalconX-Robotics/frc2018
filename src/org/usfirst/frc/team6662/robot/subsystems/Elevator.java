package org.usfirst.frc.team6662.robot.subsystems;

import org.usfirst.frc.team6662.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Elevator extends Subsystem {

	WPI_TalonSRX elevatorMotor = new WPI_TalonSRX(RobotMap.ELEVATOR_MOTOR_ID);
	
	private double MAX_SPEED =  79.0;  // in./sec,  output of gearbox
	private double SPROCKET_DIAMETER = 1.9; //inches
	
	private static final int PID_LoopNum = 0;
	private static final double PID_F_SETTING = 0;
	private static final double PID_P_SETTING = 50.0;
	private static final double PID_I_SETTING = 0.005;
	private static final double PID_D_SETTING = 0;
	private static final int timeoutMS = 10;
	
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
	public void moveUp(){
		
	}
	
	public void moveDown(){
		
	}
	
    public void initDefaultCommand() {
      
    }
    public Elevator() {
    		elevatorMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, PID_LoopNum, timeoutMS);
		elevatorMotor.setSensorPhase(true);
		elevatorMotor.configAllowableClosedloopError(0,PID_LoopNum, timeoutMS);
		elevatorMotor.config_kF(PID_LoopNum, PID_F_SETTING, timeoutMS);
		elevatorMotor.config_kP(PID_LoopNum, PID_P_SETTING, timeoutMS);
		elevatorMotor.config_kI(PID_LoopNum, PID_I_SETTING, timeoutMS);
		elevatorMotor.config_kD(PID_LoopNum, PID_D_SETTING, timeoutMS);
		elevatorMotor.configNominalOutputForward(0, timeoutMS);
		elevatorMotor.configNominalOutputReverse(0, timeoutMS);
		elevatorMotor.configPeakOutputForward(MAX_SPEED, timeoutMS);
		elevatorMotor.configPeakOutputReverse(MAX_SPEED*-1, timeoutMS);
    }
}
