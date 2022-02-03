// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.IntakeArm;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeArm;

public class Extend extends CommandBase {
    private final IntakeArm m_intakeArm;

    public Extend(IntakeArm intakeArm) {
        m_intakeArm = intakeArm;
        addRequirements(m_intakeArm);
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        m_intakeArm.extend();
    }

    @Override
    public void end(boolean interrupted) {}

    @Override
    public boolean isFinished() {
        return false;
    }
}
