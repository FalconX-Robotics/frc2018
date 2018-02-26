package org.usfirst.frc.team6662.robot.commands;

import org.usfirst.frc.team6662.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class MoveElevatorToPosition extends Command {
	private double targetPosition = 0;
	
	public MoveElevatorToPosition(double targetPosition) {
		super("Move elevator to " + targetPosition + "u"); // TO-DO: Convert targetPosition from inches to units
		requires(Robot.elevator);
		
		this.targetPosition = targetPosition;
	}
	
	@Override
	protected void execute() {
		Robot.elevator.goToPosition(targetPosition);
	}
	
	@Override
	protected boolean isFinished() {
		return true;
	}
}
