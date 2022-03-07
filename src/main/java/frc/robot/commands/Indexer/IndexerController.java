// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Indexer;

import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Indexer;

public class IndexerController extends CommandBase {
    private final Indexer indexer;
    private final DoubleSupplier input;

    public IndexerController(Indexer indexer, DoubleSupplier input) {
        this.indexer = indexer;
        this.input = input;
        addRequirements(indexer);
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        indexer.IndexerController(input.getAsDouble());
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        indexer.stop();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}