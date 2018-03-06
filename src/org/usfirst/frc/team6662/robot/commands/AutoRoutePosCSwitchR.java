package org.usfirst.frc.team6662.robot.commands;

import org.usfirst.frc.team6662.robot.AutoMeasures;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoRoutePosCSwitchR extends CommandGroup {

    public AutoRoutePosCSwitchR() {
        
    	addSequential(new DriveForward(AutoMeasures.TO_SWITCH_Y));
		addSequential(new TurnAngle(-90));
		addSequential(new DriveForward(AutoMeasures.TO_SWITCH_X));
		addSequential(new MoveElevatorToPosition(AutoMeasures.SWITCH_HEIGHT));
		addParallel(new MoveRolleyGrabberRightSide(-0.7));
		addSequential(new MoveRolleyGrabberLeftSide(0.7));
		
    }
}
