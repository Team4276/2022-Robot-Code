// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.systems.AutoRunner;
import frc.systems.ControlShooter;
import frc.systems.Drivetrain;
import frc.systems.HoodActuator;
import frc.systems.Intake;
import frc.systems.RgbSensorRunnable;
import frc.utilities.LimitSwitch;
import frc.utilities.LogJoystick;
import frc.utilities.RoboRioPorts;

public class Robot extends TimedRobot {

  // Declaring all the objects we need to use in this class

  public static Joystick leftJoystick;
  public static Joystick rightJoystick;
  public static Joystick xboxJoystick;
  public static Intake intake;
  public static ControlShooter shooterControler;
  public static HoodActuator hActuator;

  private static Thread myRgbSensorThread;

  Notifier driveRateGroup;
  public static Drivetrain mDrivetrain;

  public static Timer systemTimer;

  public Autonomous myAutonomous;
  public static AutoRunner myAutoRunner;

  public static LimitSwitch lowerLimitSwitch;
  public static LimitSwitch upperLimitSwitch;
  public static XboxController xboxController;

  private Boolean prevAutoClimbButtonState = false;

  /**
   * This function is run when the robot is first started up and should be used
   * for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    CameraServer.startAutomaticCapture();

    // Creates objects for the controlers
    leftJoystick = new Joystick(0);
    rightJoystick = new Joystick(1);
    xboxController = new XboxController(2);

    // limit switch initialization
    lowerLimitSwitch = new LimitSwitch(RoboRioPorts.DIO_LOWER_SWITCH);
    upperLimitSwitch = new LimitSwitch(RoboRioPorts.DIO_UPPER_SWITCH);

    // intake motor initalization

    intake = new Intake(RoboRioPorts.CAN_INTAKE, RoboRioPorts.INTAKE_SOLENOID);

    // shooter motor initialization
    shooterControler = new ControlShooter(RoboRioPorts.CAN_SHOOT_UPPER, RoboRioPorts.CAN_SHOOT_LOWER,
        RoboRioPorts.CAN_SHOOTER);
    RgbSensorRunnable rbgSensorRunnable = new RgbSensorRunnable();
    myRgbSensorThread = new Thread(rbgSensorRunnable);
    myRgbSensorThread.start();

    // hood actuator initalization
    hActuator = new HoodActuator(RoboRioPorts.HOOD_SOLENOID);

    // drive train initialization
    mDrivetrain = new Drivetrain(true, RoboRioPorts.CAN_DRIVE_L1, RoboRioPorts.CAN_DRIVE_L2, RoboRioPorts.CAN_DRIVE_L3,
        RoboRioPorts.CAN_DRIVE_R1, RoboRioPorts.CAN_DRIVE_R2, RoboRioPorts.CAN_DRIVE_R3,
        RoboRioPorts.DRIVE_DOUBLE_SOLENOID_FWD, RoboRioPorts.DRIVE_DOUBLE_SOLENOID_REV, RoboRioPorts.DIO_DRIVE_RIGHT_A,
        RoboRioPorts.DIO_DRIVE_RIGHT_B, RoboRioPorts.DIO_DRIVE_LEFT_A, RoboRioPorts.DIO_DRIVE_LEFT_B);

    // Drive train motor control is done on its own timer driven thread regardless
    // of disabled/teleop/auto mode selection
    driveRateGroup = new Notifier(mDrivetrain::operatorDrive);
    driveRateGroup.startPeriodic(0.05);
    // shooterControler.shooterInit();

    myAutoRunner = new AutoRunner();
    myAutonomous = new Autonomous();
    myAutonomous.start();
  }

  public Boolean IsAutoClimbButtonPushed() {
    //return myDebouncer.calculate(leftJoystick.getRawButton(LogJoystick.B12));
    return leftJoystick.getRawButton(LogJoystick.B12);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for
   * items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and
   * test.
   *
   * <p>
   * This runs after the mode specific periodic functions, but before LiveWindow
   * and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {

    // essential shooter function
    shooterControler.runShooter();
    upperLimitSwitch.determineCase();
    lowerLimitSwitch.determineCase();
    hActuator.toggleShooterHood();
    // lineSensor.getSensorData();

    SmartDashboard.putBoolean("prevAutoClimbButtonState", prevAutoClimbButtonState);

    myAutoRunner.DoCurrentTask();
  }

  @Override
  public void autonomousInit() {
    if (myAutonomous.team == myAutonomous.red) {
      switch (myAutonomous.startingPosition) {
        case Autonomous.LEFT:
          myAutoRunner.StartTask(AutoRunner.TASK_ID.AUTO_RED_LEFT);
          break;

        case Autonomous.CENTER:
          myAutoRunner.StartTask(AutoRunner.TASK_ID.AUTO_RED_CENTER);
          break;

        case Autonomous.RIGHT:
          myAutoRunner.StartTask(AutoRunner.TASK_ID.AUTO_RED_RIGHT);
          break;
      }
    } else { // blue
      switch (myAutonomous.startingPosition) {
        case Autonomous.LEFT:
          myAutoRunner.StartTask(AutoRunner.TASK_ID.AUTO_BLUE_LEFT);
          break;

        case Autonomous.CENTER:
          myAutoRunner.StartTask(AutoRunner.TASK_ID.AUTO_BLUE_CENTER);
          break;

        case Autonomous.RIGHT:
          myAutoRunner.StartTask(AutoRunner.TASK_ID.AUTO_BLUE_RIGHT);
          break;
      }
    }
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
  }

  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {

    if (IsAutoClimbButtonPushed()) {
      if (!prevAutoClimbButtonState) {
        prevAutoClimbButtonState = true;
        myAutoRunner.StartTask(AutoRunner.TASK_ID.CLIMB);
      }
    } else { // button NOT pressed
      if (prevAutoClimbButtonState) {
        prevAutoClimbButtonState = false;
        myAutoRunner.StopCurrentTask();
      }
    }
  }

  /** This function is called once when the robot is disabled. */
  @Override
  public void disabledInit() {
  }

  /** This function is called periodically when disabled. */
  @Override
  public void disabledPeriodic() {
  }

  /** This function is called once when test mode is enabled. */
  @Override
  public void testInit() {
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {
  }
}
