package frc.systems;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import frc.robot.Robot;
import frc.utilities.Xbox;

public class HoodActuator {

    public enum HoodState {UP,DOWN};

    public static HoodState hoodState;

    public Solenoid hoodActuator;
    public PneumaticsModuleType moduleType;
    public HoodActuator (int channel){
        hoodActuator = new Solenoid(moduleType, channel);
    }

    public void toggleShooterHood(){
        if (Robot.xboxController.getRawButton(Xbox.A)){
            hoodActuator.set(true);
            hoodState = HoodState.UP;
        }
        else if (Robot.xboxController.getRawButton(Xbox.B)){
            hoodActuator.set(false);
            hoodState = HoodState.DOWN;
        }    
    }
}
