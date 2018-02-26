package org.usfirst.frc.team6662.robot.commands;

import org.usfirst.frc.team6662.robot.Robot;
import org.usfirst.frc.team6662.robot.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.command.Command;

public class DriveDistance extends Command {
	private double targetRotations = 0;
	private double targetPosition = 0;
	
	// TO-DO: Test out behavior when lines commented out below are un-commented
	
	//private boolean firstExecution = true;
	
	public DriveDistance(double targetDistance) {
		super("Drive " + targetDistance + 
				(targetDistance == 1 ? " inch " : " inches ") + 
				(targetDistance > 0 ? "forward" : "backward"));
		requires(Robot.drivetrain);
		
		targetRotations = targetDistance / Drivetrain.WHEEL_CIRCUMFERENCE;
		this.targetPosition = targetRotations * 
				Drivetrain.ENCODER_UNITS_PER_ROTATION / Drivetrain.SHAFT_RATIO;
	}
	
	@Override
	protected void initialize() {
		Robot.drivetrain.zeroPosition();
		//firstExecution = true;
	}
	
	@Override
	protected void execute() {
		//if (firstExecution) {
			Robot.drivetrain.driveDistance(targetPosition);
			
			//firstExecution = false;
		//}
		
		System.out.println("Current: " + Robot.drivetrain.getCurrentPosition());
		System.out.println("Target: " + targetPosition);
	}
	
	@Override
	protected void end() {
		Robot.drivetrain.zeroPosition();
	}
	
	@Override
	protected boolean isFinished() {
		return Math.abs(Robot.drivetrain.getCurrentPosition()) >= Math.abs(targetPosition);
	}
}
