package org.usfirst.frc.team6662.robot.commands;

import org.usfirst.frc.team6662.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriveForward extends Command{
	private double distance = 0.0;
	private double velocity = 0.0;

	public DriveForward(double distance, double velocity) {
		super("DriveForward");
		requires(Robot.drivetrain);
		this.distance = distance;
		this.velocity  = velocity;
		
	}
	
	@Override
	protected void initialize() {
		Robot.drivetrain.resetPosition();
	}
	
	@Override
	protected void execute() {
		Robot.drivetrain.driveForward(velocity);
	}
	
	@Override
	protected boolean isFinished() {
		return Robot.drivetrain.getCurrentPosition()== distance;
	}
}
