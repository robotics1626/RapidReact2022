// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Shooter;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

public class ShooterController extends CommandBase {
    private final Shooter m_shooter;
    private final DoubleSupplier m_input;
    private double m_maxRpm;

    public ShooterController(Shooter shooter, DoubleSupplier input) {
        m_shooter = shooter;
        m_input = input;
        m_maxRpm = 3600;
        addRequirements(m_shooter);
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        m_maxRpm = SmartDashboard.getNumber("Preferred RPM", m_maxRpm);
        if (m_input.getAsDouble() > 0.5) {
            m_shooter.ShooterController(m_maxRpm);
            //SmartDashboard.putBoolean("Shooting", true);
        } else m_shooter.stop();
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        m_shooter.stop();
        SmartDashboard.putBoolean("Shooting", false);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}