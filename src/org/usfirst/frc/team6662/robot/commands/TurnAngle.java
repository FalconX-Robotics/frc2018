package org.usfirst.frc.team6662.robot.commands;

import org.usfirst.frc.team6662.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/*** ANGLE REFERENCE ***
 * 
 * Positive (+) angle => Turn right
 * Negative (-) angle => Turn left
 * 
 * NOTE: Above mapping corresponds to arcadeDrive rotation rate directions
 */

public class TurnAngle extends Command {

	private static final double SPEED = 1;
	
	private double angle = 0;
	
	public TurnAngle(double angle) {
		super("Turn " + (angle > 0 ? " right " : " left ") + angle + 
				(angle == 1 ? " degree " : " degrees"));
		
		this.angle = angle;
	}
	
	@Override
	protected void initialize() {
		Robot.drivetrain.zeroYaw();
	}
	
	@Override
	protected void execute() {
	//	double ROTATION = Math.copySign(ROTATION, angle);
		double SPEED = Math.copySign(TurnAngle.SPEED, angle);
		
		Robot.drivetrain.tankDrive(SPEED, -1 * SPEED);
	}
	
	@Override
	protected void end() {
		Robot.drivetrain.zeroYaw();
	}

	@Override
	protected boolean isFinished() {
		return Math.abs(Robot.drivetrain.getCurrentYaw()) >= Math.abs(angle);
	}
}