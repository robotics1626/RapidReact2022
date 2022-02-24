// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.RelativeEncoder;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {

    // Shooter motors
    static CANSparkMax m_shooterLeft  = new CANSparkMax(Constants.SHOOTER_LEFT, MotorType.kBrushless);
    static CANSparkMax m_shooterRight = new CANSparkMax(Constants.SHOOTER_RIGHT, MotorType.kBrushless);

    // Shooter drivetrain
    static DifferentialDrive m_shooter = new DifferentialDrive(m_shooterLeft, m_shooterRight);

    // Shooter PID controllers
    SparkMaxPIDController m_pidControllerLeft;
    SparkMaxPIDController m_pidControllerRight;

    // Shooter encoders
    static RelativeEncoder m_encoderLeft  = m_shooterLeft.getEncoder();
    static RelativeEncoder m_encoderRight = m_shooterRight.getEncoder();

    // Input
    double m_input, m_velocity;

    public Shooter() {
        // Set shooter motors' idle mode to brake 
        m_shooterLeft.setIdleMode(IdleMode.kCoast);
        m_shooterRight.setIdleMode(IdleMode.kCoast);
        // PID Stuff
        m_pidControllerLeft  = m_shooterLeft.getPIDController();
        m_pidControllerRight = m_shooterRight.getPIDController();
        // Invert right-side motor
        m_shooterRight.setInverted(true);
    }

    public void ShooterController(double input) {
        if (input != 0) {
            m_pidControllerLeft.setReference(Constants.SHOOTER_RPM, CANSparkMax.ControlType.kVelocity);
            m_pidControllerRight.setReference(Constants.SHOOTER_RPM, CANSparkMax.ControlType.kVelocity);
        }
    }

    public void stop() {
        // Stop motors
        m_shooter.stopMotor();
    }
/** This method will be called once per scheduler run */
    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        m_velocity = ((m_encoderLeft.getVelocity() + m_encoderRight.getVelocity()/2)*6e-5);
        SmartDashboard.putNumber("Shooter Speed (RPM)", m_velocity);
    }

}