// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.*;
import frc.robot.commands.Drivetrain.*;
import frc.robot.commands.Gatekeep.*;
import frc.robot.commands.Indexer.*;
import frc.robot.commands.Shooter.*;
import frc.robot.commands.Autonomous.*;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */

public class RobotContainer {
  // Controllers
  private final XboxController m_operator = new XboxController(Constants.CONTROLLER_OPERATOR);
  private final Joystick m_driverLeft = new Joystick(Constants.JOYSTICK_LEFT);
  private final Joystick m_driverRight = new Joystick(Constants.JOYSTICK_RIGHT);

  // Robot Compontents
  private final Drivetrain m_drivetrain = new Drivetrain();
  private final IntakeBelt m_intakeBelt = new IntakeBelt();
  private final IntakeArm m_intakeArm = new IntakeArm();
  private final Indexer m_indexer = new Indexer();
  private final Gatekeep m_gatekeeper = new Gatekeep();
  private final Shooter m_shooter = new Shooter();
  private final Climber m_climber = new Climber();

  // Autonomous
  private final TemporaryAutonomous m_temporaryAutonomous = new TemporaryAutonomous();

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    // Drive Train
    // The Driver's left joystick controls the left side of the robot
    // The Driver's right joystick controls the right side of the robot
    m_drivetrain.setDefaultCommand(
      new TankDrive(
        m_drivetrain,
        () -> m_driverLeft.getY(),
        () -> m_driverRight.getY()
      )
    );

    // Indexer
    // The Operator's left joystick controls the indexer
    m_indexer.setDefaultCommand(
      new IndexerController(
        m_indexer, 
        () -> m_operator.getLeftY()
      )
    );

    // Gatekeeper
    // The Operator's right trigger controls the gatekeeper
    m_gatekeeper.setDefaultCommand(
      new Girlboss(
        m_gatekeeper, 
        () -> m_operator.getRightTriggerAxis()
      )
    );

    // Shooter
    // The Operator's right trigger controls the shooter
    m_shooter.setDefaultCommand(
      new ShooterController(
        m_shooter, 
        () -> m_operator.getLeftTriggerAxis()
      )
    );
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    // Intake
    // The Driver's left trigger extends the arm
    new JoystickButton(m_driverLeft, 1)
      .whenPressed(() -> m_intakeArm.extend()
    );
    // The Driver's right trigger retracts the arm
    new JoystickButton(m_driverRight, 1)
      .whenPressed(() -> m_intakeArm.retract()
    );
    // The Driver's left thumb button runs the belt backwards
    new JoystickButton(m_driverLeft, 2)
      .whileActiveContinuous(() -> m_intakeBelt.backwards()
    );
    // The Driver's right thumb button runs the belt forwards
    new JoystickButton(m_driverRight, 2)
      .whileActiveContinuous(() -> m_intakeBelt.forwards()
    );
    // The Operator's A and B buttons toggle the climber's claws
    new JoystickButton(m_operator, Button.kA.value)
      .whenPressed(() -> m_climber.toggleUpper()
    );
    new JoystickButton(m_operator, Button.kB.value)
      .whenPressed(() -> m_climber.toggleLower()
    );
    // The Operator's bumbers spin the climber
    new JoystickButton(m_operator, Button.kLeftBumper.value)
      .whenPressed(() -> m_climber.spin(1)
    );
    new JoystickButton(m_operator, Button.kRightBumper.value)
      .whenPressed(() -> m_climber.spin(-1)
    );
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return m_temporaryAutonomous;
  }
}