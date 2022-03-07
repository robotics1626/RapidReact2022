// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// Autonomous program created with help from Team 303

package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import static frc.robot.RobotContainer.*;

import frc.robot.commands.Drivetrain.TankDrive;
import frc.robot.commands.Gatekeeper.GatekeeperController;
import frc.robot.commands.Indexer.IndexerController;
import frc.robot.commands.Shooter.ShooterController;

import edu.wpi.first.wpilibj2.command.WaitCommand;


public class Autonomous extends SequentialCommandGroup {
  /** Creates a new BasicAuto. */
  public Autonomous() {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new IndexerController(m_indexer, () -> 1.0).alongWith(
        new GatekeeperController(m_gatekeeper, () -> 1.0),
        new ShooterController(m_shooter, () -> 1.0)
      ).withTimeout(5),
      //new WaitCommand(5),
      new TankDrive(m_drivetrain, () -> -0.75, () -> -0.75).withTimeout(2)
    );
  }
}
