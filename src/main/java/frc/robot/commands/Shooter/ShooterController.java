// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Shooter;

import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

public class ShooterController extends CommandBase {
    private final Shooter m_shooter;
    private final DoubleSupplier m_input;

    public ShooterController(Shooter shooter, DoubleSupplier input) {
        m_shooter = shooter;
        m_input = input;

        addRequirements(m_shooter);
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        m_shooter.ShooterController(m_input.getAsDouble());
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        m_shooter.stop();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}