// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {
    
    CANSparkMax m_intake = new CANSparkMax(Constants.INTAKE, MotorType.kBrushless);

    public Intake() {
        m_intake.setIdleMode(IdleMode.kBrake);
    }

    public void pull() {
        m_intake.set(1.0);
    }

    public void push() {
        m_intake.set(-1.0);
    }


    public void stop() {
        m_intake.stopMotor();
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

}