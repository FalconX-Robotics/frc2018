package org.usfirst.frc.team6662.robot.subsystems;

import org.usfirst.frc.team6662.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PWMTalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Elevator extends Subsystem {

	TalonSRX elevatorMotor = new TalonSRX(RobotMap.ELEVATOR_MOTOR);
	Encoder elevatorEncoder = new Encoder(RobotMap.ELEVATOR_ENCODER_A, RobotMap.ELEVATOR_ENCODER_B);
	
	public void moveUp(){
		
	}
	
	public void moveDown(){
		
	}
	
    public void initDefaultCommand() {
      
    }
}
