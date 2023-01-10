// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.Constants;

public class Indexer extends SubsystemBase {
    
    private CANSparkMax m_leadMotor, m_followMotor;
    private boolean m_indexing;

    public Indexer() {
        /** Create a new object to control the SPARK MAX motor controllers. */
        m_leadMotor = new CANSparkMax(Constants.INDEXER_LEFT, MotorType.kBrushless);
        m_followMotor = new CANSparkMax(Constants.INDEXER_RIGHT, MotorType.kBrushless);

        /**
         * Restore motor controller parameters to factory default until the next controller 
         * reboot.
         */
        m_leadMotor.restoreFactoryDefaults();
        m_followMotor.restoreFactoryDefaults();

        m_followMotor.setInverted(true);
        m_followMotor.follow(m_leadMotor,true);
        /**
         * When the SPARK MAX is receiving a neutral command, the idle behavior of the motor 
         * will effectively disconnect all motor wires. This allows the motor to spin down at 
         * its own rate. \
         */
        m_leadMotor.setIdleMode(IdleMode.kCoast);
        m_followMotor.setIdleMode(IdleMode.kCoast);

        m_indexing = false;
        SmartDashboard.putBoolean("Indexing", m_indexing);
    }

    public void IndexerController(double input) {
        if (input < 0) { m_indexing = true; } else { m_indexing = false; }
        m_leadMotor.set(input);
    }

    /** This function is called once each time the the command ends or is interrupted. */
    public void stop() {
        /**
         * Stop motor movement. Motor can be moved again by calling set without having to 
         * re-enable the motor.
         */
        m_leadMotor.stopMotor();
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        SmartDashboard.putBoolean("Indexing", m_indexing);
    }

}