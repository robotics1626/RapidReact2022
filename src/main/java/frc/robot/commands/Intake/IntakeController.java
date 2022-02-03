// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Intake;

import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class IntakeController extends CommandBase {
    private final Intake m_intake;
    private final DoubleSupplier m_input;

    public IntakeController(Intake intake, DoubleSupplier input) {
        m_intake = intake;
        m_input = input;

        addRequirements(m_intake);
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        m_intake.IntakeController(m_input.getAsDouble());
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        m_intake.stop();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}