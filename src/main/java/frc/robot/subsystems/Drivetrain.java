// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.Constants;

public class Drivetrain extends SubsystemBase {
    // Define the drivetrain motors.
    private final CANSparkMax m_motorFrontLeft = new CANSparkMax(Constants.DRIVE_FRONT_LEFT, MotorType.kBrushless);
    private final CANSparkMax m_motorRearLeft = new CANSparkMax(Constants.DRIVE_REAR_LEFT, MotorType.kBrushless);
    private final CANSparkMax m_motorFrontRight = new CANSparkMax(Constants.DRIVE_FRONT_RIGHT, MotorType.kBrushless);
    private final CANSparkMax m_motorRearRight = new CANSparkMax(Constants.DRIVE_REAR_RIGHT, MotorType.kBrushless);

    // Group the motors by left and right side.
    private final MotorControllerGroup m_motorsLeft = new MotorControllerGroup(m_motorFrontLeft, m_motorRearLeft);
    private final MotorControllerGroup m_motorsRight = new MotorControllerGroup(m_motorFrontRight, m_motorRearRight);

    // Drivetrain
    public Drivetrain() {
        // Set the motors to coast while idle.
        m_motorFrontLeft.setIdleMode(IdleMode.kCoast);
        m_motorFrontRight.setIdleMode(IdleMode.kCoast);
        m_motorRearLeft.setIdleMode(IdleMode.kCoast);
        m_motorRearRight.setIdleMode(IdleMode.kCoast);

        // Invert the right-side motors.
        m_motorFrontRight.setInverted(true);
        m_motorRearRight.setInverted(true);
    }

    /** This function makes use of the driver input to control the robot like a tank. */
    public void tankDrive(double left, double right) {
        // Set the drivetrain to operate as tank drive.
        m_drivetrain.tankDrive(
                // Exponentiate the driver input by a number for a more comfortable
                // sensitivity curve.
                Math.pow(left,Constants.JOYSTICK_CURVE), 
                Math.pow(right,Constants.JOYSTICK_CURVE)
            );
    }

    /** This function is called once each time the the command ends or is interrupted. */
    public void stop() {
        // Stop all of the motors on the drivetrain.
        m_drivetrain.stopMotor();
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

}