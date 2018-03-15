package org.usfirst.frc.team6662.robot.commands;

import org.usfirst.frc.team6662.robot.Robot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class MoveRolleyGrabber extends Command{
	private double targetSeconds = 0.0;
	private double speed = 0.0;
	private double currentSeconds = 0.0;
	
	private Timer timer = new Timer();
	
	public MoveRolleyGrabber(double speed) {
		this(speed, 0.0);
	}
	
	public MoveRolleyGrabber(double speed, double seconds) {
		super("Move rolley-grabber motors" + (seconds > 0 ? " for " + seconds + " seconds" : ""));
		requires(Robot.rolleyGrabber);
		
		this.speed = speed;
		this.targetSeconds = seconds;	
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
		
		boolean finished = currentSeconds >= targetSeconds;
		
		if (finished) {
			timer.stop();
			timer.reset();
		}
		
		return finished;
	}

}
