package frc.utilities;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;


public class LimitSwitch extends DigitalInput {
    public LimitSwitch(int channel) {
        super(channel);//reads data from the roboRIO
    }//end method

    public boolean ballTouching() {
        return get();//.get() Get the value from a digital input channel. 
    }// end method

    //Determine state
    static public int ballState = -1;
    public int determineCase(){
        boolean lowerActive = Robot.lowerLimitSwitch.get();
        boolean upperActive = Robot.upperLimitSwitch.get();

        //flips value of upper limit switch to make 0 ground state (this can be removed if we use a different switch)
        if (upperActive==true)
        upperActive = false;
        else
        upperActive = true;

        //assigns values to ball state: https://trello.com/1/cards/61fe9da66b821961ffda689a/attachments/61fea05939f1fa34232eabfd/download/image.png
        
        //ocupied = true
        //open = false
        if (upperActive){
            if (lowerActive){
                ballState = 4;//upper occupied; lower occupied
                upperActive = true;
                lowerActive = true;
            }//end if
            else {
                ballState = 3;//upper occupied; lower open
                upperActive = true;
                lowerActive = false;
            }//end else
        }//end if
        else if (lowerActive){
            ballState = 2;//upper open; lower closed
            upperActive = false;
            lowerActive = true;
        }//end else if
            else {
            ballState = 1;//upper open; lower open
            upperActive = false;
            lowerActive = false;
            }//end else
            
            //outputs limit switch values and ball state to the smart dashboard
            SmartDashboard.putBoolean("Upper Limit Switch:", upperActive);
            SmartDashboard.putBoolean("Lower Limit Switch:", lowerActive);
            SmartDashboard.putNumber("Ball State:", ballState);

        return ballState;
    }//end determine case()

    
}//end class

