
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

    private DigitalInput lineSensor_R;
    private DigitalInput lineSensor_L;


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
        return ( onTheLine_R() && onTheLine_L() );
    }

    @Override
    public void stepPeriodic() {
        double Rpower = 0.1;
        double Lpower = 0.1;
        if(onTheLine_R()) {
            Rpower = 0.0;
        }
        if(onTheLine_L()) {
            Lpower = 0.0;
        }
        Robot.mDrivetrain.assignMotorPower(Rpower, Lpower);
    }

    public Boolean onTheLine_R() {
        if (lineSensor_R.get())
        return false;
        else
        return true;
    }

    public Boolean onTheLine_L() {
        if (lineSensor_L.get())
        return false;
        else
        return true;
    }

}
