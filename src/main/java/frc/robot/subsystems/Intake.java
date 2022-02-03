// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Constants;

public class Intake extends SubsystemBase {
    
    CANSparkMax m_intake_motor_left = new CANSparkMax(Constants.INTAKE_FRONT, MotorType.kBrushless);
    CANSparkMax m_intake_motor_right = new CANSparkMax(Constants.INTAKE_FRONT, MotorType.kBrushless);

    MotorControllerGroup m_intake_motors = new MotorControllerGroup(m_intake_motor_left, m_intake_motor_right);
    DifferentialDrive m_intake = new DifferentialDrive(m_intake_motor_left, m_intake_motor_right);

    private final DoubleSolenoid m_intake_piston_left = new DoubleSolenoid(
        PneumaticsModuleType.REVPH, 
        Constants.INTAKE_PISTON_LEFT[0], 
        Constants.INTAKE_PISTON_LEFT[1]
    );

    private final DoubleSolenoid m_intake_piston_right = new DoubleSolenoid(
        PneumaticsModuleType.REVPH, 
        Constants.INTAKE_PISTON_RIGHT[0], 
        Constants.INTAKE_PISTON_RIGHT[1]
    );


    public Intake() {
        m_intake_motor_left.setIdleMode(IdleMode.kBrake);
        m_intake_motor_right.setIdleMode(IdleMode.kBrake);
    }

    public void IntakeController(double input) {
        m_intake.arcadeDrive(input, 0.0);
    }

    public void stop() {
        m_intake.stopMotor();
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

}