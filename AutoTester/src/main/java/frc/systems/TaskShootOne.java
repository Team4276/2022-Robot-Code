
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.systems;


public class TaskShootOne extends AutoTask {

    private final AutoStep[] stepsForShootOneTask = {
            AutoTask.stepEnd
    };

    public TaskShootOne() {
        super();
        taskInit(AutoRunner.TASK_ID.SHOOT_ONE, stepsForShootOneTask);
    }
}
