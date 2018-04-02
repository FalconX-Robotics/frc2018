/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6662.robot;

import org.usfirst.frc.team6662.robot.AutoMeasures.Element;
import org.usfirst.frc.team6662.robot.AutoMeasures.StartingPosition;
import org.usfirst.frc.team6662.robot.commands.AutoScale;
import org.usfirst.frc.team6662.robot.commands.AutoSwitch;
import org.usfirst.frc.team6662.robot.commands.DriveForward;
import org.usfirst.frc.team6662.robot.subsystems.Drivetrain;
import org.usfirst.frc.team6662.robot.subsystems.Elevator;
import org.usfirst.frc.team6662.robot.subsystems.RolleyGrabber;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends TimedRobot {
	public static final int LENGTH = 40; // inches
	
	public static Drivetrain drivetrain;
	public static Elevator elevator;
	public static RolleyGrabber rolleyGrabber;
	
	public static OI oi;

	private Command driveForward;
	private Command autoRun;
	
	private SendableChooser<StartingPosition> positionSelect;
	private SendableChooser<Element> elementSelect;
	
	@Override
	public void robotInit() {
		drivetrain = new Drivetrain();
		elevator = new Elevator(); 
		rolleyGrabber = new RolleyGrabber();

		oi = new OI();
		
		driveForward = new DriveForward(AutoMeasures.TO_AUTOLINE);
		
		positionSelect = new SendableChooser<>();
		positionSelect.addDefault("Switch from left", StartingPosition.L);
		positionSelect.addObject("Switch from middle", StartingPosition.M);
		positionSelect.addObject("Switch from right", StartingPosition.R);
		
		elementSelect = new SendableChooser<>();
		elementSelect.addDefault("Switch", Element.SWITCH);
		elementSelect.addObject("Scale", Element.SCALE);
		elementSelect.addObject("Autoline", Element.AUTOLINE);
		elementSelect.addObject("Same Side Switch and Scale", Element.COMBINE);
		
		SmartDashboard.putData("Position Select", positionSelect);
		SmartDashboard.putData("Element Select", elementSelect);
		
		CameraServer.getInstance().startAutomaticCapture();
	}
	
	@Override
	public void autonomousInit() {
		StartingPosition startingPosition = positionSelect.getSelected();
		Element element = elementSelect.getSelected();
		
		String gameData = "";
		char allianceSwitch = ' ';
		char allianceScale = ' ';
		
		SmartDashboard.putString("Position", startingPosition.toString());
		SmartDashboard.putString("Element", element.toString());
	
		while (gameData.length() != 3) {
			gameData = DriverStation.getInstance().getGameSpecificMessage();
		}
		
		allianceSwitch = gameData.charAt(0);
		allianceScale = gameData.charAt(1);
		
		switch(element) {
		case SWITCH:
			autoRun = new AutoSwitch(startingPosition, allianceSwitch);
			break;
		case SCALE:
			autoRun = new AutoScale(startingPosition, allianceScale);
			break;
		case COMBINE:
			if (startingPosition.getChar() == allianceSwitch) {
				autoRun = new AutoSwitch(startingPosition, allianceSwitch);
			}
			else if (startingPosition.getChar() == allianceScale) {
				autoRun = new AutoScale(startingPosition, allianceScale);
			}
			else {
				autoRun = driveForward;
			}
			break;
		default:
			autoRun = driveForward;
			break;
		}
		
		autoRun.start();
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