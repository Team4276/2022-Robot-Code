package frc.systems;

import frc.robot.Robot;
import frc.utilities.SoftwareTimer;

public class StepDrive extends AutoStep {

    private SoftwareTimer autoDriveTimer;
    private SoftwareTimer climbTimer;

    public StepDrive(){
        stepID = AutoStep.STEP_ID.FWD_500MS;  
        autoDriveTimer = new SoftwareTimer();
        climbTimer = new SoftwareTimer();
    }

    public void climbSetDriveTimer(){
        climbTimer.setTimer(0.5);
    }

    public void climbDriveForward(){    
        if(!climbTimer.isExpired())
        Robot.mDrivetrain.assignMotorPower(0.4, 0.4);
    }

    public void climbDriveReverse(){    
        if(!climbTimer.isExpired())
        Robot.mDrivetrain.assignMotorPower(-0.4, -0.4);
    }

    public void climbStopDrive(){
        if(climbTimer.isExpired())
        Robot.mDrivetrain.assignMotorPower(-0.0, -0.0);
    }

    public Boolean isClimbTimerExpired(){
        return climbTimer.isExpired();
    }

    //drive commands for autonomus
    public void setAutoDriveTimer(){
        autoDriveTimer.setTimer(5);
    }

    public void autoDriveReverse(){
        if(!autoDriveTimer.isExpired())
        Robot.mDrivetrain.assignMotorPower(-0.4, -0.4);   
    }

    public void autoDriveStop(){
        if(autoDriveTimer.isExpired())
        Robot.mDrivetrain.assignMotorPower(-0.0, -0.0);
    }

    public Boolean isAutoDriveTimerExpired(){
        return autoDriveTimer.isExpired();
    }

}
