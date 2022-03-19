package frc.systems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Timer;
import frc.robot.Robot;
import frc.utilities.LimitSwitch;
import frc.utilities.SoftwareTimer;
import frc.utilities.Xbox;
import frc.utilities.LimitSwitch.BallState;
import frc.utilities.RoboRioPorts;

public class ShooterCommands {

    public static VictorSPX upperMotor;
    public static VictorSPX lowerMotor;
    public static CANSparkMax shooterMotor;

    public static SoftwareTimer shootDelay;

    public static double highShooterPower = 1;// high power can range from 0.9 to 1.0 at a full battery
    public static double feederPower = 0.9;
    public double lowShooterPower = 0.65;

    public ShooterCommands() {
        upperMotor = new VictorSPX(RoboRioPorts.CAN_SHOOT_UPPER);
        lowerMotor = new VictorSPX(RoboRioPorts.CAN_SHOOT_LOWER);
        shooterMotor = new CANSparkMax(RoboRioPorts.CAN_SHOOTER, MotorType.kBrushless);
    }


    public void feedIndexer(){
        /**
         * This method uses the upper and lower limit switches on the indexer 
         * to feed the balls into the indexer. It runs/stops the feeder motors 
         * depending on the position of balls on the limit switches. 
         * (See LimitSwitch.java for more info on how the limit switches work).
         */

        if (LimitSwitch.ballState == BallState.NONE) {
            // upper open; lower open
            upperMotor.set(ControlMode.PercentOutput, feederPower);
            lowerMotor.set(ControlMode.PercentOutput, feederPower);
        }

        else if (LimitSwitch.ballState == BallState.LOWER) {
            // upper open; lower closed
            upperMotor.set(ControlMode.PercentOutput, feederPower);
            lowerMotor.set(ControlMode.PercentOutput, feederPower);
        }

        else if (LimitSwitch.ballState == BallState.UPPER) {
            if (Robot.xboxController.getRawAxis(Xbox.RT) == 0) {
                // upper occupied; lower open
                upperMotor.set(ControlMode.PercentOutput, 0);
                lowerMotor.set(ControlMode.PercentOutput, feederPower);
            }
        }

        else if (LimitSwitch.ballState == BallState.BOTH) {
            if (Robot.xboxController.getRawAxis(Xbox.RT) == 0) {
                // upper occupied; lower occupied
                upperMotor.set(ControlMode.PercentOutput, 0);
                lowerMotor.set(ControlMode.PercentOutput, 0);
            }
        } 
    }//end feedIndexer()

    public void shootHigh(){

    /**
     * Sends high power to the shooter action. Delays allow time 
     * for the shooter motor to increase its velocity before feeding
     * the next ball.
     */

        shooterMotor.set(highShooterPower);
        Timer.delay(.75);
        upperMotor.set(ControlMode.PercentOutput, feederPower);
        lowerMotor.set(ControlMode.PercentOutput, feederPower);
        Timer.delay(.75);
        upperMotor.set(ControlMode.PercentOutput, 0);
        lowerMotor.set(ControlMode.PercentOutput, 0);
        Timer.delay(.75);
        upperMotor.set(ControlMode.PercentOutput, feederPower);
        lowerMotor.set(ControlMode.PercentOutput, feederPower);

    }//end shootHigh()

    public void shootLow(){

    /**
     * Sends low power to the shooter action. There are no delays 
     * becasue low goal needs less power and doesn't need a delay after 
     * the first shot to spin up the shooter motor.
     */

        shooterMotor.set(lowShooterPower);
        Timer.delay(0.25);
        upperMotor.set(ControlMode.PercentOutput, feederPower);
        lowerMotor.set(ControlMode.PercentOutput, feederPower);

    }//end shootLow()
    
    public void motorStop(){

        /**Method that stops all the motors in the shooter assembly */

        upperMotor.set(ControlMode.PercentOutput, 0);
        lowerMotor.set(ControlMode.PercentOutput, 0);
        shooterMotor.set(0);

    }//end motorStop()

}//end class
