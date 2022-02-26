
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.systems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoRunner {

    public enum TASK_ID {
        NONE,
        SHOOT_ONE,
        CLIMB,
        TEST_AUTO
    };

    private AutoTask currentTask;

    public AutoRunner() {
        currentTask = new AutoTask();
        ;
    }

    public void StartTask(TASK_ID id) {
        switch (id) {
            case SHOOT_ONE:
                currentTask = new TaskShootOne();
                break;

            case CLIMB:
                currentTask = new TaskClimb();
                break;

            case TEST_AUTO:
                currentTask = new TaskTestAuto();
                break;

            case NONE:
            default:
                currentTask = new AutoTask();
                break;
        }
        SmartDashboard.putString("Current Task: ", currentTask.myTaskID.toString());
    }

    public void DoCurrentTask() {
        currentTask.stepPeriodic();
        if (currentTask.stepIsComplete()) {
            currentTask.gotoNextStep();
        }
        if (currentTask.taskIsDone()) {
            currentTask = new AutoTask();
            SmartDashboard.putString("Current Task: ", currentTask.myTaskID.toString());
        }
    }
}
