package frc.systems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import frc.robot.Robot;
import frc.utilities.Xbox;

public class Climber {

    public VictorSPX leftClimbMotor;
    public VictorSPX rightClimbMotor;

    public Climber(int rightClimbMotorPort, int leftClimbMotorPort){
        leftClimbMotor = new VictorSPX(leftClimbMotorPort);
        rightClimbMotor = new VictorSPX(rightClimbMotorPort);

    }

    public void runClimb(){
        if (Robot.xboxController.getRawButtonPressed(Xbox.X)){
            leftClimbMotor.set(ControlMode.PercentOutput, .2);
            rightClimbMotor.set(ControlMode.PercentOutput, -.2);
            
         } else if (Robot.xboxController.getRawButtonPressed(Xbox.Y)){
             leftClimbMotor.set(ControlMode.PercentOutput, -.2);
             rightClimbMotor.set(ControlMode.PercentOutput, .2);
                }
                else {

                    leftClimbMotor.set(ControlMode.PercentOutput, 0);
                    rightClimbMotor.set(ControlMode.PercentOutput, 0);
                }
            }
        
    }
        
