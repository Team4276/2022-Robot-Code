package frc.systems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import frc.robot.Robot;
import frc.utilities.RoboRioPorts;
import frc.utilities.Xbox;

public class Turret {

    public VictorSPX turretMotor;

    private final double SLOW_ZONE = 10.0; // degrees field-of-view. Camera frame is -27 left to +27 degrees right
    private final double DEAD_ZONE = 2.0;

    private final double MAX_POWER = 0.45; // Percent moter output
    private final double SLOW_POWER = 0.2;
    private final double HOLD_POSITION = 0.0;

    public Turret() {
        turretMotor = new VictorSPX(RoboRioPorts.CAN_TURRET);
    }

    public void update() {
        if (Robot.xboxController.getRawButton(Xbox.B)) {
            if (Robot.limelightVisionTarget.isValidVisionTarget) {
                if (Robot.limelightVisionTarget.targetOffsetAngle_Horizontal > 0) {
                    if (Robot.limelightVisionTarget.targetOffsetAngle_Horizontal > SLOW_ZONE) {
                        turretMotor.set(ControlMode.PercentOutput, MAX_POWER);
                    } else if (Robot.limelightVisionTarget.targetOffsetAngle_Horizontal > DEAD_ZONE) {
                        turretMotor.set(ControlMode.PercentOutput, SLOW_POWER);
                    } else {
                        // Close enough - do nothing
                        turretMotor.set(ControlMode.PercentOutput, HOLD_POSITION);
                    }
                } else {
                    if (Robot.limelightVisionTarget.targetOffsetAngle_Horizontal < (-1.0 * SLOW_ZONE)) {
                        turretMotor.set(ControlMode.PercentOutput, (-1.0 * MAX_POWER));
                    } else if (Robot.limelightVisionTarget.targetOffsetAngle_Horizontal < (-1.0 * DEAD_ZONE)) {
                        turretMotor.set(ControlMode.PercentOutput, (-1.0 * SLOW_POWER));
                    } else {
                        // Close enough - do nothing
                        turretMotor.set(ControlMode.PercentOutput, HOLD_POSITION);
                    }
                }
            } else {
                turretMotor.set(ControlMode.PercentOutput, HOLD_POSITION);
            }
        } else {
            turretMotor.set(ControlMode.PercentOutput, HOLD_POSITION);
        }
    }
}
