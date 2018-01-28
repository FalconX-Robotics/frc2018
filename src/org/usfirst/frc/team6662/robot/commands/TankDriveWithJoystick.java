package org.usfirst.frc.team6662.robot.commands;

import org.usfirst.frc.team6662.robot.Robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

public class TankDriveWithJoystick extends Command {
	private Joystick joystick;
	private int leftSpeedAxis = 0;
	private int rightSpeedAxis = 1;
	
	public TankDriveWithJoystick(Joystick joystick, int leftSpeedAxis, int rightSpeedAxis) {
		super("Tank Drive with Joystick");
		requires(Robot.drivetrain);
		
		this.joystick = joystick;
		this.leftSpeedAxis = leftSpeedAxis;
		this.rightSpeedAxis = rightSpeedAxis;
	}
	
	@Override
	protected void execute() {
		double leftSpeed = joystick.getRawAxis(leftSpeedAxis);
		double rightSpeed = joystick.getRawAxis(rightSpeedAxis);
		
		Robot.drivetrain.tankDrive(leftSpeed, rightSpeed);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
}
