
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
        NUMBER_OF_TASK_ID, TEST_AUTO
    };

    private TASK_ID currentTask = TASK_ID.NONE;
    
    private AutoTask taskAutoShoot;
    private TaskClimb taskClimb;
    private TaskTestAuto taskAuto;

    public AutoRunner() {
        currentTask = TASK_ID.NONE;

        taskAutoShoot = new TaskShootOne();
        taskClimb = new TaskClimb();
        taskAuto= new TaskTestAuto();
    }

    public void StartTask(TASK_ID id) {
        currentTask = id;
        switch (currentTask) {
            case SHOOT_ONE:
                taskAutoShoot.Init();
                break;

                case CLIMB:
                taskClimb.Init();
                break;

                case TEST_AUTO:
                taskAuto.Init();
                break;

            case NONE:
            default:
                currentTask = TASK_ID.NONE;
                break;
        }
    }

    public void DoCurrentTask() {
        switch (currentTask) {
            case SHOOT_ONE:
                if (taskAutoShoot.IsCurrentStepComplete()) {
                    taskAutoShoot.GotoNextStep();
                } else {
                    taskAutoShoot.DoCurrentStep();
                }
                break;

            case CLIMB:
                if (taskClimb.IsCurrentStepComplete()) {
                    taskClimb.GotoNextStep();
                } else {
                    taskClimb.DoCurrentStep();
                }
                break;

            case NONE:
            default:
                currentTask = TASK_ID.NONE;
                break;
        }
        SmartDashboard.putNumber("Current Task: ", currentTask.ordinal());
        SmartDashboard.putNumber("Current Step: ", currentTask.ordinal());
    }
}
