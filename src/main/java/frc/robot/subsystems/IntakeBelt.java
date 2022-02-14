// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.RelativeEncoder;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.PowerDistribution.ModuleType;
import frc.robot.Constants;

public class IntakeBelt extends SubsystemBase {
    
    CANSparkMax m_intakeBeltLeft = new CANSparkMax(Constants.INTAKE_BELT_LEFT, MotorType.kBrushless);
    CANSparkMax m_intakeBeltRight = new CANSparkMax(Constants.INTAKE_BELT_RIGHT, MotorType.kBrushless);

    DifferentialDrive m_intakeBelt = new DifferentialDrive(m_intakeBeltLeft, m_intakeBeltRight);

    RelativeEncoder m_encoderBeltLeft = m_intakeBeltLeft.getEncoder();
    RelativeEncoder m_encoderBeltRight = m_intakeBeltRight.getEncoder();

    PowerDistribution m_pdh = new PowerDistribution(Constants.PDH,ModuleType.kRev);

    double m_velocity, voltage;

    double defaultBeltPower=0.80;

    public IntakeBelt() {
        m_intakeBeltLeft.setIdleMode(IdleMode.kBrake);
        m_intakeBeltRight.setIdleMode(IdleMode.kBrake);

        m_intakeBeltRight.setInverted(true);

    }

    public void forwards(){
        m_intakeBelt.tankDrive(defaultBeltPower, defaultBeltPower);
    }

    public void backwards(){
        m_intakeBelt.tankDrive(-defaultBeltPower, -defaultBeltPower);
    }

    public void stop() {
        m_intakeBelt.stopMotor();
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        m_velocity = m_encoderBeltLeft.getVelocity();
        SmartDashboard.putNumber("Intake Belt Velocity (RPM)", m_velocity);
    }

}