
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.systems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.systems.AutoRunner.DRIVETRAIN_CONTROL_TYPE;
import frc.systems.AutoStep.STEP_ID;

public class AutoTask {

    public DRIVETRAIN_CONTROL_TYPE taskDrivetrainRequired = DRIVETRAIN_CONTROL_TYPE.AUTO;

    public static StepEnd stepEnd;
    public static StepDelay_1sec stepDelay_1sec;
     public static StepAutoShoot autoShooter;

    protected AutoStep[] mySteps;
    protected AutoRunner.TASK_ID myTaskID;

    private int myCurrentStepIndex = 0;

    private AutoStep[] stepsForNoneTask;

    public AutoTask() {
         autoShooter = new StepAutoShoot();
        stepEnd = new StepEnd();
        stepDelay_1sec = new StepDelay_1sec();

        stepsForNoneTask = new AutoStep[] {
                stepEnd
        };
        taskInit(AutoRunner.TASK_ID.NONE, stepsForNoneTask);
    }

    public void taskInit(AutoRunner.TASK_ID id, AutoStep[] steps) {
        mySteps = steps;
        myTaskID = id;
        myCurrentStepIndex = 0;
    }

    public AutoStep getCurrentStep() {
        try {
            return mySteps[myCurrentStepIndex];
        } catch (Exception e) {
            System.err.println("Exception in getCurrentStep");
            e.printStackTrace();
        }
        return new AutoStep();
    }

    public int getCurrentStepNumber() {
        return myCurrentStepIndex;
    }

    public void gotoNextStep() {
        try {
            if (getCurrentStep().stepID != STEP_ID.END) {
                getCurrentStep().stepExit();
                myCurrentStepIndex++;
                getCurrentStep().stepInit();
            }
            SmartDashboard.putNumber("step# ", getCurrentStepNumber());
            SmartDashboard.putString("Current step", getCurrentStep().stepID.toString());
        } catch (Exception e) {
            System.err.println("Exception in gotoNextStep");
            e.printStackTrace();
        }
    }

    public void taskStart() {
        try {
            myCurrentStepIndex = 0;
            SmartDashboard.putNumber("step# ", getCurrentStepNumber());
            SmartDashboard.putString("Current step", getCurrentStep().stepID.toString());
            getCurrentStep().stepInit();
        } catch (Exception e) {
            System.err.println("Exception in taskStart");
            e.printStackTrace();
        }
    }

    public void taskStop() {
        try {
            while (getCurrentStep().stepID != STEP_ID.END) {
                myCurrentStepIndex++;
                SmartDashboard.putNumber("step# ", getCurrentStepNumber());
                SmartDashboard.putString("Current step", getCurrentStep().stepID.toString());
            }
        } catch (Exception e) {
            System.err.println("Exception in taskStop");
            e.printStackTrace();
        }
    }

    public Boolean taskIsDone() {
        try {
            return (getCurrentStep().stepID == STEP_ID.END);
        } catch (Exception e) {
            System.err.println("Exception in taskIsDone");
            e.printStackTrace();
        }
        return true;
    }
}
