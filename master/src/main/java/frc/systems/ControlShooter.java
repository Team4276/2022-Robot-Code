package frc.systems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.utilities.LimitSwitch;
import frc.utilities.LimitSwitch.BallState;
import frc.utilities.Xbox;

public class ControlShooter extends ShooterCommands{

    /** See super for details on shooter commands **/

    public ControlShooter() {}

    public void runShooter() {
  
        if (!Robot.isAutoMode) {

            // load shooter action
            if (Robot.xboxController.getRawAxis(Xbox.LT) > 0.1) {
               super.feedIndexer();
            } 

            // high shooter action
            if (Robot.xboxController.getRawAxis(Xbox.RT) > 0.1) {
                super.shootHigh();
            } 

            //low shooter action
            if (Robot.xboxController.getRawButton(Xbox.Start)){
                super.shootLow();
            }
                
            //stop motor action
            if (Robot.xboxController.getRawAxis(Xbox.LT) == 0 && Robot.xboxController.getRawAxis(Xbox.RT) == 0) {
                super.motorStop();
            } 
        }

        // Smart Dashboard outputs
        SmartDashboard.putNumber("Upper Motor Speed", upperMotor.getMotorOutputPercent());
        SmartDashboard.putNumber("Lower Motor Speed", lowerMotor.getMotorOutputPercent());

        if (LimitSwitch.ballState == BallState.NONE)
            SmartDashboard.putString("INDEX STATUS:", "INDEXER EMPTY. PRESS LT TO LOAD");
        else if (LimitSwitch.ballState == BallState.LOWER)
            SmartDashboard.putString("INDEX STATUS:", "ONE BALL IN LOW POSITION. PRESS LT TO LOAD");
        else if (LimitSwitch.ballState == BallState.UPPER)
            SmartDashboard.putString("INDEX STATUS:", "ONE BALL IN UPPER POSITION. PRESS LT TO LOAD");
        else if (LimitSwitch.ballState == BallState.BOTH)
            SmartDashboard.putString("INDEX STATUS:", "INDEXER FULL. BOMBS AWAY!!");

    }// end runShooter()
}// end class
