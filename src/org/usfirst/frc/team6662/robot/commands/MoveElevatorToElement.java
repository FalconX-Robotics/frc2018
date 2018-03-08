package org.usfirst.frc.team6662.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team6662.robot.AutoMeasures;
import org.usfirst.frc.team6662.robot.Robot;

public class MoveElevatorToElement extends CommandGroup{
	String element = "";
	private double elementHeight = 0.0;
	
	public MoveElevatorToElement(String element) {
		super("Moving elevator to " + element);
		requires(Robot.elevator);
		this.element = element;
		
		if (element == "switch") {
			this.elementHeight = AutoMeasures.SWITCH_HEIGHT + 4.0;
		}
		else {//scale
			this.elementHeight = AutoMeasures.SCALE_HEIGHT;
		}
		addSequential(new MoveElevatorToPosition(elementHeight));//elevator up
		addSequential(new MoveRolleyGrabber(0.7, 1.0, "Out"));//powercube out
		addSequential(new MoveElevatorToPosition(0.0));//elevator down
		
		
	}
}
