// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.Constants;

public class Indexer extends SubsystemBase {
    
    private final CANSparkMax m_indexer = new CANSparkMax(Constants.INDEXER, MotorType.kBrushless);

    public Indexer() {
        m_indexer.setIdleMode(IdleMode.kBrake);
    }

    public void IndexerController(double input) {
        m_indexer.set(input);
    }

    public void stop() {
        m_indexer.stopMotor();
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

}