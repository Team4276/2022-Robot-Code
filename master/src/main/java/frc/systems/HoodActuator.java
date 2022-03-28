package frc.systems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.robot.Robot;
import frc.utilities.Xbox;

public class HoodActuator {

    public static DoubleSolenoid hoodSolenoid;

    public static enum HoodState{
        HIGH,
        LOW,
    }

    public static HoodState hoodState;

    public HoodActuator(){
        hoodSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 0, 1);
    }

    public void runHoodActuator(){

        if(Robot.xboxController.getRawButton(Xbox.A)){
            hoodSolenoid.set(Value.kForward);
            hoodState = HoodState.HIGH;
        }
            
        else if (Robot.xboxController.getRawButton(Xbox.B)){
            hoodSolenoid.set(Value.kReverse);
            hoodState = HoodState.LOW;
        }
                
        else{
        hoodSolenoid.set(Value.kOff);
        }

    }
}
