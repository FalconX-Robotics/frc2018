package org.usfirst.frc.team6662.robot.commands;

import org.usfirst.frc.team6662.robot.Robot;
import org.usfirst.frc.team6662.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.Command;

public class MoveElevatorToPosition extends Command {
	private double targetDistance = 0;
	private double targetPosition = 0;
	
	public MoveElevatorToPosition(double targetDistance) {
		super("Move elevator " + targetDistance + " inches");
		requires(Robot.elevator);
		
		double targetRotations = targetDistance / Elevator.SPROCKET_CIRCUMFERENCE;
		this.targetPosition = targetRotations * Elevator.ENCODER_UNITS_PER_ROTATION / 
				Elevator.SHAFT_RATIO;
	}
	
	@Override
	protected void execute() {
		Robot.elevator.goToPosition(targetDistance);
	}
	
	@Override
	protected boolean isFinished() {
		return Robot.elevator.getCurrentPosition() >= targetPosition;
	}
}
