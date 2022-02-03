// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {
    
    CANSparkMax m_shooter = new CANSparkMax(Constants.SHOOTER, MotorType.kBrushless);

    public Shooter() {
        m_shooter.setIdleMode(IdleMode.kBrake);
    }

    public void IndexerController(double input) {
        m_shooter.set(input);
    }

    public void stop() {
        m_shooter.stopMotor();
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

}