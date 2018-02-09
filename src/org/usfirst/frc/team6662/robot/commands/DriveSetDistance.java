package org.usfirst.frc.team6662.robot.commands;

import org.usfirst.frc.team6662.robot.Robot;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Command;

public class DriveSetDistance extends Command {
	public static final double SPEED = 1;

	private Encoder leftEncoder = Robot.drivetrain.getLeftEncoder();
	private Encoder rightEncoder = Robot.drivetrain.getRightEncoder();
	private double distance = 0.0;
	
    public DriveSetDistance(double distance) {
       requires(Robot.drivetrain);
       this.distance = distance;
    }

    protected void execute() {
    	Robot.drivetrain.tankDrive(SPEED,SPEED);
    }

    protected boolean isFinished() {
        return leftEncoder.getDistance() <= distance && rightEncoder.getDistance() <= distance;
    }
}
