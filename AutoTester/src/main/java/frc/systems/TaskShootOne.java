
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.systems;

public class TaskShootOne extends AutoTask {

    private final TASK_STEP[] stepsForShootOneTask = {
            TASK_STEP.SPIN_UP_SHOOTER,
            TASK_STEP.SHOOT_ONE,
            TASK_STEP.STOP_SHOOTER,
            TASK_STEP.ADVANCE_INTAKE,
            TASK_STEP.END };

    public TaskShootOne() {
        super();
        myTaskID = AutoRunner.TASK_ID.SHOOT_ONE;
        mySteps = stepsForShootOneTask;
    }

    @Override
    public void Init() {

    }

    @Override
    public void InitStep(TASK_STEP step) {

        switch (GetCurrentStep()) {

            case STOP_SHOOTER:
                // TODO: Command motor to stop
                break;

            case SHOOT_ONE:
                // TODO: Set delay for how long to advance upper intake
                break;

            case SPIN_UP_SHOOTER:
            case ADVANCE_INTAKE:
            default:
                break;
        }
    }

    @Override
    public Boolean IsCurrentStepComplete() {
        if (GetCurrentStep() == TASK_STEP.END) {
            return true;
        }

        switch (GetCurrentStep()) {
            case SPIN_UP_SHOOTER:
                // TODO: return false if not up to speed yet
                break;

            case SHOOT_ONE:
                // TODO: return false if cycle ball into shooter is not complete yet
                break;

            case STOP_SHOOTER:
                return true;  // Command was sent in InitStep(), nothing else to do
 
            case ADVANCE_INTAKE:
                // TODO return false if intake is still advancing
                break;

            default:
                break;
        }

        return true;
    }

    @Override
    public void DoCurrentStep() {
        switch (GetCurrentStep()) {
            case SPIN_UP_SHOOTER:
                // TODO: change motor speed
                break;

            case SHOOT_ONE:
                // TODO: advance upper intake to engage with shooter
                break;

            case ADVANCE_INTAKE:
                // TODO return false if intake is still advancing
                break;

            case STOP_SHOOTER:  // Command sent in InitStep(), nothing else to do
            default:
                break;
        }

    }

}
