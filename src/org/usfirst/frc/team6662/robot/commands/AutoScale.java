package org.usfirst.frc.team6662.robot.commands;

import org.usfirst.frc.team6662.robot.AutoMeasures;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoScale extends CommandGroup {
	public AutoScale(char position, char side) {
		if(position == 'a') {
			if(side == 'L') {
				sameSideAutonomous(90);
			}
			else if(side == 'R'){
				oppositeSideAutonomous(-90);
			}
		}
		else if(position == 'b') {
			if(side == 'L') {
				positionB(AutoMeasures.MIDDLE_TO_LEFT, 90);
			}
			else if(side == 'R'){
				positionB(AutoMeasures.MIDDLE_TO_RIGHT, -90);
			}
		}
		else if(position == 'c'){
			if(side == 'L') {
				oppositeSideAutonomous(90);
			}
			else if(side == 'R'){
				sameSideAutonomous(-90);
			}
		}
	}
	
	void positionB(double distance, double angle) {
		addSequential(new DriveDistance(AutoMeasures.INITIAL_DRIVE_FORWARD));
		addSequential(new TurnAngle(-angle));
		addSequential(new DriveDistance(distance));
		addSequential(new TurnAngle(angle));
		addSequential(new DriveDistance(AutoMeasures.TO_SCALE_Y - AutoMeasures.INITIAL_DRIVE_FORWARD));
		addSequential(new TurnAngle(angle));
		addSequential(new DriveDistance(AutoMeasures.TO_SCALE_X));
		addSequential(new MoveElevatorToElement("scale"));
	}
	
	void oppositeSideAutonomous(double angle) {
		addSequential(new DriveDistance(AutoMeasures.TO_PLATFORM_ZONE_Y));
		addSequential(new TurnAngle(-angle));
		addSequential(new DriveDistance(264));
		addSequential(new TurnAngle(angle));
		addSequential(new DriveDistance(AutoMeasures.TO_SCALE_Y - AutoMeasures.TO_PLATFORM_ZONE_Y));
		addSequential(new TurnAngle(angle));
		addSequential(new DriveDistance(AutoMeasures.TO_SCALE_X));
		addSequential(new MoveElevatorToElement("scale"));
	}
	
	void sameSideAutonomous(double angle) {
		addSequential(new DriveDistance(AutoMeasures.TO_SCALE_Y));
		addSequential(new TurnAngle(angle));
		addSequential(new DriveDistance(AutoMeasures.TO_SCALE_X));
		addSequential(new MoveElevatorToElement("scale"));
	}
}
