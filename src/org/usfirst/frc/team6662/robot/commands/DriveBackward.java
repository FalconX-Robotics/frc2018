package org.usfirst.frc.team6662.robot.commands;

public class DriveBackward extends DriveDistance {
	public DriveBackward(double targetDistance) {
		super(-1 * targetDistance);
	}
}