// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.*;

import frc.robot.commands.Drivetrain.*;
import frc.robot.commands.Gatekeeper.*;
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
  /** Controllers */
  private static final XboxController m_operator = new XboxController(Constants.CONTROLLER_OPERATOR);
  private static final Joystick m_driverLeft = new Joystick(Constants.JOYSTICK_LEFT);
  private static final Joystick m_driverRight = new Joystick(Constants.JOYSTICK_RIGHT);

  /** Robot Components */
  public static final Drivetrain m_drivetrain = new Drivetrain();
  public static final Intake m_intake = new Intake();
  public static final Indexer m_indexer = new Indexer();
  public static final Gatekeeper m_gatekeeper = new Gatekeeper();
  public static final Shooter m_shooter = new Shooter();
  public static final Climber m_climber = new Climber();
  public static final Gyroscope m_gyro = new Gyroscope();
  public static final Limelight m_limelight = new Limelight();

  /** Autonomous Commands */
  private final Command TWO_BALL_AUTO = new TwoBall();
  private final Command FOUR_BALL_AUTO = new FourBallAuto();
  private final Command ONE_BALL_AUTO = new OneBall();
  private final Command TAXI_AUTO = new Taxi();
  private final Command WAIT_NONE = new WaitCommand(0);
  private SendableChooser<Command> m_auto = new SendableChooser<>();

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {

    /** Autonomous Chooser */
    m_auto.setDefaultOption("Two Ball", TWO_BALL_AUTO);
    m_auto.addOption("One Ball", ONE_BALL_AUTO);
    m_auto.addOption("Taxi", TAXI_AUTO);
    m_auto.addOption("Four Ball", FOUR_BALL_AUTO);
    m_auto.addOption("Do Nothing", WAIT_NONE);
    SmartDashboard.putData("Autonomous Routine", m_auto);

    /** Configure the button bindings */
    configureButtonBindings();

    /** Drivetrain Controls */
    /** The Driver's joysticks control each side of the drivetrain respectively. */
    m_drivetrain.setDefaultCommand(
      new TankDrive(
        m_drivetrain,
        () -> -1 * Math.pow(m_driverLeft.getY(),Constants.JOYSTICK_CURVE),
        () -> -1 * Math.pow(m_driverRight.getY(),Constants.JOYSTICK_CURVE)
      )
    );

    /** Indexer Controls */
    /** The Operator's left joystick controls the indexer belt. */
    m_indexer.setDefaultCommand(new IndexerController(m_indexer, () -> m_operator.getLeftY()));

    /** Gatekeeper Controls */
    /** The Operator's right trigger unlocks the gatekeeper. */
    m_gatekeeper.setDefaultCommand(new GatekeeperController(m_gatekeeper, () -> m_operator.getRightTriggerAxis()));

    /** Shooter Controls */
    /** The Operator's left trigger spins up the flywheel. */
    m_shooter.setDefaultCommand(new ShooterController(m_shooter, () -> m_operator.getLeftTriggerAxis()));
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
    new JoystickButton(m_driverLeft, 2)
      .whenPressed(() -> m_intake.eject())
      .whenReleased(() -> m_intake.stop());
    new JoystickButton(m_driverRight, 2)
      .whenPressed(() -> m_intake.retrieve())
      .whenReleased(() -> m_intake.stop());

    /** Climber Controls */
    /** The Operator's right bumper retracts the climber */
    new JoystickButton(m_operator, Button.kRightBumper.value)
      .whenPressed(() -> m_climber.climb(1.0))
      .whenReleased(() -> m_climber.stop());
    /** The Operator's left bumper extends the climber */
    new JoystickButton(m_operator, Button.kLeftBumper.value)
      .whenPressed(() -> m_climber.climb(-1.0))
      .whenReleased(() -> m_climber.stop());
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return m_auto.getSelected();
  }
}