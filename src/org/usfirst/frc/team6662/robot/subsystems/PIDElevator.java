package org.usfirst.frc.team6662.robot.subsystems;

import org.usfirst.frc.team6662.robot.RobotMap;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;



public class PIDElevator extends Subsystem {

	private WPI_TalonSRX elevatorMotor = new WPI_TalonSRX(RobotMap.ELEVATOR_MOTOR_NODE);
	private Encoder elevatorEncoder = new Encoder(RobotMap.ELEVATOR_ENCODER_A, RobotMap.ELEVATOR_ENCODER_B);

	

	private double maxSpeedUpward = 1;
	private double maxSpeedDown = -1;
	
	private static final int PID_LoopNum = 0;
	private static final double PID_F_SETTING = 0;
	private static final double PID_P_SETTING = 50.0;
	private static final double PID_I_SETTING = 0.005;
	private static final double PID_D_SETTING = 0;
	private static final int timeoutMS = 10;

	private double negativeDistance = 0;
	private double positiveDistance = 0;
	private double distanceFromGround = 0;

	public PIDElevator() {

		elevatorMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, PID_LoopNum, timeoutMS);
		elevatorMotor.setSensorPhase(true);
		elevatorMotor.configAllowableClosedloopError(0,PID_LoopNum, timeoutMS);
		elevatorMotor.config_kF(PID_LoopNum, PID_F_SETTING, timeoutMS);
		elevatorMotor.config_kP(PID_LoopNum, PID_P_SETTING, timeoutMS);
		elevatorMotor.config_kI(PID_LoopNum, PID_I_SETTING, timeoutMS);
		elevatorMotor.config_kD(PID_LoopNum, PID_D_SETTING, timeoutMS);
		elevatorMotor.configNominalOutputForward(0, timeoutMS);
		elevatorMotor.configNominalOutputReverse(0, timeoutMS);
		elevatorMotor.configPeakOutputForward(maxSpeedUpward, timeoutMS);
		elevatorMotor.configPeakOutputReverse(maxSpeedDown, timeoutMS);
	}

	

	public Encoder getEncoder() {
		return elevatorEncoder;
	}

	

	public void moveToPosition(double targetPosition) {
		elevatorMotor.set(ControlMode.Position, targetPosition);
	}

	

	public void moveAtSpeed(double targetSpeed) {
		elevatorMotor.set(ControlMode.Velocity, targetSpeed);
	}

	

	public void getDirectionOfEncoder() {
		if (elevatorEncoder.getDirection() == false){
			negativeDistance = elevatorEncoder.getDistance();
		}

		else if (elevatorEncoder.getDirection() == true){
			positiveDistance = elevatorEncoder.getDistance();
		}
		distanceFromGround = positiveDistance - negativeDistance;
	}

	

	public double getDistanceFromGround(){

		return distanceFromGround;

	}

	

	

	public void checkDistanceFromGround(int height) {

		while (distanceFromGround >= height - 5 || distanceFromGround <= height + 5) {

			System.out.println("You are approaching this" + height + "height");

		}

	}



    public void initDefaultCommand() {

      

    }

}