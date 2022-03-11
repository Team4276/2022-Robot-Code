package frc.systems;

import com.ctre.phoenix.motorcontrol.ControlMode;

import frc.utilities.SoftwareTimer;

public class StepAutoShoot extends AutoStep {

    private SoftwareTimer autoShootTimer;
    private SoftwareTimer shooterDelay;

    public StepAutoShoot() {
        autoShootTimer = new SoftwareTimer();
        shooterDelay = new SoftwareTimer();
    }

    public void autoShooterTimerInit() {
        autoShootTimer.setTimer(1.0);
        shooterDelay.setTimer(.75);
    }

    public void autoShootOne() {
        if (!autoShootTimer.isExpired()) {
            ControlShooter.shooterMotor.set(ControlMode.PercentOutput, ControlShooter.highShooterPower);
            if (shooterDelay.isExpired()) {
                ControlShooter.upperMotor.set(ControlMode.PercentOutput, ControlShooter.feederPower);
                ControlShooter.lowerMotor.set(ControlMode.PercentOutput, ControlShooter.feederPower);
            }
        }
    }

    public void stepInit() {
        autoShooterTimerInit();
        autoShootOne();
    }

    public void stepExit() {

        ControlShooter.upperMotor.set(ControlMode.PercentOutput, 0);
        ControlShooter.lowerMotor.set(ControlMode.PercentOutput, 0);
        ControlShooter.shooterMotor.set(ControlMode.PercentOutput, 0);

    }
}
