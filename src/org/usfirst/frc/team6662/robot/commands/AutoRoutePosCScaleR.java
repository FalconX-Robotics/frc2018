package org.usfirst.frc.team6662.robot.commands;

import org.usfirst.frc.team6662.robot.AutoMeasures;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoRoutePosCScaleR extends CommandGroup {

    public AutoRoutePosCScaleR() {
        
    	addSequential(new DriveDistance(AutoMeasures.TO_SCALE_Y));
		addSequential(new TurnAngle(-90));
		addSequential(new DriveDistance(AutoMeasures.TO_SCALE_X));
		addSequential(new MoveElevatorToPosition(AutoMeasures.SCALE_HEIGHT));
		addParallel(new MoveRolleyGrabberRightSide(-0.7));
		addSequential(new MoveRolleyGrabberLeftSide(0.7));
		
    }
}
