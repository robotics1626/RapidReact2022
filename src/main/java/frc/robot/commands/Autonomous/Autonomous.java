// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// Autonomous program created with help from Team 303

package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import static frc.robot.Robot.*;

import frc.robot.commands.Drivetrain.TankDrive;
import frc.robot.commands.Gatekeeper.GatekeeperController;
import frc.robot.commands.Indexer.IndexerController;
import frc.robot.commands.Shooter.ShooterController;
import frc.robot.commands.Intake.*;

public class Autonomous extends SequentialCommandGroup {
  /** Creates a new autonomous command. */
  /*public Autonomous() {
    addCommands(
      new IndexerController(INDEXER, () -> 1.0).alongWith(
        new GatekeeperController(GATEKEEPER, () -> 1.0),
        new ShooterController(SHOOTER, () -> 1.0)
      ).withTimeout(5),
      new TankDrive(DRIVETRAIN, () -> -0.5, () -> -0.5).withTimeout(3)
    );
  }*/
  public Autonomous() {
    addCommands(
      new InstantCommand(INTAKE::extend, INTAKE),
      new TankDrive(DRIVETRAIN, () -> 0.5, () -> 0.5).alongWith(
        new Retrieve(INTAKE),
        new IndexerController(INDEXER, () -> 1.0)
      ).withTimeout(3),
      new InstantCommand(INTAKE::retract, INTAKE),
      new TankDrive(DRIVETRAIN, () -> -0.5, () -> 0.5).withTimeout(3),
      new TankDrive(DRIVETRAIN, () -> 0.5, () -> 0.5).withTimeout(3),
      new IndexerController(INDEXER, () -> 1.0).alongWith(
        new GatekeeperController(GATEKEEPER, () -> 1.0),
        new ShooterController(SHOOTER, () -> 1.0)
      ).withTimeout(6)
    );
  }
}
