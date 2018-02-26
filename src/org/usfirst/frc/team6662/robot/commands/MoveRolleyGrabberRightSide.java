package org.usfirst.frc.team6662.robot.commands;

import org.usfirst.frc.team6662.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class MoveRolleyGrabberRightSide extends Command {
	private double speed = 0;
	
	public MoveRolleyGrabberRightSide(double speed) {
		super("Move rolley-grabber right motor");
		
		this.speed = speed;
	}
	
	@Override
	protected void execute() {
		Robot.rolleyGrabber.moveRightMotor(speed);
	}
	
	@Override
	protected boolean isFinished() {
		return true;
	}
}
