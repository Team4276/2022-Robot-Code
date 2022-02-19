
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.systems;

public class AutoTask {

    public enum TASK_STEP {
        END,
        SPIN_UP_SHOOTER,
        STOP_SHOOTER,
        SHOOT_ONE,
        SHOOT_ALL,
        ADVANCE_INTAKE,
        FIND_LINE,
        FWD_500MS,
        REV_500MS,
        HOOK_UP_500MS,
        HOOK_DOWN_500MS,
        NUMBER_OF_AUTO_STEPS
    };

    protected AutoRunner.TASK_ID myTaskID = AutoRunner.TASK_ID.NONE;
    protected TASK_STEP[] mySteps;

    private int myCurrentStepIndex = 0;

    public AutoTask(AutoRunner.TASK_ID id, TASK_STEP[] steps) {
        myTaskID = id;
        mySteps = steps;
    }

    public AutoTask() {
    }

    public TASK_STEP GetCurrentStep() {
        return mySteps[myCurrentStepIndex];
    }

    public void GotoNextStep() {
        if (GetCurrentStep() != TASK_STEP.END) {
            myCurrentStepIndex++;
            InitStep(mySteps[myCurrentStepIndex]);
        }
    }

    public void StopTask() {
        while (GetCurrentStep() != TASK_STEP.END) {
            myCurrentStepIndex++;
        }
    }

    public void Init() {

    }

    public void InitStep(TASK_STEP step) {

    }

    public Boolean IsCurrentStepComplete() {
        return true;
    }

    public void DoCurrentStep() {

    }

}
