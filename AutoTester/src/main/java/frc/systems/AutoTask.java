
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.systems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.systems.AutoRunner.TASK_ID;
import frc.utilities.SoftwareTimer;

public class AutoTask {

    public enum TASK_STEP {
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
        SHOW_TEST_ENDED
    };

    protected TASK_STEP[] mySteps;
    protected AutoRunner.TASK_ID myTaskID;
    protected SoftwareTimer myTimer;

    private int myCurrentStepIndex = 0;

    public AutoTask() {
        mySteps = new TASK_STEP[] { TASK_STEP.END };
        myTaskID = TASK_ID.NONE;
        myCurrentStepIndex = 0;
    }

    public void taskInit(AutoRunner.TASK_ID id, TASK_STEP[] steps) {
        myTimer = new SoftwareTimer();
        mySteps = steps;
        myTaskID = id;
        myCurrentStepIndex = 0;
    }

    public TASK_STEP getCurrentStep() {
        return mySteps[myCurrentStepIndex];
    }

    public int getCurrentStepNumber() {
        return myCurrentStepIndex;
    }

    public void gotoNextStep() {
        if (getCurrentStep() != TASK_STEP.END) {
            stepExit(mySteps[myCurrentStepIndex]);
            myCurrentStepIndex++;
            stepInit(mySteps[myCurrentStepIndex]);
        }
        SmartDashboard.putNumber("step# ", getCurrentStepNumber());
        SmartDashboard.putString("Current step", getCurrentStep().toString());
    }

    public void taskStop() {
        while (getCurrentStep() != TASK_STEP.END) {
            myCurrentStepIndex++;
        }
    }

    public Boolean taskIsDone() {
        return (getCurrentStep() == TASK_STEP.END);
    }

    public void stepInit(TASK_STEP step) {

    }

    public void stepExit(TASK_STEP step) {

    }

    public Boolean stepIsComplete() {
        return true;
    }

    public void stepPeriodic() {

    }

}
