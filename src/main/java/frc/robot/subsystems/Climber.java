// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.Constants;

public class Climber extends SubsystemBase {

    CANSparkMax m_climberSpinner = new CANSparkMax(Constants.CLIMBER_MOTOR, MotorType.kBrushless);
    
    DoubleSolenoid m_climberLeftUpper = new DoubleSolenoid(PneumaticsModuleType.REVPH, Constants.CLIMBER_LEFT_UPPER[0], Constants.CLIMBER_LEFT_UPPER[1]);
    DoubleSolenoid m_climberLeftLower = new DoubleSolenoid(PneumaticsModuleType.REVPH, Constants.CLIMBER_LEFT_LOWER[0], Constants.CLIMBER_LEFT_LOWER[1]);

    DoubleSolenoid m_climberRightUpper = new DoubleSolenoid(PneumaticsModuleType.REVPH, Constants.CLIMBER_RIGHT_UPPER[0], Constants.CLIMBER_RIGHT_UPPER[1]);
    DoubleSolenoid m_climberRightLower = new DoubleSolenoid(PneumaticsModuleType.REVPH, Constants.CLIMBER_RIGHT_LOWER[0], Constants.CLIMBER_RIGHT_LOWER[1]);

    public Climber() {
        m_climberSpinner.setIdleMode(IdleMode.kBrake);

        m_climberLeftUpper.set(Value.kForward);
        m_climberLeftLower.set(Value.kForward);

        m_climberRightUpper.set(Value.kForward);
        m_climberRightLower.set(Value.kForward);
    }

    public void spin(double input){
        m_climberSpinner.set(input);
    }

    public void toggle(int side) {
        switch (side) {
            case 0:
                m_climberLeftUpper.toggle();
                m_climberRightUpper.toggle();
                break;
            case 1:
                m_climberLeftLower.toggle();
                m_climberRightLower.toggle();
                break;
        }
    }

    public void stop() {
        m_climberSpinner.stopMotor();

        m_climberLeftUpper.set(Value.kOff);
        m_climberLeftLower.set(Value.kOff);
        
        m_climberRightUpper.set(Value.kOff);
        m_climberRightLower.set(Value.kOff);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

}