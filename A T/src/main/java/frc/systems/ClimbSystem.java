package frc.systems;

import frc.utilities.Xbox;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.Encoder;
public class ClimbSystem {

       /**
     * The class must:
     * 
     * Create a method to be able to read and output data from the sensor
     * Create a method that allows the robot to be able to position 
     * Create a mwthod that drives the robot forward then complets climbing action once the robot is beneth the bar     *  
     **/
    

    private final double MAX_POWER = 1.0;

    private TalonSRX myGrabberController;
    private Encoder myGrabberEncoder;

    private double grabberPower = 0;// motor speed needs to be calibrated
    double MAX_ENCODER_DISTANCE = 0;//needs to be calibrated to the max distance


    public ClimbSystem (int armCANPort1, int arm_encoderPortA, int arm_encoderPortB){
        myGrabberController = new TalonSRX(armCANPort1);
        myGrabberEncoder = new Encoder(arm_encoderPortA, arm_encoderPortB);
        myGrabberEncoder.reset();
    }

    public void positionGrabber(){
        /**
         * This method shoud detect the shadow and position the robot to be ready to raise the grabber
         * 
         * if (sensor doesn't detect shadow) drive forward
         * else stop the robot
         * 
         * may include side to side motion as well depending on what wheels we use
         */
    }//end positionGrabber()

    public void raiseGrabber(){
        while (myGrabberEncoder.getDistance()<=MAX_ENCODER_DISTANCE) {
            grabberPower = 0.5;//set to the same value as in the instantiation
        }
        //drive forward a little bit
        grabberPower = 0;
    }//end raiseGrabber()

    public void contractGrabber(){
        while (myGrabberEncoder.getDistance()>=0) {
            grabberPower = -0.5;// motor speed needs to be calibrated
        }
        grabberPower = 0;
    }//end contractGrabber()

    public void climbSystemPeriodic(){
        positionGrabber();
        raiseGrabber();
        contractGrabber();

        SmartDashboard.putNumber("Grabber Power", grabberPower);
        SmartDashboard.putNumber("Grabber Distance", myGrabberEncoder.getDistance()); 

    }//end climbSystemPeriodic()

}//end ClimbSystem class
