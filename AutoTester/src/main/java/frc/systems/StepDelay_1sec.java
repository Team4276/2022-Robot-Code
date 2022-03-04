
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.systems;

import frc.utilities.SoftwareTimer;

public class StepDelay_1sec extends AutoStep {
   
    private SoftwareTimer myTimer;

    public StepDelay_1sec() {
        stepID = AutoStep.STEP_ID.DELAY_1SEC;
        myTimer = new SoftwareTimer();
    }

    @Override
    public void stepInit() {
        myTimer.setTimer(1.0);
    }
 
    @Override
     public void stepExit() {

    }

    @Override
    public Boolean stepIsComplete() {
        return myTimer.isExpired();
    }

    @Override

    public void stepPeriodic() {

    }

}
