/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
//test
package org.usfirst.frc.team6662.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import org.usfirst.frc.team6662.robot.commands.AutoDrive;
import org.usfirst.frc.team6662.robot.commands.TankDriveWithJoystick;
import org.usfirst.frc.team6662.robot.subsystems.Drivetrain;
import org.usfirst.frc.team6662.robot.subsystems.Elevator;

public class Robot extends TimedRobot {
	public static Drivetrain drivetrain;
	public static OI oi;
	public static Elevator elevator;
	Command autoDrive;
	Command tankDriveWithJoystick;
	
	char allianceSwitch;
	char scale;
	char opponentSwitch;
	
	double opp = 0.0;
	double position = 0.0;
	String element = "scale";
	String gameData = "";
	@Override
	public void robotInit() {
		drivetrain = new Drivetrain();
		oi = new OI();
		elevator = new Elevator();
	}
	
	@Override
	public void autonomousInit() {

		while (gameData == ""){
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		}
		char allianceSwitch = gameData.charAt(0);
		char scale = gameData.charAt(1);
		char opponentSwitch = gameData.charAt(2);	
	}
	
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}
	
	@Override
	public void teleopInit() {
		tankDriveWithJoystick = new TankDriveWithJoystick(oi.getJoystick(), 1, 5);
		
	}
	
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}
}
