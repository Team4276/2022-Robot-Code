package frc.systems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import frc.robot.Robot;
import frc.utilities.LimitSwitch;
import frc.utilities.RoboRioPorts;
import frc.utilities.Xbox;

public class Climber {

    public VictorSPX leftClimbMotor;
    public VictorSPX rightClimbMotor;

    public static LimitSwitch rightClimbSwitch;
    public static LimitSwitch leftClimbSwitch;

    public Climber(){
        leftClimbMotor = new VictorSPX(RoboRioPorts.CAN_LEFT_CLIMB);
        rightClimbMotor = new VictorSPX(RoboRioPorts.CAN_RIGHT_CLIMB);
        leftClimbSwitch = new LimitSwitch(RoboRioPorts.DIO_L_CLIMB_SWITCH);
        rightClimbSwitch = new LimitSwitch(RoboRioPorts.DIO_R_CLIMB_SWITCH);
        leftClimbMotor.setNeutralMode(NeutralMode.Brake);
        rightClimbMotor.setNeutralMode(NeutralMode.Brake);

    }

    public void runClimb(){
        //x = down
        //y = up


        if (Robot.xboxController.getRawButton(Xbox.X)){
            leftClimbMotor.set(ControlMode.PercentOutput, 1);
            rightClimbMotor.set(ControlMode.PercentOutput, 1);
            //Climber go Down(Make Robot go Up)
        } 
        else if (Robot.xboxController.getRawButton(Xbox.Y)){
            leftClimbMotor.set(ControlMode.PercentOutput, -0.5);
            rightClimbMotor.set(ControlMode.PercentOutput, -0.5);
  
        }
        else{
            leftClimbMotor.set(ControlMode.PercentOutput, 0);
            rightClimbMotor.set(ControlMode.PercentOutput, 0); 
        }
       
    }
                
        
                
                    
}
        
    
        