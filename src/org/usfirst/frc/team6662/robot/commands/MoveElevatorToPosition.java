package org.usfirst.frc.team6662.robot.commands;

import org.usfirst.frc.team6662.robot.Robot;
import org.usfirst.frc.team6662.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class MoveElevatorToPosition extends Command {
	private double targetPosition = 0;
	
	public MoveElevatorToPosition(double targetDistance) {
		super("Move elevator " + targetDistance + " inches");
		requires(Robot.elevator);
		
		double targetRotations = targetDistance / Elevator.SPROCKET_CIRCUMFERENCE / 2;
		this.targetPosition = targetRotations * Elevator.ENCODER_UNITS_PER_ROTATION;
	}
	
	@Override
	protected void execute() {
		double distance = Robot.elevator.getCurrentPosition() / 
				Elevator.ENCODER_UNITS_PER_ROTATION * 
				Elevator.SPROCKET_CIRCUMFERENCE * 2;
		
		SmartDashboard.putNumber("Elevator Position", distance);
		Robot.elevator.move(0.7);
	}
	
	@Override
	protected void end() {
		Robot.elevator.move(0);
	}
	
	@Override
	protected boolean isFinished() {
		return Math.abs(Robot.elevator.getCurrentPosition()) >= Math.abs(targetPosition);
	}
}
