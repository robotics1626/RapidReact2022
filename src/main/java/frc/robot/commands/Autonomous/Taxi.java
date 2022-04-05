// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
//test
// Autonomous program created with help from Team 303

package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import static frc.robot.RobotContainer.*;

import frc.robot.commands.Drivetrain.*;

public class Taxi extends SequentialCommandGroup {
    public Taxi() {
      addCommands(
        new TankDrive(m_drivetrain, () -> -0.65, () -> -0.65).alongWith(
        ).withTimeout(1.5)
      );
  }
}
