package frc.systems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import frc.robot.Robot;
import frc.utilities.RoboRioPorts;
import frc.utilities.Xbox;

public class Intake {

    private static VictorSPX intakeMotor;

    private static DoubleSolenoid intakeSolenoid;

    private static double intakeMotorPower = 0.5;

    public Intake(){
        intakeMotor = new VictorSPX(RoboRioPorts.CAN_INTAKE);
        intakeSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, RoboRioPorts.INTAKE_SOLENOID1, RoboRioPorts.INTAKE_SOLENOID2);

    }//end constructor

    public void runIntake(){

        //intake balls
        if (Robot.xboxController.getRawAxis(Xbox.RAxisY)>0.2)
            intakeMotor.set(ControlMode.PercentOutput, -intakeMotorPower);

        //outtake balls
        else if (Robot.xboxController.getRawAxis(Xbox.RAxisY)<-0.2)
            intakeMotor.set(ControlMode.PercentOutput, intakeMotorPower);
        else
        intakeMotor.set(ControlMode.PercentOutput, 0);
        
        //raise intake
        if (Robot.xboxController.getRawAxis(Xbox.LAxisY)>0.2)
        intakeSolenoid.set(DoubleSolenoid.Value.kReverse);

        //lower intake 
        else if (Robot.xboxController.getRawAxis(Xbox.LAxisY)<-0.2)
            intakeSolenoid.set(DoubleSolenoid.Value.kForward);
        
    }//end runIntake()
}//end class
