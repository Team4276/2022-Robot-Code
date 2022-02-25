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

      private static VictorSPX upperMotor;
      private static VictorSPX lowerMotor;
      private static TalonSRX shooterMotor;
      
      private double highShooterPower = -0.5;
      private double feederPower = 0.6;
      private double lowShooterPower = 0.3;
      

      public ControlShooter(int upperMotorCANPort, int lowerMotorCANPort, int shooterCANPort ) {
          upperMotor = new VictorSPX(upperMotorCANPort);
          lowerMotor = new VictorSPX(lowerMotorCANPort);
          shooterMotor = new TalonSRX(shooterCANPort);
      }//end constructor
  
      public void loadShooter(){
                
        if(Robot.xboxController.getRawAxis(Xbox.LT)>0.1){
            if (LimitSwitch.ballState == BallState.BOTH){
                //upper occupied; lower occupied
                upperMotor.set(ControlMode.PercentOutput, 0);
                lowerMotor.set(ControlMode.PercentOutput, 0);
                SmartDashboard.putNumber("Upper Motor Speed", upperMotor.getMotorOutputPercent());
                SmartDashboard.putNumber("Lower Motor Speed", lowerMotor.getMotorOutputPercent());
                SmartDashboard.putNumber("Balls in shooter:", 2);
            }//end if

            else if (LimitSwitch.ballState == BallState.UPPER){
                //upper occupied; lower open
                upperMotor.set(ControlMode.PercentOutput, 0);
                lowerMotor.set(ControlMode.PercentOutput, feederPower);
                SmartDashboard.putNumber("Upper Motor Speed", upperMotor.getMotorOutputPercent());
                SmartDashboard.putNumber("Lower Motor Speed", lowerMotor.getMotorOutputPercent());
                SmartDashboard.putNumber("Balls in shooter:", 1);
            }//end if

            else if (LimitSwitch.ballState == BallState.LOWER){
                //upper open; lower closed
                upperMotor.set(ControlMode.PercentOutput, feederPower);
                lowerMotor.set(ControlMode.PercentOutput, feederPower);
                SmartDashboard.putNumber("Upper Motor Speed", upperMotor.getMotorOutputPercent());
                SmartDashboard.putNumber("Lower Motor Speed", lowerMotor.getMotorOutputPercent());
                SmartDashboard.putNumber("Balls in shooter:", 1);
            }//end if

            else if (LimitSwitch.ballState == BallState.NONE){
                //upper open; lower open
                upperMotor.set(ControlMode.PercentOutput, feederPower);
                lowerMotor.set(ControlMode.PercentOutput, feederPower);
                SmartDashboard.putNumber("Upper Motor Speed", upperMotor.getMotorOutputPercent());
                SmartDashboard.putNumber("Lower Motor Speed", lowerMotor.getMotorOutputPercent());
                SmartDashboard.putNumber("Balls in shooter:", 0);
            }//end if
        }//end loadShooter if

          else{
              upperMotor.set(ControlMode.PercentOutput, 0);
              lowerMotor.set(ControlMode.PercentOutput, 0);
              SmartDashboard.putNumber("Upper Motor Speed", 0);
              SmartDashboard.putNumber("Lower Motor Speed", 0);
          }//end else
      }//end setShooterMotorSpeed()
       
      public void runShooter(){
        if (LimitSwitch.ballState == BallState.BOTH||LimitSwitch.ballState == BallState.UPPER){

            //Smart Dashboard Outputs...
            if (LimitSwitch.ballState == BallState.BOTH)
            SmartDashboard.putString("Can Shoot:", "There are 2 balls in the shooter. BOMBS AWAY!!");
            else
            SmartDashboard.putString("Can Shoot:", "There is 1 ball in the shooter. BOMBS AWAY!!");

         }//end check if balls in shooter   
         
        else if(LimitSwitch.ballState == BallState.LOWER)
        SmartDashboard.putString("Can Shoot:", "There is ONE ball in the LOW position. Press LT to load");
        if (LimitSwitch.ballState == BallState.NONE)       
        SmartDashboard.putString("Can Shoot:", "There ZERO balls in the shooter. Press LT to load ");
        
        //TODO: Might want to change this code. It may/may not work
        if (Robot.xboxController.getRawButton(Xbox.Start)){
            if (LimitSwitch.ballState == BallState.BOTH||LimitSwitch.ballState == BallState.UPPER){
                upperMotor.set(ControlMode.PercentOutput, feederPower);
                lowerMotor.set(ControlMode.PercentOutput, feederPower);
                shooterMotor.set(ControlMode.PercentOutput, highShooterPower);
            }
        }
        else{
            upperMotor.set(ControlMode.PercentOutput, 0);
            lowerMotor.set(ControlMode.PercentOutput, 0);
            shooterMotor.set(ControlMode.PercentOutput, 0);
        }//end if else: single shot
        
        //Use the right trigger to set the motor power to 
        if (Robot.xboxController.getRawAxis(Xbox.RT)>0.1){
            upperMotor.set(ControlMode.PercentOutput, feederPower);
            lowerMotor.set(ControlMode.PercentOutput, feederPower);
            shooterMotor.set(ControlMode.PercentOutput, highShooterPower);
        }
        else{
            upperMotor.set(ControlMode.PercentOutput, 0);
            lowerMotor.set(ControlMode.PercentOutput, 0);
            shooterMotor.set(ControlMode.PercentOutput, 0);
        }//end full power
        

        
    }//end startShooter()
}//end class
