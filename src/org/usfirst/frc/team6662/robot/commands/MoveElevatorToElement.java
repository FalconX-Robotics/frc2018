package org.usfirst.frc.team6662.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team6662.robot.AutoMeasures;
import org.usfirst.frc.team6662.robot.Robot;

public class MoveElevatorToElement extends CommandGroup{
	private String element = "";
	private double elementHeight = 0.0;
	
	public MoveElevatorToElement(String element) {
		super("Moving elevator to " + element);
		requires(Robot.elevator);
		
		this.element = element;
		
		if (element == "switch") {
			this.elementHeight = AutoMeasures.SWITCH_HEIGHT + 4.0;
		}
		else if (element == "scale") {
			this.elementHeight = AutoMeasures.SCALE_HEIGHT;
		}
		
		addSequential(new MoveElevatorToPosition(elementHeight)); //elevator up
		addSequential(new MoveRolleyGrabber(1.0, 0.7)); //powercube out
		addSequential(new MoveElevatorToPosition(0.0)); //elevator down
	}
}
