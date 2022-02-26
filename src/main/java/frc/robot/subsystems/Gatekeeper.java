// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// TEMPORARY SUBSYSTEM (WILL BE REPLACED)

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.Constants;

public class Gatekeeper extends SubsystemBase {
    
    static CANSparkMax m_gatekeeper = new CANSparkMax(Constants.GATEKEEPER, MotorType.kBrushless);

    public Gatekeeper() {
        m_gatekeeper.setIdleMode(IdleMode.kBrake);
    }

    public void GatekeeperController(double speed) {
        m_gatekeeper.set(speed);
    }

    public void stop() {
        m_gatekeeper.stopMotor();
    }

    @Override
    public void periodic() {}

}