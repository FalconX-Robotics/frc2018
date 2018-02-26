package org.usfirst.frc.team6662.robot.commands;

import org.usfirst.frc.team6662.robot.subsystems.Drivetrain.Gear;

public class ShiftToHighGear extends ShiftGear {
	public ShiftToHighGear() {
		super(Gear.HIGH);
	}
}
