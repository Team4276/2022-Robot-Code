
package frc.robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Autonomous extends Thread {
    // Selection modes
    private final int COMMIT_MODE = 0;
    private final int EDIT_MODE = 1;
    public String[] selectionModeArray = new String[2];

    // Starting positions
    public static enum StartingPosition {
        LEFT, CENTER, RIGHT
    };

    static final int LEFT = 0;
    static final int CENTER = 1;
    static final int RIGHT = 2;
    static String[] startPositionArray = new String[3];
    final int red = 0;
    private final int blue = 1;
    static String[] redStartPositionArray = new String[3];
    static String[] blueStartPositionArray = new String[3];
    static String[] teamSelectionArray = new String[3];
    // Starting positions

    public int startingPosition = 0;
    public int team = 0;
    static int autoModeToExecute;
    public boolean breakLoop = false;
    public int selectionMode = COMMIT_MODE;

    public Autonomous() {
        selectionModeArray[COMMIT_MODE] = "Commit mode";
        selectionModeArray[EDIT_MODE] = "Edit mode";
        teamSelectionArray[red] = "red";
        teamSelectionArray[blue] = "blue";
        startPositionArray[LEFT] = "left";
        startPositionArray[CENTER] = "center";
        startPositionArray[RIGHT] = "right";

        SmartDashboard.putString("Selection mode", "COMMIT_MODE");
        // experimental

    }

    public void autonomousSelector() {

        if (Robot.leftJoystick.getRawButton(7) && (selectionMode == COMMIT_MODE)) {
            selectionMode = EDIT_MODE;
            SmartDashboard.putBoolean("Edit Mode?", true);
            SmartDashboard.putString("Selection mode", selectionModeArray[selectionMode]);
            Timer.delay(.5);
        } else if ((Robot.leftJoystick.getRawButton(7)) && (selectionMode == EDIT_MODE)) {
            selectionMode = COMMIT_MODE;
            SmartDashboard.putBoolean("Edit Mode?", false);
            SmartDashboard.putString("Selection mode", selectionModeArray[selectionMode]);
            Timer.delay(.5);
        }
        if ((Robot.leftJoystick.getRawButton(8)) && (selectionMode == EDIT_MODE)) {
            startingPosition = LEFT;
            SmartDashboard.putBoolean("LEFT", true);
            SmartDashboard.putBoolean("CENTER", false);
            SmartDashboard.putBoolean("RIGHT", false);
            SmartDashboard.putString("Starting Position", startPositionArray[startingPosition]);
        } else if ((Robot.leftJoystick.getRawButton(9)) && (selectionMode == EDIT_MODE)) {
            startingPosition = CENTER;
            SmartDashboard.putBoolean("CENTER", true);
            SmartDashboard.putBoolean("LEFT", false);
            SmartDashboard.putBoolean("RIGHT", false);
            SmartDashboard.putString("Starting Position", startPositionArray[startingPosition]);
        } else if ((Robot.leftJoystick.getRawButton(10)) && (selectionMode == EDIT_MODE)) {
            startingPosition = RIGHT;
            SmartDashboard.putBoolean("LEFT", false);
            SmartDashboard.putBoolean("CENTER", false);
            SmartDashboard.putBoolean("RIGHT", true);
            SmartDashboard.putString("Starting Position", startPositionArray[startingPosition]);
        }
        if ((Robot.leftJoystick.getRawButton(11)) && (selectionMode == EDIT_MODE)) {
            team = blue;
            SmartDashboard.putBoolean("blue team", true);
            SmartDashboard.putBoolean("red team", false);
            SmartDashboard.putString("Team", teamSelectionArray[team]);
        } else if ((Robot.leftJoystick.getRawButton(12)) && (selectionMode == EDIT_MODE)) {
            team = red;
            SmartDashboard.putBoolean("blue team", false);
            SmartDashboard.putBoolean("red team", true);
            SmartDashboard.putString("Team", teamSelectionArray[team]);
        }
        // } else if (Robot.logitechJoystickL.getRawButton(11)) {
        // startingPosition = RIGHT;

        // if (Robot.logitechJoystickL.getRawButton(8)) {
        // } else if (Robot.logitechJoystickL.getRawButton(10)) {
        // }

        // SmartDashboard.putString("Team", teamSelectionArray[team]);
        // SmartDashboard.putString("Selection mode",
        // selectionModeArray[selectionMode]);
        // SmartDashboard.putString("Starting Position",
        // startPositionArray[startingPosition]);
        // SmartDashboard.putBoolean("AutoSelector Error", false);
        Timer.delay(0.1);

        // }
        // } catch (Exception autoSelectorError) {
        // SmartDashboard.putBoolean("AutoSelector Error", true);
        // SmartDashboard.putString("Auto ERROR", autoSelectorError.getMessage());
        // }
        // }

    }

    public int getSelectionMode() {
        return selectionMode;
    }

    public int getTeamColor() {
        return team;
    }

    public int getPosition() {
        return startingPosition;
    }
}