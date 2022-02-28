
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.systems;


public class AutoStep {

    public enum STEP_ID {
        END,
        SPIN_UP_SHOOTER,
        STOP_SHOOTER,
        SHOOT_ONE,
        SHOOT_ALL,
        ADVANCE_INTAKE,
        FIND_LINE,
        FWD_500MS,
        REV_500MS,
        HOOK_UP_500MS,
        HOOK_DOWN_500MS,
        SHOW_TEST_STARTED,
        DELAY_1SEC,
        SHOW_TEST_ENDED,
        NUMBER_OF_STEP_TYPES
    };

    public STEP_ID  stepID;
 

  public AutoStep() {
     }
     
    public void stepInit() {

    }

    public void stepExit() {

    }

    public Boolean stepIsComplete() {
        return true;
    }

    public void stepPeriodic() {

    }

}
