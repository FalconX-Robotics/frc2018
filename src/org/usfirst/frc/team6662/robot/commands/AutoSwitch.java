package org.usfirst.frc.team6662.robot.commands;

import org.usfirst.frc.team6662.robot.AutoMeasures;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoSwitch extends CommandGroup {

    	public AutoSwitch(char position, char side){
    	      
    	      if(position == 'a'){
    	        if(side == 'l'){
    	          sameSideAutonomous(90);
    	        }
    	        else if(side == 'r'){
    	          oppositeSideAutonomous(90);
    	        }
    	      }
    	      else if(position == 'b'){
    	      	if(side == 'l'){
    			positionB(AutoMeasures.MIDDLE_TO_LEFT, -90);
    		}
    	      	else if(side == 'r'){
    			positionB(AutoMeasures.MIDDLE_TO_RIGHT, 90);
    		}
    	      }
    	      else if(position == 'c'){
    	        if(side == 'l'){
    		  oppositeSideAutonomous(-90);
    	        }
    	        else if(side == 'r'){
    	          sameSideAutonomous(-90);
    	        }
    	      }
    	}
    	      
    	void sameSideAutonomous(double angle){
    	  addSequential(new DriveDistance(AutoMeasures.TO_SWITCH_Y));
    	  addSequential(new TurnAngle(angle));
    	  addSequential(new DriveDistance(AutoMeasures.TO_SWITCH_X));
    	  addSequential(new MoveElevatorToElement("switch"));
    	}

    	void oppositeSideAutonomous(double angle){
    	  addSequential(new DriveDistance(AutoMeasures.TO_PLATFORM_ZONE_Y));
    	  addSequential(new TurnAngle(angle));
    	  addSequential(new DriveDistance(264));
    	  addSequential(new TurnAngle(angle));
    	  addSequential(new DriveDistance(AutoMeasures.TO_PLATFORM_ZONE_Y - AutoMeasures.TO_SWITCH_Y));
    	  addSequential(new TurnAngle(angle));
    	  addSequential(new DriveDistance(AutoMeasures.TO_SWITCH_X));
    	  addSequential(new MoveElevatorToElement("switch"));
    	}
 
    	void positionB(double distance, double angle){
    	  addSequential(new DriveDistance(AutoMeasures.INITIAL_DRIVE_FORWARD));
    	  addSequential(new TurnAngle(angle));
    	  addSequential(new DriveDistance(distance));
    	  addSequential(new TurnAngle(-angle));
    	  addSequential(new DriveDistance(AutoMeasures.TO_SWITCH_Y - AutoMeasures.INITIAL_DRIVE_FORWARD));
    	  addSequential(new TurnAngle(-angle));
    	  addSequential(new DriveDistance(AutoMeasures.TO_SWITCH_X));
    	  addSequential(new MoveElevatorToElement("switch"));
    	}
}
