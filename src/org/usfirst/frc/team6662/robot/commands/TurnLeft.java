package org.usfirst.frc.team6662.robot.commands;

import org.usfirst.frc.team6662.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.interfaces.Gyro;

public class TurnLeft extends Command {
	public static final double LEFT_SPEED = -1;
	public static final double RIGHT_SPEED = 1;
	public static final double TARGET_ANGLE = -90;
	
	private Gyro gyro = Robot.drivetrain.getGyro();
	
	public TurnLeft() {
		super("Turn Left");
		requires(Robot.drivetrain);
	}
	
	protected void initalize() {
		gyro.reset();
	}
	
	@Override
	protected void execute() {
		Robot.drivetrain.tankDrive(LEFT_SPEED, RIGHT_SPEED);
	}

	@Override
	protected boolean isFinished() {
		return gyro.getAngle() <= TARGET_ANGLE;
	}

}
