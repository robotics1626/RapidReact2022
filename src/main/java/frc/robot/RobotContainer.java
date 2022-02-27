// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;

import frc.robot.subsystems.*;

import frc.robot.commands.Drivetrain.*;
//import frc.robot.commands.Autonomous.*;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */

public class RobotContainer {
  /** Controllers */
  private final XboxController m_operator = new XboxController(Constants.CONTROLLER_OPERATOR);
  private final Joystick m_driverLeft = new Joystick(Constants.JOYSTICK_LEFT);
  private final Joystick m_driverRight = new Joystick(Constants.JOYSTICK_RIGHT);

  /** Robot Components */
  private final Drivetrain m_drivetrain = new Drivetrain();
  private final Intake m_intake = new Intake();
  private final Indexer m_indexer = new Indexer();
  private final Gatekeeper m_gatekeep = new Gatekeeper();
  private final Shooter m_shooter = new Shooter();
  private final Climber m_climber = new Climber();

  /** Autonomous */
  //private final TimedDrive m_timedDrive = new TimedDrive(5, 0.25);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    /** Drivetrain Controls */
    /** The Driver's joysticks control each side of the drivetrain respectively. */
    m_drivetrain.setDefaultCommand(
      new TankDrive(
        m_drivetrain,
        () -> -Math.pow(m_driverLeft.getY(),Constants.DRIVER_INPUT_CURVE),
        () -> -Math.pow(m_driverRight.getY(),Constants.DRIVER_INPUT_CURVE)
      )
    );

    /** Indexer Controls */
    /** The Operator's left joystick controls the indexer belt. */
    m_indexer.IndexerController(m_operator.getLeftY());

    /** Gatekeeper Controls */
    /** The Operator's right trigger unlocks the gatekeeper. */
    m_gatekeep.GatekeeperController(m_operator.getRightTriggerAxis());

    /** Shooter Controls */
    /** The Operator's left trigger spins up the flywheel. */
    m_shooter.ShooterController(m_operator.getLeftTriggerAxis());
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
    new JoystickButton(m_driverLeft, 1).whenPressed(() -> m_intake.toggle());
    new JoystickButton(m_driverRight, 1).whenPressed(() -> m_intake.toggle());
    /** The Driver's thumb buttons control the retrieval and ejection of the cargo. */
    new JoystickButton(m_driverLeft, 2).whileActiveContinuous(() -> m_intake.eject());
    new JoystickButton(m_driverRight, 2).whileActiveContinuous(() -> m_intake.retrieve());

    /** Climber Controls */
    /** The Operator's A and B buttons toggle the climber's claws.*/
    new JoystickButton(m_operator, Button.kA.value).whenPressed(() -> m_climber.toggle(0));
    new JoystickButton(m_operator, Button.kB.value).whenPressed(() -> m_climber.toggle(1));
    /** The Operator's bumpers spin the climber's arms.*/
    new JoystickButton(m_operator, Button.kLeftBumper.value).whileActiveContinuous(() -> m_climber.spin(0.25));
    new JoystickButton(m_operator, Button.kRightBumper.value).whileActiveContinuous(() -> m_climber.spin(-0.25));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  /*public Command getAutonomousCommand() {
    return m_timedDrive;
  }*/
}