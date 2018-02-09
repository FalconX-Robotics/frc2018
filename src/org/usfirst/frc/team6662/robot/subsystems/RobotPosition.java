package org.usfirst.frc.team6662.robot.subsystems;

import org.usfirst.frc.team6662.robot.Robot;
import edu.wpi.first.wpilibj.command.Subsystem;
public class RobotPosition extends Subsystem {
	public RobotPosition() {
		super("RobotPosition");
	}
	
	//static private double changeinxy [] = {0.0,0.0};//creates arrays
	static private double robotPosition[] = {0.0, 0.0};
	static private double targetPosition[] = {0.0, 0.0};
	
	static private double errorInX; //this is the difference of the target location and robot location
	static private double errorInY;
	static public double atanInput = errorInY/errorInX;
	static private double targetAngle = Math.atan(atanInput); // angle to face target
	static public double getTargetAngle() {return targetAngle;}
	static public void updateXYError() {errorInX = Math.abs(targetPosition[0] - robotPosition[0]);errorInY = Math.abs(targetPosition[1] - robotPosition[1]);}
	
	static double distance = 0.0; //distance from target point (hypotenuse)
	
	static public void setDistance(double newDistance) {distance = newDistance;}
	static public double getDistance() {return distance;}
	
	
	static public void setRobotPosition(double x, double y) {robotPosition[0]=x;robotPosition[1]=y;}
	static public double getRobotAxisPosition(boolean axis) {if (axis == false) {return robotPosition[0];} else {return robotPosition[1];}}
	/* Boolean axis is in robotmap: use false for x, and true for y, and use 
	 * the fields as a switch.
	 */
	private double changeinxy[] = {0.0, 0.0};
	
	public double getChangeInX() {
		return (Robot.drivetrain.getLeftEncoder().getDistance() + Robot.drivetrain.getRightEncoder().getDistance())/2 ;
	}
	
	public void getChangeInY() {
		
	}
	
	public void determineChange() {
		if (Robot.drivetrain.getGyro().getAngle() >= 360.0 || Robot.drivetrain.getGyro().getAngle() <= -360.0) {
			Robot.drivetrain.getGyro().reset();
		}
		if(Robot.drivetrain.getGyro().getAngle() == 0.0 || Math.abs(Robot.drivetrain.getGyro().getAngle()) == 360.0) {
			changeinxy[0] = 0.0;
			changeinxy[1] = getChangeInY();
		}
		if (0.0 < Robot.drivetrain.getGyro().getAngle() && Robot.drivetrain.getGyro().getAngle() < 90.0 || -360.0 < Robot.drivetrain.getGyro().getAngle() && Robot.drivetrain.getGyro().getAngle() < -270.0) {
			changeinxy[0] = changeinx;
			changeinxy[1] = getChangeInY()();
		}
		if (Robot.drivetrain.getGyro().getAngle() == 90.0 || Robot.drivetrain.getGyro().getAngle() == ) {
			changeinxy[0] = changeinx();
			changeinxy[1] = 0.0;
		}
		
		}
		if ((90.0 < Robot.drivetrain.getGyro().getAngle() && Robot.drivetrain.getGyro().getAngle() <= 180.0) || (-270.0 < Robot.drivetrain.getGyro().getAngle() && Robot.drivetrain.getGyro().getAngle() < -180.0)){
			changeinxy[0] = changeinx;
			changeinxy[1] = -getChangeInY();
		}
		if (180.0 < Robot.drivetrain.getGyro().getAngle() && Robot.drivetrain.getGyro().getAngle() < 270.0 ||  -180.0 < Robot.drivetrain.getGyro().getAngle() && Robot.drivetrain.getGyro().getAngle() < -90.0) {
			changeinxy[0] = -changeinx;
			changeinxy[1] = -getChangeInY();
		}
		else {
			changeinxy[0] = -changeinx;
			changeinxy[1] = getChangeInY();
		}
		
	}
	
	@Override
	protected void initDefaultCommand() {
	}
}
