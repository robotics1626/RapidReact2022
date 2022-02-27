// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.IntakeBelt;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeBelt;

public class Forwards extends CommandBase {
    private final IntakeBelt m_intakeBelt;

    public Forwards(IntakeBelt intakeBelt) {
        m_intakeBelt = intakeBelt;
        addRequirements(m_intakeBelt);
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        m_intakeBelt.forwards();
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        m_intakeBelt.stop();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
