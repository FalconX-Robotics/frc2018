package org.usfirst.frc.team6662.robot.commands;

import org.usfirst.frc.team6662.robot.Robot;
import org.usfirst.frc.team6662.robot.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.command.Command;

public class DriveDistance extends Command {
	private double targetDistance = 0;
	private double targetPosition = 0;
	protected boolean finished = false;
	
	public DriveDistance(double targetDistance) {
		super("Drive " + targetDistance + 
				(Math.abs(targetDistance) == 1 ? " inch " : " inches ") + 
				(targetDistance > 0 ? "forward" : "backward"));
		requires(Robot.drivetrain);
		
		this.targetDistance = targetDistance;
		double targetRotations = targetDistance / Drivetrain.WHEEL_CIRCUMFERENCE;
		this.targetPosition = targetRotations * 
				Drivetrain.ENCODER_UNITS_PER_ROTATION / Drivetrain.SHAFT_RATIO;
	}
	
	@Override
	protected void initialize() {
		finished = false;
		Robot.drivetrain.zeroPosition();
	}
	
	@Override
	protected void execute() {
		Robot.drivetrain.tankDrive(0.8 , 0.8);
		//System.out.println("Current: " + Robot.drivetrain.getCurrentPosition());
		//System.out.println("Target: " + targetPosition);
		finished = Math.abs(Robot.drivetrain.getCurrentPosition()) >= Math.abs(targetPosition);
	}
	
	@Override
	protected boolean isFinished() {
		return finished;
	}
	
	@Override
	protected void end() {
		Robot.drivetrain.zeroPosition();
		Robot.drivetrain.tankDrive(0.0, 0.0);
	}
}
