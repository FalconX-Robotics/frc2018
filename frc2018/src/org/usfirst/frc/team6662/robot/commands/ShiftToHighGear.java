package org.usfirst.frc.team6662.robot.commands;

import org.usfirst.frc.team6662.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ShiftToHighGear extends Command {
	public ShiftToHighGear() {
		super("Shift to High Gear");
		requires(Robot.drivetrain);
	}
	
	@Override
	protected void execute() {
		Robot.drivetrain.shiftToHighGear();
	}

	@Override
	protected boolean isFinished() {
		return true;
	}
}
