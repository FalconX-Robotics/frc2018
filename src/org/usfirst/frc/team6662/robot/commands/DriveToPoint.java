package org.usfirst.frc.team6662.robot.commands;

import org.usfirst.frc.team6662.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriveToPoint extends Command{
	double targetxy []  = {0,0};
	public DriveToPoint(double x, double y) {
		requires (Robot.drivetrain);
		this.targetxy [0] = x; this.targetxy[1] = y;
	}
	
	@Override
	protected void initialize() {
		
	}
	
	@Override
	protected void execute() {
		
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
