package org.usfirst.frc.team6662.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;//only works for right side so far...

public class AutoRoutes extends CommandGroup {
	
	private static final int wallToSwitch = 101;//actual distance is 101.25 inches
	private static final int wallToScale = 1; //these distances are undetermined
	private static final int switchToSide = 1;//wall means the alliance wall and side is the right side of the playing field
	private static final int scaleToSide = 1;
	private static final int initialDriveForward = 30;
	
	private static int distance;//I made this variable because I couldn't get AutoRoutes.getAdjacent to work for some reason

    public AutoRoutes(char position, boolean Scale, char ScaleSide, char SwitchSide) {
    	
    	int adjacent = AutoRoutes.getAdjacent(position);// thinking of adjacent as in opposite, adjacent, hypotenuse
    	double firstAngle = AutoRoutes.getFirstAngle(adjacent);//diagonal distance is hypotenuse, wallToSwitch/Scale is opposite
    	
    	addSequential(new DriveSetDistance(initialDriveForward));//drive forward so we can turn w/o bumping into the wall
    	addSequential(new TurnAngle(firstAngle, 0.7));
    	addSequential(new DriveSetDistance(AutoRoutes.getFirstDistance(adjacent)));//this takes us to wall next to the switch
    	
    	if (Scale == true){
	
		if (ScaleSide == 'R'){
    			addSequential(new TurnAngle(AutoRoutes.getSecondAngleForScale(firstAngle), 0.7));//make parallel to wall
    			addSequential(new DriveSetDistance(AutoRoutes.getSecondDistanceForScale()));//drive along wall until scale
    			addSequential(new TurnAngle(AutoRoutes.getThirdAngle(), 0.7));//make aligned with scale
    			addSequential(new DriveSetDistance(scaleToSide));//robot approaches from null territory
		}
		else {
			addSequential(new TurnAngle(AutoRoutes.getSecondAngleForScale(-1*firstAngle), 0.7));//make parallel to wall
    			addSequential(new DriveSetDistance(AutoRoutes.getSecondDistanceForScale()));//drive along wall until scale
    			addSequential(new TurnAngle(-1*AutoRoutes.getThirdAngle(), 0.7));//make aligned with scale
    			addSequential(new DriveSetDistance(scaleToSide));//robot approaches from null territory
		}
		
    	}
    	
    	else {
	
		if (SwitchSide == 'R'){
    			addSequential(new TurnAngle(AutoRoutes.getSecondAngleForSwitch(firstAngle), 0.7));//align with switch
    			addSequential(new DriveSetDistance(switchToSide));approach switch from the right
		}
		else {
			addSequential(new TurnAngle(-1*Routes.getSecondAngleForSwitch(firstAngle), 0.7));//align with switch
    			addSequential(new DriveSetDistance(switchToSide));approach switch from the right
    		}
    }
    
    public static int getFirstDistance(int angle){
    	return (int) Math.sqrt(Math.pow(angle, 2) + Math.pow(wallToSwitch - initialDriveForward, 2));
	//using pythagorean theorem to find the distance needed to travel^
    }
    
    public static int getSecondDistanceForScale(){
    	return wallToScale - wallToSwitch - initialDriveForward;//distance that we have to drive from switch to scale
    }
    
    public static int getAdjacent(char position){//getting the distance from robot to right wall
    	
    	if(position == 'a'){
    		distance = 1;}//distances are undetermined
    	else if(position == 'b'){
    		distance = 1;}
    	else if(position == 'c'){
    		distance = 1;}
    	return distance;
    	
    	}
    
    public static double getFirstAngle(int adjacent){
    	return Math.atan((wallToSwitch-initialDriveForward)/adjacent);//basically, tan^-1(opposite/adjacent) = angle
    }
    
    public static double getSecondAngleForScale(double angle){
    	return -angle;
    }
    
    public static double getSecondAngleForSwitch(double angle){
    	return -1*(90 + angle);
    }
    
    public static int getThirdAngle(){
    	return -90;
    }
}
   
