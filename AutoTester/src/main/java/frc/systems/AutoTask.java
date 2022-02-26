
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.systems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.systems.AutoRunner.TASK_ID;
import frc.utilities.SoftwareTimer;

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
        SHOW_TEST_STARTED,
        DELAY_1SEC,
        SHOW_TEST_ENDED
    };

    protected TASK_STEP[] mySteps;
    protected AutoRunner.TASK_ID myTaskID;
    protected SoftwareTimer myTimer;

    private int myCurrentStepIndex = 0;

    public AutoTask() {
        mySteps = new TASK_STEP[] { TASK_STEP.END };
        myTaskID = TASK_ID.NONE;
        myCurrentStepIndex = 0;
    }

    public void Init(AutoRunner.TASK_ID id, TASK_STEP[] steps) {
        myTimer = new SoftwareTimer();
        mySteps = steps;
        myTaskID = id;
        myCurrentStepIndex = 0;
    }

    public TASK_STEP GetCurrentStep() {
        return mySteps[myCurrentStepIndex];
    }

    public int GetCurrentStepNumber() {
        return myCurrentStepIndex;
    }

    public void GotoNextStep() {
        if (GetCurrentStep() != TASK_STEP.END) {
            myCurrentStepIndex++;
            InitStep(mySteps[myCurrentStepIndex]);
        }
        SmartDashboard.putNumber("step# ", GetCurrentStepNumber());
        SmartDashboard.putString("Current step", GetCurrentStep().toString());
    }

    public void StopTask() {
        while (GetCurrentStep() != TASK_STEP.END) {
            myCurrentStepIndex++;
        }
    }

    public Boolean IsTaskDone() {
        return (GetCurrentStep() == TASK_STEP.END);
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
