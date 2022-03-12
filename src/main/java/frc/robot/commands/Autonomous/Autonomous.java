// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// Autonomous program created with help from Team 303

package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import static frc.robot.RobotContainer.*;

import org.ejml.equation.Sequence;

import frc.robot.commands.Drivetrain.*;
import frc.robot.commands.Gatekeeper.*;
import frc.robot.commands.Indexer.*;
import frc.robot.commands.Shooter.*;
import frc.robot.subsystems.Gatekeeper;

public class Autonomous extends SequentialCommandGroup {
    public Autonomous() {
      addCommands(
        new InstantCommand(m_intake::extend, m_intake),
        new TankDrive(m_drivetrain, () -> 0.5, () -> 0.5).alongWith(
          new InstantCommand(m_intake::retrieve, m_intake),
          new IndexerController(m_indexer, () -> -0.75),
          new InstantCommand(m_gatekeeper::manual, m_gatekeeper)
        ).withTimeout(4),
        new InstantCommand(m_gatekeeper::stop, m_gatekeeper),
        new InstantCommand(m_intake::stop, m_intake),
        new InstantCommand(m_intake::retract, m_intake),
        new TankDrive(m_drivetrain, () -> -0.75, () -> 0.75).withTimeout(0.815),
        new TankDrive(m_drivetrain, () -> 0.5, () -> 0.5).withTimeout(6),
        /*new ShooterController(m_shooter, () -> 1.0).alongWith(
          new IndexerController(m_indexer, () -> -1.0),
          new GatekeeperController(m_gatekeeper, () -> 1.0)
        ).withTimeout(5),*/
        new SequentialCommandGroup(
          new ShooterController(m_shooter, () -> 1.0).withTimeout(5)
        ).alongWith(
          new IndexerController(m_indexer, () -> -1.0).withTimeout(3),
          new GatekeeperController(m_gatekeeper, () -> 1.0).withTimeout(3)
        )
      );
  }
}
