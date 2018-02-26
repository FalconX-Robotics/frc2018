package org.usfirst.frc.team6662.robot.commands;

import org.usfirst.frc.team6662.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class MoveElevatorWithXbox extends Command {
	public MoveElevatorWithXbox() {
		super("Move elevator with Xbox controller");
		requires(Robot.elevator);
	}
	
	@Override
	protected void execute() {
		double downSpeed = -1 * Robot.oi.getManipulatorLeftTriggerAxis();
		double upSpeed = Robot.oi.getManipulatorRightTriggerAxis();
		double dominantSpeed = Math.abs(downSpeed) > Math.abs(upSpeed) ? downSpeed : upSpeed;
		
		Robot.elevator.move(dominantSpeed);
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}
}
