// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMax.SoftLimitDirection;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;


import frc.robot.Constants;

public class Climber extends SubsystemBase {

    private CANSparkMax m_climber;
    private RelativeEncoder m_encoder;

    private DoubleSolenoid m_armLeft = new DoubleSolenoid(PneumaticsModuleType.REVPH, Constants.CLIMBER_ARM_LEFT[0], Constants.CLIMBER_ARM_LEFT[1]);
    private DoubleSolenoid m_armRight = new DoubleSolenoid(PneumaticsModuleType.REVPH, Constants.CLIMBER_ARM_RIGHT[0], Constants.CLIMBER_ARM_RIGHT[1]);

    public Climber() {
        m_climber = new CANSparkMax(Constants.CLIMBER_MOTOR, MotorType.kBrushless);
        m_encoder = m_climber.getEncoder();
        m_climber.setIdleMode(IdleMode.kBrake);
        m_climber.setSoftLimit(SoftLimitDirection.kForward, 150);
        m_climber.setSoftLimit(SoftLimitDirection.kReverse, 0);
        m_encoder.setPosition(0);
    }

    public void traverse() {
        m_armLeft.toggle();
        m_armRight.toggle();
    }

    public void climb(double input){
        m_climber.set(input);
    }

    public void stop() {
        m_climber.stopMotor();
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

}