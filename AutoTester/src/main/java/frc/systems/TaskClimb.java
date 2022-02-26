
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.systems;

public class TaskClimb extends AutoTask {

    private final TASK_STEP[] stepsForClimbTask = {
            TASK_STEP.FIND_LINE,
            TASK_STEP.END };

    public TaskClimb() {
        super();
        Init(AutoRunner.TASK_ID.CLIMB, stepsForClimbTask);
    }

    @Override
    public void Init() {

    }

    @Override
    public void InitStep(TASK_STEP step) {

        switch (GetCurrentStep()) {
            case DELAY_1SEC:
                myTimer.setTimer(1.0);
                break;

            case FIND_LINE:
                // TODO: Set delay for how long to search for line
                break;

            default:
                break;
        }
    }

    @Override
    public Boolean IsCurrentStepComplete() {
        if (GetCurrentStep() == TASK_STEP.END) {
            return true;
        }

        // TODO: return true if soecific button is no longer held down

        switch (GetCurrentStep()) {
            case DELAY_1SEC:
                return myTimer.isExpired();

            case FIND_LINE:
                // TODO: return true if both sensors have found the line
                break;

            default:
                return true;
        }

        return true;
    }

    @Override
    public void DoCurrentStep() {
        switch (GetCurrentStep()) {
            case FIND_LINE:
                // TODO: if delay expired, StopTask() and return

                // TODO:
                // If left sensor has found the line
                // Left motor stop
                // else
                // Left motor forward

                // TODO:
                // If right sensor has found the line
                // right motor stop
                // else
                // right motor forward
                break;

            default:
                break;
        }

    }
}
