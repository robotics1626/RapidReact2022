// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.Constants;

public class IntakeBelt extends SubsystemBase {
    
    CANSparkMax m_intakeBeltLeft = new CANSparkMax(Constants.INTAKE_BELT_LEFT, MotorType.kBrushless);
    CANSparkMax m_intakeBeltRight = new CANSparkMax(Constants.INTAKE_BELT_RIGHT, MotorType.kBrushless);

    DifferentialDrive m_intakeBelt = new DifferentialDrive(m_intakeBeltLeft, m_intakeBeltRight);

    public IntakeBelt() {
        m_intakeBeltLeft.setIdleMode(IdleMode.kBrake);
        m_intakeBeltRight.setIdleMode(IdleMode.kBrake);
        m_intakeBeltRight.setInverted(true);
    }

    public void forwards(){
        m_intakeBelt.tankDrive(0.25, 0.25);
    }

    public void backwards(){
        m_intakeBelt.tankDrive(-0.25, -0.25);
    }

    public void stop() {
        m_intakeBelt.stopMotor();
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

}