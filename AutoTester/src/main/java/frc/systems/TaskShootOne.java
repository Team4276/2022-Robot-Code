
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
            TASK_STEP.DELAY_1SEC,
            TASK_STEP.SHOOT_ONE,
            TASK_STEP.STOP_SHOOTER,
            TASK_STEP.ADVANCE_INTAKE,
            TASK_STEP.END
    };

    public TaskShootOne() {
        super();
        taskInit(AutoRunner.TASK_ID.SHOOT_ONE, stepsForShootOneTask);
    }

    @Override
    public void taskInit(AutoRunner.TASK_ID id, TASK_STEP[] steps) {

    }

    @Override
    public void stepInit(TASK_STEP step) {

        switch (getCurrentStep()) {
            case DELAY_1SEC:
                myTimer.setTimer(1.0);
                break;

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
    public void stepExit(TASK_STEP step) {

    }

    @Override
    public Boolean stepIsComplete() {
        if (getCurrentStep() == TASK_STEP.END) {
            return true;
        }
        switch (getCurrentStep()) {
            case DELAY_1SEC:
                return myTimer.isExpired();

            case SPIN_UP_SHOOTER:
                // TODO: return false if not up to speed yet
                break;

            case SHOOT_ONE:
                // TODO: return false if cycle ball into shooter is not complete yet
                break;

            case STOP_SHOOTER:
                return true; // Command was sent in stepInit(), nothing else to do

            case ADVANCE_INTAKE:
                // TODO return false if intake is still advancing
                break;

            default:
                break;
        }

        return true;
    }

    @Override
    public void stepPeriodic() {
        switch (getCurrentStep()) {
            case SPIN_UP_SHOOTER:
                // TODO: change motor speed
                break;

            case SHOOT_ONE:
                // TODO: advance upper intake to engage with shooter
                break;

            case ADVANCE_INTAKE:
                // TODO return false if intake is still advancing
                break;

            case STOP_SHOOTER: // Command sent in stepInit(), nothing else to do
            default:
                break;
        }

    }

}
