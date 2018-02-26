/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6662.robot;

import org.usfirst.frc.team6662.robot.subsystems.Drivetrain;
import org.usfirst.frc.team6662.robot.subsystems.Elevator;
import org.usfirst.frc.team6662.robot.subsystems.RolleyGrabber;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;

public class Robot extends TimedRobot {
	public static Drivetrain drivetrain;
	public static Elevator elevator;
	public static RolleyGrabber rolleyGrabber;
	
	public static OI oi;
	
	@Override
	public void robotInit() {
		drivetrain = new Drivetrain();
		elevator = new Elevator(); 
		rolleyGrabber = new RolleyGrabber();
		
		oi = new OI();
		
		CameraServer.getInstance().startAutomaticCapture();
	}
	
	@Override
	public void autonomousInit() {
		
	}
	
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}
	
	@Override
	public void teleopInit() {
		
	}
	
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}
}
