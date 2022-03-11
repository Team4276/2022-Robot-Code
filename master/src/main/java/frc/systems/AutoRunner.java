
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.systems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoRunner {
    public enum TASK_ID {
        NONE,
        AUTO_RED_LEFT,
        AUTO_RED_CENTER,
        AUTO_RED_RIGHT,
        AUTO_BLUE_LEFT,
        AUTO_BLUE_CENTER,
        AUTO_BLUE_RIGHT,
        SHOOT_ONE,
        CLIMB,
        TEST_AUTO
    };

    public enum DRIVETRAIN_CONTROL_TYPE {
        NONE, // Robot not enabled, or driver control using joysticks etc.
        AUTO // Full autonomous uses motors / joysticks and any other controls for motors are
             // all disabled
        // SEMI-AUTO modes are possible, where only specific controls for motors needed
        // for a task are disabled
    }

    public static DRIVETRAIN_CONTROL_TYPE myDrivetrainControl = DRIVETRAIN_CONTROL_TYPE.NONE;

    private TaskNone taskNone;
    private TaskAutoRedLeft taskAutoRedLeft;
    private TaskAutoRedCenter taskAutoRedCenter;
    private TaskAutoRedRight taskAutoRedRight;
    private TaskAutoBlueLeft taskAutoBlueLeft;
    private TaskAutoBlueCenter taskAutoBlueCenter;
    private TaskAutoBlueRight taskAutoBlueRight;
    private TaskClimb taskClimb;
    private TaskShootOne taskShootOne;
    private TaskTestAuto taskTestAuto;

    private AutoTask currentTask;

    public AutoRunner() {
        taskNone = new TaskNone();
        taskAutoRedLeft = new TaskAutoRedLeft();
        taskAutoRedCenter = new TaskAutoRedCenter();
        taskAutoRedRight = new TaskAutoRedRight();
        taskAutoBlueLeft = new TaskAutoBlueLeft();
        taskAutoBlueCenter = new TaskAutoBlueCenter();
        taskAutoBlueRight = new TaskAutoBlueRight();
        taskClimb = new TaskClimb();
        taskShootOne = new TaskShootOne();
        taskTestAuto = new TaskTestAuto();

        currentTask = taskNone;
    }

    public void StartTask(TASK_ID id) {
        try {
            switch (id) {
                case AUTO_RED_LEFT:
                    currentTask = taskAutoRedLeft;
                    break;

                case AUTO_RED_CENTER:
                    currentTask = taskAutoRedCenter;

                case AUTO_RED_RIGHT:
                    currentTask = taskAutoRedRight;
                    break;

                case AUTO_BLUE_LEFT:
                    currentTask = taskAutoBlueLeft;
                    break;

                case AUTO_BLUE_CENTER:
                    currentTask = taskAutoBlueCenter;

                case AUTO_BLUE_RIGHT:
                    currentTask = taskAutoBlueRight;
                    break;

                case CLIMB:
                    currentTask = taskClimb;
                    break;

                case SHOOT_ONE:
                    currentTask = taskShootOne;
                    break;

                case TEST_AUTO:
                    currentTask = taskTestAuto;
                    break;

                case NONE:
                default:
                    currentTask = taskNone;
                    break;
            }
            myDrivetrainControl = currentTask.taskDrivetrainRequired;
            SmartDashboard.putString("Drivetrain control: ", myDrivetrainControl.toString());
            currentTask.taskStart();
            SmartDashboard.putString("Current Task: ", currentTask.myTaskID.toString());
        } catch (Exception e) {
            System.err.println("Exception in StartTask");
            e.printStackTrace();
        }
    }

    public void DoCurrentTask() {
        currentTask.getCurrentStep().stepPeriodic();
        if (currentTask.getCurrentStep().stepIsComplete()) {
            currentTask.gotoNextStep();
        }
        if (currentTask.taskIsDone()) {
            myDrivetrainControl = AutoRunner.DRIVETRAIN_CONTROL_TYPE.NONE;
            currentTask = taskNone;
            SmartDashboard.putString("Drivetrain control: ", myDrivetrainControl.toString());
            SmartDashboard.putString("Current Task: ", currentTask.myTaskID.toString());
        }

    }

    public void StopCurrentTask() {
        myDrivetrainControl = AutoRunner.DRIVETRAIN_CONTROL_TYPE.NONE;
        currentTask.taskStop();
        SmartDashboard.putString("Drivetrain control: ", myDrivetrainControl.toString());
    }

}
