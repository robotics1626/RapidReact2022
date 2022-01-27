// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Drivetrain;

import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class TankDrive extends CommandBase {
    private final Drivetrain m_drivetrain;
    private final DoubleSupplier m_left, m_right;

    public TankDrive(Drivetrain drivetrain, DoubleSupplier left, DoubleSupplier right) {
        m_drivetrain = drivetrain;
        m_left = left;
        m_right = right;

        addRequirements(m_drivetrain);
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        m_drivetrain.tankDrive(m_left.getAsDouble(), m_right.getAsDouble());
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