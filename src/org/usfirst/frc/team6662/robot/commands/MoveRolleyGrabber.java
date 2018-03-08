package org.usfirst.frc.team6662.robot.commands;

import org.usfirst.frc.team6662.robot.Robot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class MoveRolleyGrabber extends Command{
	private double targetSeconds = 0.0;
	private double speed = 0.0;
	private double currentSeconds = 0.0;
	
	private Timer timer = new Timer();
	
	public MoveRolleyGrabber(double seconds, double speed, String direction) {
		super("Moving Rolleygrabber " + seconds + " seconds "+ direction + "wards");
		requires(Robot.rolleyGrabber);
		this.targetSeconds = seconds;
		//target seconds for how long the rolleygrabber will draw in the powercube
		if(direction == "In") {//this has to be in perspective of the right rolleygrabber motor
			this.speed = Math.abs(speed) * -1;
		}
		else {
			this.speed = Math.abs(speed);
		}
	}
	@Override
	protected void initialize() {
		timer.start();
		this.currentSeconds = 0.0;
	}
	@Override
	protected void execute() {
		Robot.rolleyGrabber.moveLeftMotor(speed);
		Robot.rolleyGrabber.moveRightMotor(speed);
	}
	
	@Override
	protected boolean isFinished() {
		currentSeconds = timer.get();
		if (timer.get() == targetSeconds) {
			timer.stop();
			timer.reset();
		}
		return this.currentSeconds == targetSeconds;
	}

}
