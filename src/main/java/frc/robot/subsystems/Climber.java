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

import frc.robot.Constants;

public class Climber extends SubsystemBase {

    private CANSparkMax climber;
    private RelativeEncoder encoder;

    public Climber() {
        climber = new CANSparkMax(Constants.Climber.MOTOR, MotorType.kBrushless);
        encoder = climber.getEncoder();
        climber.setIdleMode(IdleMode.kBrake);
        climber.setSoftLimit(SoftLimitDirection.kForward, 150);
        climber.setSoftLimit(SoftLimitDirection.kReverse, 0);
        encoder.setPosition(0);
    }

    public void spin(double input){
        climber.set(input);
    }

    public void stop() {
        climber.stopMotor();
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

}