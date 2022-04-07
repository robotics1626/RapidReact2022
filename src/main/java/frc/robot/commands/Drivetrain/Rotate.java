// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;
import static frc.robot.RobotContainer.*;

public class Rotate extends CommandBase {

  private double m_initAngle, m_angle;

  public Rotate(double angle) {
    m_initAngle = 0;
    m_angle = angle;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_initAngle = m_gyro.getAngle();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    while(m_gyro.getAngle() < (m_initAngle + m_angle)-20) {
      m_drivetrain.tankDrive(0.6, -0.6);
    }
    
    while(m_gyro.getAngle() < m_initAngle + m_angle) {
      m_drivetrain.tankDrive(0.5, -0.5);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_drivetrain.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
