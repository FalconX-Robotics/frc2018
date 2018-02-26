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
	public static final double SPEED = 0;
	public static final double ROTATION = 0.5;
	
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
		double rotation = Math.copySign(ROTATION, angle);
		
		Robot.drivetrain.arcadeDrive(SPEED, rotation);
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
