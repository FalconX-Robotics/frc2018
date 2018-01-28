/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6662.robot;

import org.usfirst.frc.team6662.robot.commands.ShiftGear;
import org.usfirst.frc.team6662.robot.commands.TurnLeft;
import org.usfirst.frc.team6662.robot.commands.TurnRight;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {
	public static final int JOYSTICK_PORT = 0;
	
	public static final int LEFT_Y_AXIS = 1;
	public static final int RIGHT_Y_AXIS = 5;
	
	public static final int SHIFTER_BUTTON_NUMBER = 1; // Xbox "A" button
	public static final int TURN_LEFT_BUTTON_NUMBER = 5; // Xbox left shoulder
	public static final int TURN_RIGHT_BUTTON_NUMBER = 6; // Xbox right shoulder
	
	private Joystick joystick = new Joystick(JOYSTICK_PORT);
	
	private Button shifterButton = new JoystickButton(joystick, 
			SHIFTER_BUTTON_NUMBER);
	private Button turnLeftButton = new JoystickButton(joystick, 
			TURN_LEFT_BUTTON_NUMBER);
	private Button turnRightButton = new JoystickButton(joystick, 
			TURN_RIGHT_BUTTON_NUMBER);
	
	public OI() {
		shifterButton.whenPressed(new ShiftGear());
		turnLeftButton.whenPressed(new TurnLeft());
		turnRightButton.whenPressed(new TurnRight());
	}
	
	public Joystick getJoystick() {
		return joystick;
	}
}
