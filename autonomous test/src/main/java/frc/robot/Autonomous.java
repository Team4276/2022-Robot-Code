package frc.robot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;





public class Autonomous extends Thread implements Runnable {

	// Selection modes
	private final int COMMIT_MODE = 0;
	private final int EDIT_MODE = 1;
	public String[] selectionModeArray = new String[2];
 public static SendableChooser selectionModeChooser;
	// Starting positions
	static final int LEFT = 0;
	static final int CENTER = 1;
	static final int RIGHT = 2;
	static String[] startPositionArray = new String[3];
	private final int red = 0;
	private final int blue = 1;
	static String[] redStartPositionArray = new String[3];
	static String[] blueStartPositionArray= new String[3];
	static String[] teamSelectionArray = new String [3];

	// Starting positions
	

	private int selectionMode = COMMIT_MODE;
	static int startingPosition = 0;
	private int team = 0;
	static int autoModeToExecute;

	public boolean breakLoop = false;

	public void Autonomous() {
		selectionModeArray[COMMIT_MODE] = "Commit mode";
		selectionModeArray[EDIT_MODE] = "Edit mode";
		teamSelectionArray[red] = "red";
		teamSelectionArray[blue] = "blue";
		redStartPositionArray[LEFT] = "left";
		redStartPositionArray[CENTER] = "center";
		redStartPositionArray[RIGHT] = "right";
		blueStartPositionArray[LEFT] = "left";
		blueStartPositionArray[CENTER] = "center";
		blueStartPositionArray[RIGHT] = "right";

	

			
		SmartDashboard.putString("Team: ", teamSelectionArray[team]);
		   
		

	}

	public void run() {
		
			//while (true) {
				/*
				 * if (selectionMode == COMMIT_MODE) { selectionMode = (int)
				 * selectionModeChooser.getSelected(); } else { selectionMode =
				 * (int) selectionModeChooser.getSelected(); startingPosition =
				 * (int) startPosition.getSelected(); // strategy = (int) //
				 * strategyChooser.getSelectedautoModeToExecute = //
				 * startingPosition; }
				 */

				if (Robot.xboxJoystick.getStartButton()) {
					startingPosition = LEFT;

				//} else if (Robot.logitechJoystickL.getRawButton(9)) {

					//startingPosition = CENTER;

				//} else if (Robot.logitechJoystickL.getRawButton(11)) {

					//startingPosition = RIGHT;

				}

				//if (Robot.logitechJoystickL.getRawButton(8)) {

					

				//} else if (Robot.logitechJoystickL.getRawButton(10)) {

					

				//}
				SmartDashboard.putString("Team", teamSelectionArray[team]);
				SmartDashboard.putString("Selection mode", selectionModeArray[selectionMode]);
				SmartDashboard.putString("Starting Position", startPositionArray[startingPosition]);
				

				Timer.delay(0.1);
				SmartDashboard.putBoolean("AutoSelector Error", false);

				
				
				
				
			
		}

			//}
		//} catch (Exception autoSelectorError) {

		//	SmartDashboard.putBoolean("AutoSelector Error", true);
		//	SmartDashboard.putString("Auto ERROR", autoSelectorError.getMessage());
		//}

	//}

}

