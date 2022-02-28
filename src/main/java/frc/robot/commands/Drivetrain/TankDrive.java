// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

import java.util.function.DoubleSupplier;

public class TankDrive extends CommandBase {

    private final Drivetrain m_drivetrain;
    private final DoubleSupplier m_leftSpeed, m_rightSpeed;

    /** This function acts as the liaison between the user and the code */
    public TankDrive(Drivetrain drivetrain, DoubleSupplier left, DoubleSupplier right) {
        m_drivetrain = drivetrain;
        m_leftSpeed = left;
        m_rightSpeed = right;
        addRequirements(m_drivetrain);
    }
    
    @Override
    public void initialize() {}

    /** This function is run any time that the command is executed. */
    @Override
    public void execute() {
        m_drivetrain.tankDrive(m_leftSpeed.getAsDouble(), m_rightSpeed.getAsDouble());
    }

    /** This function is called once the command ends or is interrupted. */
    @Override
    public void end(boolean interrupted) {
        m_drivetrain.stop();
    }

    /** This function returns true when the command should end. */
    @Override
    public boolean isFinished() {
        return false;
    }
}