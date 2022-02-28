// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.Constants;

public class Gatekeeper extends SubsystemBase {
    
    private CANSparkMax m_gatekeeper;
    //private boolean indexing;
    private double indexerSpeed;

    public Gatekeeper() {
        m_gatekeeper = new CANSparkMax(Constants.GATEKEEPER, MotorType.kBrushless);
        /**
         * Restore motor controller parameters to factory default until the next controller 
         * reboot.
         */
        m_gatekeeper.restoreFactoryDefaults();

        /**
         * When the SPARK MAX is receiving a neutral command, the idle behavior of the motor 
         * will effectively short all motor wires together. This quickly dissipates any 
         * electrical energy within the motor and brings it to a quick stop. 
         */
        m_gatekeeper.setIdleMode(IdleMode.kBrake);
    }

    public void GatekeeperController(double speed) {
        m_gatekeeper.set(speed);
    }

    public void stop() {
        m_gatekeeper.stopMotor();
    }

    @Override
    public void periodic() {
        
    }

}