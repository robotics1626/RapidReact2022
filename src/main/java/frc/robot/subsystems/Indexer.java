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

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.Constants;

public class Indexer extends SubsystemBase {
    
    private CANSparkMax m_leadMotor, m_followMotor;
    private SparkMaxPIDController m_pidController;
    private RelativeEncoder m_encoder;

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

        /**
         * In CAN mode, one SPARK MAX can be configured to follow another. This is done by calling
         * the follow() method on the SPARK MAX you want to configure as a follower, and by passing
         * as a parameter the SPARK MAX you want to configure as a leader.
         */
        m_followMotor.follow(m_leadMotor, true);

        /** Returns an object for interfacing with the integrated PID controller. */
        m_pidController = m_leadMotor.getPIDController();

        /**
         * Returns an object for interfacing with the hall sensor integrated into a brushless 
         * motor, which is connected to the front port of the SPARK MAX.
         */
        m_encoder = m_leadMotor.getEncoder();

        /**
         * When the SPARK MAX is receiving a neutral command, the idle behavior of the motor 
         * will effectively disconnect all motor wires. This allows the motor to spin down at 
         * its own rate. 
         */
        m_leadMotor.setIdleMode(IdleMode.kCoast);
        m_followMotor.setIdleMode(IdleMode.kCoast);
    }

    public void IndexerController(double input) {
        m_leadMotor.set(input);
        SmartDashboard.putBoolean("Indexing", true);
    }

    /** This function is called once each time the the command ends or is interrupted. */
    public void stop() {
        /**
         * Stop motor movement. Motor can be moved again by calling set without having to 
         * re-enable the motor.
         */
        m_leadMotor.stopMotor();
        SmartDashboard.putBoolean("Indexing", false);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

}