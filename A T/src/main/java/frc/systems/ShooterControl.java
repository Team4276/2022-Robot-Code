package frc.systems;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.utilities.LimitSwitch;
import frc.utilities.Xbox;

public class ShooterControl {
   

    //private final double MAX_POWER = 1.0;

    static public VictorSPX upperMotor;
    static public VictorSPX lowerMotor;
    static public VictorSPX shooterMotor;
    
    private double motorPower = 0.5;
  
    public ShooterControl(int shooterCANPort1, int shooterCANPort2) {
        upperMotor = new VictorSPX(shooterCANPort1);
        lowerMotor = new VictorSPX(shooterCANPort2);
    }//end constructor

    public void setShooterMotorSpeed(){
              
        //TODO: initial condition: Assign a button to Lord Garett's preference
        if(Robot.xboxController.getBButton()){

            if (LimitSwitch.ballState == 4){
                //upper occupied; lower occupied
                upperMotor.set(ControlMode.PercentOutput, 0);
                lowerMotor.set(ControlMode.PercentOutput, 0);
                SmartDashboard.putNumber("Upper Motor Speed", 0);
                SmartDashboard.putNumber("Lower Motor Speed", 0);
                SmartDashboard.putNumber("Balls in shooter:", 2);
            }//end if

            if (LimitSwitch.ballState == 3){
                //upper occupied; lower open
                upperMotor.set(ControlMode.PercentOutput, 0);
                lowerMotor.set(ControlMode.PercentOutput, motorPower);
                SmartDashboard.putNumber("Upper Motor Speed", 0.0);
                SmartDashboard.putNumber("Lower Motor Speed", motorPower);
                SmartDashboard.putNumber("Balls in shooter:", 1);
            }//end if

            if (LimitSwitch.ballState == 2){
                //upper open; lower closed
                upperMotor.set(ControlMode.PercentOutput, motorPower);
                lowerMotor.set(ControlMode.PercentOutput, motorPower);
                SmartDashboard.putNumber("Upper Motor Speed", motorPower);
                SmartDashboard.putNumber("Lower Motor Speed", motorPower);
                SmartDashboard.putNumber("Balls in shooter:", 1);
            }//end if

            if (LimitSwitch.ballState == 1){
                //upper open; lower open
                upperMotor.set(ControlMode.PercentOutput, motorPower);
                lowerMotor.set(ControlMode.PercentOutput, motorPower);
                SmartDashboard.putNumber("Upper Motor Speed", motorPower);
                SmartDashboard.putNumber("Lower Motor Speed", motorPower);
                SmartDashboard.putNumber("Balls in shooter:", 0);
            }//end if
            
            //outputs data to the smartdashboard
            //SmartDashboard.putNumber("Upper Motor Speed", upperMotor.getMotorOutputPercent());
            //SmartDashboard.putNumber("Lower Motor Speed", lowerMotor.getMotorOutputPercent());
            //SmartDashboard.putNumber("ballState", LimitSwitch.ballState);

        }//end initial if
        else{
            upperMotor.set(ControlMode.PercentOutput, 0);
            lowerMotor.set(ControlMode.PercentOutput, 0);
            SmartDashboard.putNumber("Upper Motor Speed", 0);
            SmartDashboard.putNumber("Lower Motor Speed", 0);
        }
    }//end setShooterMotorSpeed()

    public void runShooter(){
        if (LimitSwitch.ballState == 4||LimitSwitch.ballState == 3){
            if (LimitSwitch.ballState == 4)
            SmartDashboard.putString("Can Shoot:", "There are two balls in the shooter. BOMBS AWAY!!");
            else
            SmartDashboard.putString("Can Shoot:", "There is one ball in the shooter. BOMBS AWAY!!");

            if (Robot.xboxController.getYButton()){
                //Single shot
                //TODO: Set for a certian duration to be calibrated later
                upperMotor.set(ControlMode.PercentOutput, 0.5);
                shooterMotor.set(ControlMode.PercentOutput, 0.5);

            }//end if

            if (Robot.xboxController.getRightTriggerAxis()>0.1){
                //FULL POWAAAAAAA
                upperMotor.set(ControlMode.PercentOutput, Robot.xboxController.getRightTriggerAxis());
                lowerMotor.set(ControlMode.PercentOutput, Robot.xboxController.getRightTriggerAxis());
                shooterMotor.set(ControlMode.PercentOutput, Robot.xboxController.getRightTriggerAxis());

            }//end full power
        }//end if
        else
        SmartDashboard.putString("Can Shoot:", "There aren't enough balls in the shooter :(");
    }//end startShooter()
}//end class
