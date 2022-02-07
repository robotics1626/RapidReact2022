// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.RelativeEncoder;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {
    
    CANSparkMax m_shooter = new CANSparkMax(Constants.SHOOTER, MotorType.kBrushless);
    SparkMaxPIDController m_pidController = m_shooter.getPIDController();
    RelativeEncoder m_encoder = m_shooter.getEncoder();

    public Shooter() {
        m_shooter.setIdleMode(IdleMode.kBrake);

        // PID Control
        /* m_pidController.setP(0);
        m_pidController.setI(0);
        m_pidController.setD(0);
        m_pidController.setIZone(0);
        m_pidController.setFF(0);
        m_pidController.setOutputRange(-1, 1); */
    }

    public void ShooterController(double input) {
        m_pidController.setReference(
            input * Constants.SHOOTER_RPM,
            CANSparkMax.ControlType.kVelocity
        );
    }

    public void stop() {
        m_shooter.stopMotor();
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        SmartDashboard.putNumber("Shooter Velocity (RPM)", m_encoder.getVelocity());
    }

}