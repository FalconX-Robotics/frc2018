package org.usfirst.frc.team6662.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class TestAutoCommand extends CommandGroup {
	
	private Command turns [] = {new TurnRight()};
	private int distances [] = {0};
	
    public TestAutoCommand(int distances[], Command turns []) {
    	
    	this.distances = distances;
    	this.turns = turns;
    	
       addSequential(turns [0]);
       addSequential(new DriveSetDistance(distances[0]));
       addSequential(turns [1]);
       addSequential(new DriveSetDistance(distances[1]));
       addSequential(turns [2]);
       addSequential(new DriveSetDistance(distances[2]));
    }
}
