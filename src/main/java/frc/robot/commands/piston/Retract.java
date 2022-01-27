// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Piston;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Piston;

public class Retract extends CommandBase {
    private final Piston m_piston;

    public Retract(Piston piston) {
        m_piston = piston;
        addRequirements(m_piston);
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        m_piston.retract();
    }

    @Override
    public void end(boolean interrupted) {}

    @Override
    public boolean isFinished() {
        return false;
    }
}
