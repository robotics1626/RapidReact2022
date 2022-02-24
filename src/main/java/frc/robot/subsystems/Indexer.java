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
    
    static CANSparkMax m_indexerLeft = new CANSparkMax(Constants.INDEXER_LEFT, MotorType.kBrushless);
    static CANSparkMax m_indexerRight = new CANSparkMax(Constants.INDEXER_RIGHT, MotorType.kBrushless);

    public Indexer() {
        m_indexerLeft.setIdleMode(IdleMode.kCoast);
        m_indexerRight.setIdleMode(IdleMode.kCoast);
        m_indexerRight.setInverted(true);
    }

    public void IndexerController(double input) {
        m_indexerLeft.set(input);
        m_indexerRight.set(input);
    }

    public void stop() {
        m_indexerLeft.stopMotor();
        m_indexerRight.stopMotor();
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

}