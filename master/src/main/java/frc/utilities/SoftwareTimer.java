package frc.utilities;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SoftwareTimer {

	private static double expirationTime = 0;

	public void setTimer(double timerValue) {
		expirationTime = Timer.getFPGATimestamp() + timerValue;
		SmartDashboard.putNumber("TIME", Timer.getFPGATimestamp());
	}

	public boolean isExpired() {
		return (Timer.getFPGATimestamp() > expirationTime);
		// if robotTime exceeds expirationTime, then this returns true
	}

    public boolean softDelay(double delay){
        setTimer(delay);
        if (isExpired())
        return true;
        else
        return false;
        
        //TODO: Create new method to set delay timer
    }
}
