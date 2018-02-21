package org.usfirst.frc.team6662.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team6662.robot.commands.TurnAngle;
import org.usfirst.frc.team6662.robot.commands.DriveForward;
public class AutoDrive extends CommandGroup {
	
	private double initDistance = 2.5;
	private double distanceToInitElementPosition = 0.0; //calculated
	private double distanceToElement = 0.0;//perpendicular distance from alliance wall edge to element
	private double distanceFromOppToElement = 0.0;//distance to drive from driving the wall to the game element
	private double turnA = 0.0;//turn on point A (start of distanceA)
	private double turnC = 90.0;//turn to point C(end of distanceB)
	double position = 0.0;
	double distanceToElements[] = {13.6, 17};
	double distanceFromOppositeToElements[] = {7, 5.4375};
	public double modTurns (double angle, char side) {
		if(side == 'L') {
			return angle *-1;
		}
		else {//(side == 'R')
			return angle;
		}
	}
	
	public AutoDrive(double opp, double position, String element, char side) { //use RobotMap for the side
		super("AutoDrive");
		//opp is the point A's height difference from the start (after startDistance)
		//adj is the pure horizontal distance from wall
		this.position = position;
		
		if (side == 'L') {
			this.position = 27 - position;
		}

		this.turnA = 90-Math.atan2(opp, position);
		this.distanceToInitElementPosition = Math.hypot(opp, position);
		
		switch (element) {
		case "switch":
			this.distanceToElement = distanceToElements[0];
			this.distanceFromOppToElement = distanceFromOppositeToElements[0];
			break;
		case "scale":
			this.distanceToElement = distanceToElements[1];
			this.distanceFromOppToElement = distanceFromOppositeToElements[1];
			break;
		}
		
		this.distanceToElement = distanceToElements[1];
		if (element == "switch") {
			this.distanceToElement = distanceToElements[0];
		}
		this.distanceToElement -=initDistance;
		
		addSequential(new DriveForward(initDistance, 1));//
		addSequential(new TurnAngle(modTurns(turnA, side), 0.5));//
		addSequential(new DriveForward(distanceToInitElementPosition, 1));//
		addSequential(new TurnAngle(0.0, 0.5));//
		addSequential(new DriveForward(distanceToElement, 1));
		addSequential(new TurnAngle(modTurns(turnC, side), 0.5));
		addSequential(new DriveForward(distanceFromOppToElement, 1));
	
	}
}
