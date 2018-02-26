package org.usfirst.frc.team6662.robot.commands;

import org.usfirst.frc.team6662.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class TankDriveWithXbox extends Command {
	public TankDriveWithXbox() {
		super("Tank drive with Xbox controller");
		requires(Robot.drivetrain);
	}
	
	@Override
	protected void execute() {
		double leftSpeed = Robot.oi.getDriverLeftYAxis();
		double rightSpeed = Robot.oi.getDriverRightYAxis();
		
		Robot.drivetrain.tankDrive(leftSpeed, rightSpeed);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
}
