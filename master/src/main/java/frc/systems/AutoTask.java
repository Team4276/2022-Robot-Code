
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.systems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.systems.AutoStep.STEP_ID;

public class AutoTask {

    public static StepEnd stepEnd;
    public static StepDelay_1sec stepDelay_1sec;

    protected AutoStep[] mySteps;
    protected AutoRunner.TASK_ID myTaskID;

    private int myCurrentStepIndex = 0;

    private AutoStep[] stepsForNoneTask;

    public AutoTask() {
        stepEnd = new StepEnd();
        stepDelay_1sec = new StepDelay_1sec();

        stepsForNoneTask = new AutoStep[] {
                stepEnd
        };
        taskInit(AutoRunner.TASK_ID.NONE, stepsForNoneTask);
    }

    public void taskInit(AutoRunner.TASK_ID id, AutoStep[] steps) {
        mySteps = steps;
        myTaskID = id;
        myCurrentStepIndex = 0;
    }

    public AutoStep getCurrentStep() {
        return mySteps[myCurrentStepIndex];
    }

    public int getCurrentStepNumber() {
        return myCurrentStepIndex;
    }

    public void gotoNextStep() {
        if (getCurrentStep().stepID != STEP_ID.END) {
            getCurrentStep().stepExit();
            myCurrentStepIndex++;
            getCurrentStep().stepInit();
        }
        SmartDashboard.putNumber("step# ", getCurrentStepNumber());
        SmartDashboard.putString("Current step", getCurrentStep().stepID.toString());
    }

    public void taskStop() {
        while (getCurrentStep().stepID != STEP_ID.END) {
            myCurrentStepIndex++;
        }
    }

    public Boolean taskIsDone() {
        return (getCurrentStep().stepID == STEP_ID.END);
    }
}
