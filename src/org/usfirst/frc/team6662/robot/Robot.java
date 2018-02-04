/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
//test
package org.usfirst.frc.team6662.robot;
//test : Please feel free to remove.
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;

import org.usfirst.frc.team6662.robot.subsystems.Drivetrain;

public class Robot extends TimedRobot {
	public static Drivetrain drivetrain;
	public static OI oi;
	
	Command testAutoCommand;
	
	@Override
	public void robotInit() {
		drivetrain = new Drivetrain();
		oi = new OI();
	}
	
	@Override
	public void autonomousInit() {
		
			final int leftSwitchDistancesPosA [] = {10,6,7};
		final int leftSwitchDistancesPosB [] = {2,4,5};
		final int leftSwitchDistancesPosC [] = {2,4,5};
		
		final int rightSwitchDistancesPosA [] = {2,3,4};
		final int rightSwitchDistancesPosB [] = {5,6,7};
		final int rightSwitchDistancesPosC [] = {9,5,3};
		
		final int leftScaleDistancesPosA [] = {2,3,4};
		final int leftScaleDistancesPosB [] = {32,13};
		final int leftScaleDistancesPosC [] = {3,5,8};
		
		final int rightScaleDistancesPosA [] = {8,4,9};
		final int rightScaleDistancesPosB [] = {6,3,6};
		final int RightScaleDistancesPosC [] = {3,5,6};
		
		final Command leftSide [] = {new TurnRight(), new TurnLeft(), new TurnRight()};
		final Command rightSide [] = {new TurnLeft(), new TurnRight(), new TurnLeft()};
		
		String gameData = DriverStation.getInstance().getGameSpecificMessage();
		char allianceSwitch = gameData.charAt(0);
		char scale = gameData.charAt(1);
		char opponentSwitch = gameData.charAt(2);
		
		if (allianceSwitch == 'L') {
			testAutoCommand = new TestAutoCommand(leftSwitchDistancesPosA, leftSide);
		testAutoCommand.start();
			
		}
		else if (allianceSwitch == 'R') {
			testAutoCommand = new TestAutoCommand(rightSwitchDistancesPosA, rightSide);
		testAutoCommand.start();
		}
		
		if (scale == 'L') {
			testAutoCommand = new TestAutoCommand(leftScaleDistancesPosA, leftSide);
		testAutoCommand.start();
			
		}
		else if (scale == 'R') {
			testAutoCommand = new TestAutoCommand(rightScaleDistancesPosA, rightSide);
		testAutoCommand.start();
		}
		
		if (opponentSwitch == 'L') {
			
		}
		else if (opponentSwitch == 'R') {
			
		}
	}
	
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}
	
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}
}
