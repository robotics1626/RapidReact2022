// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {
    
    // Shooter motors
    private final CANSparkMax m_shooterLeft  = new CANSparkMax(Constants.SHOOTER_LEFT, MotorType.kBrushless);
    private final CANSparkMax m_shooterRight = new CANSparkMax(Constants.SHOOTER_RIGHT, MotorType.kBrushless);

    // Shooter drivetrain
    private final DifferentialDrive m_shooter = new DifferentialDrive(m_intakeBeltLeft, m_intakeBeltRight);

    // Shooter PID controllers
    private final SparkMaxPIDController m_pidControllerLeft  = m_shooterLeft.getPIDController();
    private final SparkMaxPIDController m_pidControllerRight = m_shooterRight.getPIDController();

    // Shooter encoders
    private RelativeEncoder m_encoderLeft  = m_shooterLeft.getEncoder();
    private RelativeEncoder m_encoderRight = m_shooterRight.getEncoder();

    // Input
    private final double m_input, m_velocity;

    public Shooter() {
        // Set shooter motors' idle mode to brake 
        m_shooterLeft.setIdleMode(IdleMode.kBrake);
        m_shooterRight.setIdleMode(IdleMode.kBrake);
        // Invert right-side motor
        m_shooterRight.setInverted(true);
    }

    public void ShooterController(double input) {
        m_input = (input == 0 ? input : Math.pow(input,0)) * Constants.SHOOTER_RPM;
        m_pidControllerLeft.setReference(input, CANSparkMax.ControlType.kVelocity);
        m_pidControllerRight.setReference(input, CANSparkMax.ControlType.kVelocity);
    }

    public void stop() {
        // Stop motors
        m_shooter.stopMotor();
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        m_velocity = (m_encoderLeft.getVelocity() + m_encoderLeft.getVelocity())/2;
        SmartDashboard.putNumber("ProcessVariable", m_velocity);
    }

}