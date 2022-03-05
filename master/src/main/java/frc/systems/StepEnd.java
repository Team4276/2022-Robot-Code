
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.systems;


public class StepEnd extends AutoStep {
   
    public StepEnd() {
        stepID = AutoStep.STEP_ID.END;
    }

    @Override
    public void stepInit() {

    }
 
    @Override
     public void stepExit() {

    }

    @Override
    public Boolean stepIsComplete() {
        return false;
    }

    @Override

    public void stepPeriodic() {

    }

}
