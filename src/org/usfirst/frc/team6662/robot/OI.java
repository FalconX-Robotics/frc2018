/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6662.robot;

import org.usfirst.frc.team6662.robot.commands.ShiftGear;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {
	public static final int JOYSTICK_PORT = 0;
	public static final int LEFT_Y_AXIS = 1;
	public static final int RIGHT_Y_AXIS = 5;
	
	public static final int SHIFTER_BUTTON_NUMBER = 6;
	
	private Joystick joystick = new Joystick(JOYSTICK_PORT);
	private Button shifterButton = new JoystickButton(joystick, SHIFTER_BUTTON_NUMBER);
	
	public OI() {
		shifterButton.whenPressed(new ShiftGear());
	}
	
	public Joystick getJoystick() {
		return joystick;
	}
}
