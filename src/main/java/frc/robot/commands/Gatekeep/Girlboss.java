// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Gatekeep;

import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Gatekeep;

public class Girlboss extends CommandBase {
    private final Gatekeep m_gatekeeper;
    private final DoubleSupplier m_input;

    public Girlboss(Gatekeep gatekeeper, DoubleSupplier input) {
        m_gatekeeper = gatekeeper;
        m_input = input;

        addRequirements(m_gatekeeper);
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        m_gatekeeper.Girlboss(m_input.getAsDouble());
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        m_gatekeeper.stop();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}