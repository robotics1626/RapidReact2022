// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.IntakeBelt;
import frc.robot.subsystems.IntakeArm;
import frc.robot.commands.Indexer.IndexerController;
import frc.robot.commands.Drivetrain.TankDrive;
import frc.robot.commands.Autonomous.TemporaryAutonomous;

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

  private final Drivetrain m_drivetrain = new Drivetrain();
  private final IntakeBelt m_intakeBelt = new IntakeBelt();
  private final IntakeArm m_intakeArm = new IntakeArm();
  private final Indexer m_indexer = new Indexer();

  private final TemporaryAutonomous m_temporaryAutonomous = new TemporaryAutonomous();

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    // Tank Drive
    m_drivetrain.setDefaultCommand(
      new TankDrive(
        m_drivetrain,
        () -> m_driverLeft.getY(),
        () -> m_driverRight.getY()
      )
    );

    // Indexer
    m_indexer.setDefaultCommand(
      new IndexerController(
        m_indexer, 
        () -> m_operator.getRightY()
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
    new JoystickButton(m_driverLeft, 1)
      .whenPressed(() -> m_intakeArm.extend());
    new JoystickButton(m_driverRight, 1)
      .whenPressed(() -> m_intakeArm.retract());
    new JoystickButton(m_driverLeft, 2)
      .whenPressed(() -> m_intakeBelt.pull());
    new JoystickButton(m_driverRight, 2)
      .whenPressed(() -> m_intakeBelt.push());
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