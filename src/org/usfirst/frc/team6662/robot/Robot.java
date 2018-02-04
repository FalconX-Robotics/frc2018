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
	
	@Override
	public void robotInit() {
		drivetrain = new Drivetrain();
		oi = new OI();
	}
	
	@Override
	public void autonomousInit() {
		String gameData = DriverStation.getInstance().getGameSpecificMessage();
		char allianceSwitch = gameData.charAt(0);
		char scale = gameData.charAt(1);
		char opponentSwitch = gameData.charAt(2);
		
		if (allianceSwitch == 'L') {
			
		}
		else if (allianceSwitch == 'R') {
			
		}
		
		if (scale == 'L') {
			
		}
		else if (scale == 'R') {
			
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
