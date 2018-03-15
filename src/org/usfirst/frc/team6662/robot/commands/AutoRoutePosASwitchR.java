package org.usfirst.frc.team6662.robot.commands;

import org.usfirst.frc.team6662.robot.AutoMeasures;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoRoutePosASwitchR extends CommandGroup {
    public AutoRoutePosASwitchR() {
    	addSequential(new DriveDistance(AutoMeasures.TO_PLATFORM_ZONE_Y));
		addSequential(new TurnAngle(90));
		addSequential(new DriveDistance(264));
		addSequential(new TurnAngle(90));
		addSequential(new DriveDistance(AutoMeasures.TO_PLATFORM_ZONE_Y - AutoMeasures.TO_SWITCH_Y));
		addSequential(new TurnAngle(90));
		addSequential(new DriveDistance(AutoMeasures.TO_SWITCH_X));
		addSequential(new MoveElevatorToPosition(AutoMeasures.SWITCH_HEIGHT));
		addSequential(new MoveRolleyGrabber(1.0, 1.0));	
    }
}
