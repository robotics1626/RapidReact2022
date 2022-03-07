// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;

import frc.robot.subsystems.*;

import frc.robot.commands.Drivetrain.*;
import frc.robot.commands.Gatekeeper.*;
import frc.robot.commands.Indexer.*;
import frc.robot.commands.Shooter.*;
import frc.robot.commands.Autonomous.*;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  /** Controllers */
  public static final class Controller {
    public static final XboxController OPERATOR = new XboxController(Constants.Controller.OPERATOR);
    public static final class Driver {
      public static final Joystick LEFT = new Joystick(Constants.Controller.Driver.LEFT);
      public static final Joystick RIGHT = new Joystick(Constants.Controller.Driver.RIGHT);
    }
  }

  /** Robot Components */
  public static final Drivetrain DRIVETRAIN = new Drivetrain();
  public static final Intake INTAKE = new Intake();
  public static final Indexer INDEXER = new Indexer();
  public static final Gatekeeper GATEKEEPER = new Gatekeeper();
  public static final Shooter SHOOTER = new Shooter();
  public static final Climber CLIMBER = new Climber();

  /** Autonomous */
  private Command autonomousCommand;

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    /** Configure the button bindings */
    configureButtonBindings();

    /** Drivetrain Controls */
    /** The Driver's joysticks control each side of the DRIVETRAIN respectively. */
    DRIVETRAIN.setDefaultCommand(
      new TankDrive(
        DRIVETRAIN,
        () -> -1 * Math.pow(Controller.Driver.LEFT.getY(),Constants.Controller.Driver.CURVE),
        () -> -1 * Math.pow(Controller.Driver.RIGHT.getY(),Constants.Controller.Driver.CURVE)
      )
    );

    /** Indexer Controls */
    /** The Operator's left joystick controls the indexer belt. */
    INDEXER.setDefaultCommand(new IndexerController(INDEXER, () -> Controller.OPERATOR.getLeftY()));

    /** Gatekeeper Controls */
    /** The Operator's right trigger unlocks the gatekeeper. */
    GATEKEEPER.setDefaultCommand(new GatekeeperController(GATEKEEPER, () -> Controller.OPERATOR.getRightTriggerAxis()));

    /** Shooter Controls */
    /** The Operator's left trigger spins up the flywheel. */
    SHOOTER.setDefaultCommand(new ShooterController(SHOOTER, () -> Controller.OPERATOR.getLeftTriggerAxis()));
    
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    
    /** Intake Controls */
    /** The Driver's triggers toggle the state of the intake arms. */
    new JoystickButton(Controller.Driver.LEFT, 1).whenPressed(new InstantCommand(INTAKE::toggle, INTAKE));
    new JoystickButton(Controller.Driver.RIGHT, 1).whenPressed(new InstantCommand(INTAKE::toggle, INTAKE)); 
    /** The Driver's thumb buttons control the retrieval and ejection of the cargo. */
    new JoystickButton(Controller.Driver.LEFT, 2)
      .whenPressed(() -> INTAKE.eject())
      .whenReleased(() -> INTAKE.stop());
    new JoystickButton(Controller.Driver.RIGHT, 2)
      .whenPressed(() -> INTAKE.retrieve())
      .whenReleased(() -> INTAKE.stop());

    /** Climber Controls */

    /** The Operator's bumpers raise and lower the climber's arms.*/
    new JoystickButton(Controller.OPERATOR, Button.kLeftBumper.value)
      .whenPressed(() -> CLIMBER.spin(-1.0))
      .whenReleased(() -> CLIMBER.stop());
      new JoystickButton(Controller.OPERATOR, Button.kRightBumper.value)
      .whenPressed(() -> CLIMBER.spin(1.0))
      .whenReleased(() -> CLIMBER.stop());
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
  @Override
  public void autonomousInit() {
    autonomousCommand = new Autonomous();

    // schedule the autonomous command (example)
    if (autonomousCommand != null) {
      autonomousCommand.schedule();
    }
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (autonomousCommand != null) {
      autonomousCommand.cancel();
    }
  }

  /** This function is called periodically during Controller.OPERATOR control. */
  @Override
  public void teleopPeriodic() {}

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}
}
