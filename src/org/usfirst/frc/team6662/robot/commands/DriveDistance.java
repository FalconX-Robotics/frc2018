package org.usfirst.frc.team6662.robot.commands;

import org.usfirst.frc.team6662.robot.Robot;
import org.usfirst.frc.team6662.robot.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.command.Command;

public class DriveDistance extends Command {
	private double targetDistance = 0;
	private double targetPosition = 0;
	private double speed = -1;
	
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
		Robot.drivetrain.zeroPosition();
		
		if (targetDistance < 0) {
			speed *= -1;
		}
	}
	
	@Override
	protected void execute() {
		Robot.drivetrain.tankDrive(speed, speed);
	}
	
	@Override
	protected void end() {
		Robot.drivetrain.zeroPosition();
	}
	
	@Override
	protected boolean isFinished() {
		return Math.abs(Robot.drivetrain.getAveragePosition()) >= Math.abs(targetPosition);
	}
}
