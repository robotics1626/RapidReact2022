// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.Constants;

public class Gatekeep extends SubsystemBase {
    
    static CANSparkMax m_gatekeeper = new CANSparkMax(Constants.GATEKEEPER, MotorType.kBrushless);
    
    double m_shooterSpeed;
    boolean m_gaslight, m_indexerEnabled;

    public Gatekeep() {
        m_gatekeeper.setIdleMode(IdleMode.kBrake);
        m_gaslight = false; m_indexerEnabled = false;
    }

    public void Girlboss(double input) {
        m_gaslight = (input == 1) ? true : false;
    }

    public void stop() {
        m_gatekeeper.stopMotor();
        m_gaslight = false;
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        /*m_shooterSpeed = (Shooter.m_encoderLeft.getVelocity() + Shooter.m_encoderRight.getVelocity())/2;
        m_indexerEnabled = ((Indexer.m_indexerLeft.getEncoder().getVelocity() + Indexer.m_indexerRight.getEncoder().getVelocity())/2 < -50) ? true : false;
        if(m_shooterSpeed < Constants.SHOOTER_RPM && m_indexerEnabled && m_gatekeeperLocked) m_gatekeeper.set(-0.25);
        else if (m_shooterSpeed >= Constants.SHOOTER_RPM && m_indexerEnabled && !m_gatekeeperLocked) m_gatekeeper.set(0.25);
        else if (m_shooterSpeed <= Constants.SHOOTER_RPM - 100 || !m_indexerEnabled) stop();*/
        if (m_gaslight) {
            m_gatekeeper.set(0.5);
        } else {
            stop();
        }
    }

}