package org.usfirst.frc.team6662.robot.commands;

import org.usfirst.frc.team6662.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ToggleGear extends Command {
	public ToggleGear() {
		super("Toggle Gear");
		requires(Robot.drivetrain);
	}
	
	@Override
	protected void execute() {
		Robot.drivetrain.shiftGear();
	}

	@Override
	protected boolean isFinished() {
		return true;
	}
}
