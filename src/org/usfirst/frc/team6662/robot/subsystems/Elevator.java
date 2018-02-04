package org.usfirst.frc.team6662.robot.subsystems;

import org.usfirst.frc.team6662.robot.RobotMap;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PWMTalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Elevator extends Subsystem {

	PWMTalonSRX elevatorMotor = new PWMTalonSRX(RobotMap.ELEVATOR_MOTOR);
	Encoder elevatorEncoder = new Encoder(RobotMap.ELEVATOR_ENCODER_A, RobotMap.ELEVATOR_ENCODER_B);
	
	public void moveUp(){
		elevatorMotor.set(1);
	}
	
	public void moveDown(){
		elevatorMotor.set(-1);
	}
	
    public void initDefaultCommand() {
      
    }
}
