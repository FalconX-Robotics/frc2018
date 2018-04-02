package org.usfirst.frc.team6662.robot;

public class AutoMeasures {
	public static final double ROBOT_LENGTH = 40;
	
	public static final double MIDDLE_TO_RIGHT = 144;
	public static final double MIDDLE_TO_LEFT = 120 - ROBOT_LENGTH;
	public static final double MIDWAY_WALL_TO_SWITCH = 70 - ROBOT_LENGTH;
	
	public static final double SCALE_MAX_HEIGHT = 58;
	public static final double SWITCH_HEIGHT = 20;
	
	public static final double TO_SCALE_X = 41.88 - ROBOT_LENGTH;
	public static final double TO_SWITCH_X = 55.56 - ROBOT_LENGTH;
	public static final double TO_SCALE_Y = 324 - ROBOT_LENGTH / 2;
	public static final double TO_SWITCH_Y = 168 - ROBOT_LENGTH / 2;
	public static final double TO_PLATFORM_ZONE_Y = 228.735 - ROBOT_LENGTH / 2;
	public static final double TO_AUTOLINE = 121;
	
	public enum Element {
		SWITCH, SCALE, AUTOLINE, COMBINE
	}
	
	public enum StartingPosition {
		L('L'), M('M'), R('R');
		
		char startingPosition;
		
		StartingPosition(char startingPosition) {
			this.startingPosition = startingPosition;
		}
		
		public char getChar() {
			return startingPosition;
		}
	}
	
	public enum Side {
		L, R
	}
}
