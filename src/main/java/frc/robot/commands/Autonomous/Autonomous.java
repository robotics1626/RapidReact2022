// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// Autonomous program created with help from Team 303

package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import static frc.robot.RobotContainer.*;

import frc.robot.commands.Drivetrain.*;
import frc.robot.commands.Gatekeeper.*;
import frc.robot.commands.Indexer.*;
import frc.robot.commands.PIDtoValue.PIDtoValue;
import frc.robot.commands.Shooter.*;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Gyroscope;

public class Autonomous extends SequentialCommandGroup {
  double initialGyro;
    public Autonomous() {
      SmartDashboard.putNumber("Initial Gyro: ", Gyroscope.getAngle());
      addCommands(
        new InstantCommand(m_limelight::getPIDBlue, m_limelight),
        new InstantCommand(m_intake::extend, m_intake),
        new TankDrive(m_drivetrain, () -> 0.75, () -> 0.75).alongWith(
          new InstantCommand(m_intake::retrieve, m_intake),
          new IndexerController(m_indexer, () -> -0.75),
          new InstantCommand(m_gatekeeper::manual, m_gatekeeper)
        ).withTimeout(1.4),
        new InstantCommand(m_gatekeeper::stop, m_gatekeeper).alongWith(
          new InstantCommand(m_intake::stop, m_intake)
        ),
        new InstantCommand(m_intake::stop, m_intake),
        new InstantCommand(m_intake::retract, m_intake),
        new PIDtoValue(SmartDashboard.getNumber("Initial Gyro: ", 0)),
        new SequentialCommandGroup(
          new ShooterController(m_shooter, () -> 1.0).withTimeout(5)).alongWith(
            new IndexerController(m_indexer, () -> -1.0).withTimeout(3),
            new GatekeeperController(m_gatekeeper, () -> 1.0).withTimeout(3)
        )
      );
  }
}

// long endTime = 0;
// double time = 0.0;
// long startTime = System.nanoTime();

// startTime = System.nanoTime();
// time = (double) startTime/100000000;
// double startAngle = Gyroscope.getAngle();

// SmartDashboard.putNumber("Angles: ", startAngle);
// SmartDashboard.putNumber("Time: ", time);

// new ShooterController(m_shooter, () -> 0.0);
// while(time>=2){
//   time = (double) startTime/100000000;
//   startTime = System.nanoTime();
//   new ShooterController(m_shooter, () -> 1.0);
// }
// new ShooterController(m_shooter, () -> 0.0);