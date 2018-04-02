package org.usfirst.frc.team6662.robot.commands;

import org.usfirst.frc.team6662.robot.AutoMeasures;
import org.usfirst.frc.team6662.robot.AutoMeasures.StartingPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoSwitch extends CommandGroup {
    public AutoSwitch(StartingPosition startingPosition, char side) {
    	switch (startingPosition) {
    	case L:
    		switch (side) {
    		case 'L':
    			sameSideAuto(90);
    			break;
    		case 'R':
    			oppositeSideAuto(90);
    			break;
    		}
    		break;
    	case M:
    		switch (side) {
    		case 'L':
    			middleAuto(AutoMeasures.MIDDLE_TO_LEFT, -90);
    			break;
    		case 'R':
    			middleAuto(AutoMeasures.MIDDLE_TO_RIGHT, 90);
    			break;
    		}
    		break;
    	case R:
    		switch (side) {
    		case 'L':
    			oppositeSideAuto(-90);
    			break;
    		case 'R':
    			sameSideAuto(-90);
    			break;
    		}
    		break;
    	}
    }
    
    void sameSideAuto(double angle) {
    	addSequential(new ShiftToLowGear());
    	addSequential(new MoveRolleyGrabber(-0.5, 1));
    	addSequential(new DriveDistance(AutoMeasures.TO_SWITCH_Y));
    	addSequential(new TurnAngle(angle));
    	addSequential(new MoveElevatorToPosition(AutoMeasures.SWITCH_HEIGHT));
    	addSequential(new DriveDistance(AutoMeasures.TO_SWITCH_X));
    	addSequential(new MoveRolleyGrabber(0.8, 1));
    }
    
    void oppositeSideAuto(double angle) {
    	addSequential(new ShiftToLowGear());
    	addSequential(new MoveRolleyGrabber(-0.5, 1));
    	addSequential(new DriveDistance(AutoMeasures.TO_PLATFORM_ZONE_Y));
    	addSequential(new TurnAngle(angle));
    	addSequential(new DriveDistance(160));
    	addSequential(new TurnAngle(angle));
    	addSequential(new MoveElevatorToPosition(AutoMeasures.SWITCH_HEIGHT));
    	addSequential(new DriveDistance(AutoMeasures.TO_SWITCH_X));
    	addSequential(new MoveRolleyGrabber(0.8, 1));
    }
    
    void middleAuto(double distance, double angle) {
    	addSequential(new ShiftToLowGear());
    	addSequential(new DriveDistance(AutoMeasures.MIDWAY_WALL_TO_SWITCH));
    	addSequential(new TurnAngle(angle));
    	addSequential(new DriveDistance(distance));
    	addSequential(new TurnAngle(-angle));
    	addSequential(new DriveDistance(AutoMeasures.TO_SWITCH_Y - AutoMeasures.MIDWAY_WALL_TO_SWITCH));
    	addSequential(new TurnAngle(-angle));
    	addSequential(new DriveDistance(AutoMeasures.TO_SWITCH_X));
    }
}