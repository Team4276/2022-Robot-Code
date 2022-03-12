package frc.systems;

import com.ctre.phoenix.motorcontrol.ControlMode;

import frc.robot.Robot;
import frc.utilities.SoftwareTimer;

public class AutoCode {
public static SoftwareTimer autoShootTimer;
  public static SoftwareTimer shooterDelay;
  public static SoftwareTimer reverseDelay;
  private boolean isTaskFinished = false;

  public AutoCode(){
    autoShootTimer = new SoftwareTimer();
    shooterDelay = new SoftwareTimer();
    reverseDelay = new SoftwareTimer();
    autoShootTimer.setTimer(3);
    shooterDelay.setTimer(0.75);
    reverseDelay.setTimer(5);
  }

  public void runAuto(){
    if (!autoShootTimer.isExpired()) {
        ControlShooter.shooterMotor.set(ControlMode.PercentOutput, ControlShooter.highShooterPower);
        if (shooterDelay.isExpired()) {
            ControlShooter.upperMotor.set(ControlMode.PercentOutput, ControlShooter.feederPower);
            ControlShooter.lowerMotor.set(ControlMode.PercentOutput, ControlShooter.feederPower);
        }
    }
      
    else{
        if (!isTaskFinished){

          ControlShooter.upperMotor.set(ControlMode.PercentOutput, 0);
          ControlShooter.lowerMotor.set(ControlMode.PercentOutput, 0);
          ControlShooter.shooterMotor.set(ControlMode.PercentOutput, 0);
          Robot.mDrivetrain.assignMotorPower(-0.4, -0.4);
          if(reverseDelay.isExpired()){
            Robot.mDrivetrain.assignMotorPower(0, 0);
            isTaskFinished = true;
          }
          


        }
        
        
        
        
    }       
  }
    
}