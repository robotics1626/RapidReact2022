// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj.Timer;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TimedDrive extends CommandBase {
  private final Drivetrain m_drivetrain;
  private final Timer m_timer = new Timer();
  private double m_time, m_speed;

  // Use addRequirements() here to declare subsystem dependencies.
  public TimedDrive(Drivetrain drivetrain) {
    SmartDashboard.putNumber("Auto Duration (s)", 2);
    SmartDashboard.putNumber("Auto Speed (%)", -0.75);
    m_drivetrain = drivetrain;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_time = SmartDashboard.getNumber("Auto Duration (s)", 2);
    m_speed = SmartDashboard.getNumber("Auto Speed (%)", -0.75);
    m_timer.reset();
    m_timer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    while (m_timer.get() < m_time) m_drivetrain.tankDrive(m_speed, m_speed);
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