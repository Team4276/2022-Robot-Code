package frc.systems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.robot.Robot;
import frc.utilities.Xbox;

public class HoodActuator {

    public static DoubleSolenoid hoodSolenoid;

    public static enum HoodState{
        UP,
        DOWN,
    }

    public static HoodState hoodState = HoodState.DOWN;
    
    public HoodActuator(){
        hoodSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 0, 1);
    }

    public void runHoodActuator(){

        if((Robot.xboxController.getRawButton(Xbox.A)) && (hoodState == HoodState.DOWN)){
            hoodSolenoid.set(Value.kForward);
            hoodState = HoodState.UP;
            Timer.delay(0.5);
        }
            
        else if ((Robot.xboxController.getRawButton(Xbox.A)) && (hoodState == HoodState.UP)){
            hoodSolenoid.set(Value.kReverse);
            hoodState = HoodState.DOWN;
            Timer.delay(0.5);
        }
                
        else{
        hoodSolenoid.set(Value.kOff);
        }

    }
}
