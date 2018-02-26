package org.usfirst.frc.team6662.robot.commands;

import org.usfirst.frc.team6662.robot.OI;
import org.usfirst.frc.team6662.robot.Robot;
import org.usfirst.frc.team6662.robot.subsystems.RolleyGrabber;

import edu.wpi.first.wpilibj.command.Command;

public class MoveRolleyGrabberWithXbox extends Command {
	public MoveRolleyGrabberWithXbox() {
		super("Move rolley-grabber with Xbox controller");
		requires(Robot.rolleyGrabber);
	}
	
	@Override
	protected void execute() {
		double leftSpeed = -1 * Robot.oi.getManipulatorLeftYAxis();
		double rightSpeed = -1 * Robot.oi.getManipulatorRightYAxis();
		int pov = Robot.oi.getManipulatorPOV();
		
		if (pov == OI.POV_BACKWARD) {
			leftSpeed = RolleyGrabber.LEFT_PEAK_OUTPUT_REVERSE;
			rightSpeed = RolleyGrabber.RIGHT_PEAK_OUTPUT_REVERSE;
		}
		else if (pov == OI.POV_FORWARD) {
			leftSpeed = RolleyGrabber.LEFT_PEAK_OUTPUT_FORWARD;
			rightSpeed = RolleyGrabber.RIGHT_PEAK_OUTPUT_FORWARD;
		}
		
		Robot.rolleyGrabber.moveLeftMotor(leftSpeed);
		Robot.rolleyGrabber.moveRightMotor(rightSpeed);
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}
}
