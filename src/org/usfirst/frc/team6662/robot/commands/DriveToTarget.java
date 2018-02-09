package org.usfirst.frc.team6662.robot.commands;

import org.usfirst.frc.team6662.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team6662.robot.commands.TurnAngle;
import org.usfirst.frc.team6662.robot.subsystems.RobotPosition;
public class DriveToTarget extends CommandGroup{
	
	public DriveToTarget(double x, double y) {
		super("DriveToTarget");
		requires(Robot.drivetrain);
		
		
		
		addSequential(new TurnAngle(RobotPosition.getTargetAngle(), .5)); //turns angle at half speed
		addSequential(new DriveSetDistance(RobotPosition.getDistance())); //drives to target
	}
}
