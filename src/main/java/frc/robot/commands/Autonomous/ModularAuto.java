// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj2.command.CommandBase;

import edu.wpi.first.wpilibj.Timer;

import frc.robot.subsystems.*;

import frc.robot.commands.Drivetrain.*;
import frc.robot.commands.Gatekeeper.*;
import frc.robot.commands.Indexer.*;
import frc.robot.commands.Shooter.*;
import frc.robot.commands.Autonomous.*;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ModularAuto extends CommandBase {
  private final Drivetrain m_drivetrain;
  private final Intake m_intake;
  private final Indexer m_indexer;
  private final Gatekeeper m_gatekeeper;
  private final Shooter m_shooter;
  private final Climber m_climber;

  private final Timer m_timer = new Timer();

  private double m_time, m_speed;

  // Use addRequirements() here to declare subsystem dependencies.
  public ModularAuto(Drivetrain drivetrain, Intake intake, Indexer indexer, Gatekeeper gatekeeper, Shooter shooter, Climber climber) {
    SmartDashboard.putNumber("A.Duration", 2);
    SmartDashboard.putNumber("A.Speed", -0.75);
    
    m_drivetrain = drivetrain;
    m_indexer = indexer;
    m_intake = intake;
    m_gatekeeper = gatekeeper;
    m_shooter = shooter;
    m_climber = climber;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_time = SmartDashboard.getNumber("A.Duration", 2);
    m_speed = SmartDashboard.getNumber("A.Speed", -0.75);
    m_timer.reset();
    m_timer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_intake.retract();

    while (m_timer.get() < 5) {
      m_indexer.setDefaultCommand(new IndexerController(m_indexer, () -> 1.0));
      m_gatekeeper.setDefaultCommand(new GatekeeperController(m_gatekeeper, () -> 1.0));
      m_shooter.setDefaultCommand(new ShooterController(m_shooter, () -> 1.0));
    }

    while (m_timer.get() > 5 && m_timer.get() < 5 + m_time) {
      m_drivetrain.tankDrive(m_speed, m_speed);
    }

    if (m_timer.get() == 5) m_intake.extend();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}