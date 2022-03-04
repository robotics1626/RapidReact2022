// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMax.SoftLimitDirection;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.Constants;

public class Climber extends SubsystemBase {

    CANSparkMax m_climber = new CANSparkMax(Constants.CLIMBER_MOTOR, MotorType.kBrushless);

    public Climber() {
        m_climber.setIdleMode(IdleMode.kBrake);
        //m_climber.setSoftLimit(SoftLimitDirection.kForward, 10);
        //m_climber.setSoftLimit(SoftLimitDirection.kReverse, 10);
    }

    public void spin(double input){
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