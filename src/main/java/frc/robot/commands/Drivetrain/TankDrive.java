// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Drivetrain;

import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class TankDrive extends CommandBase {

    // Initialize the drive train and its inputs as variables.
    private final Drivetrain m_drivetrain;
    private final DoubleSupplier m_left, m_right;

    /** This function acts as the liaison between the user and the code */
    public TankDrive(Drivetrain drivetrain, DoubleSupplier left, DoubleSupplier right) {
        // Get the user input as definitions for the drivetrain and inputs.
        m_drivetrain = drivetrain;
        m_left = left;
        m_right = right;

        // Ensure that the drivetrain has been defined by the user.
        addRequirements(m_drivetrain);
    }
    
    @Override
    public void initialize() {}

    /** This function is run any time that the command is executed. */
    @Override
    public void execute() {
        // Get the left and right input as a decimal number and pipe it into the
        // drivetrain's controller with the tank drive mode.
        m_drivetrain.tankDrive(m_left.getAsDouble(), m_right.getAsDouble());
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