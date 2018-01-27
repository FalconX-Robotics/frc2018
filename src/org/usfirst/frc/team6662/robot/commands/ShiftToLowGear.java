package org.usfirst.frc.team6662.robot.commands;

import org.usfirst.frc.team6662.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ShiftToLowGear extends Command {
	public ShiftToLowGear() {
		super("Shift to Low Gear");
		requires(Robot.drivetrain);
	}
	
	@Override
	protected void execute() {
		Robot.drivetrain.shiftToLowGear();
	}

	@Override
	protected boolean isFinished() {
		return true;
	}
}
