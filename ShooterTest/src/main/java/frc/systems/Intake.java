package frc.systems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.Encoder;
import frc.robot.Robot;
import frc.utilities.Xbox;

public class Intake {

    private static VictorSPX intakeMotor;
    private static VictorSPX raiseIntake;
    private static Encoder raiseIntakeEncoder;

    private static double intakeMotorPower = 0.5;
    private static double raiseInatakePower = 0.5;

    public Intake(int intakeMotorInput, int raiseIntakeInput, int encoderIntakeA, int encoderIntakeB){
        intakeMotor = new VictorSPX(intakeMotorInput);
        raiseIntake = new VictorSPX(raiseIntakeInput);
        raiseIntakeEncoder = new Encoder(encoderIntakeA, encoderIntakeB);

    }//end constructor

    public void runIntake(){

        //intake balls
        if (Robot.xboxController.getRawButton(Xbox.LB))
            intakeMotor.set(ControlMode.PercentOutput, intakeMotorPower);

        //outtake balls
        else if (Robot.xboxController.getRawButton(Xbox.RB))
            intakeMotor.set(ControlMode.PercentOutput, -intakeMotorPower);

        //raise intake
        if (Robot.xboxController.getRawAxis(Xbox.LAxisY)>0.1){
            raiseIntake.set(ControlMode.PercentOutput, raiseInatakePower);

        }
            
        //lower intake 
        else if (Robot.xboxController.getRawAxis(Xbox.LAxisY)<-0.1){
            raiseIntake.set(ControlMode.PercentOutput, -raiseInatakePower);
            
        }
            
        
    }//end runIntake()
}//end class
