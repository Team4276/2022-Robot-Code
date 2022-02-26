package frc.systems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.systems.HoodActuator.HoodState;
import frc.utilities.LimitSwitch;
import frc.utilities.Xbox;
import frc.utilities.LimitSwitch.BallState;

public class ControlShooter {

    public static VictorSPX upperMotor;
    public static VictorSPX lowerMotor;
    public static TalonSRX shooterMotor;
    
    private double highShooterPower = -0.45;
    private double feederPower = 0.6;
    private double lowShooterPower = 0.3;
    

    public ControlShooter(int upperMotorCANPort, int lowerMotorCANPort, int shooterCANPort ) {
        upperMotor = new VictorSPX(upperMotorCANPort);
        lowerMotor = new VictorSPX(lowerMotorCANPort);
        shooterMotor = new TalonSRX(shooterCANPort);
    }//end constructor

    public void runShooter(){
            
        //load shooter action
        if(Robot.xboxController.getRawAxis(Xbox.LT)>0.1){ 
            if (LimitSwitch.ballState == BallState.NONE){
                //upper open; lower open
                upperMotor.set(ControlMode.PercentOutput, feederPower);
                lowerMotor.set(ControlMode.PercentOutput, feederPower);
            }//end if

            else if (LimitSwitch.ballState == BallState.LOWER){
                //upper open; lower closed
                upperMotor.set(ControlMode.PercentOutput, feederPower);
                lowerMotor.set(ControlMode.PercentOutput, feederPower);
            }//end if
            
            else if (LimitSwitch.ballState == BallState.UPPER){
                if(Robot.xboxController.getRawAxis(Xbox.RT)==0){
                    //upper occupied; lower open
                    upperMotor.set(ControlMode.PercentOutput, 0);
                    lowerMotor.set(ControlMode.PercentOutput, feederPower);  
                }//sets power to upper Limit Switch values if RT is not pressesd
                
            }//end if

            else if (LimitSwitch.ballState == BallState.BOTH){
                if(Robot.xboxController.getRawAxis(Xbox.RT)==0){
                    //upper occupied; lower occupied
                    upperMotor.set(ControlMode.PercentOutput, 0);
                    lowerMotor.set(ControlMode.PercentOutput, 0);
                }//sets power to 0 if RT is not pressesd
            }//end if
        }//end load shooter action

        //high shooter action
        if(Robot.xboxController.getRawAxis(Xbox.RT)>0.1){
            upperMotor.set(ControlMode.PercentOutput, feederPower);
            lowerMotor.set(ControlMode.PercentOutput, feederPower);
            shooterMotor.set(ControlMode.PercentOutput, highShooterPower);
        }//end HIGH shoot action

        //this code will not run currently
        //low shooter action
        if (HoodActuator.hoodState == HoodState.UP){
            if(Robot.xboxController.getRawButton(Xbox.RT)){
                upperMotor.set(ControlMode.PercentOutput, feederPower);
                lowerMotor.set(ControlMode.PercentOutput, feederPower);
                shooterMotor.set(ControlMode.PercentOutput, lowShooterPower);
                }
        }//end low shoot action

        if(Robot.xboxController.getRawAxis(Xbox.LT) == 0 && Robot.xboxController.getRawAxis(Xbox.RT) == 0) {
            upperMotor.set(ControlMode.PercentOutput, 0);
            lowerMotor.set(ControlMode.PercentOutput, 0);
            shooterMotor.set(ControlMode.PercentOutput, 0);
        }//end reset motor power action

        //Smart Dashboard outputs 

        SmartDashboard.putNumber("Upper Motor Speed", upperMotor.getMotorOutputPercent());
        SmartDashboard.putNumber("Lower Motor Speed", lowerMotor.getMotorOutputPercent());

        if (LimitSwitch.ballState == BallState.NONE)       
        SmartDashboard.putString("INDEX STATUS:", "INDEXER EMPTY. PRESS LT TO LOAD");
        else if(LimitSwitch.ballState == BallState.LOWER)
        SmartDashboard.putString("INDEX STATUS:", "ONE BALL IN LOW POSITION. PRESS LT TO LOAD");
        else if(LimitSwitch.ballState == BallState.UPPER)
        SmartDashboard.putString("INDEX STATUS:", "ONE BALL IN UPPER POSITION. PRESS LT TO LOAD");
        else if(LimitSwitch.ballState == BallState.BOTH)
        SmartDashboard.putString("INDEX STATUS:", "INDEXER FULL. BOMBS AWAY!!");

    }//end loadShooter()
}//end class
