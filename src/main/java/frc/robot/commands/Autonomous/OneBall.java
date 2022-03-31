// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// Autonomous program created with help from Team 303

package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import static frc.robot.RobotContainer.*;

import frc.robot.commands.Gatekeeper.*;
import frc.robot.commands.Indexer.*;
import frc.robot.commands.Shooter.*;

public class OneBall extends SequentialCommandGroup {
    public OneBall() {
      addCommands(
        new SequentialCommandGroup(
          new ShooterController(m_shooter, () -> 1.0).withTimeout(5)).alongWith(
            new IndexerController(m_indexer, () -> -1.0).withTimeout(3),
            new GatekeeperController(m_gatekeeper, () -> 1.0).withTimeout(3)
        )
      );
  }
}
