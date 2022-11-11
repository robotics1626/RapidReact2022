// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANSparkMax.IdleMode;

import frc.robot.Constants;

public class Intake extends SubsystemBase {
    private CANSparkMax m_leadMotor, m_followMotor;
    DoubleSolenoid m_intakeArmLeft, m_intakeArmRight;

    public Intake() {
        /** Create a new object to control the SPARK MAX motor controllers. */
        m_leadMotor = new CANSparkMax(Constants.INTAKE_MOTOR_LEFT, MotorType.kBrushless);
        m_followMotor = new CANSparkMax(Constants.INTAKE_MOTOR_RIGHT, MotorType.kBrushless);

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

        /**
         * When the SPARK MAX is receiving a neutral command, the idle behavior of the motor 
         * will effectively disconnect all motor wires. This allows the motor to spin down at 
         * its own rate. 
         */
        m_leadMotor.setIdleMode(IdleMode.kCoast);
        m_followMotor.setIdleMode(IdleMode.kCoast);

        /** Construct a pair of double solenoid for REV pneumatics modules. */
        m_intakeArmLeft = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Constants.INTAKE_ARM_LEFT[0], Constants.INTAKE_ARM_LEFT[1]);
        m_intakeArmRight = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Constants.INTAKE_ARM_RIGHT[0], Constants.INTAKE_ARM_RIGHT[1]);

        /** Set the intake arms to contract by default. */
        m_intakeArmLeft.set(Value.kReverse);
        m_intakeArmRight.set(Value.kReverse);
    }

    /** Retrieve cargo for transportation. */
    public void retrieve() {
        m_leadMotor.set(Constants.INTAKE_SPEED);
    }

    /** Eject cargo from the robot. */
    public void eject() {
        m_leadMotor.set(-Constants.INTAKE_SPEED);
    }

    /** Toggle the state of the intake arms. */
    public void toggle() {
        /**
         * If the solenoid is set to forward, it'll be set to reverse. If the solenoid is set 
         * to reverse, it'll be set to forward. If the solenoid is set to off, nothing happens.
         */
        m_intakeArmLeft.toggle();
        m_intakeArmRight.toggle();
    }

    /** Set the state of the intake arms to retract. */
    public void retract() {
        m_intakeArmLeft.set(Value.kReverse);
        m_intakeArmRight.set(Value.kReverse);
    }

    /** Set the state of the intake arms to extend. */
    public void extend() {
        m_intakeArmLeft.set(Value.kForward);
        m_intakeArmRight.set(Value.kForward);
    }

    /** This function is called once each time the the command ends or is interrupted. */
    public void stop() {
        /**git
         * Stop motor movement. Motor can be moved again by calling set without having to 
         * re-enable the motor.
         */
        m_leadMotor.stopMotor();
    }

    /** This method will be called once per scheduler run. */
    @Override
    public void periodic() {}

}