// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Shooter;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

public class ShooterController extends CommandBase {
    private final Shooter shooter;
    private final DoubleSupplier input;
    private double maxRpm;

    public ShooterController(Shooter shooter, DoubleSupplier input) {
        this.shooter = shooter;
        this.input = input;
        this.maxRpm = 3600;
        addRequirements(shooter);
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        maxRpm = SmartDashboard.getNumber("Preferred RPM", maxRpm);
        if (input.getAsDouble() > 0.5) {
            shooter.ShooterController(maxRpm);
            //SmartDashboard.putBoolean("Shooting", true);
        } else shooter.stop();
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        shooter.stop();
        SmartDashboard.putBoolean("Shooting", false);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}