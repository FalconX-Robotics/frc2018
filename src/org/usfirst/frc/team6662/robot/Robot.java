/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6662.robot;

import org.usfirst.frc.team6662.robot.commands.AutoScale;
import org.usfirst.frc.team6662.robot.commands.AutoSwitch;
import org.usfirst.frc.team6662.robot.subsystems.Drivetrain;
import org.usfirst.frc.team6662.robot.subsystems.Elevator;
import org.usfirst.frc.team6662.robot.subsystems.RolleyGrabber;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

public class Robot extends TimedRobot {
	public static Drivetrain drivetrain;
	public static Elevator elevator;
	public static RolleyGrabber rolleyGrabber;
	
	Command autoRoute;
	SendableChooser<Command> autoChooser;
	
	public static OI oi;
	
	char allianceSwitch = 'L';
	char allianceScale = 'L';
	
	@Override
	public void robotInit() {
		drivetrain = new Drivetrain();
		elevator = new Elevator(); 
		rolleyGrabber = new RolleyGrabber();

		oi = new OI();
		
		autoChooser = new SendableChooser<Command>();
		
		CameraServer.getInstance().startAutomaticCapture();
	}
	
	@Override
	public void autonomousInit() {
		String gameData = "";		
		while (gameData != null && gameData.length() >= 2){
			gameData = DriverStation.getInstance().getGameSpecificMessage();
			allianceSwitch = gameData.charAt(0);
			allianceScale = gameData.charAt(1);
		}
		
		autoChooser.addDefault("Position L to Switch", new AutoSwitch("Left",allianceSwitch));
		autoChooser.addObject("Position M to Switch", new AutoSwitch("Middle",allianceSwitch));
		autoChooser.addObject("Position R to Switch", new AutoSwitch("Right",allianceSwitch));
		autoChooser.addObject("Position L to Scale", new AutoScale("Left",allianceScale));
		autoChooser.addObject("Position M to Scale", new AutoScale("Middle",allianceScale));
		autoChooser.addObject("Position R to Scale", new AutoScale("Right",allianceScale));
		
		autoRoute = autoChooser.getSelected();
		autoRoute.start();
	}
	
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}
	
	@Override
	public void teleopInit() {
		Scheduler.getInstance().removeAll();
	}
	
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}
}
