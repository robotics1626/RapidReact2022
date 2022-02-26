// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Indexer;

import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Indexer;

public class IndexerController extends CommandBase {
    private final Indexer m_indexer;
    private final DoubleSupplier m_input;

    public IndexerController(Indexer indexer, DoubleSupplier input) {
        m_indexer = indexer;
        m_input = input;
        addRequirements(m_indexer);
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        m_indexer.IndexerController(m_input.getAsDouble());
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        m_indexer.stop();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}