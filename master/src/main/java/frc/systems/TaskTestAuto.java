
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.systems;

public class TaskTestAuto extends AutoTask {

    private final AutoStep[] stepsForTestAutoTask = {
        AutoTask.stepDelay_1sec,
        AutoTask.stepDelay_1sec,
        AutoTask.stepDelay_1sec,
        AutoTask.stepDelay_1sec,
        AutoTask.stepDelay_1sec,
        AutoTask.stepDelay_1sec,
        AutoTask.stepDelay_1sec,
        AutoTask.stepDelay_1sec,
        AutoTask.stepEnd
    };

    public TaskTestAuto() {
        super();
        taskInit(AutoRunner.TASK_ID.TEST_AUTO, stepsForTestAutoTask);
    }
}
