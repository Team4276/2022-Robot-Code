
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.systems;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TaskTestAuto extends AutoTask {

    private final TASK_STEP[] stepsForTestAutoTask = {
            TASK_STEP.SHOW_TEST_STARTED,
            TASK_STEP.DELAY_1SEC,
            TASK_STEP.SHOW_TEST_ENDED,
            TASK_STEP.END
    };

    public TaskTestAuto() {
        super();
        myTaskID = AutoRunner.TASK_ID.TEST_AUTO;
        mySteps = stepsForTestAutoTask;
    }

    private Timer myDelayTimer;

    @Override
    public void Init() {
        myDelayTimer = new Timer();
    }

    @Override
    public void InitStep(TASK_STEP step) {

        switch (GetCurrentStep()) {

            case DELAY_1SEC:
                myDelayTimer.reset();
                break;

            case SHOW_TEST_STARTED:
                SmartDashboard.putString("TEST_AUTO:", "Test Started!");
                break;

            case SHOW_TEST_ENDED:
                SmartDashboard.putString("TEST_AUTO:", "Test END");
                break;

            default:
                break;
        }
        SmartDashboard.putString("Current step", GetCurrentStep().toString());
    }

    @Override
    public Boolean IsCurrentStepComplete() {
        if (GetCurrentStep() == TASK_STEP.END) {
            return true;
        }

        switch (GetCurrentStep()) {
            case DELAY_1SEC:
                return myDelayTimer.advanceIfElapsed(1.0);

            default:
                return true;
        }
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
