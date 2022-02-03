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
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Constants;

public class Intake extends SubsystemBase {
    
    CANSparkMax m_intakeBeltLeft = new CANSparkMax(Constants.INTAKE_BELT_LEFT, MotorType.kBrushless);
    CANSparkMax m_intakeBeltRight = new CANSparkMax(Constants.INTAKE_BELT_RIGHT, MotorType.kBrushless);

    DifferentialDrive m_intakeBelt = new DifferentialDrive(m_intakeBeltLeft, m_intakeBeltRight);

    private final DoubleSolenoid m_intakeArmLeft = new DoubleSolenoid(
        PneumaticsModuleType.REVPH, 
        Constants.INTAKE_ARM_LEFT[0], 
        Constants.INTAKE_ARM_LEFT[1]
    );

    private final DoubleSolenoid m_intakeArmRight = new DoubleSolenoid(
        PneumaticsModuleType.REVPH, 
        Constants.INTAKE_ARM_RIGHT[0], 
        Constants.INTAKE_ARM_RIGHT[1]
    );


    public Intake() {
        m_intakeBeltLeft.setIdleMode(IdleMode.kBrake);
        m_intakeBeltRight.setIdleMode(IdleMode.kBrake);
    }

    public void IntakeController(double input) {
        m_intakeBelt.arcadeDrive(input, 0.0);
    }

    public void pull(){
        m_intakeBelt.tankDrive(1.0, 1.0);
    }

    public void push(){
        m_intakeBelt.tankDrive(-1.0, -1.0);
    }

    public void extend() {
        m_intakeArmLeft.set(Value.kForward);
        m_intakeArmRight.set(Value.kForward);
      }
    
      public void retract() {
        m_intakeArmLeft.set(Value.kReverse);
        m_intakeArmRight.set(Value.kReverse);
      }

    public void stop() {
        m_intakeBelt.stopMotor();
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

}