
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.systems;

import frc.robot.Robot;
import frc.utilities.RoboRioPorts;
import edu.wpi.first.wpilibj.DigitalInput;

public class StepFindLine extends AutoStep {

    public DigitalInput lineSensor_R;
    public DigitalInput lineSensor_L;

    public StepFindLine() {
        stepID = AutoStep.STEP_ID.FIND_LINE;

        lineSensor_R = new DigitalInput(RoboRioPorts.DIO_LINE_SENSOR_R);
        lineSensor_L = new DigitalInput(RoboRioPorts.DIO_LINE_SENSOR_L);
    }

    @Override
    public void stepInit() {
        Robot.mDrivetrain.assignMotorPower(0.1, 0.1);
    }

    @Override
    public void stepExit() {
        Robot.mDrivetrain.assignMotorPower(0.0, 0.0);
    }

    @Override
    public Boolean stepIsComplete() {
        return ( lineSensor_R.get() && lineSensor_L.get() );
    }

    @Override
    public void stepPeriodic() {

    }

}
