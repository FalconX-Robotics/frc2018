package org.usfirst.frc.team6662.robot.commands;

import org.usfirst.frc.team6662.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class MoveElevatorUp extends Command {

	double position = 0.0;
	public MoveElevatorUp(double position) {
		super("MoveElevatorUp");
		requires(Robot.elevator);
		this.position = position;
	}
	
	@Override
	protected void initialize() {
	}
	@Override
	protected void execute() {
		
	}
	@Override
	protected boolean isFinished() {
		return false;
	}
}
