
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.systems;

public class TaskTestAuto extends AutoTask {

    private final TASK_STEP[] stepsForTestAutoTask = {
            TASK_STEP.SHOW_TEST_STARTED,
            TASK_STEP.DELAY_1SEC,
            TASK_STEP.DELAY_1SEC,
            TASK_STEP.DELAY_1SEC,
            TASK_STEP.DELAY_1SEC,
            TASK_STEP.DELAY_1SEC,
            TASK_STEP.SHOW_TEST_ENDED,
            TASK_STEP.END
    };

    public TaskTestAuto() {
        super();
        taskInit(AutoRunner.TASK_ID.TEST_AUTO, stepsForTestAutoTask);
    }

    @Override
    public void taskInit(AutoRunner.TASK_ID id, TASK_STEP[] steps) {
    }

    @Override
    public void stepInit(TASK_STEP step) {

        switch (getCurrentStep()) {

            case DELAY_1SEC:
                myTimer.setTimer(1.0);
                break;

            default:
                break;
        }
    }

    @Override
    public void stepExit(TASK_STEP step) {

    }

    @Override
    public Boolean stepIsComplete() {
        if (getCurrentStep() == TASK_STEP.END) {
            return true;
        }

        switch (getCurrentStep()) {
            case DELAY_1SEC:
                return myTimer.isExpired();

            default:
                return true;
        }
    }

    @Override
    public void stepPeriodic() {
        switch (getCurrentStep()) {
            case FIND_LINE:
                // TODO: if delay expired, taskStop() and return

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
