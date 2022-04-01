package frc.systems;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import frc.robot.Robot;
import frc.utilities.SoftwareTimer;

public class AutoCode {
  public static SoftwareTimer autoShootTimer;
  public static SoftwareTimer shooterDelay;
  public static SoftwareTimer reverseDelay;
  public static SoftwareTimer spinTimer;
  public static SoftwareTimer intakeTimer;

  public static SoftwareTimer forwardTimer2;

  public static SoftwareTimer autoShootTimer2;

  private boolean isShootTaskFinished = false;
  private boolean isTurnAroundFinished;
  public ADXRS450_Gyro robGyro;
  public static SoftwareTimer forwardTimer;

  public AutoCode() {
    autoShootTimer = new SoftwareTimer();
    shooterDelay = new SoftwareTimer();
    reverseDelay = new SoftwareTimer();
    autoShootTimer.setTimer(3);
    shooterDelay.setTimer(0.75);
    reverseDelay.setTimer(5);
    forwardTimer.setTimer(6.0);
    intakeTimer.setTimer(7.0);
    forwardTimer2.setTimer(8.0);
    autoShootTimer.setTimer(9.0);
    robGyro = new ADXRS450_Gyro();
    robGyro.calibrate();
  }

  public void runAuto() {

    if (!autoShootTimer.isExpired()) {
      ControlShooter.shooterMotor.set(ControlShooter.highShooterPower);
      if (shooterDelay.isExpired()) {
        ControlShooter.upperMotor.set(ControlMode.PercentOutput, ControlShooter.feederPower);
        ControlShooter.lowerMotor.set(ControlMode.PercentOutput, ControlShooter.feederPower);
      }
    }

    else if (!isShootTaskFinished) {

      ControlShooter.upperMotor.set(ControlMode.PercentOutput, 0);
      ControlShooter.lowerMotor.set(ControlMode.PercentOutput, 0);
      ControlShooter.shooterMotor.set(0);
      Robot.mDrivetrain.assignMotorPower(0.4, -0.4);
      if (reverseDelay.isExpired()) {
        Robot.mDrivetrain.assignMotorPower(0, 0);
        isShootTaskFinished = true;
      }
    } else if (!isTurnAroundFinished) {
      Robot.mDrivetrain.assignMotorPower(-0.4, -0.4);
      if (robGyro.getAngle() == 180.0) {
        Robot.mDrivetrain.assignMotorPower(0, 0);
      }

      if (!forwardTimer.isExpired())
        Robot.mDrivetrain.assignMotorPower(0.4, -0.4);
      else
        Robot.mDrivetrain.assignMotorPower(0, 0);

      if (!intakeTimer.isExpired()) {
        Robot.intake.intakeBalls();
        Robot.intake.lowerIntake();
      } else {
        Robot.intake.stopIntakeMotor();
        Robot.intake.raiseIntake();
        Robot.mDrivetrain.assignMotorPower(-0.4, -0.4);
        if (robGyro.getAngle() == 180.0)
          ;
        Robot.mDrivetrain.assignMotorPower(0, 0);
      }

      if (!forwardTimer2.isExpired())
        Robot.mDrivetrain.assignMotorPower(0.4, -0.4);
      else
        Robot.mDrivetrain.assignMotorPower(0, 0);

      if (!autoShootTimer2.isExpired()) {
        ControlShooter.shooterMotor.set(ControlShooter.highShooterPower);
        if (shooterDelay.isExpired()) {
          ControlShooter.upperMotor.set(ControlMode.PercentOutput, ControlShooter.feederPower);
          ControlShooter.lowerMotor.set(ControlMode.PercentOutput, ControlShooter.feederPower);
        }
      }

      else if (!isShootTaskFinished) {

        ControlShooter.upperMotor.set(ControlMode.PercentOutput, 0);
        ControlShooter.lowerMotor.set(ControlMode.PercentOutput, 0);
        ControlShooter.shooterMotor.set(0);
        Robot.mDrivetrain.assignMotorPower(0.4, -0.4);
        if (reverseDelay.isExpired()) {
          Robot.mDrivetrain.assignMotorPower(0, 0);
          isShootTaskFinished = true;
        }
      }

    }
  }
}
