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
        hoodActuator = new Solenoid(PneumaticsModuleType.CTREPCM, channel);
    }

    public void toggleShooterHood(){

        //Lowers the shooter hood for high goal
        if (Robot.xboxController.getRawButton(Xbox.A)){
            hoodActuator.set(false);
            hoodState = HoodState.DOWN;
        }//end if

        //Raises the shooter hood for low goal
        else if (Robot.xboxController.getRawButton(Xbox.B)){
            hoodActuator.set(true);
            hoodState = HoodState.DOWN;
        }//end else if
    }//end toggleShooter()
}//end HoodActuator
