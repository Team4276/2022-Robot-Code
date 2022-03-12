
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.systems;

import frc.systems.AutoRunner.TASK_ID;

public class TaskClimb extends AutoTask {

    public static AutoStep[] stepsForClimbTask;
    
    public TaskClimb() {
        super();

        taskDrivetrainRequired = AutoRunner.DRIVETRAIN_CONTROL_TYPE.AUTO;

        stepsForClimbTask = new AutoStep[] {
                AutoTask.stepDelay_1sec,
                AutoTask.stepDelay_1sec,
                AutoTask.stepDelay_1sec,
                AutoTask.stepDelay_1sec,
                AutoTask.stepEnd
        };
        taskInit(TASK_ID.CLIMB, stepsForClimbTask);
    }
}