
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.systems;

import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.Robot;
import frc.utilities.RoboRioPorts;

public class TaskClimb extends AutoTask {

    private DigitalInput lineSensor_L;

    private final TASK_STEP[] stepsForClimbTask = {
            TASK_STEP.FIND_LINE,
            TASK_STEP.END };

    public TaskClimb() {
        super();
        lineSensor_L = new DigitalInput(RoboRioPorts.DIO_AUTO_CLIMB);
        taskInit(AutoRunner.TASK_ID.CLIMB, stepsForClimbTask);
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

            case FIND_LINE:
                // set delay for how long to search for line
                myTimer.setTimer(10.0);

                // Drive forward slowly
                Robot.mDrivetrain.assignMotorPower(0.1, 0.1);
                break;

            default:
                break;
        }
    }

    @Override
    public void stepExit(TASK_STEP step) {
        switch (getCurrentStep()) {
            case FIND_LINE:
            Robot.mDrivetrain.assignMotorPower(0.0, 0.0);
            break;

            default:
                break;
        }
    }

    @Override
    public Boolean stepIsComplete() {
        if (getCurrentStep() == TASK_STEP.END) {
            return true;
        }

        // Continue only if the button is still held down
        if (!Robot.IsAutoClimbButtonPushed()) {
            return true;
        }

        switch (getCurrentStep()) {
            case DELAY_1SEC:
                return myTimer.isExpired();

            case FIND_LINE:
                if (myTimer.isExpired()) {
                    // Timed out looking for the line
                    return true;
                }
                if (lineSensor_L.get()) {
                    return true;
                }
                return false;

            default:
                return true;
        }
    }

    @Override
    public void stepPeriodic() {
        switch (getCurrentStep()) {
            case FIND_LINE:

                break;

            default:
                break;
        }

    }
}
