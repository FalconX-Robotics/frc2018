package org.usfirst.frc.team6662.robot.commands;

import org.usfirst.frc.team6662.robot.AutoMeasures;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoScale extends CommandGroup {
	public AutoScale(String position, char side) {
		switch (position) {
    	case "Left":
    		switch (side) {
    		case 'L':
    			sameSideAutonomous(90);
    			break;
    		case 'R':
    			oppositeSideAutonomous(-90);
    			break;
    		}
    		break;
    	case "Middle":
    		switch (side) {
    		case 'L':
    			positionB(AutoMeasures.MIDDLE_TO_LEFT, 90);
    			break;
    		case 'R':
    			positionB(AutoMeasures.MIDDLE_TO_RIGHT, -90);
    			break;
    		}
    		break;
    	case "Right":
    		switch (side) {
    		case 'L':
    			oppositeSideAutonomous(90);
    			break;
    		case 'R':
    			sameSideAutonomous(-90);
    			break;
    		}
    		break;
    	}
	}
	
	void positionB(double distance, double angle) {
		addSequential(new ShiftToLowGear());
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
		addSequential(new ShiftToLowGear());
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
		addSequential(new ShiftToLowGear());
		addSequential(new DriveDistance(AutoMeasures.TO_SCALE_Y));
		addSequential(new TurnAngle(angle));
		addSequential(new DriveDistance(AutoMeasures.TO_SCALE_X));
		addSequential(new MoveElevatorToElement("scale"));
	}
}