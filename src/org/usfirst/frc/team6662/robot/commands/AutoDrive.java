package org.usfirst.frc.team6662.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team6662.robot.commands.TurnAngle;
import org.usfirst.frc.team6662.robot.commands.DriveForward;
public class AutoDrive extends CommandGroup {
	
	private double initDistance = 2.5;
	private double distanceToInitElementPosition = 0.0; //calculated
	private double distanceToElement = 0.0;//perpendicular distance from alliance wall edge to switch
	private double WallAndSwitchDist = 8.4375;//distance to drive to switch
	private double turnA = 0.0;//turn on point A (start of distanceA)
	private double turnC = 90.0;//turn to point C(end of distanceB)
	double adj = 54;
	
	public double modTurns (double angle, char side) {
		if(side == 'L') {
			return angle *-1;
		}
		else {//(side == 'R')
			return angle;
		}
	}
	
	public AutoDrive(double opp, double adj, double targetDistance, char side) { //use RobotMap for the side
		super("AutoDrive");
		//opp is the point A's height difference from the start (after startDistance)
		//adj is the pure horizontal distance from wall
		
		if (side == 'L') {
			adj = 27 - adj;
		}
		
		this.adj = adj;
		this.turnA = 90-Math.atan2(opp, adj);
		this.distanceToInitElementPosition = Math.sqrt(Math.pow(opp, 2) + Math.pow(adj, 2.0));
		this.distanceToElement = targetDistance - 3.0;//adjust distanceB to whether it's for the switch or scale
    		
		addSequential(new DriveForward(initDistance, 1));//
		addSequential(new TurnAngle(modTurns(turnA, side), 0.5));//
		addSequential(new DriveForward(distanceToInitElementPosition, 1));//
		addSequential(new TurnAngle(0.0, 0.5));//
		addSequential(new DriveForward(distanceToElement, 1));
		addSequential(new TurnAngle(modTurns(turnC, side), 0.5));
		addSequential(new DriveForward(WallAndSwitchDist, 1));
	}
}
