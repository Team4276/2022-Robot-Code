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
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.systems.AutoCode;
import frc.systems.Climber;
import frc.systems.ControlShooter;
import frc.systems.Drivetrain;
import frc.systems.Intake;
import frc.utilities.LimitSwitch;
import frc.utilities.LogJoystick;
import frc.utilities.RoboRioPorts;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;



public class Robot extends TimedRobot {

  // Declaring all the objects we need to use in this class

  public static Joystick leftJoystick;
  public static Joystick rightJoystick;
  public static Joystick xboxJoystick;
  public static Intake intake;
  public static ControlShooter shooterControler;
  public static Spark mySpark;
  public static MotorController myController;
  public static AutoCode myAutoCode;

  Notifier driveRateGroup;
  public static Drivetrain mDrivetrain;

  public static Timer systemTimer;

  public Autonomous myAutonomous;
  
  public static LimitSwitch lowerLimitSwitch;
  public static LimitSwitch upperLimitSwitch;
  public static XboxController xboxController;

  public static Climber climber;

  private Boolean prevAutoClimbButtonState = false;

  public static  Boolean isAutoMode = false;

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
    mySpark = new Spark(4324);
    

    // limit switch initialization
    lowerLimitSwitch = new LimitSwitch(RoboRioPorts.DIO_LOWER_SWITCH);
    upperLimitSwitch = new LimitSwitch(RoboRioPorts.DIO_UPPER_SWITCH);

    // intake motor initalization

    intake = new Intake();

    // shooter motor initialization
    shooterControler = new ControlShooter();
 
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

    climber=new Climber();

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
    // lineSensor.getSensorData();

    SmartDashboard.putBoolean("prevAutoClimbButtonState", prevAutoClimbButtonState);
    intake.runIntake();

    SmartDashboard.putBoolean("Right DIO", Climber.rightClimbSwitch.get());
    SmartDashboard.putBoolean("Left DIO", Climber.leftClimbSwitch.get());
    

    //myAutoRunner.DoCurrentTask();
  }

  @Override
  public void autonomousInit() {
    
  isAutoMode = true;

    myAutoCode = new AutoCode();

  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    //myAutoRunner.DoCurrentTask();
    myAutoCode.runAuto();

  }

  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {
    isAutoMode = false;
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    climber.runClimb();

    //myAutoRunner.DoCurrentTask();
  }

  /** This function is called once when the robot is disabled. */
  @Override
  public void disabledInit() {
  }

  /** This function is called periodically when disabled. */
  @Override
  public void disabledPeriodic() {
    Autonomous.autonomousSelector();
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
