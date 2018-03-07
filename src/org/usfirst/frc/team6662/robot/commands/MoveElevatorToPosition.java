package org.usfirst.frc.team6662.robot.commands;

import org.usfirst.frc.team6662.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class MoveElevatorToPosition extends Command {
	private double targetPosition = 0;

	final private double SPROCKET_DIAMETER = 1.751;
	final private double  SPROCKET_CIRUMFERENCE = Math.PI * SPROCKET_DIAMETER;
	private double inchesToUnits = 4096/SPROCKET_CIRUMFERENCE;
	final private double GEAR_RATIO = 7.0;
	
	public MoveElevatorToPosition(double targetPosition) {
		super("Move elevator to " + targetPosition + "inches"); // TO-DO: Convert targetPosition from inches to units
		requires(Robot.elevator);
		
		this.targetPosition = targetPosition / GEAR_RATIO * inchesToUnits;
	}
	
	@Override
	protected void execute() {
		Robot.elevator.goToPosition(targetPosition);
	}
	
	@Override
	protected boolean isFinished() { 
		return Robot.elevator.getCurrentPosition() == targetPosition;
	}
}
