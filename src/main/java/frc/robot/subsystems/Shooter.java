// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.RelativeEncoder;

import frc.robot.Constants;

public class Shooter extends SubsystemBase {

    private CANSparkMax m_leadMotor, m_followMotor;
    private SparkMaxPIDController m_pidController;
    private RelativeEncoder m_encoder;
    private double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput, maxRPM, setPoint;

    public Shooter() {
        /** Create a new object to control the SPARK MAX motor controllers. */
        m_leadMotor = new CANSparkMax(Constants.SHOOTER_LEFT, MotorType.kBrushless);
        m_followMotor = new CANSparkMax(Constants.SHOOTER_RIGHT, MotorType.kBrushless);

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

        /** Returns an object for interfacing with the integrated PID controller. */
        m_pidController = m_leadMotor.getPIDController();

        /**
         * Returns an object for interfacing with the hall sensor integrated into a brushless 
         * motor, which is connected to the front port of the SPARK MAX.
         */
        m_encoder = m_leadMotor.getEncoder();

        /** Define the PID coefficients. */
        kP = 0.00005;
        kI = 0;
        kD = 0;
        kIz = 0;
        kFF = 0.000175;
        kMaxOutput = 1;
        kMinOutput = -1;
        maxRPM = 3350;

        /** Set the PID coefficients. */
        m_pidController.setP(kP);
        m_pidController.setI(kI);
        m_pidController.setD(kD);
        m_pidController.setIZone(kIz);
        m_pidController.setFF(kFF);
        m_pidController.setOutputRange(kMinOutput, kMaxOutput);

        /** Display the PID coefficients on the dashboard. */
        SmartDashboard.putNumber("P Gain", kP);
        SmartDashboard.putNumber("I Gain", kI);
        SmartDashboard.putNumber("D Gain", kD);
        SmartDashboard.putNumber("I Zone", kIz);
        SmartDashboard.putNumber("Feed Forward", kFF);
        SmartDashboard.putNumber("Max Output", kMaxOutput);
        SmartDashboard.putNumber("Min Output", kMinOutput);
    }

    public void ShooterController(double input) {
        setPoint = maxRPM * input;
        m_pidController.setReference(setPoint, CANSparkMax.ControlType.kVelocity);
    }

    /** This function is called once each time the the command ends or is interrupted. */
    public void stop() {
        /**
         * Stop motor movement. Motor can be moved again by calling set without having to 
         * re-enable the motor.
         */
        m_leadMotor.stopMotor();
    }

    /** This method will be called once per scheduler run. */
    @Override
    public void periodic() {
        /** Read the PID coefficients from the dashboard. */
        double p = SmartDashboard.getNumber("P Gain", 0);
        double i = SmartDashboard.getNumber("I Gain", 0);
        double d = SmartDashboard.getNumber("D Gain", 0);
        double iz = SmartDashboard.getNumber("I Zone", 0);
        double ff = SmartDashboard.getNumber("Feed Forward", 0);
        double max = SmartDashboard.getNumber("Max Output", 0);
        double min = SmartDashboard.getNumber("Min Output", 0);

        /** Write the PID coefficients to the controller from the dashboard. */
        if(p != kP) m_pidController.setP(p); kP = p;
        if(i != kI) m_pidController.setI(i); kI = i;
        if(d != kD) m_pidController.setD(d); kD = d;
        if(iz != kIz) m_pidController.setIZone(iz); kIz = iz;
        if(ff != kFF) m_pidController.setFF(ff); kFF = ff;
        if(max != kMaxOutput || min != kMinOutput) {
            m_pidController.setOutputRange(min, max);
            kMinOutput = min; kMaxOutput = max;
        }

        //** Display the set point and velocity on the dashboard. */
        SmartDashboard.putNumber("SetPoint", setPoint);
        SmartDashboard.putNumber("Velocity", m_encoder.getVelocity());
    }

}