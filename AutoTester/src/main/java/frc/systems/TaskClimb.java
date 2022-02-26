
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.systems;

import frc.systems.AutoRunner.TASK_ID;

public class TaskClimb extends AutoTask {

    public StepFindLine stepFindLine;
 
    private final AutoStep[] stepsForClimbTask = {
            stepFindLine,
            AutoTask.stepEnd };

    public TaskClimb() {
        super();

        stepFindLine = new StepFindLine();
        stepEnd = new StepEnd();
        taskInit(TASK_ID.CLIMB, stepsForClimbTask);
    }
}