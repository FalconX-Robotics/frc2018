package org.usfirst.frc.team6662.robot.commands;

import org.usfirst.frc.team6662.robot.Robot;
import org.usfirst.frc.team6662.robot.subsystems.Drivetrain.Gear;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ShiftGear extends Command {
	private Gear targetGear = Gear.LOW;
	
	public ShiftGear(Gear targetGear) {
		super("Shift to " + targetGear.toString() + " gear");
		requires(Robot.drivetrain);
		
		this.targetGear = targetGear;
	}
	
	@Override
	protected void execute() {
		Robot.drivetrain.shiftGear(targetGear);
		
		SmartDashboard.putString("Gear", Robot.drivetrain.getShiftStateName());
	}

	@Override
	protected boolean isFinished() {
		return true;
	}
}
