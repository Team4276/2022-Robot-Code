
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.systems;

import frc.systems.AutoRunner.TASK_ID;

public class TaskAutoRedLeft extends AutoTask {
 
    private final AutoStep[] stepsForAutoTask = {
        AutoTask.autoShooter,
        AutoTask.stepDrive,
        AutoTask.stepEnd
    
    };

    public TaskAutoRedLeft() {
        super();

        taskInit(TASK_ID.AUTO_RED_LEFT, stepsForAutoTask);
    }
}