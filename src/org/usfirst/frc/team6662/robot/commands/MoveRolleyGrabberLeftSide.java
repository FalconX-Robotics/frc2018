package org.usfirst.frc.team6662.robot.commands;

import org.usfirst.frc.team6662.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class MoveRolleyGrabberLeftSide extends Command {
	private double speed = 0;
	
	public MoveRolleyGrabberLeftSide(double speed) {
		super("Move rolley-grabber left motor");
		
		this.speed = speed;
	}
	
	@Override
	protected void execute() {
		Robot.rolleyGrabber.moveLeftMotor(speed);
	}
	
	@Override
	protected boolean isFinished() {
		return true;
	}
}
