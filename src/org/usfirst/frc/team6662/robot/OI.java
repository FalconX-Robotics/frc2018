/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6662.robot;

import org.usfirst.frc.team6662.robot.commands.ShiftToHighGear;
import org.usfirst.frc.team6662.robot.commands.ShiftToLowGear;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {
	public static final int DRIVER_PORT = 0;
	public static final int MANIPULATOR_PORT = 1;

	public static final int SHIFT_DOWN_BUTTON = 5; // Xbox left bumper button
	public static final int SHIFT_UP_BUTTON = 6; // Xbox right bumper button
	
	public static final double XBOX_LEFT_Y_THRESHOLD = 0.1;
	public static final double XBOX_RIGHT_Y_THRESHOLD = 0.1;
	public static final double XBOX_LEFT_TRIGGER_THRESHOLD = 0.1;
	public static final double XBOX_RIGHT_TRIGGER_THRESHOLD = 0.1;
	
	public static final int POV_FORWARD = 0;
	public static final int POV_BACKWARD = 180;
	
	private XboxController driver = new XboxController(DRIVER_PORT);
	private XboxController manipulator = new XboxController(MANIPULATOR_PORT);
	
	private Button shiftDownButton = new JoystickButton(driver, SHIFT_DOWN_BUTTON);
	private Button shiftUpButton = new JoystickButton(driver, SHIFT_UP_BUTTON);
	
	public OI() {
		shiftDownButton.whenPressed(new ShiftToLowGear());
		shiftUpButton.whenPressed(new ShiftToHighGear());
	}
	
	public double getDriverLeftYAxis() {
		double rawLeftYAxis = driver.getY(Hand.kLeft);
		
		return deadband(rawLeftYAxis, XBOX_LEFT_Y_THRESHOLD);
	}
	
	public double getDriverRightYAxis() {
		double rawRightYAxis = driver.getY(Hand.kRight);
		
		return deadband(rawRightYAxis, XBOX_RIGHT_Y_THRESHOLD);
	}
	
	public double getManipulatorLeftYAxis() {
		double rawLeftYAxis = manipulator.getY(Hand.kLeft);
		
		return deadband(rawLeftYAxis, XBOX_LEFT_Y_THRESHOLD);
	}
	
	public double getManipulatorRightYAxis() {
		double rawRightYAxis = manipulator.getY(Hand.kRight);
		
		return deadband(rawRightYAxis, XBOX_RIGHT_Y_THRESHOLD);
	}
	
	public double getManipulatorLeftTriggerAxis() {
		double rawLeftTriggerAxis = manipulator.getTriggerAxis(Hand.kLeft);
		
		return deadband(rawLeftTriggerAxis, XBOX_LEFT_TRIGGER_THRESHOLD);
	}
	
	public double getManipulatorRightTriggerAxis() {
		double rawRightTriggerAxis = manipulator.getTriggerAxis(Hand.kRight);
		
		return deadband(rawRightTriggerAxis, XBOX_RIGHT_TRIGGER_THRESHOLD);
	}
	
	public int getManipulatorPOV() {
		return manipulator.getPOV();
	}
	
	public double deadband(double input, double threshold) {
		return Math.abs(input) <= Math.abs(threshold) ? 0 : input;
	}
}
