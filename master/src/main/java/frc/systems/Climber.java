package frc.systems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import frc.robot.Robot;
import frc.utilities.RoboRioPorts;
import frc.utilities.Xbox;

public class Climber {

    public VictorSPX leftClimbMotor;
    public VictorSPX rightClimbMotor;

    public Climber(){
        leftClimbMotor = new VictorSPX(RoboRioPorts.CAN_LEFT_CLIMB);
        rightClimbMotor = new VictorSPX(RoboRioPorts.CAN_RIGHT_CLIMB);

    }

    public void runClimb(){
        if (Robot.xboxController.getRawButtonPressed(Xbox.X)){
            leftClimbMotor.set(ControlMode.PercentOutput, 0.3);
            rightClimbMotor.set(ControlMode.PercentOutput, 0.3);
            
         } else if (Robot.xboxController.getRawButtonPressed(Xbox.Y)){
             leftClimbMotor.set(ControlMode.PercentOutput, -0.3);
             rightClimbMotor.set(ControlMode.PercentOutput, -03);
                }
                else {

                    leftClimbMotor.set(ControlMode.PercentOutput, 0);
                    rightClimbMotor.set(ControlMode.PercentOutput, 0);
                }
            }
        
    }
        
