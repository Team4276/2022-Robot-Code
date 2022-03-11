package frc.systems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import frc.utilities.SoftwareTimer;

public class StepClimb extends AutoStep{
    
    public VictorSPX rightClimbMotor;
    public VictorSPX leftClimbMotor; 

    private SoftwareTimer climbTimer;
    private double climbPower = 0.25;

    public StepClimb(int rightPort, int leftPort) {
        rightClimbMotor = new VictorSPX(rightPort);
        leftClimbMotor = new VictorSPX(leftPort);
    }

    public void setClimbTimer(){
        climbTimer.setTimer(1.0);
    }

    public void extendClimber(){
        if (!climbTimer.isExpired()){
            
            rightClimbMotor.set(ControlMode.PercentOutput, climbPower);
            leftClimbMotor.set(ControlMode.PercentOutput,-climbPower);
        }

    }
    
}
