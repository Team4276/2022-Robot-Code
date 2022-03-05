package frc.systems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import frc.robot.Robot;
import frc.utilities.Xbox;

public class Intake {

    private static VictorSPX intakeMotor;

    private static Solenoid intakeSolenoid;

    private static double intakeMotorPower = 0.5;

    public Intake(int intakeMotorInput, int intakeSolenoidInput){
        intakeMotor = new VictorSPX(intakeMotorInput);
        intakeSolenoid = new Solenoid(PneumaticsModuleType.CTREPCM, intakeSolenoidInput);

    }//end constructor

    public void runIntake(){

        //intake balls
        if (Robot.xboxController.getRawButton(Xbox.LB))
            intakeMotor.set(ControlMode.PercentOutput, intakeMotorPower);

        //outtake balls
        else if (Robot.xboxController.getRawButton(Xbox.RB))
            intakeMotor.set(ControlMode.PercentOutput, -intakeMotorPower);

        //raise intake
        if (Robot.xboxController.getRawAxis(Xbox.LAxisY)>0.1)
            intakeSolenoid.set(true);

        //lower intake 
        else if (Robot.xboxController.getRawAxis(Xbox.LAxisY)<-0.1)
            intakeSolenoid.set(false);
        
    }//end runIntake()
}//end class
