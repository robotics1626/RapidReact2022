// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Gatekeeper;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Gatekeeper;

import java.util.function.DoubleSupplier;

public class GatekeeperController extends CommandBase {
    private final Gatekeeper m_gatekeeper;
    private final DoubleSupplier m_input;

    public GatekeeperController(Gatekeeper gatekeeper, DoubleSupplier input) {
        m_gatekeeper = gatekeeper;
        m_input = input;
        addRequirements(m_gatekeeper);
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        m_gatekeeper.GatekeeperController(m_input.getAsDouble());
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