package org.usfirst.frc.team6662.robot.commands;

import org.usfirst.frc.team6662.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.interfaces.Gyro;

public class TurnAngle extends Command {
	private double targetAngle = 0;
	private double leftSpeed = 1;
	private double rightSpeed = -1;
	
	private Gyro gyro = Robot.drivetrain.getGyro();
	
	public TurnAngle(double targetAngle, double speed) {
		super("Turn Angle");
		requires(Robot.drivetrain);
		
		this.targetAngle = targetAngle;
		
		leftSpeed = targetAngle >= 0 ? speed : -speed;
		rightSpeed = targetAngle >= 0 ? -speed : speed;
	}
	
	protected void initalize() {
		gyro.reset();
	}
	
	@Override
	protected void execute() {
		Robot.drivetrain.tankDrive(leftSpeed, rightSpeed);
	}

	@Override
	protected boolean isFinished() {
		return Math.abs(gyro.getAngle()) >= Math.abs(targetAngle);
	}

}
