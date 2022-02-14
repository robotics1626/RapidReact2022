// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IntakeArm extends SubsystemBase {
    
    DoubleSolenoid m_intakeArmLeft = new DoubleSolenoid(
        PneumaticsModuleType.REVPH, 
        Constants.INTAKE_ARM_LEFT[0], 
        Constants.INTAKE_ARM_LEFT[1]
    );

    DoubleSolenoid m_intakeArmRight = new DoubleSolenoid(
        PneumaticsModuleType.REVPH, 
        Constants.INTAKE_ARM_RIGHT[0], 
        Constants.INTAKE_ARM_RIGHT[1]
    );


    public IntakeArm() {}

    public void extend() {
        m_intakeArmLeft.set(Value.kForward);
        m_intakeArmRight.set(Value.kForward);
      }
    
      public void retract() {
        m_intakeArmLeft.set(Value.kReverse);
        m_intakeArmRight.set(Value.kReverse);
      }

    public void stop() {}

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

}