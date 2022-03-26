package frc.systems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.robot.Robot;
import frc.utilities.SoftwareTimer;
import frc.utilities.Xbox;

public class HoodActuator {

    public static DoubleSolenoid hoodSolenoid;
    public static Boolean hoodState;
    public static SoftwareTimer hoodActuatorTimer;

    public HoodActuator(){
        hoodSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 0, 1);
        hoodActuatorTimer = new SoftwareTimer();
    }

    public void runHoodActuator(){

        if(Robot.xboxController.getRawButton(Xbox.A)){
            if (hoodState){
                if(hoodActuatorTimer.isExpired()){
                    hoodSolenoid.set(Value.kForward);
                    hoodState = false;
                    hoodActuatorTimer.setTimer(0.5);
                }
            }
            else{
                if (hoodActuatorTimer.isExpired()){
                    hoodSolenoid.set(Value.kReverse);
                    hoodState = true;
                    hoodActuatorTimer.setTimer(0.5);
                }
                
            }
        }
        else
        hoodSolenoid.set(Value.kOff);

    }
}
