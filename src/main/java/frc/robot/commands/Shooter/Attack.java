// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

public class Attack extends CommandBase {
    private final Shooter m_shooter;

    public Attack(Shooter intakeBelt) {
        m_shooter = intakeBelt;
        addRequirements(m_shooter);
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        m_shooter.attack();
    }

    @Override
    public void end(boolean interrupted) {
        m_shooter.stop();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
